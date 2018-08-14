package com.guanghe.management.entity;

/**
 * Created by yxw on 2018/8/13.
 */
public class XtgMaterialLibrary {
    private  int imageWidth;
    private  int imageHeight;
    private  String imageType;
    private   String imageName;
    private  Integer Id;
    private  String imageUrl;


    public int getImageWidth() {
        return imageWidth;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }



    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
