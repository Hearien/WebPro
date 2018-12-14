import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Calendar;

/**
 * @ClassName Test
 * @Author WangHaiyang
 * @Date 2018/12/7 15:38
 **/
public class Test {

    public static void main(String[] args) {
        int year = 2018;
        int month = 12;
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "年" + month + "月有" + dayOfMonth + "天");
    }

    public static void pbTest(){
        //模拟将对象转成byte[]，方便传输
        PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
        builder.setId(1);
        builder.setName("ant");
        builder.setEmail("ghb@soecode.com");
        PersonEntity.Person person = builder.build();
        System.out.println("before :"+ person.toString());

        System.out.println("===========Person Byte==========");
        for(byte b : person.toByteArray()){
            System.out.print(b);
        }
        System.out.println();
        System.out.println(person.toByteString());
        System.out.println("================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =person.toByteArray();
        PersonEntity.Person p2 = null;
        try {
            p2 = PersonEntity.Person.parseFrom(byteArray);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        System.out.println("after :" +p2.toString());
    }
}
