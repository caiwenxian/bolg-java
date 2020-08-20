package service.ins.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.java.ins.InsDao;
import model.constant.MessageType;
import model.po.ins.PicturePo;
import model.po.mq.MessagePO;
import model.vo.ins.PictureVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.common.impl.BaseServiceImpl;
import service.ins.InsService;
import service.mq.rabbitmq.RabbitProducer;
import utils.DownloadUtil;
import utils.SpiderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：爬取并下载图片
 * @author: caiwx
 * @createDate ： 2020年08月05日 17:13:00
 */

@Service
public class InsServiceImpl extends BaseServiceImpl implements InsService {

    static String queryHash = "7437567ae0de0773fd96545592359a6b";
    static int amount = 12;
    static String path = "d://ins";

    @Autowired
    RabbitProducer rabbitProducer;
    @Autowired
    InsDao insDao;

    @Override
    public List<PictureVo> listPictureUrlByLink(String link) throws Exception {
        List<PictureVo> list = new ArrayList<>();
        PictureVo pictureVo;
        List<String> displayResourceUrl;
        //加上__a=1参数可以直接获取json数据
        link = link + "&__a=1";
        String s = SpiderUtil.get(link, "utf-8");
        logger.info("json数据: " + s);

        JSONObject graphql = JSONObject.parseObject(s).getJSONObject("graphql");
        JSONObject shortCodeMedia = graphql.getJSONObject("shortcode_media");

        JSONObject edgeSidecarToChildren = shortCodeMedia.getJSONObject("edge_sidecar_to_children");
        //是否存在多张图片
        if (edgeSidecarToChildren == null) {
            pictureVo = new PictureVo();
            String displayUrl = shortCodeMedia.getString("display_url");
            pictureVo.setDisplayUrl(displayUrl);
            logger.info("displayUrl: " + displayUrl);

            JSONArray displayResource = shortCodeMedia.getJSONArray("display_resources");
            int i = 1;
            displayResourceUrl = new ArrayList<>();
            for (Object o : displayResource) {
                String displayResourceUrlSrc = ((JSONObject)o).getString("src");
                displayResourceUrl.add(displayResourceUrlSrc);
                logger.info("displayResourceUrl_" + i +": " + displayResourceUrl);
                i ++;
            }
            pictureVo.setDisplayResourceUrl(displayResourceUrl);
            list.add(pictureVo);
        } else {
            JSONArray edges = edgeSidecarToChildren.getJSONArray("edges");
            for (Object object : edges) {
                pictureVo = new PictureVo();
                JSONObject node = ((JSONObject)object).getJSONObject("node");
                String displayUrl = node.getString("display_url");
                pictureVo.setDisplayUrl(displayUrl);
                logger.info("displayUrl: " + displayUrl);

                JSONArray displayResource = shortCodeMedia.getJSONArray("display_resources");
                int i = 1;
                displayResourceUrl = new ArrayList<>();
                for (Object o : displayResource) {
                    String displayResourceUrlSrc = ((JSONObject)o).getString("src");
                    displayResourceUrl.add(displayResourceUrlSrc);
                    logger.info("displayResourceUrl_" + i +": " + displayResourceUrlSrc);
                    i ++;
                }
                pictureVo.setDisplayResourceUrl(displayResourceUrl);
                list.add(pictureVo);
            }
        }

        return list;
    }

    @Override
    public List<PictureVo> listPictureUrlByUser(String userName) throws Exception {

        String url = "https://www.instagram.com/" + userName + "/?__a=1";
        String s = SpiderUtil.get(url,"utf-8");

        JSONObject shareData = JSONObject.parseObject(s);
        JSONObject user = shareData.getJSONObject("graphql").getJSONObject("user");
        String id = user.getString("id");
        logger.info(userName + "的id为：" + id);

        if (StringUtils.isBlank(id)) {
            return null;
        }

        List<PictureVo> list = new ArrayList<>();
        listPictureUrlById(id, "", list);

        //发送到消息队列
        if (list != null && list.size() > 0) {
            for (PictureVo pictureVo : list) {
                MessagePO messagePO = new MessagePO();
                messagePO.setData(JSONObject.toJSON(pictureVo).toString());
                messagePO.setType(MessageType.DOWNLOAD_PICTURE);
                rabbitProducer.sendMessage(messagePO);
            }
        }

        return list;
    }

