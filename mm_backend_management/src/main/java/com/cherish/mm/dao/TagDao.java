package com.cherish.mm.dao;

import com.cherish.mm.pojo.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
public interface TagDao {
    Long findCount(Integer id);
    List<Tag> findAll(Integer courseId);

    void add(Map map);
}
