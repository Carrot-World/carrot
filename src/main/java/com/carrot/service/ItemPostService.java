package com.carrot.service;

import com.carrot.domain.ImageVO;
import com.carrot.domain.ItemPostVO;
import com.carrot.repository.ImageRepository;
import com.carrot.repository.ItemPostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ItemPostService {

    @Autowired
    private AWSS3 AWSS3;

    @Autowired
    private SqlSession sqlSession;

    public int insert(ItemPostVO vo, List<MultipartFile> imageList) throws IOException {
        vo.setWriter("imkeunho");
        vo.setCreated_at(new Date(System.currentTimeMillis()));
        if (sqlSession.getMapper(ItemPostRepository.class).insert(vo) > 0) {
            AWSS3.uploadImage(vo.getId(), imageList);
            for (int i = 0; i < imageList.size(); i++) {
                String url = "https://carrot-world.s3.ap-northeast-2.amazonaws.com/";
                String fileName = AWSS3.getFileName(vo.getId(), i, imageList.get(i));
                ImageVO imageVO = new ImageVO(vo.getId(), i, url + fileName);
                sqlSession.getMapper(ImageRepository.class).insert(imageVO);
            }
        } else {
            return -1;
        }
        return 1;
    }

    public List<ItemPostVO> select() {
        List<ItemPostVO> itemPostList = sqlSession.getMapper(ItemPostRepository.class).selectAll();
        for (ItemPostVO itemPost : itemPostList) {
            List<ImageVO> imageList = sqlSession.getMapper(ImageRepository.class).selectById(itemPost.getId());
            if (imageList.size() != 0) {
                itemPost.setImageList(imageList);
            }
        }
        return itemPostList;
    }

    public ItemPostVO detail(int id) {
        ItemPostVO itemPost = sqlSession.getMapper(ItemPostRepository.class).selectById(id);
        itemPost.setImageList(sqlSession.getMapper(ImageRepository.class).selectById(id));
        return itemPost;
    }
}
