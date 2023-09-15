package com.carrot.repository;

import com.carrot.domain.HartVO;
import com.carrot.domain.ItemPostVO;
import com.carrot.domain.SearchVO;

import java.util.List;

public interface ItemPostRepository {
    public int insert(ItemPostVO vo);
    public int update(ItemPostVO vo);
    public List<ItemPostVO> selectAll();
    public ItemPostVO selectById(int id);
    public List<ItemPostVO> search(SearchVO vo);
    public int hartPlus(HartVO vo);
    public int hartMinus(HartVO vo);

}
