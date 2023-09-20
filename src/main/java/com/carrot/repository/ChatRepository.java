package com.carrot.repository;

import com.carrot.domain.ChatMessageVO;
import com.carrot.domain.ChatRoomVO;

import java.util.List;
import java.util.Map;

public interface ChatRepository {

    List<ChatRoomVO> getAllChatRooms(String userId);
    List<ChatMessageVO> getChatMessages(int id);
    int insertChatMessage(ChatMessageVO message);
    void updateLastMessage(Map map);
    int search(Map map);
    int createRoom(ChatRoomVO room);
    int updateIsRead(Map map);
    ChatRoomVO selectRoomById(int id);
    List<ChatRoomVO> getBuyer(int itemPostId);
}
