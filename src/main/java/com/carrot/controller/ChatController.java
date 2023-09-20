package com.carrot.controller;

import com.carrot.domain.ChatMessageVO;
import com.carrot.domain.ChatRoomVO;
import com.carrot.domain.UserVO;
import com.carrot.handler.CustomUser;
import com.carrot.service.ChatService;
import com.carrot.service.ItemPostService;
import com.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;
    @Autowired
    ItemPostService itemPostService;
    @Autowired
    SimpMessagingTemplate template;

    @GetMapping("/page/chat")
    public String chatPage(Model model) {
        UserVO user = userService.getUserInfo();
        List<ChatRoomVO> rooms = chatService.getAllChatRooms(user.getId());
        model.addAttribute("username", user.getNickname());
        model.addAttribute("rooms", rooms);
        model.addAttribute("userId", user.getId());
        if (rooms.size() > 0) {
            model.addAttribute("messages", chatService.getMessages(rooms.get(0).getId()));
        }
        return "chatPage";
    }

    @PostMapping("/page/chat")
    public String newChatRoomPage(Model model, @RequestParam int itemId) {
        UserVO user = userService.getUserInfo();
        if (!chatService.checkRoomExist(itemId, user.getId())) {
            return "redirect:/page/chat";
        }


        model.addAttribute("username", user.getNickname());
        model.addAttribute("rooms", chatService.getAllChatRooms(user.getId()));
        model.addAttribute("item", itemPostService.detail(itemId));
        model.addAttribute("userId", user.getId());
        return "chatPageNewRoom";
    }

    @MessageMapping("/socket/send/{roomId}")
    public void chat(ChatMessageVO message, @DestinationVariable String roomId, java.security.Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        UserVO user = ((CustomUser)token.getPrincipal()).getUser();
        message.setWriterName(user.getNickname());
        template.convertAndSend("/socket/message/"+roomId,
                chatService.sendMessage(message, Integer.parseInt(roomId), user.getId()));
    }

    @MessageMapping("/socket/newroom")
    public void newRoom(ChatMessageVO newRoomMessage, java.security.Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        UserVO user = ((CustomUser)token.getPrincipal()).getUser();
        ChatRoomVO newRoom = new ChatRoomVO();
        newRoom.setItemPostId(newRoomMessage.getPostId());
        newRoom.setSeller(newRoomMessage.getDestinationId());
        newRoom.setBuyer(user.getId());
        newRoom.setStatus(3);
        int roomId = chatService.createChatRoom(newRoom);
        itemPostService.addChatCnt(newRoomMessage.getPostId());

        String destinationName = userService.selectById(newRoomMessage.getDestinationId()).getNickname();

        chatService.sendMessage(newRoomMessage, roomId, user.getId());
        newRoomMessage.setDestinationName(destinationName);
        template.convertAndSend("/socket/sub/"+(newRoomMessage.getDestinationId()), newRoomMessage);
        template.convertAndSend("/socket/sub/"+(user.getId()), newRoomMessage);
    }

    @MessageMapping("/socket/room/{roomId}")
    public void roomInfo(@DestinationVariable String roomId, java.security.Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        UserVO user = ((CustomUser)token.getPrincipal()).getUser();
        int id = Integer.parseInt(roomId);

        List<ChatMessageVO> messages = chatService.getMessages(id);

        ChatRoomVO room = chatService.getRoomById(id);
        chatService.updateIsRead(user.getId(), id);

        HashMap<String, Object> header = new HashMap<>();
        header.put("roomId", id);
        header.put("sellerName", room.getSellerName());
        header.put("buyerName", room.getBuyerName());

        template.convertAndSend("/socket/roomchange/"+(user.getId()), messages, header);
    }

    @MessageMapping("/socket/read/{id}")
    public void updateIsRead(@DestinationVariable String id, java.security.Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        UserVO user = ((CustomUser)token.getPrincipal()).getUser();
        int roomId = Integer.parseInt(id);
        String userId = user.getId();

        chatService.updateIsRead(userId, roomId);
    }

    @MessageMapping("/socket/exit/{id}")
    public void exitRoom(@DestinationVariable String id, java.security.Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        UserVO user = ((CustomUser)token.getPrincipal()).getUser();
        int roomId = Integer.parseInt(id);

        ChatRoomVO room = chatService.getRoomById(roomId);
        int num = user.getId().equals(room.getSeller()) ? 1 : 2;
        chatService.exitChatRoom(num, roomId);
        itemPostService.minusChatCnt(room.getItemPostId());

        ChatMessageVO exitMessage = new ChatMessageVO();
        exitMessage.setContent(user.getNickname() + "님이 퇴장하셨습니다.");
        exitMessage.setWriterName(user.getNickname());
        chatService.sendMessage(exitMessage, roomId, user.getId());
        template.convertAndSend("/socket/message/"+roomId, exitMessage);
    }

}
