package com.hearien.demo.concurrent.communication.demo3;

import java.io.*;

/**
 * @author 王海洋
 * @className: Test
 * @description:
 * @create 2020/2/8 17:37
 **/
public class Test {

    public static void main(String[] args) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();

        pipedOutputStream.connect(pipedInputStream);

        new Thread(new Reader(pipedInputStream)).start();

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(System.in));
            pipedOutputStream.write(reader.readLine().getBytes());
        }finally {
            pipedOutputStream.close();
            if(reader!=null){
                reader.close();
            }
        }
    }
}
