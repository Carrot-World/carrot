package com.carrot.repository;

import com.carrot.domain.HartVO;
import com.carrot.domain.ItemPostVO;
import com.carrot.domain.SearchVO;

import java.util.List;

public interface ItemPostRepository {
    public int insert(ItemPostVO vo);
    public int selectCount(SearchVO vo);
    public ItemPostVO selectById(int id);
    public List<ItemPostVO> search(SearchVO vo);
    public List<ItemPostVO> selectByWriter(String writer);
    public int hartPlus(HartVO vo);
    public int hartMinus(HartVO vo);
    public void addChatCnt(int postId);
    public void minusChatCnt(int postId);
    public int delete(ItemPostVO vo);
    public int updateComplete(ItemPostVO vo);
}
