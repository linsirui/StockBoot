package com.finance.stock.util;

import com.alibaba.fastjson.JSONObject;
import com.finance.stock.service.TushareAPI;


import org.springframework.beans.factory.annotation.Autowired;

public class TushareData {

    private TushareAPI tushareAPI;

    @Autowired
    public void GetTushareAPI(){

        String ParamInput ="{\n" +
                "        \"exchange\":\"\",\n" +
                "        \"start_date\":\"20180901\",\n" +
                "        \"end_date\":\"20181001\",\n" +
                "        \"is_open\":\"0\"\n" +
                "    }";

        String Fields ="exchange,cal_date,is_open,pretrade_date";
        JSONObject APIResult = tushareAPI.GetTushareList("trade_cal","f2d2d0d2791d0e639d175f90778c6be9e502d4119962787d642fa960",ParamInput,Fields);
        System.out.println(APIResult.toString());

    }
}
