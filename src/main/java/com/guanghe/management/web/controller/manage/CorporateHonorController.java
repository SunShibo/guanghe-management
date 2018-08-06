package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.CorporateHonorBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.CorporateHonorService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yxw on 2018/7/30.
 */
@Controller
@RequestMapping("/CorporateHonor")
public class CorporateHonorController  extends BaseCotroller{

        @Autowired
        private CorporateHonorService corporateHonorService;
        @RequestMapping("/list")
        public ModelAndView queryCoachList(){
            ModelAndView view = new ModelAndView();
            view.setViewName("/core_team");
            return view;
        }
        @RequestMapping("/delete")
        public void deleteCoreTeam(HttpServletResponse response, Integer id){
            if (id == null || id == 0 ) {
                return;
            }
            CorporateHonorBo news = corporateHonorService.queryCorporateHonor(id);

            if (news == null){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
                safeTextPrint(response, json);
            }else {
                corporateHonorService.deleteCorporateHonor(id);
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
                safeJsonPrint(response, json);
            }
        }

        @RequestMapping("/add")
        public void addCoreTeam (HttpServletResponse response, CorporateHonorBo news){
            if(news == null){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
                safeTextPrint(response, json);
            }else if(StringUtils.isEmpty(news.getImage())){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
                safeTextPrint(response, json);
            }else{
                corporateHonorService.addCorporateHonor(news);
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
                safeTextPrint(response, json);
            }
        }

        @RequestMapping("/update")
        public void updateCoreTeam (HttpServletResponse response,CorporateHonorBo news){
            CorporateHonorBo newsDetail = corporateHonorService.queryCorporateHonor(news.getId());

            if(news == null){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
                safeTextPrint(response, json);
            }else if(StringUtils.isEmpty(news.getImage())){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
                safeTextPrint(response, json);
            }else if(newsDetail == null){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
                safeTextPrint(response, json);
            }else{
                newsDetail.setImage(news.getImage());
                newsDetail.setCreateUser(news.getCreateUser());
                corporateHonorService.updateCorporateHonor(newsDetail);
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
                safeTextPrint(response, json);
            }

        }
        @RequestMapping("/detail")
        public void queryCoreTeam (HttpServletResponse response){
            CorporateHonorBo news = corporateHonorService.queryCorporateHonorDetail();
            if (news == null){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
                safeTextPrint(response, json);
            }
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
}