    /**
     * 功能描述：分页获取图片
     *
     * @author caiwx
     * @param id
     * @return after
     * @date 2020/8/6
     */
    private List<PictureVo> listPictureUrlById (String id, String after, List<PictureVo> list) throws Exception {
        PictureVo pictureVo;
        List<String> displayResourceUrl;

        StringBuffer url = new StringBuffer();
        url.append("https://www.instagram.com/graphql/query/?");
        url.append("query_hash=" + queryHash + "&");
        url.append("variables={\"id\":\""+ id +"\",\"first\":\""+ amount +"\",\"after\":\""+ after +"\"}");
        logger.info("url: " + url.toString());
        String s = SpiderUtil.get(url.toString(),"utf-8");
        JSONObject data = JSONObject.parseObject(s).getJSONObject("data");
        JSONObject user = data.getJSONObject("user");
        JSONObject edgeOwnerToTimelineMedia = user.getJSONObject("edge_owner_to_timeline_media");
        int count = edgeOwnerToTimelineMedia.getInteger("count");
        JSONObject pageInfo = edgeOwnerToTimelineMedia.getJSONObject("page_info");
        boolean hasNextPage = pageInfo.getBoolean("has_next_page");
        String endCursor = pageInfo.getString("end_cursor");
        logger.info("count: " + count + ", has_next_page: " + hasNextPage + ", end_cursor: " + endCursor);

        JSONArray edges = edgeOwnerToTimelineMedia.getJSONArray("edges");
        int j = 1;
        for (Object object : edges) {
            pictureVo = new PictureVo();
            JSONObject node = ((JSONObject)object).getJSONObject("node");
            String displayUrl = node.getString("display_url");
            pictureVo.setDisplayUrl(displayUrl);
            logger.info("display_url_"+ j + ": " + displayUrl);

            JSONArray displayResources = node.getJSONArray("display_resources");
            int i = 1;
            displayResourceUrl = new ArrayList<>();
            for (Object o : displayResources) {
                String displayUrlResource = ((JSONObject)o).getString("src");
                displayResourceUrl.add(displayUrlResource);
                logger.info("display_url_resource_" + j + "_" + i + ": " + displayUrlResource);
                i ++;
            }
            j ++;
            pictureVo.setDisplayResourceUrl(displayResourceUrl);
            list.add(pictureVo);
        }

        if (!hasNextPage) {
            return list;
        } else {
            after = endCursor;
            return listPictureUrlById(id, after, list);
        }
    }

    @Override
    public void downPicture(PictureVo pictureVo) {
        if (StringUtils.isBlank(pictureVo.getDisplayUrl())) {
            logger.error("下载图片失败，url为空");
            return;
        }
        String[] urlArr = pictureVo.getDisplayUrl().split("\\?");
        String[] urlArr2 = urlArr[0].split("/");
        String name = urlArr2[urlArr2.length- 1];
        try {
            DownloadUtil.download(pictureVo.getDisplayUrl(), name, path);
            logger.info("下载完成：" + name);
        } catch (Exception e) {
            logger.info("下载失败：" + name);
            e.printStackTrace();
        }
    }

    @Override
    public void savePictureUrl(PictureVo pictureVo) {
        PicturePo picturePo = new PicturePo();
        BeanUtils.copyProperties(pictureVo, picturePo);
        if (pictureVo.getDisplayResourceUrl() != null) {
            StringBuilder displayResourceUrl = new StringBuilder();
            for (String resource : pictureVo.getDisplayResourceUrl()) {
                displayResourceUrl.append(resource).append(";");
            }
            picturePo.setDisplayResourceUrl(displayResourceUrl.toString());
            insDao.savePictureUrl(picturePo);
        }
    }
}
