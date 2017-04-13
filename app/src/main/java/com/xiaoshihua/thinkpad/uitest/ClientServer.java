package com.xiaoshihua.thinkpad.uitest;

import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

/**
 * Created by ThinkPad on 2016/11/16.
 */

public class ClientServer {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("" ,8080);
        socket.setSoTimeout(3000);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintStream printStream = new PrintStream(socket.getOutputStream());

        String line  = null;

        boolean flag = true;
        while (flag){
            System.out.print("输入信息 ： ");
            line = bufferedReader.readLine();

            printStream.println(line);
            if (line.equals("byte")) {
                flag = false;
            } else {
                String receiver = bufferedReader1.readLine();
                System.out.print(receiver);
            }
        }


    }

}
