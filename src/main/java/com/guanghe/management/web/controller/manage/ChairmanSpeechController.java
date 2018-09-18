package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.ChairmanSpeechBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.ChairmanSpeechService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yxw on 2018/7/18.
 */
@Controller
@RequestMapping("/ChairmanSpeech")
public class ChairmanSpeechController extends BaseCotroller {
    @Autowired
    private ChairmanSpeechService chairmanSpeechService;

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/guangheon/ChairmanSpeech_pc");
        return view;
    }
    @RequestMapping("/wappage")
    public ModelAndView page1(){
        ModelAndView view = new ModelAndView();
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/guangheon/ChairmanSpeech_wap");
        return view;
    }
    @RequestMapping("/delete")
    public void deleteActivityMessage(HttpServletResponse response, Integer id) {
        if (id == null || id == 0) {
            return;
        }
        ChairmanSpeechBo news = chairmanSpeechService.queryChairmanSpeechDetailById(id);

        if (news == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        } else {
            chairmanSpeechService.deleteChairmanSpeech(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }
    }

    @RequestMapping("/add")
    public void addActivityMessage(HttpServletResponse response, ChairmanSpeechBo news) {
        if (news == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        } else if (StringUtils.isEmpty(news.getTitle())
                || StringUtils.isEmpty(news.getSource()) || StringUtils.isEmpty(news.getChairmanTpeech())
                || StringUtils.isEmpty(news.getCreateUser())) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        } else {
            chairmanSpeechService.addChairmanSpeech(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateActivityMessage(HttpServletResponse response, String imageUrl) {
        ChairmanSpeechBo newsDetail = chairmanSpeechService.queryChairmanSpeechDetail();

        if (imageUrl == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);

        } else {
            newsDetail.setImage(imageUrl);
            chairmanSpeechService.updateChairmanSpeech(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }
    @RequestMapping("/update1")
    public void updateActivityMessage1(HttpServletResponse response, String wapImage) {
        ChairmanSpeechBo newsDetail = chairmanSpeechService.queryChairmanSpeechDetail();

        if (wapImage == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);

        } else {
            newsDetail.setWapImage(wapImage);
            chairmanSpeechService.updateChairmanSpeech(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/detail")
    public void queryChairmanSpeechDetailById(HttpServletResponse response) {

        ChairmanSpeechBo news = chairmanSpeechService.queryChairmanSpeechDetail();
        if (news == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        } else {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }


    }
}
