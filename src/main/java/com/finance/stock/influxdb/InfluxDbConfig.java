package com.finance.stock.influxdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDbConfig {

    @Value("${influx.url}")
    private String influxDBUrl;

    @Value("${influx.user}")
    private String userName;

    @Value("${influx.password}")
    private String password;

    @Value("${influx.database}")
    private String database;

    @Bean
    public InfluxDbUtils influxDbUtils(){
        return new InfluxDbUtils(userName,password,influxDBUrl,database,"autogen");
    }
}
