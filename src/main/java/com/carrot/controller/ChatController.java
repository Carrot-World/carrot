package com.carrot.controller;

import com.carrot.domain.ChatRoomVO;
import com.carrot.domain.UserVO;
import com.carrot.service.ChatService;
import com.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatRoomService;

    @GetMapping("/page/chat")
    public String chatPage(Model model) {
        UserVO user = userService.getUserInfo();
        List<ChatRoomVO> rooms = chatRoomService.getAllChatRooms(user.getNickname());
        model.addAttribute("username", user.getNickname());
        model.addAttribute("rooms", rooms);
        if (rooms.size() > 0) {
            model.addAttribute("messages", chatRoomService.getMessages(rooms.get(0).getId()));
        }
        return "chatPage";
    }


}
