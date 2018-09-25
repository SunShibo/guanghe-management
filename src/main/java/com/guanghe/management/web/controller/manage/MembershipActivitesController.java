package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.ActivityReservationBo;
import com.guanghe.management.entity.bo.MembershipActivitesBo;
import com.guanghe.management.entity.bo.UserBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;

import com.guanghe.management.service.MembershipActivitesService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping("/activites")
public class MembershipActivitesController extends BaseCotroller{

    @Autowired
    private MembershipActivitesService membershipActivitesService;
    //huiyuanzunxiang.jsp
    @RequestMapping("/toAdd")
    public ModelAndView huiyuanzunxiang(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/activites/add");
//        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/activites/list");
//        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }
    @RequestMapping("/findOne")
    public ModelAndView findOne(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/activites/update");
//        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }

    @RequestMapping("/list")
    public void getMembershipActivitesList(HttpServletResponse response,HttpServletRequest request,Integer pageNo, Integer pageSize,String title){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("title",title);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("count",membershipActivitesService.getMembershipActivitesCount(map));
        resultMap.put("data",membershipActivitesService.getMembershipActivitesList(map));
        resultMap.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }



    @RequestMapping("/details")
    public void getMembershipActivitesDetails (HttpServletResponse response,HttpServletRequest request, Integer id){
        MembershipActivitesBo bo = membershipActivitesService.getMembershipActivitesDetails(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        map.put("data",bo);

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        safeTextPrint(response, json);
    }

    @RequestMapping("/add")
    public void add (HttpServletResponse response,HttpServletRequest request, MembershipActivitesBo bo){
        if (bo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        if (bo.getTitle()==null||bo.getImgUrl()==null||bo.getContent()==null||bo.getActivitieTime()==null
                ||bo.getLocal()==null||bo.getPrice()==null||bo.getSynopsis()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        membershipActivitesService.addMembershipActivites(bo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/update")
    public void update (HttpServletResponse response,HttpServletRequest request, MembershipActivitesBo bo){
        if (bo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        if (bo.getTitle()==null||bo.getImgUrl()==null||bo.getContent()==null||bo.getActivitieTime()==null
                ||bo.getLocal()==null||bo.getPrice()==null||bo.getSynopsis()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        MembershipActivitesBo news =membershipActivitesService.getMembershipActivitesDetails(bo.getId());
        if (news==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        news.setTitle(bo.getTitle());
        news.setImgUrl(bo.getImgUrl());
        news.setContent(bo.getContent());
        news.setActivitieTime(bo.getActivitieTime());
        news.setLocal(bo.getLocal());
        news.setPrice(bo.getPrice());
        news.setSynopsis(bo.getSynopsis());
        membershipActivitesService.updateMembershipActivites(news);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/delete")
    public void delete (HttpServletResponse response,HttpServletRequest request,Integer id){
        MembershipActivitesBo news =membershipActivitesService.getMembershipActivitesDetails(id);
        if (news==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        membershipActivitesService.deleteMembershipActivites(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
}
