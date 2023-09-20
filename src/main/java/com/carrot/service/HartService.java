package com.carrot.service;

import com.carrot.domain.HartVO;
import com.carrot.domain.UserVO;
import com.carrot.repository.HartRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HartService {

    @Autowired
    SqlSession sqlSession;

    public HartVO isCheck(UserVO vo, int itemPostId) {
        return sqlSession.getMapper(HartRepository.class).selectAtOne(new HartVO(itemPostId, vo.getId()));
    }

    public int plus(HartVO vo) {
        return sqlSession.getMapper(HartRepository.class).insert(vo);
    }

    public int minus(HartVO vo) {
        return sqlSession.getMapper(HartRepository.class).delete(vo);
    }
}
