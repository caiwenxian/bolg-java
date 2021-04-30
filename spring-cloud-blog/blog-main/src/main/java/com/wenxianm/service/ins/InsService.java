package com.wenxianm.service.ins;

import com.wenxianm.model.vo.ins.PictureVo;

import java.util.List;

/**
 * @description：ins相关业务
 * @author: caiwx
 * @createDate ： 2020年08月05日 09:15:00
 */
public interface InsService {

    /**
     * 功能描述：根据图片链接获取图片的下载地址
     *
     * @author caiwx
     * @param link
     * @return List<PictureVo>
     * @date 2020/8/5
     */
    List<PictureVo> listPictureUrlByLink(String link) throws Exception;

    /**
     * 功能描述：根据用户名获取该用户所有图片的下载地址
     *
     * @author caiwx
     * @param userName
     * @return List<PictureVo>
     * @date 2020/8/5
     */
    List<PictureVo> listPictureUrlByUser(String userName) throws Exception;

    /**
     * 功能描述：下载图片
     *
     * @author caiwx
     * @param pictureVo
     * @date 2020/8/6
     */
    void downPicture(PictureVo pictureVo);

    /**
     * 功能描述：保存图片url
     *
     * @author caiwx
     * @param pictureVo
     * @date 2020/8/6
     */
    void savePictureUrl(PictureVo pictureVo);


}
