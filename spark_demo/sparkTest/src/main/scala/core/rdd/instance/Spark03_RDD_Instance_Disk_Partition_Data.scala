package core.rdd.instance

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description: Spark中创建Rdd方式2
  */
object Spark03_RDD_Instance_Disk_Partition_Data {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

    // TODO 读取文件数据时，RDD模型也存在分区操作
    //      textFile方法可以传递2个参数
    //      第一个参数表示数据文件路径
    //      第二个参数表示最小分区数，参数存在默认值
    //           默认值是：math.min(defaultParallelism, 2)
    // 实际分区的数量要比第二个参数可能要大。
    // 因为Spark读取文件的分区数量的计算不是Spark完成的，是Hadoop完成的

    /*
      // 总共字节数
      totalSize = 20
      // 每个分区放的字节数, 10%
      goalSize = 20 / 3 = 6...2;
      // 总共分区数
      7 / 2 = 3 + 1 = 4
 */

    /*
    0-14: hadoop flume
    15-20 :hadoop

    [0,0+6]   => hadoop flume
    [6,6+6]   => []
    [12,12+6] => hadoop
    [18]      => []
    */

    val value: RDD[String] = sc.textFile("data/word.txt", 3)

    value.saveAsTextFile("output")

    sc.stop()
  }
}
