package com.carrot.service;

import com.carrot.domain.ImageVO;
import com.carrot.domain.ItemPostVO;
import com.carrot.repository.ImageRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    SqlSession sqlSession;

    public int insert(ImageVO vo) {
        return sqlSession.getMapper(ImageRepository.class).insert(vo);
    }

    public List<ImageVO> selectById(int id) {
        return sqlSession.getMapper(ImageRepository.class).selectById(id);
    }

    public void delete(int id) {
        sqlSession.getMapper(ImageRepository.class).delete(id);
    }

    public List<ItemPostVO> setFirstImage(List<ItemPostVO> itemPostList) {
        List<Integer> idArray = new ArrayList<>();
        for (ItemPostVO itemPost : itemPostList) {
            idArray.add(itemPost.getId());
        }
        List<ImageVO> imageList = sqlSession.getMapper(ImageRepository.class).selectAtFirst(idArray);
        for (ImageVO image : imageList) {
            int itemPostId = image.getItem_post_id();
            for (ItemPostVO itemPost : itemPostList) {
                if (itemPostId == itemPost.getId()) {
                    List<ImageVO> setImage = new ArrayList<>();
                    setImage.add(image);
                    itemPost.setImageList(setImage);
                }
            }
        }
        return itemPostList;
    }

    public List<String> getImageFileName(int id) {
        List<ImageVO> imageList = sqlSession.getMapper(ImageRepository.class).selectById(id);
        List<String> fileNames = new ArrayList<>();
        for (ImageVO image : imageList) {
            String[] url = image.getUrl().split("/");
            fileNames.add(url[url.length - 1]);
        }
        return fileNames;
    }

}
