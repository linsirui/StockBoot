package com.finance.stock.influxdb;


import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfluxDbUtils {

    private String userName;
    private String password;
    private String url;
    public String database;
    private String retentionPolicy;

    private InfluxDB influxDB;

    Logger logger = LoggerFactory.getLogger(InfluxDbUtils.class);

    public static String policyNamePix ="autogen";

    public InfluxDbUtils(String userName, String password, String url, String database, String retentionPolicy){
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.database = database;
        this.retentionPolicy = retentionPolicy;
        this.influxDB = influxDbBuild();
    }

    /**
     * Connect to Influx db
     * @return influxDb instance
     */

    public InfluxDB influxDbBuild() {
        if(influxDB == null){
            influxDB = InfluxDBFactory.connect(url, userName, password);
        }
        try{
            createDB(database);
            influxDB.setDatabase(database);
        }catch (Exception e){
            logger.error("create influx db failed, error: ",e.getMessage());
        }finally {
            influxDB.setRetentionPolicy(retentionPolicy);
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }

    /**
     * create database within the influxdb instance
     * @param database
     */

    private void createDB(String database) {
        influxDB.query(new Query("CREATE DATABASE " + database));
    }


}
