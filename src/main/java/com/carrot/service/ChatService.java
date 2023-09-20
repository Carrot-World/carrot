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
    @Autowired
    UserService userService;

    private Comparator<ChatRoomVO> comparator = new Comparator<ChatRoomVO>() {
        @Override
        public int compare(ChatRoomVO o1, ChatRoomVO o2) {
            Date d1 = o1.getLastMessage().getCreatedAt();
            Date d2 = o2.getLastMessage().getCreatedAt();
            return d2.compareTo(d1);
        }
    };

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");


    public List<ChatRoomVO> getAllChatRooms(String userId) {
        List<ChatRoomVO> rooms =  session.getMapper(ChatRepository.class).getAllChatRooms(userId);
        rooms.sort(comparator);
        fillUnReadCnt(rooms, userId);
        return rooms;
    }

    private void fillUnReadCnt(List<ChatRoomVO> rooms, String userId) {
        for (ChatRoomVO room : rooms) {
            ChatMessageVO message = room.getLastMessage();
            if (!message.getWriter().equals(userId) && message.getIsRead() == 1) {
                int cnt = 0;
                List<ChatMessageVO> messages = getMessages(room.getId());
                int index = messages.size() - 1;
                while (index >= 0 && (!messages.get(index).getWriter().equals(userId) && messages.get(index).getIsRead() == 1)) {
                    index--;
                    cnt++;
                }
                room.setUnReadCnt(cnt);
            }
        }
    }

    public List<ChatMessageVO> getMessages(int id) {
        List<ChatMessageVO> messages = session.getMapper(ChatRepository.class).getChatMessages(id);
        for (ChatMessageVO message : messages) {
            message.setTime(dateFormat.format(message.getCreatedAt()));
        }
        return messages;
    }

    public ChatMessageVO sendMessage(ChatMessageVO message, int id, String userId) {
        message.setRoomId(id);
        message.setWriter(userId);
        message.setIsRead(1);
        message.setCreatedAt(new Date(System.currentTimeMillis()));
        message.setTime(dateFormat.format(message.getCreatedAt()));
        session.getMapper(ChatRepository.class).insertChatMessage(message);
        int genId = message.getId();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("messageId", genId);
        map.put("roomId", id);
        session.getMapper(ChatRepository.class).updateLastMessage(map);
        return message;
    }

    public boolean checkRoomExist(int postId, String buyer) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("buyer", buyer);
        int cnt = session.getMapper(ChatRepository.class).search(map);
        return cnt == 0;
    }

    public int createChatRoom(ChatRoomVO room) {
        session.getMapper(ChatRepository.class).createRoom(room);
        return room.getId();
    }

    public void updateIsRead(String userId, int roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roomId", roomId);
        session.getMapper(ChatRepository.class).updateIsRead(map);
    }

    public ChatRoomVO getRoomById(int id) {
        return session.getMapper(ChatRepository.class).selectRoomById(id);
    }

    public void exitChatRoom(int num, int roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("num", num);
        map.put("roomId", roomId);
        session.getMapper(ChatRepository.class).updateRoomStatus(map);
    }
}
