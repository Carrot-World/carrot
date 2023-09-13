package com.carrot.repository;

import com.carrot.domain.ItemPostVO;

import java.util.List;

public interface ItemPostRepository {
    public int insert(ItemPostVO vo);
    public List<ItemPostVO> selectAll();
    public ItemPostVO selectById(int id);

}
