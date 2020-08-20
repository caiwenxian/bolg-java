package model.to.ins;

import model.po.common.BasePO;

import java.util.List;

/**
 * @description：图片
 * @author: caiwx
 * @createDate ： 2020年08月05日 17:09:00
 */
public class PictureTo extends BasePO {

    /**
     * 展示图片url
     */
    private String displayUrl;

    private List<String> displayResourceUrl;

    /**
     * 用户名
     */
    private String userName;

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public List<String> getDisplayResourceUrl() {
        return displayResourceUrl;
    }

    public void setDisplayResourceUrl(List<String> displayResourceUrl) {
        this.displayResourceUrl = displayResourceUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}