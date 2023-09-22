package com.carrot.service;

import com.carrot.domain.TradeVO;
import com.carrot.repository.TradeRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    SqlSession session;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");

    public List<TradeVO> getListByUserId(String userId) {
        List<TradeVO> tradeList = session.getMapper(TradeRepository.class).getListByUserId(userId);
        if (tradeList == null) {
            return new ArrayList<TradeVO>();
        }
        for (TradeVO trade : tradeList) {
            trade.setTime(dateFormat.format(trade.getCreated_at()));
        }
        return tradeList;
    }

    public List<TradeVO> getListByUserId2(String userId) {
        List<TradeVO> tradeList = session.getMapper(TradeRepository.class).getListByUserId2(userId);
        if (tradeList == null) {
            return new ArrayList<TradeVO>();
        }
        for (TradeVO trade : tradeList) {
            trade.setTime(dateFormat.format(trade.getCreated_at()));
        }
        return tradeList;
    }

    public TradeVO selectById(int tradeId) {
        return session.getMapper(TradeRepository.class).selectById(tradeId);
    }

    public void writeContent(int tradeId, String colName, String content) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tradeId", tradeId);
        map.put("colName", colName);
        map.put("content", content);
        session.getMapper(TradeRepository.class).updateTrade(map);
    }
}
