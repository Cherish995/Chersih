package com.cherish.mm.controller;

import com.cherish.mm.constants.Constants;
import com.cherish.mm.entity.PageResult;
import com.cherish.mm.entity.QueryPageBean;
import com.cherish.mm.entity.Result;
import com.cherish.mm.pojo.Course;
import com.cherish.mm.pojo.User;
import com.cherish.mm.service.CourseService;
import com.cherish.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 处理添加学科请求
     *
     * @param course
     * @param session
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Course course, HttpSession session) throws IOException {
        try {
            // 获取请求参数
            User user = (User) session.getAttribute(Constants.LOGIN_ON_USER);
            course.setCreateDate(DateUtils.parseDate2String(new Date()));
            course.setUserId(user.getId());
            course.setOrderNo(1);
            // 调用业务层
            courseService.add(course);

            // 响应
            return new Result(true, "添加成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败！");
        }
    }

    /**
     * 处理查询分页请求
     *
     * @param
     * @param
     */
    @RequestMapping("/list")
    public Result list(@RequestBody QueryPageBean queryPageBean) throws IOException {
        try {
            // 获取请求参数
            Map map = queryPageBean.getQueryParams();
            /**
             * 处理mybatis的坑会把int类型的 0 当成 null 传过来
             */
            if (map != null) {
                if (!map.get("status").equals("")) {
                    String status = (int) map.get("status") + "";
                    map.put("status", status);
                }
            }
            queryPageBean.setQueryParams(map);
            // 调用业务层
            PageResult pageResult = courseService.list(queryPageBean);

            // 响应
            return new Result(true, "查询成功", pageResult);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    /**
     * 处理修改请求
     *
     * @param
     * @param
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Course course) throws IOException {
        try {
            // 获取请求参数
            // 调用业务层
            courseService.update(course);

            // 响应
            return new Result(true, "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败！");
        }
    }

    /**
     * 处理删除请求
     *
     * @param
     * @param
     */
    @RequestMapping("/delete")
    public Result delete(Integer id) throws IOException {
        try {
            // 获取请求参数

            // 调用业务层
            courseService.delete(id);

            // 删除成功
            return new Result(true, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            // 删除失败
            return new Result(false, e.getMessage());
        }
    }

    /**
     * 处理查询所有学科请求
     *
     * @param
     * @param
     */
    @RequestMapping("/findAll")
    public Result findAll(@RequestBody Map map) throws IOException {
        try {
            // 获取请求参数
            if (!map.get("status").equals("")) {
                String status = (int) map.get("status") + "";
                map.put("status", status);
            }
            // 调用业务层
            List<Course> courseList = courseService.findAll(map);

            // 响应数据
            return new Result(true, "查询学科列表成功！", courseList);

        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            return new Result(false, "学科列表查询失败");
        }
    }
}
