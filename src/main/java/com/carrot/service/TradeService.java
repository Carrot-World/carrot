package com.carrot.service;

import com.carrot.domain.TradeVO;
import com.carrot.repository.TradeRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

}
