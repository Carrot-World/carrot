package com.carrot.repository;

import com.carrot.domain.TradeVO;

import java.util.List;

public interface TradeRepository {
    List<TradeVO> getListByUserId(String userId);
    List<TradeVO> getListByUserId2(String userId);
}
