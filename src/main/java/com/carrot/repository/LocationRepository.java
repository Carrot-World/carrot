package com.carrot.repository;

import com.carrot.domain.LocationVO;

import java.util.List;

public interface LocationRepository {
    public List<LocationVO> selectInit();
    public List<LocationVO> selectByLoc1(LocationVO vo);
    public List<LocationVO> selectByLoc2(LocationVO vo);
}
