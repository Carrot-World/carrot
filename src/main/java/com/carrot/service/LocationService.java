package com.carrot.service;

import com.carrot.domain.LocationVO;
import com.carrot.repository.LocationRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private SqlSession sqlSession;

    public List<String> loc2Set(LocationVO vo) {
        List<LocationVO> locationList = sqlSession.getMapper(LocationRepository.class).selectByLoc1(vo);
        List<String> loc2 = new ArrayList<>();
        for (int i = 0; i < locationList.size(); i++) {
            loc2.add(locationList.get(i).getLoc2());
        }
        return loc2;
    }

    public List<String> loc3Set(LocationVO vo) {
        List<LocationVO> locationList = sqlSession.getMapper(LocationRepository.class).selectByLoc2(vo);
        List<String> loc3 = new ArrayList<>();
        for (int i = 0; i < locationList.size(); i++) {
            loc3.add(locationList.get(i).getLoc3());
        }
        return loc3;
    }
}
