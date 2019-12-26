package com.finance.stock.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url ="${tushare.url}",name ="tushare")
public interface TushareAPI {

    @RequestMapping(value="",method = RequestMethod.GET)
    public JSONObject GetTushareList(@RequestParam("api_name") String ApiName, @RequestParam("token") String TushareToken, @RequestParam("params") String Params, @RequestParam("fields") String Fields);

}
