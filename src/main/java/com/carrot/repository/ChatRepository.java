package com.carrot.repository;

import com.carrot.domain.ChatMessageVO;
import com.carrot.domain.ChatRoomVO;

import java.util.List;

public interface ChatRepository {

    public List<ChatRoomVO> getAllChatRooms(String userId);
    public List<ChatMessageVO> getChatMessages(int id);

}
