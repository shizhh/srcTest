/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettest;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author zhenhui
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
            String host = "192.168.122.111";
            Socket s = new Socket("192.168.122.111", 8922);
            InputStream inStream = s.getInputStream();
            Scanner in = new Scanner(inStream);
            
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (IOException ex) {
            System.out.println("IOException lalala");
        }
    }
}
