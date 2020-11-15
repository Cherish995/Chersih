package com.cherish.mm.dao;

import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.pojo.Question;

import java.util.List;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
public interface QuestionDao {
    Long findCount(Integer id);

    List<Question> list(QueryPageBean queryPageBean);

    Long findTotal(QueryPageBean queryPageBean);

    void add(Question question);
}
