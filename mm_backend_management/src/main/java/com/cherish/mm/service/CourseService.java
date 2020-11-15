package com.cherish.mm.service;

import com.cherish.mm.entity.PageResult;
import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.pojo.Course;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/15
 */
public interface CourseService {
    PageResult list(QueryPageBean queryPageBean) throws IOException;

    void update(Course course) throws Exception;

    @Transactional
    void delete(Integer id) throws Exception;

    List<Course> findAll(Map map) throws Exception;

    void add(Course course);
}
