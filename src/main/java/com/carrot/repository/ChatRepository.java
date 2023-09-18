package com.carrot.repository;

import com.carrot.domain.ChatMessageVO;
import com.carrot.domain.ChatRoomVO;

import java.util.List;
import java.util.Map;

public interface ChatRepository {

    public List<ChatRoomVO> getAllChatRooms(String userId);
    public List<ChatMessageVO> getChatMessages(int id);
    public int insertChatMessage(ChatMessageVO message);
    public void updateLastMessage(Map map);
}
