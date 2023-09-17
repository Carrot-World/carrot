package com.carrot.service;

import com.carrot.domain.ChatMessageVO;
import com.carrot.domain.ChatRoomVO;
import com.carrot.repository.ChatRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
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
        return session.getMapper(ChatRepository.class).getChatMessages(id);
    }
}
