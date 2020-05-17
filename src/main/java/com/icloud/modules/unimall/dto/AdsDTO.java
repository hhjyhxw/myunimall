package com.icloud.modules.unimall.dto;

import com.icloud.modules.unimall.entity.UnimallAdvertisement;

import java.util.ArrayList;
import java.util.List;

public class AdsDTO {
    private String adTitle;	//广告标题
    private String adImage;	//广告图片路径
    private String adHref;		//广告链接地址

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public String getAdHref() {
        return adHref;
    }

    public void setAdHref(String adHref) {
        this.adHref = adHref;
    }


   public List<AdsDTO> getAdsDTO(List<UnimallAdvertisement> list){
       List<AdsDTO> result = new ArrayList<AdsDTO>();
       AdsDTO ads = null;
       for (UnimallAdvertisement temp:list){
           ads = new AdsDTO();
           ads.setAdImage(temp.getImgUrl());
           ads.setAdTitle(temp.getTitle());
           ads.setAdHref(temp.getUrl());
           result.add(ads);
       }
        return result;
    }
}
