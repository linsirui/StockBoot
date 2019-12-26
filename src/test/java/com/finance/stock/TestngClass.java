package com.finance.stock;

import com.finance.stock.influxdb.InfluxDbConfig;
import com.finance.stock.influxdb.InfluxDbUtils;
import com.finance.stock.service.TushareService;
import com.finance.stock.util.TushareData;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestngClass {

    InfluxDbConfig influxDbConfig = new InfluxDbConfig();
    InfluxDbUtils influxDbUtils = new InfluxDbUtils("stock","123456","http://localhost:8086","stock","autogen");

    @Test
    public void testTushareService(){
        TushareService tushareData = new TushareService();
        String result = tushareData.getTushareData();
        System.out.println(result);
    }

    @Test
    public void testTushareAPI(){
        TushareData tushareData = new TushareData();
        tushareData.GetTushareAPI();
    }

    @Test //daily
    public void rtPostObject(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.tushare.pro";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
       // MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        //map.add("api_name", "new_share");
        //map.add("token","f2d2d0d2791d0e639d175f90778c6be9e502d4119962787d642fa960");
        //String param ="\"start_date\":\"20190101\",\"end_date\":\"20191001\"";
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("start_date","20190101");
        //jsonObject.put("end_date","20191001");
        //map.add("params", jsonObject.toString());

        String post_param ="{\n" +
                "    \"api_name\":\"daily\",\n" +
                "    \"token\":\"f2d2d0d2791d0e639d175f90778c6be9e502d4119962787d642fa960\",\n" +
                "    \"params\":{\n" +
                "    \t   \"ts_code\",\"000001.SZ\",\n" +
                "        \"start_date\":\"20191101\",\n" +
                "        \"end_date\":\"20191130\"}\n" +
                "}\n";
        //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, post_param , String.class );
        System.out.println(response.getBody());
    }

    @Test //stock_basic
    public void stock_basic(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.tushare.pro";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        //map.add("api_name", "new_share");
        //map.add("token","f2d2d0d2791d0e639d175f90778c6be9e502d4119962787d642fa960");
        //String param ="\"start_date\":\"20190101\",\"end_date\":\"20191001\"";
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("start_date","20190101");
        //jsonObject.put("end_date","20191001");
        //map.add("params", jsonObject.toString());

        String post_param ="{\n" +
                "    \"api_name\":\"stock_basic\",\n" +
                "    \"token\":\"f2d2d0d2791d0e639d175f90778c6be9e502d4119962787d642fa960\"}";
        //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, post_param , String.class );
        System.out.println(response.getBody());
    }

    @Test
    public void writeToInflux() {
        InfluxDB influxDB = influxDbUtils.influxDbBuild();
        influxDB.write(Point.measurement("weekly")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("ts_code", "000001.SZ")
                .addField("trade_date", "20191129")
                .addField("close", 15.2900)
                .addField("open", 15.6400)
                .addField("high", 15.8900)
                .addField("low", 15.1800)
                .addField("vol", 310660887.000000)
                .addField("amount", 4828270986.070000)
                .build());
    }


}
