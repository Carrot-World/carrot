package com.carrot.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.FetchProfile.Item;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.domain.ImageVO;
import com.carrot.domain.ItemPostVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.TradeVO;
import com.carrot.domain.UserVO;
import com.carrot.repository.ItemPostRepository;

@Service
public class ItemPostService {

    @Autowired
    private AWSS3 AWSS3;

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ImageService imageService;

    public int insert(UserVO user, ItemPostVO vo, List<MultipartFile> imageList) throws IOException {
        vo.setWriter(user.getId());
        vo.setCreated_at(new Date(System.currentTimeMillis()));
        if (sqlSession.getMapper(ItemPostRepository.class).insert(vo) > 0) {
            return setImage(vo, imageList);
        } else {
            return -1;
        }
    }

    public int setImage(ItemPostVO vo, List<MultipartFile> imageList) throws IOException {
        AWSS3.uploadImage(vo.getId(), imageList);
        for (int i = 0; i < imageList.size(); i++) {
            String url = "https://carrot-world.s3.ap-northeast-2.amazonaws.com/";
            String fileName = AWSS3.getFileName(vo.getId(), i, imageList.get(i));
            if (imageService.insert(new ImageVO(vo.getId(), i, url + fileName)) != 1) {
                return -1;
            }
        }
        return 1;
    }

    public List<ItemPostVO> search(SearchVO vo) {
        List<ItemPostVO> itemPostList = sqlSession.getMapper(ItemPostRepository.class).search(vo);
        if (itemPostList.isEmpty()) {
            return null;
        }
        return imageService.setFirstImage(itemPostList);
    }
    
    public List<ItemPostVO> selectByWriter(String writer) { //판매내역조회 
    	List<ItemPostVO> list = sqlSession.getMapper(ItemPostRepository.class).selectByWriter(writer);
    	if (list.isEmpty()) {
            return null;
        }
    	return imageService.setFirstImage(list) ;
    }
    
    public List<ItemPostVO> selectByBuyer(String buyer) { //구매내역조회
    	List<ItemPostVO> list = sqlSession.getMapper(ItemPostRepository.class).selectByBuyer(buyer);
    	if (list.isEmpty()) {
    		return null;
    	}
    	return imageService.setFirstImage(list) ;
    }

    public ItemPostVO detail(int id) {
        ItemPostVO itemPost = sqlSession.getMapper(ItemPostRepository.class).selectById(id);
        List<ImageVO> imageList = imageService.selectById(id);
        if (!imageList.isEmpty()) {
            itemPost.setImageList(imageService.selectById(id));
        }
        return itemPost;
    }

    public void addChatCnt(int postId) {
        sqlSession.getMapper(ItemPostRepository.class).addChatCnt(postId);
    }
  
    public void minusChatCnt(int postId) {
        sqlSession.getMapper(ItemPostRepository.class).minusChatCnt(postId);
    }
  
    public int complete(ItemPostVO vo) {
        return sqlSession.getMapper(ItemPostRepository.class).updateComplete(vo);
    }
  
    public int delete(ItemPostVO vo) {
        return sqlSession.getMapper(ItemPostRepository.class).delete(vo);
    }
    
    
    public List<TradeVO> selectTradeById(String id) { //거래 후기 조회
    	return sqlSession.getMapper(ItemPostRepository.class).selectTradeById(id);
    }
}
