package com.hearien.six.server;

import com.hearien.six.DataInfo;
import com.hearien.six.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName PbServerHandler
 * @Author WangHaiyang
 * @Date 2018/12/13 15:38
 **/
public class PbServerHandler extends SimpleChannelInboundHandler<DataInfo.MyMsg> {

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
        /*System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());*/

        DataInfo.MyMsg.DataType dataType = msg.getDataType();
        if(dataType == DataInfo.MyMsg.DataType.StuType){
            DataInfo.Student student = msg.getStudent();
            System.out.println(student.getName()+"-"+student.getAge()+"-"+student.getAddress());
        } else if(dataType == DataInfo.MyMsg.DataType.TeaType){
            DataInfo.Teacher teacher = msg.getTeacher();
            System.out.println(teacher.getName()+"-"+teacher.getGrade());
        } else{
            DataInfo.Leader leader = msg.getLeader();
            System.out.println(leader.getName()+"-"+leader.getSchool());
        }
    }
}
