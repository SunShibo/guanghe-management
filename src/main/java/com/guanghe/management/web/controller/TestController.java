package com.guanghe.management.web.controller;

import com.guanghe.management.dao.TestDAO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestDAO testDAO;

    @RequestMapping("/test")
    public void test(HttpServletResponse response) {
//        hostDAO.insertHost(hostDO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(testDAO.test()));
        super.safeJsonPrint(response, json);

    }

}
