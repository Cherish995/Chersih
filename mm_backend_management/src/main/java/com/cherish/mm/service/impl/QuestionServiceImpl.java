package com.cherish.mm.service.impl;

import com.cherish.mm.dao.QuestionDao;
import com.cherish.mm.dao.QuestionItemDao;
import com.cherish.mm.dao.TagDao;
import com.cherish.mm.entity.PageResult;
import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.pojo.Question;
import com.cherish.mm.pojo.QuestionItem;
import com.cherish.mm.pojo.Tag;
import com.cherish.mm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/04
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionItemDao questionItemDao;
    @Autowired
    private TagDao tagDao;

    @Override
    public PageResult list(QueryPageBean queryPageBean) throws Exception {

        Long total = questionDao.findTotal(queryPageBean);
        List<Question> rows = questionDao.list(queryPageBean);
        return new PageResult(total, rows);
    }

    @Override
    public void add(Question question) {
        try {
            questionDao.add(question);

            // 关联选项表
            List<QuestionItem> questionItemList = question.getQuestionItemList();
            if (questionItemList != null) {
                for (QuestionItem questionItem : questionItemList) {
                    questionItem.setQuestionId(question.getId());
                    questionItemDao.add(questionItem);
                }
            }
            // 关联标签表
            List<Tag> tagList = question.getTagList();
            if (tagList != null) {
                for (Tag tag : tagList) {
                    Map map = new HashMap();
                    map.put("questionId", question.getId());
                    map.put("tagId", tag.getId());
                    tagDao.add(map);
                }
            }
        } catch (Exception e) {

            throw new RuntimeException("添加失败");
        }
    }

}
