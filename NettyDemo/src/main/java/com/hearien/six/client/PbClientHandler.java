package com.hearien.six.client;

import com.hearien.six.DataInfo;
import com.hearien.six.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @ClassName PbClientHandler
 * @Author WangHaiyang
 * @Date 2018/12/13 15:47
 **/
public class PbClientHandler extends SimpleChannelInboundHandler<DataInfo.MyMsg> {
    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyMsg msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*Person.Student student = Person.Student.newBuilder().setName("张三").setAge(12).setAddress("重庆").build();
        ctx.writeAndFlush(student);*/
        int random = new Random().nextInt(3);
        DataInfo.MyMsg myMsg = null;
        if(random==0){
            myMsg = DataInfo.MyMsg.newBuilder().setDataType(DataInfo.MyMsg.DataType.StuType)
                    .setStudent(DataInfo.Student.newBuilder().setName("张三").setAge(12).setAddress("重庆").build())
                    .build();
        } else if(random==1){
            myMsg = DataInfo.MyMsg.newBuilder().setDataType(DataInfo.MyMsg.DataType.TeaType)
                    .setTeacher(DataInfo.Teacher.newBuilder().setName("李老师").setGrade("九年级").build())
                    .build();
        } else{
            myMsg = DataInfo.MyMsg.newBuilder().setDataType(DataInfo.MyMsg.DataType.LeaType)
                    .setLeader(DataInfo.Leader.newBuilder().setName("王校长").setSchool("重庆一中").build())
                    .build();
        }
        ctx.channel().writeAndFlush(myMsg);
    }
}
