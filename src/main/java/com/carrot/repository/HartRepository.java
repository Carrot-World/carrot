package com.carrot.repository;

import com.carrot.domain.HartVO;

public interface HartRepository {
    public int insert(HartVO vo);
    public int delete(HartVO vo);
    public HartVO selectAtOne(HartVO vo);
}
