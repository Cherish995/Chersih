package com.cherish.mm.dao;

import com.cherish.mm.entity.PageResult;
import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
public interface CourseDao {
    void add(Course course);

    Long findCount(QueryPageBean queryPageBean);

    List<Course> findList(QueryPageBean queryPageBean);

    void update(Course course);

    void delete(Integer id);

    List<Course> findAll(Map map);
}
