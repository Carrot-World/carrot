package com.carrot.service;

import com.carrot.domain.PageVO;
import com.carrot.domain.SearchVO;
import com.carrot.repository.ItemPostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagingService {

    @Autowired
    private SqlSession sqlSession;

    private final static int pageSize = 2;
    private final static int pageBlock = 5;

    public SearchVO setPaging(SearchVO vo) {
        searchInfoChecker(vo);
        if (vo.getPageNo() == 0) {
            vo.setPageNo(1);
        }
        vo.setPageSize(pageSize);
        vo.setPageStartCnt((vo.getPageNo() - 1) * pageSize);
        return vo;
    }

    public PageVO getPagingInfo(SearchVO vo) {
        searchInfoChecker(vo);
        PageVO pageVO = new PageVO();
        int searchResultCnt = sqlSession.getMapper(ItemPostRepository.class).selectCount(vo);
        pageVO.setTotal((int) Math.ceil((double) searchResultCnt / pageSize));
        pageVO.setStart(((vo.getPageNo() - 1) / pageBlock) * pageBlock + 1);
        pageVO.setEnd(Math.min((pageVO.getStart() - 1 + pageBlock), pageVO.getTotal()));
        pageVO.setCurrent(vo.getPageNo() == 0 ? 1 : vo.getPageNo());
        pageVO.setSize(pageSize);
        pageVO.setBlock(pageBlock);
        pageVO.setCurrPageCnt(Math.abs(searchResultCnt - (pageVO.getCurrent() * pageSize)));
        return pageVO;
    }

    public void searchInfoChecker(SearchVO vo) {
        if (vo.getLoc1() != null && vo.getLoc1().equals("")) {
            vo.setLoc1(null);
        }
        if (vo.getLoc2() != null && vo.getLoc2().equals("")) {
            vo.setLoc2(null);
        }
        if (vo.getLoc3() != null && vo.getLoc3().equals("")) {
            vo.setLoc3(null);
        }
        if (vo.getTitle() != null && vo.getTitle().equals("")) {
            vo.setTitle(null);
        }
    }
}
