package Serialize;

import java.io.*;

/**
 * @author stf
 * 对象序列化与反序列化
 */
public class SerializedTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serialized();
        abSerialized();
    }
    
    public static void serialized() throws IOException {
        //创建字节输出流
        FileOutputStream fis = new FileOutputStream("D://Person.txt");
        //创建对象输出流，参数为字节输出流
        ObjectOutputStream oop = new ObjectOutputStream(fis);
        //创建需要序列化的对象
        Person person = new Person(22,"小明","男");
        //序列化对象
        oop.writeObject(person);
        oop.close();
    }
    
    public static void abSerialized() throws IOException, ClassNotFoundException {
        //创建字节输入流对象
        FileInputStream fio = new FileInputStream("D:/Person.txt");
        //创建对象输入流对象，参数为字节输入流对象
        ObjectInputStream ois = new ObjectInputStream(fio);
        //获取反序列化对象
        Object o = ois.readObject();
        System.out.println("o = " + o);
        ois.close();
    }
}
