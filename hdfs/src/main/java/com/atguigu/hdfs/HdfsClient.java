package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * 操作 HDFS的API
 * @author stf
 */
public class HdfsClient {

    @Test
    public void getFile() throws IOException, InterruptedException {
        Configuration con = new Configuration();
        // 1
        FileSystem sys = FileSystem.get(
                URI.create("hdfs://master:9000"), con, "root"
        );


        // 2
        // Boolean delSrc 是否删除源文件
        // Boolean useRawLocalFileSystem 是否开启文件校验
        sys.copyToLocalFile(false,new Path("/1.data"),new Path("D://harry.txt"),true);


        // 3
        sys.close();

    }

    @Test
    public  void test() throws IOException, InterruptedException {
        Configuration configuration = new Configuration();

        //配置副本数为一个
        configuration.set("dfs.replication","1");

        // 1、new一个对象
        FileSystem fs =  FileSystem.get(
                URI.create("hdfs://master:9000"),configuration,"root");


        // 2、对象做点事情
        // 上传 hadoop fs -put d:/1.xtt /
        // fs.copyFromLocalFile(
        //         new Path("d:/d.txt"),
        //         new Path("/")
        // );

        // 下载    hadoop fs -get /d.txt  d:/
        fs.copyToLocalFile(
                new Path("/d.txt"),
                new Path("d:/")
        );



        // 3、关闭对象
        fs.close();
    }
}

