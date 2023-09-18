package com.carrot.service;

import com.carrot.domain.ChatMessageVO;
import com.carrot.domain.ChatRoomVO;
import com.carrot.repository.ChatRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    SqlSession session;

    private Comparator<ChatRoomVO> comparator = new Comparator<ChatRoomVO>() {
        @Override
        public int compare(ChatRoomVO o1, ChatRoomVO o2) {
            Date d1 = o1.getLastMessage().getCreatedAt();
            Date d2 = o2.getLastMessage().getCreatedAt();
            return d2.compareTo(d1);
        }
    };

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd hh:mm");


    public List<ChatRoomVO> getAllChatRooms(String userName) {
        List<ChatRoomVO> rooms =  session.getMapper(ChatRepository.class).getAllChatRooms(userName);
        rooms.sort(comparator);
        fillUnReadCnt(rooms, userName);
        return rooms;
    }

    private void fillUnReadCnt(List<ChatRoomVO> rooms, String userName) {
        for (ChatRoomVO room : rooms) {
            ChatMessageVO message = room.getLastMessage();
            if (!message.getWriter().equals(userName) && message.getIsRead() == 1) {
                int cnt = 0;
                List<ChatMessageVO> messages = getMessages(room.getId());
                int index = messages.size() - 1;
                while (index >= 0 && (!messages.get(index).getWriter().equals(userName) && message.getIsRead() == 1)) {
                    index--;
                    cnt++;
                }
                room.setUnReadCnt(cnt);
            }
        }
    }

    public List<ChatMessageVO> getMessages(int id) {
        List<ChatMessageVO> messages = session.getMapper(ChatRepository.class).getChatMessages(id);
        return messages;
    }

    public ChatMessageVO sendMessage(ChatMessageVO message, int id, String userName) {
        message.setRoomId(id);
        message.setWriter(userName);
        message.setIsRead(1);
        message.setCreatedAt(new Date(System.currentTimeMillis()));
        session.getMapper(ChatRepository.class).insertChatMessage(message);
        int genId = message.getId();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("messageId", genId);
        map.put("roomId", id);
        session.getMapper(ChatRepository.class).updateLastMessage(map);
        message.setTime(dateFormat.format(message.getCreatedAt()));
        return message;
    }
}
