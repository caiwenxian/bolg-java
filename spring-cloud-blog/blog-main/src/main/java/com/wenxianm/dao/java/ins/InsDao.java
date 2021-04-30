package com.wenxianm.dao.java.ins;

import com.wenxianm.model.po.ins.PicturePo;

/**
 * @description：ins业务
 * @author: caiwx
 * @createDate ： 2020年08月06日 10:46:00
 */
public interface InsDao {

    /**
     * 功能描述：保存图片url
     *
     * @author caiwx
     * @param pictureVo
     * @date 2020/8/6
     */
    void savePictureUrl(PicturePo pictureVo);
}
