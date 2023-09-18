package com.carrot.controller;

import com.carrot.domain.ChatMessageVO;
import com.carrot.domain.ChatRoomVO;
import com.carrot.domain.UserVO;
import com.carrot.handler.CustomUser;
import com.carrot.service.ChatService;
import com.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;
    @Autowired
    SimpMessagingTemplate template;

    @GetMapping("/page/chat")
    public String chatPage(Model model) {
        UserVO user = userService.getUserInfo();
        List<ChatRoomVO> rooms = chatService.getAllChatRooms(user.getNickname());
        model.addAttribute("username", user.getNickname());
        model.addAttribute("rooms", rooms);
        if (rooms.size() > 0) {
            model.addAttribute("messages", chatService.getMessages(rooms.get(0).getId()));
        }
        return "chatPage";
    }

    @MessageMapping("/socket/send/{roomId}")
    public void chat(ChatMessageVO message, @DestinationVariable String roomId, java.security.Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        UserVO user = ((CustomUser)token.getPrincipal()).getUser();
        template.convertAndSend("/socket/message/"+roomId, chatService.sendMessage(message, Integer.parseInt(roomId), user.getNickname()));
    }


}
