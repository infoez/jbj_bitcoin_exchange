package com.common.model;


/**
 * Created by voodoo on 16. 8. 13.
 */
public class ImgVO {

    private String imgListId;
    private String itemId;
    private String blogId;
    private String bpId;
    private String imgUrl;
    private String imgName;
    private String imgSize;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBpId() {
        return bpId;
    }

    public void setBpId(String bpId) {
        this.bpId = bpId;
    }

    public String getImgListId() {
        return imgListId;
    }

    public void setImgListId(String imgListId) {
        this.imgListId = imgListId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgSize() {
        return imgSize;
    }

    public void setImgSize(String imgSize) {
        this.imgSize = imgSize;
    }
}
