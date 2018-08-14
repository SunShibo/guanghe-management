package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.*;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.BannerService;
import com.guanghe.management.service.InfoService;
import com.guanghe.management.util.DateUtils;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseCotroller {
    @Resource
    private InfoService infoService;
    @Resource
    private BannerService bannerService;
    @RequestMapping("/list")
    public ModelAndView queryCoachList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/homeInfo");
        return view;
    }
    @RequestMapping("/info")
    public void info(HttpServletResponse response){
        List<BannerBo> bannerBo= bannerService.queryBannerInfo();
        if (bannerBo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        List<CompanyIntroductionBo> companyIntroductionBo =infoService.queryCompanyIntroductionInfo();
        if (companyIntroductionBo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        List<NewsInformationBO> newsInformationBO = infoService.queryNewsInformationInfo();
        if (newsInformationBO==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        List<ImageBo> imageBo =infoService.queryImageInfo();
        if (imageBo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        List<ModuleBo> moduleBo = infoService.queryModuleInfo();



        List<PrivateClubBo> privateClubBo =infoService.queryPrivateClub();
        if (privateClubBo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }


        // simple code
        JSONObject result = new JSONObject();
        result.put("banner", bannerBo);
        result.put("companyIntroduction",companyIntroductionBo);
        result.put("news", JsonUtils.getJsonString4JavaListDate(newsInformationBO, DateUtils.LONG_DATE_PATTERN));
        result.put("image",imageBo);
        result.put("detail",moduleBo);
        result.put("club" ,privateClubBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);
    }
}