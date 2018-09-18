package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.CompanyIntroductionBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.CompanyItroductionService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;





@Controller
@RequestMapping("/CompanyIntroduction")
public class CompanyIntroductionController extends BaseCotroller {
    @Autowired
    private CompanyItroductionService companyItroductionService;
    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/guangheon/company_introduction_pc");
        return view;
    }
    @RequestMapping("/wappage")
    public ModelAndView page1(){
        ModelAndView view = new ModelAndView();
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/guangheon/company_introduction_wap");
        return view;
    }
    @RequestMapping("/delete")
    public void deleteCompanyItroduction(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        CompanyIntroductionBo news =companyItroductionService.queryCompanyItroduction(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            companyItroductionService.deleteCompanyItroduction(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }
    }

    @RequestMapping("/add")
    public void addBigECompanyItroduction (HttpServletResponse response, CompanyIntroductionBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getTitle())
                || StringUtils.isEmpty(news.getSource()) || StringUtils.isEmpty(news.getCompanyIntroduction())
                || StringUtils.isEmpty(news.getCreateUser())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            companyItroductionService.addCompanyItroduction(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateCompanyItroduction (HttpServletResponse response,String imageUrl) {
        CompanyIntroductionBo newsDetail =  companyItroductionService.queryCompanyIntroductionDetail();

        if (imageUrl == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else {
            newsDetail.setImage(imageUrl);
            companyItroductionService.updateCompanyItroduction(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }
    @RequestMapping("/update1")
    public void updateCompanyItroduction1 (HttpServletResponse response,String wapImage) {
        CompanyIntroductionBo newsDetail =  companyItroductionService.queryCompanyIntroductionDetail();

        if (wapImage == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else {
            newsDetail.setWapImage(wapImage);
            companyItroductionService.updateCompanyItroduction(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

        @RequestMapping("/detail")
    public void queryCompanyItroduction (HttpServletResponse response){
            CompanyIntroductionBo news = companyItroductionService.queryCompanyIntroductionDetail();
            if (news == null){
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
                safeTextPrint(response, json);
            }else{
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
                safeTextPrint(response, json);
            }
        }
}
