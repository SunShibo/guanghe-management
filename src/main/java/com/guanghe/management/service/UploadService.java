package com.guanghe.management.service;

import com.guanghe.management.dao.XtgMaterialLibraryDao;
import com.guanghe.management.entity.XtgMaterialLibrary;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.util.DateUtils;
import com.guanghe.management.util.OssUploadFileUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * Created by yxw on 2018/8/13.
 */
@Service("UploadService")
@Transactional
public class UploadService {

    public String uploadMaterialLibrary(MultipartFile file){
        JSONObject jo = new JSONObject();
        if(file.isEmpty()){
            jo.put("code", 0);
            jo.put("msg", "文件有误，请重新选择！");
        }else{
            String originalFilename = file.getOriginalFilename();
            //获得文件后缀名称
            String imageRoot = SystemConfig.getString("img_file_root");
            String yyyyMMdd = DateUtils.formatDate(DateUtils.DATE_PATTERN_PLAIN, new Date());
            String imageUrl = imageRoot  + "/"+yyyyMMdd +  originalFilename;
            OssUploadFileUtil.uploadMultipartFile(file,
                    SystemConfig.getString("image_bucketName"), imageUrl);

            String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            XtgMaterialLibrary materialLibrary = new XtgMaterialLibrary();
            try {
                BufferedImage bi = ImageIO.read(file.getInputStream());
                materialLibrary.setImageWidth(bi.getWidth());
                materialLibrary.setImageHeight(bi.getHeight());
                bi.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
            materialLibrary.setImageUrl(imageUrl);
            String Url = "https://" +SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/"+imageUrl;
            // 添加素材信息
            jo.put("imageUrl", imageUrl);
            jo.put("Url",Url);
        }
        return jo.toString();
    }
}
