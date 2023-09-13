package com.carrot.repository;

import com.carrot.domain.ImageVO;

import java.util.List;

public interface ImageRepository {

    public void insert(ImageVO vo);
    public List<ImageVO> selectById(int id);
}
