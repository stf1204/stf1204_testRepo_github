show databases ;

create database realtime;

CREATE external TABLE TestConnectionHBase(
  ba string,
  ts bigint,
  start_date date,
  start_time timestamp,
  id string
)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES (
  "hbase.columns.mapping" = "0:BA ,0:TS, 0:START_DATE ,0:START_TIME ,:key"
)
TBLPROPERTIES("hbase.table.name" = "REALTIME2022_STARTLOG");


select * from testconnectionhbase;



CREATE external TABLE REALTIME2022_STARTLOG_HIVE(
              ID string ,
              OPEN_AD_MS int,
              OS string,
              CH string,
              IS_NEW string,
              MID string,
              OPEN_AD_ID string,
              VC string,
              AR string,
              UID string,
              ENTRY string,
              OPEN_AD_SKIP_MS int,
              MD string,
              LOADING_TIME string,
              BA string,
              TS bigint,
              START_DATE date,
              START_TIME timestamp
)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES (
"hbase.columns.mapping" = ":key,0:OPEN_AD_MS ,0:OS ,0:CH ,0:IS_NEW ,0:MID ,0:OPEN_AD_ID ,0:VC ,0:AR ,0:UID ,0:ENTRY ,0:OPEN_AD_SKIP_MS ,0:MD ,0:LOADING_TIME ,0:BA ,0:TS ,0:START_DATE ,0:START_TIME"
)
TBLPROPERTIES("hbase.table.name" = "REALTIME2022_STARTLOG");

select *
from realtime2022_startlog_hive;