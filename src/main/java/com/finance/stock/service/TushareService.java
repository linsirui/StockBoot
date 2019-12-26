package com.finance.stock.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TushareService {

    @Autowired
    private RestTemplate restTemplate;

    public String getTushareData(){
        String body ="{\n" +
                "    \"api_name\":\"trade_cal\",\n" +
                "    \"token\":\"f2d2d0d2791d0e639d175f90778c6be9e502d4119962787d642fa960\",\n" +
                "    \"params\":{\n" +
                "        \"exchange\":\"\",\n" +
                "        \"start_date\":\"20180901\",\n" +
                "        \"end_date\":\"20181001\",\n" +
                "        \"is_open\":\"0\"\n" +
                "    },\n" +
                "    \"fields\":\"exchange,cal_date,is_open,pretrade_date\"\n" +
                "}";
        JSONObject jsonObject = restTemplate.postForEntity("http://api.tushare.pro",body,JSONObject.class).getBody();
        return jsonObject.toJSONString();
    }
}
