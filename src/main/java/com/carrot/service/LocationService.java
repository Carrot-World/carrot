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
    private static List<String> loc1Arr;

    public List<String> loc1Set() {
        if (loc1Arr != null) {
            return loc1Arr;
        }
        List<LocationVO> locationList = sqlSession.getMapper(LocationRepository.class).selectInit();
        List<String> loc1 = new ArrayList<>();
        for (LocationVO locationVO : locationList) {
            loc1.add(locationVO.getLoc1());
        }
        loc1Arr = loc1;
        return loc1;
    }
    public List<String> loc2Set(LocationVO vo) {
        List<LocationVO> locationList = sqlSession.getMapper(LocationRepository.class).selectByLoc1(vo);
        List<String> loc2 = new ArrayList<>();
        for (LocationVO locationVO : locationList) {
            loc2.add(locationVO.getLoc2());
        }
        return loc2;
    }

    public List<String> loc3Set(LocationVO vo) {
        List<LocationVO> locationList = sqlSession.getMapper(LocationRepository.class).selectByLoc2(vo);
        List<String> loc3 = new ArrayList<>();
        for (LocationVO locationVO : locationList) {
            loc3.add(locationVO.getLoc3());
        }
        return loc3;
    }
}
