package com.wenxianm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description：下载
 * @author: caiwx
 * @createDate ： 2020年08月04日 15:26:00
 */
public class DownloadUtil {

    /**代理IP*/
    private static final String proxyHost="127.0.0.1";
    /**代理端口*/
    private static final String proxyPort="10808";

    public static void download(String urlString, String filename,String savePath) throws Exception {

        //代理
        Proxy proxy = new Proxy(Proxy.Type.SOCKS,new InetSocketAddress(proxyHost, Integer.valueOf(proxyPort)));
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection(proxy);
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
