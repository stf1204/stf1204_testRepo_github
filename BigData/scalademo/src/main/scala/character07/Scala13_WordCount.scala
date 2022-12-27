package character07

/**
  * @ClassName: Scala13_WordCount
  * @Author: stf
  * @Date: 2022/11/20 21:29
  * @Description: 使用集合的处理方案 统计单词出现次数 并且获取前三
  */
object Scala13_WordCount {

  def main(args: Array[String]): Unit = {

    val list8: List[String] = List("Java Hbase Hive Flink Flume Mysql",
      "Linux Hadoop Hadoop Flink Flume",
      "Hive Flume Hive Hbase Spark",
      "Kafka Scala Spark Flink")
    // 1 扁平化
    val strings: List[String] = list8.flatMap(da => da.split(" "))
    println(strings)
    // List(Java, Hbase, Hive, Flink, Flume, Mysql, Linux, Hadoop, Hadoop, Flink, Flume, Hive, Flume, Hive, Hbase, Spark, Kafka, Scala, Spark, Flink)

    // 2 分组
    val stringToStrings: Map[String, List[String]] = strings.groupBy(da => da)
    println(stringToStrings)
    //Map(Hbase -> List(Hbase, Hbase),
    // Hive -> List(Hive, Hive, Hive),
    // Scala -> List(Scala),
    // Flume -> List(Flume, Flume, Flume),
    // Linux -> List(Linux),
    // Kafka -> List(Kafka),
    // Spark -> List(Spark, Spark),
    // Mysql -> List(Mysql),
    // Java -> List(Java),
    // Flink -> List(Flink, Flink, Flink),
    // Hadoop -> List(Hadoop, Hadoop))

    // 3 tolist
    val toList: List[(String, List[String])] = stringToStrings.toList
    println(toList)

    // 4 统计
    val list: List[(String, Int)] = toList.map(mp => (mp._1, mp._2.length))
    println(list)
    // List((Hbase,2), (Hive,3), (Scala,1), (Flume,3), (Linux,1), (Kafka,1), (Spark,2), (Mysql,1), (Java,1), (Flink,3), (Hadoop,2))

    // 5 排序
    val unit: List[(String, Int)] = list.sortWith((on, to) => on._2 > to._2)
    println(unit)
    //    List((Hive,3), (Flume,3), (Flink,3), (Hbase,2), (Spark,2), (Hadoop,2), (Scala,1), (Linux,1), (Kafka,1), (Mysql,1), (Java,1))

    // 6 取top3
    val tuples: List[(String, Int)] = unit.take(3)
    println(tuples)
    // List((Hive,3), (Flume,3), (Flink,3))


    // 迭代式开发
    val tuples1: List[(String, Int)] = list8.flatMap(_.split(" "))
      .groupBy(data => data)
      .mapValues(_.length)
      .toList
      .sortWith(_._2 > _._2)
      .take(3)
    println(tuples1)
  }

}


// test
object test extends App {
  private val strings: List[String] = List("flume,kafka,zookeeper,hbase", "flink,spark,hadoop,zookeeper,hbase", "spark,flume,zookeeper,hbase,flume")

  private val strings1: List[String] = strings.flatMap(data => data.split(","))
  println(strings1)

  private val stringToStrings: Map[String, List[String]] = strings1.groupBy(data => data)

  private val stringToInt: Map[String, Int] = stringToStrings.map(data => (data._1, data._2.size))

  private val tuples: List[(String, Int)] = stringToInt.toList.sortWith((first, second) => first._2 > second._2)

  private val list: List[(String, Int)] = tuples.take(3)

  println(list)
  // List((zookeeper,3), (flume,3), (hbase,3))

}
