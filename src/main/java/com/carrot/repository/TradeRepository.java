package com.carrot.repository;

import com.carrot.domain.TradeVO;

import java.util.List;
import java.util.Map;

public interface TradeRepository {
    List<TradeVO> getListByUserId(String userId);
    List<TradeVO> getListByUserId2(String userId);
    TradeVO selectById(int tradeId);
    int updateTrade(Map map);
}
