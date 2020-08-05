package utils;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static final String proxyHost="127.0.0.1";
    /**代理端口*/
    private static final String proxyPort="10808";

    /**
     *
     * @param urlAll:请求接口
     * @param charset:字符编码
     * @return 返回json结果
     */
    public static String get(String urlAll, String charset) {
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
            connection.setRequestProperty("cookie", "ig_did=E2E440C5-F45F-42E3-8386-C5A355EB23B7; rur=VLL; mid=XyiykwALAAEzgbPIunXhhPfgVdzD; csrftoken=acwUSaB1rMoL3psNv08balx673i0p2vw; ds_user_id=39704443979; sessionid=39704443979%3A4YLglkfalRFfJ1%3A21; urlgen=\"{\\\"40.83.114.181\\\": 8075}:1k2nom:gQFVKXjuGc7579VtA92ff3VMDxs\"");
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

    @Test
    public void test() {
        String r = SpiderUtil.get("https://www.instagram.com/guan._.xiaotong/","utf-8");

        List<String> lists = getSubUtil(r,"window._sharedData = (.*?);</script>");
        JSONObject shareData = JSONObject.parseObject(lists.get(0));
        JSONObject entryData = JSONObject.parseObject(shareData.getString("entry_data"));
        JSONArray profilePage = entryData.getJSONArray("ProfilePage");
        JSONObject p0 = profilePage.getJSONObject(0);
        String user =JSONObject.parseObject(p0.getString("graphql")).getString("user");
        String id =JSONObject.parseObject(user).getString("id");
        String userName =JSONObject.parseObject(user).getString("username");

        System.out.println(shareData);
        System.out.println("id: " + id);
        System.out.println("userName: " + userName);
    }

    @Test
    public void test1() {
        String queryHash = "7437567ae0de0773fd96545592359a6b";
        String userId = "37048332785";
        int amount = 12;
        String after = "";

        /*Map<String, Object> variables = new HashMap<>();
        variables.put("id", userId);
        variables.put("first", amount);
        variables.put("after", after);*/

        /*String variables = "{\"id\":\""+ userId +"\", \"first\":\""+ amount +"\", \"after\":\""+ after +"\"}";*/

        StringBuffer url = new StringBuffer();
        url.append("https://www.instagram.com/graphql/query/?");
        url.append("query_hash=" + queryHash + "&");
        url.append("variables={\"id\":\""+ userId +"\",\"first\":\""+ amount +"\",\""+ after +"\":\"\"}");

        String r = SpiderUtil.get(url.toString(),"utf-8");
        System.out.println(r);

        JSONObject data = JSONObject.parseObject(r).getJSONObject("data");
        JSONObject user = data.getJSONObject("user");
        JSONObject edge_owner_to_timeline_media = user.getJSONObject("edge_owner_to_timeline_media");
        int count = edge_owner_to_timeline_media.getInteger("count");
        JSONObject page_info = edge_owner_to_timeline_media.getJSONObject("page_info");
        boolean has_next_page = page_info.getBoolean("has_next_page");
        String end_cursor = page_info.getString("end_cursor");
        System.out.println("count: " + count + ", has_next_page: " + has_next_page + ", end_cursor: " + end_cursor);

        JSONArray edges = edge_owner_to_timeline_media.getJSONArray("edges");
        int j = 1;
        for (Object object : edges) {
            JSONObject node = ((JSONObject)object).getJSONObject("node");
            String display_url = node.getString("display_url");
            System.out.println("display_url_"+ j + ": " + display_url);
            JSONArray display_resources = node.getJSONArray("display_resources");
            int i = 1;
            for (Object o : display_resources) {
                String display_url_resource = ((JSONObject)o).getString("src");
                System.out.println("display_url_resource_" + j + "_" + i + ": " + display_url_resource);
                i ++;
            }
            j ++;
        }


    }




    @Test
    public void test2() {
        String r = SpiderUtil.get("https://www.instagram.com/p/CDOTHZjpNKo/?igshid=nia3bedct0sc","utf-8");
        List<String> lists = getSubUtil(r,"window.__additionalDataLoaded(.*?);</script>");
        String[] graphqlArr = lists.get(0).split("graphql\":");
        String graphql = graphqlArr[1].substring(0, graphqlArr[1].length() - 2);
        JSONObject shareData = JSONObject.parseObject(graphql);
        System.out.println(shareData);
        JSONObject shortCodeMedia = shareData.getJSONObject("shortcode_media");

        String displayUrl = shortCodeMedia.getString("display_url");
        System.out.println("displayUrl: " + displayUrl);

        JSONArray displayResource = shortCodeMedia.getJSONArray("display_resources");
        int i = 1;
        for (Object o : displayResource) {
            String displayResourceUrl = ((JSONObject)o).getString("src");
            System.out.println("displayResourceUrl_" + i +": " + displayResourceUrl);
            i ++;
        }

        JSONArray edges = shortCodeMedia.getJSONObject("edge_sidecar_to_children").getJSONArray("edges");
        for (Object object : edges) {
            JSONObject node = ((JSONObject)object).getJSONObject("node");
            String display_url = node.getString("display_url");
            System.out.println("displayUrl: " + display_url);

            displayResource = shortCodeMedia.getJSONArray("display_resources");
            i = 1;
            for (Object o : displayResource) {
                String displayResourceUrl = ((JSONObject)o).getString("src");
                System.out.println("displayResourceUrl_" + i +": " + displayResourceUrl);
                i ++;
            }
        }

        String[] urlArr = displayUrl.split("\\?");
        String[] urlArr2 = urlArr[0].split("/");
        String name = urlArr2[urlArr2.length- 1];
        try {
            DownloadUtil.download(displayUrl, name, "d://ins");
            System.out.println("下载完成：" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        String displayUrl = "https://scontent-nrt1-1.cdninstagram.com/v/t51.2885-15/e35/116236803_311966293260102_1553769797290456944_n.jpg?_nc_ht=scontent-nrt1-1.cdninstagram.com&_nc_cat=106&_nc_ohc=-MkVcBmXPlUAX8wWO8k&oh=f5cd36eb7446388e76d09a2725c375e9&oe=5F515121";
        String[] urlArr = displayUrl.split("\\?");
        String[] urlArr2 = urlArr[0].split("/");
        String name = urlArr2[urlArr2.length- 1];
        try {
            DownloadUtil.download(displayUrl, name, "d://ins");
            System.out.println("下载完成：" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private List<String> getSubUtil(String soap, String rgex){
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
