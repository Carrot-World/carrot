package com.carrot.service;

import com.carrot.domain.HartVO;
import com.carrot.domain.UserVO;
import com.carrot.repository.HartRepository;
import com.carrot.repository.ItemPostRepository;
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
        int insert = sqlSession.getMapper(HartRepository.class).insert(vo);
        int plus = sqlSession.getMapper(ItemPostRepository.class).hartPlus(vo);
        return insert + plus;
    }

    public int minus(HartVO vo) {
        int minus = sqlSession.getMapper(ItemPostRepository.class).hartMinus(vo);
        if (minus < 1) {
            return -1;
        } else {
            int delete = sqlSession.getMapper(HartRepository.class).delete(vo);
            return delete;
        }
    }
}
