package com.guanghe.management.service;


import com.guanghe.management.dao.ImageDao;
import com.guanghe.management.entity.bo.ImageBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yxw on 2018/8/7.
 */
@Service("ImageService")
@Transactional
public class ImageService {
    @Resource
    private ImageDao imageDao;
    public ImageBo queryImage(Integer id){
        return  imageDao.queryImage(id);
    }
    public void updateImage(ImageBo imageBo){
        imageDao.updateImage(imageBo);
    }
    public  void addImage(ImageBo imageBo){
        imageDao.addImage(imageBo);
    }
    public void deleteImage(Integer Id){
        imageDao.deleteImage(Id);
    }
}
