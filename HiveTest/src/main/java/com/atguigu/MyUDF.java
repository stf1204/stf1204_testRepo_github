package com.atguigu;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.Arrays;

/**
 * 检测输入的参数个数和类型
 * @author stf
 */
public class MyUDF extends GenericUDF {
    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

        // 先确认个数
        if (arguments.length!=1){
            throw new UDFArgumentLengthException("参数只能有一个");
        }

        // 再确认类型
        if ("string".equals(arguments[0])){
            throw new UDFArgumentTypeException(0,"类型必须为string类型");
        }

        // 返回函数输出的元数据类型
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
     * 逻辑处理
     * @param arguments
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        // 先获取string
        Object argument = arguments[0].get();
        String lines = argument.toString();

        // 返回长度
        return lines.length();
    }

    /**
     * 具体没啥用
     * @param children
     * @return
     */
    @Override
    public String getDisplayString(String[] children) {
        return Arrays.toString(children);
    }
}
