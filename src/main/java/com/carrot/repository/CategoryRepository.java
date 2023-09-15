package com.carrot.repository;

import com.carrot.domain.CategoryVO;

import java.util.List;

public interface CategoryRepository {
    public List<CategoryVO> selectAll();
    public List<CategoryVO> selecttownpostAll(); //town_post_category
}
