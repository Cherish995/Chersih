package com.cherish.mm.service;

import com.cherish.mm.entity.PageResult;
import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.pojo.Question;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/15
 */
public interface QuestionService {
    PageResult list(QueryPageBean queryPageBean) throws Exception;

    void add(Question question);
}
