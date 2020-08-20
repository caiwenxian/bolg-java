package dao.java.ins.impl;

import dao.java.impl.DaoImpl;
import dao.java.ins.InsDao;
import model.po.ins.PicturePo;
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
