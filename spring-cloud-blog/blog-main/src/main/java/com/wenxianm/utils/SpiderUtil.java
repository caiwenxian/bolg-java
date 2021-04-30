package com.wenxianm.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description：爬取工具
 * @author: caiwx
 * @createDate ： 2020年08月04日 11:22:00
 */
public class SpiderUtil {

    private static final Logger logger = LoggerFactory.getLogger(SpiderUtil.class);

    /**代理IP*/
    private static final String proxyHost = "127.0.0.1";
    /**代理端口*/
    private static final String proxyPort = "10808";
    /**cookie*/
    private static final String cookie = "ig_did=E2E440C5-F45F-42E3-8386-C5A355EB23B7; mid=XyiykwALAAEzgbPIunXhhPfgVdzD; csrftoken=acwUSaB1rMoL3psNv08balx673i0p2vw; ds_user_id=39704443979; rur=VLL; sessionid=39704443979%3ATh3VYhHifiYDFj%3A4";


    /**
     *
     * @param urlAll:请求接口
     * @param charset:字符编码
     * @return 返回json结果
     */
    public static String get(String urlAll, String charset) throws InterruptedException {
        //线程睡眠一秒
        Thread.sleep(1000);

        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        // 模拟浏览器
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36 OPR/69.0.3686.66";

        try {
            Proxy proxy = new Proxy(Proxy.Type.SOCKS,new InetSocketAddress(proxyHost, Integer.valueOf(proxyPort)));
            URL url = new URL(urlAll);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestMethod("GET");
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);
            connection.setRequestProperty("User-agent", userAgent);
//            connection.setRequestProperty("sec-fetch-dest", "document");
//            connection.setRequestProperty("sec-fetch-mode", "navigate");
//            connection.setRequestProperty("sec-fetch-site", "same-origin");
//            connection.setRequestProperty("referer", "https://www.instagram.com/p/CDMIQ9xj6kE/?igshid=10s4qdiz1dfvh");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("cookie", cookie);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, charset));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }

            result = sbf.toString();
            if(200!=connection.getResponseCode()){
                throw new Exception("connection failed:"+result);
            }
        } catch (Exception e) {
            logger.error(urlAll,e);
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("IO close exception",e);
                }
            }
        }
        return result;
    }





    public static List<String> getSubUtil(String soap, String rgex){
        List<String> list = new ArrayList<String>();
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }
}
