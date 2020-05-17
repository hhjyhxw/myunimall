package com.icloud.api.newmall.ads;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icloud.annotation.AuthIgnore;
import com.icloud.basecommon.model.MapRespone;
import com.icloud.modules.unimall.dto.AdsDTO;
import com.icloud.modules.unimall.entity.UnimallAdvertisement;
import com.icloud.modules.unimall.enums.AdvertisementType;
import com.icloud.modules.unimall.service.UnimallAdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/mall/ads")
public class MallAdsController {

    private final Logger logger = LoggerFactory.getLogger(MallAdsController.class);
    @Autowired
   private UnimallAdvertisementService unimallAdvertisementService;

    /**
     *首页广告列表
     * @return
     */
    @RequestMapping("/getAdvertisementList")
    @ResponseBody
    @AuthIgnore
    public MapRespone getAdvertisementList(){// )
        List<UnimallAdvertisement> list =unimallAdvertisementService.list(new QueryWrapper<UnimallAdvertisement>()
                .eq("ad_type", AdvertisementType.CAROUSEL.getCode())
                .eq("status", 1));
        logger.info("ads/getAdvertisementList:list=="+ JSON.toJSONString(list!=null?list:"size=0"));
        logger.info("result ads/getAdvertisementList:list=="+ JSON.toJSONString(list));
        return MapRespone.ok().put("list",new AdsDTO().getAdsDTO(list));
    }




}
