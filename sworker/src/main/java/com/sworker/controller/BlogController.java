package com.sworker.controller;

import com.sworker.entity.AcitvityEntity;
import com.sworker.service.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Session;
import java.util.List;

/**
 * Created by zhangjin on 2014/8/7.
 */
@Controller
public class BlogController {

    @Resource(name="blogService")
    private IBlogService blogService;

    /**
     * 获得全部动态
     * @return
     */
    @RequestMapping(value="/activties", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AcitvityEntity> getActivityList(){
//        Session session = null;
        List<AcitvityEntity> list = blogService.dynamicList();
        return list;
    }

    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public void addDynamic(@RequestBody AcitvityEntity entity){
        blogService.addDynamic(entity);
    }
}
