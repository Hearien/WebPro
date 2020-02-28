package com.hearien.demo.concurrent.communication.demo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author 王海洋
 * @className: Reader
 * @description:
 * @create 2020/2/8 17:37
 **/
public class Reader implements Runnable {

    private PipedInputStream pipedInputStream;

    public Reader(PipedInputStream pipedInputStream){
        this.pipedInputStream=pipedInputStream;
    }

    @Override
    public void run() {
        if(pipedInputStream!=null){
            String collect = new BufferedReader(new InputStreamReader(pipedInputStream)).lines().collect(Collectors.joining());
            System.out.println(collect);

        }
        try {
            pipedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
