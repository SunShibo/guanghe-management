package com.guanghe.management.web.controller.manage;

import com.guanghe.management.common.constants.SysConstants;
import com.guanghe.management.entity.bo.AdminBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.AdminService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.MD5Util;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yxw on 2018/7/19.
 */
@Controller
@RequestMapping("/AdminController")
public class AdminController  extends BaseCotroller{
    @Resource
    private AdminService adminService;

    @RequestMapping("/login")
    public void login(HttpServletResponse response, String account, String password){
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000006"));
            safeTextPrint(response, json);
            return ;
        }
        AdminBo adminBo = adminService.queryUserInfoByAccount(account);
        if(adminBo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000007"));
            safeTextPrint(response, json);
            return ;
        }
        System.out.println(MD5Util.digest(password));
        if(!MD5Util.digest(password).equals(adminBo.getPassword())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            safeTextPrint(response, json);
            return ;
        }

        //super.putLoginUser(adminBo.getUuid(), adminBo);    // 保存到缓存
        super.setCookie(response , SysConstants.CURRENT_LOGIN_ID, adminBo.getUuid(), SysConstants.SEVEN_DAY_TIME);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeJsonPrint(response,json);
        return ;
    }

}
