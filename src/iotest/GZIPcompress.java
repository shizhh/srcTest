/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotest;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author zhenhui
 */
public class GZIPcompress {
    public static void gzip(String inFilePath, String outFilePath) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilePath));
            BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(outFilePath)));
            System.out.println("Writing file...");
            int c;
            while ( (c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
            System.out.println("Reading file...");
            BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(outFilePath))));
            String s;
            while ((s = in2.readLine()) != null) {
                System.out.println(s);
            }
            in2.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
   
    
    public static void main(String[] args) {
        String inFilePath = "E:\\1.txt";
        String outFilePath = "E:\\2.zip";
        gzip(inFilePath, outFilePath);
    }
}
