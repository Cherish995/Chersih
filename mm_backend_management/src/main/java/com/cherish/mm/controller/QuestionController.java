package com.cherish.mm.controller;

import com.cherish.mm.constants.Constants;
import com.cherish.mm.entity.PageResult;
import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.entity.Result;
import com.cherish.mm.pojo.Question;
import com.cherish.mm.pojo.User;
import com.cherish.mm.service.QuestionService;
import com.cherish.mm.service.impl.QuestionServiceImpl;
import com.cherish.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/04
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 处理分页查询
     *
     * @param
     * @param
     */
    @RequestMapping("/list")
    public Result list(@RequestBody QueryPageBean queryPageBean) throws IOException {
        try {
            // 获取请求参数

            // 调用业务层
            PageResult pageResult = questionService.list(queryPageBean);

            // 响应
            return new Result(true, "分页查询成功！", pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "分页查询失败！");
        }
    }

    /**
     * 处理添加请求
     *
     * @param request
     * @param response
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Question question, HttpSession session) throws IOException {
        try {

            // 获取请求参数

            // 手动设置其它参数
            User user = (User) session.getAttribute(Constants.LOGIN_ON_USER);
            question.setUserId(user.getId());
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
            question.setReviewStatus(0); // 待审核
            question.setStatus(0); // 0 待发布（待审核、已拒绝）
            // 业务层
            questionService.add(question);

            // 响应
            return new Result(true, "添加成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败！");
        }
    }
}
