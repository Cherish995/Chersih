package com.cherish.mm.service.impl;

import com.cherish.mm.dao.CatalogDao;
import com.cherish.mm.dao.CourseDao;
import com.cherish.mm.dao.QuestionDao;
import com.cherish.mm.dao.TagDao;
import com.cherish.mm.entity.PageResult;
import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.pojo.Course;
import com.cherish.mm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CatalogDao catalogDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private QuestionDao questionDao;

    @Override
    @Transactional
    public void add(Course course) {
        courseDao.add(course);
    }

    @Override
    public PageResult list(QueryPageBean queryPageBean) throws IOException {

        Long total = courseDao.findCount(queryPageBean);
        List<Course> rows = courseDao.findList(queryPageBean);
        if (total == null || rows == null) {
            throw new RuntimeException("查询失败");
        }
//            System.out.println(total);
        return new PageResult(total, rows);
    }

    @Override
    public void update(Course course) throws Exception {

        courseDao.update(course);

    }

    @Transactional
    @Override
    public void delete(Integer id) throws Exception {

        Long cata = catalogDao.findCount(id);
        if (cata != 0) {
            throw new RuntimeException("该学科存在二级目录,删除失败！");
        }

        // 判断该学科是否有标签
        Long tag = tagDao.findCount(id);
        if (tag != 0) {
            throw new RuntimeException("该学科存在标签,删除失败！");
        }

        // 判断该学科是否有题目
        Long question = questionDao.findCount(id);
        if (question != 0) {
            throw new RuntimeException("该学科存在题目,删除失败！");
        }

        // 可以删除
        courseDao.delete(id);


    }

    @Override
    public List<Course> findAll(Map map) throws Exception {

        return courseDao.findAll(map);

    }
}
