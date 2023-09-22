package com.carrot.repository;

import java.util.List;

import com.carrot.domain.ItemPostVO;
import com.carrot.domain.SearchVO;

import java.util.List;
import java.util.Map;
import com.carrot.domain.TradeVO;

public interface ItemPostRepository {
    public int insert(ItemPostVO vo);
    public int selectCount(SearchVO vo);
    public ItemPostVO selectById(int id);
    public List<ItemPostVO> search(SearchVO vo);
    public List<ItemPostVO> selectByWriter(String writer);
    public List<ItemPostVO> selectByBuyer(String buyer); //구매내역 조회
    public void addChatCnt(int postId);
    public void minusChatCnt(int postId);
    public int delete(ItemPostVO vo);
    public int updateComplete(int postId);
    public void createTrade(Map map);
    public List<ItemPostVO> selectHeartById(String id); //찜 목록 조회
}
