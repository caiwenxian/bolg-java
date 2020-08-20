package com.ins;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import utils.DownloadUtil;
import utils.SpiderUtil;

import java.util.List;

/**
 * @description：
 * @author: caiwx
 * @createDate ： 2020年08月06日 15:01:00
 */
public class Test {

    @org.junit.Test
    public void test() throws InterruptedException {
        String r = SpiderUtil.get("https://www.instagram.com/guan._.xiaotong/","utf-8");

        List<String> lists = SpiderUtil.getSubUtil(r,"window._sharedData = (.*?);</script>");
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

    @org.junit.Test
    public void test1() throws InterruptedException {
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
            download(display_url);

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




    @org.junit.Test
    public void test2() throws InterruptedException {
        String r = SpiderUtil.get("https://www.instagram.com/p/CDOTHZjpNKo/?igshid=nia3bedct0sc","utf-8");
        List<String> lists = SpiderUtil.getSubUtil(r,"window.__additionalDataLoaded(.*?);</script>");
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

    @org.junit.Test
    public void test2_1() throws InterruptedException {
        String url = "https://www.instagram.com/p/CDOTHZjpNKo/?igshid=nia3bedct0sc";
        url = url + "&__a=1";
        String r = SpiderUtil.get(url,"utf-8");
        JSONObject graphql = JSONObject.parseObject(r).getJSONObject("graphql");
        System.out.println(graphql);
        JSONObject shortCodeMedia = graphql.getJSONObject("shortcode_media");

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

    @org.junit.Test
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

    public void download(String displayUrl) {
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
}
