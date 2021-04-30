package com.wenxianm.dao.java.ins.impl;

import com.wenxianm.dao.java.impl.DaoImpl;
import com.wenxianm.dao.java.ins.InsDao;
import com.wenxianm.model.po.ins.PicturePo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description：
 * @author: caiwx
 * @createDate ： 2020年08月06日 10:48:00
 */

@Repository
public class InsDaoImpl extends DaoImpl<PicturePo, String> implements InsDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplateASS;

    @Override
    public void savePictureUrl(PicturePo picturePo) {
        add(picturePo, false);
    }
}
