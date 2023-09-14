package com.carrot.service;

import com.carrot.domain.*;
import com.carrot.repository.CategoryRepository;
import com.carrot.repository.ItemPostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemPostService {

    @Autowired
    private AWSS3 AWSS3;

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ImageService imageService;
    private static HashMap<Integer, String> categoryMap;
    private static boolean isSetCategory;

    public int insert(UserVO user, ItemPostVO vo, List<MultipartFile> imageList) throws IOException {
        vo.setWriter(user.getNickname());
        vo.setCreated_at(new Date(System.currentTimeMillis()));
        if (sqlSession.getMapper(ItemPostRepository.class).insert(vo) > 0) {
            AWSS3.uploadImage(vo.getId(), imageList);
            for (int i = 0; i < imageList.size(); i++) {
                String url = "https://carrot-world.s3.ap-northeast-2.amazonaws.com/";
                String fileName = AWSS3.getFileName(vo.getId(), i, imageList.get(i));
                if (imageService.insert(new ImageVO(vo.getId(), i, url + fileName)) != 1) {
                    return -1;
                }
            }
        } else {
            return -1;
        }
        return 1;
    }

    public List<ItemPostVO> search(SearchVO vo) {
        List<ItemPostVO> itemPostList = sqlSession.getMapper(ItemPostRepository.class).search(vo);
        if (!isSetCategory) {
            setCategoryMap();
        }
        if (itemPostList.isEmpty()) {
            return null;
        }
        return imageService.setFirstImage(itemPostList);
    }

    public ItemPostVO detail(int id) {
        ItemPostVO itemPost = sqlSession.getMapper(ItemPostRepository.class).selectById(id);
        itemPost.setImageList(imageService.selectById(id));
        itemPost.setCategory_name(categoryMap.get(itemPost.getCategory_id()));
        return itemPost;
    }

    public void setCategoryMap() {
        List<CategoryVO> categoryList = sqlSession.getMapper(CategoryRepository.class).selectAll();
        categoryMap = new HashMap<>();
        for (CategoryVO vo : categoryList) {
            categoryMap.put(vo.getId(), vo.getName());
        }
        isSetCategory = true;
    }
}
