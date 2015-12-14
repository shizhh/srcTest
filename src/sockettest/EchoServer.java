/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettest;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author zhenhui
 */
public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8922);
            Socket incoming = server.accept();
            InputStream inStream = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream();
            
            Scanner in = new Scanner(inStream);
            
            PrintWriter out = new PrintWriter(outStream, true);
            out.println("Hello! Enter BYE to EXIT");
            
            // echo client input
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE"))
                    done = true;
            }
        } catch (IOException e) {
            System.out.println("IOException hahaha");
        }
    }
}
