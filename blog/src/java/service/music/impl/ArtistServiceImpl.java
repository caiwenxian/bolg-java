package service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.java.music.IArtistDao;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.po.music.ArtistPO;
import model.po.music.HotArtistPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.common.impl.BaseServiceImpl;
import service.music.IArtistService;
import service.music.reptile.IReptileArtistService;
import utils.HttpClientHelper;
import utils.RandomUtil;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ArtistServiceImpl extends BaseServiceImpl implements IArtistService {

    @Autowired
    IArtistDao artistDao;
    @Autowired
    IReptileArtistService reptileArtistService;

    public void addArtist(ArtistPO po) throws SerException {
        synchronized (ArtistServiceImpl.class) {
            if (StringUtils.isBlank(po.getArtistId())) {
                throw new SerException("歌手artistId为空");
            }
            ArtistPO old = artistDao.getArtistByArtistId(po.getArtistId());
            if (old != null) {
                logger.info("歌手已存在");
                return;
            }
            po.setId(RandomUtil.getUid());
            artistDao.addArtist(po);
        }

    }

    @Override
    public void addHotArtist(List<HotArtistPO> pos) throws SerException {
        if (pos == null || pos.size() == 0) {
            return;
        }
        for (HotArtistPO po : pos) {
            po.setId(RandomUtil.getUid());
        }
        artistDao.addHotArtist(pos);
    }

    @Override
    public List<ArtistPO> listArtistByName(String name) throws SerException {
        List<ArtistPO> list = artistDao.listArtistByName(name);
        if (list.size() == 0) { //若本地数据库找不到，则进行爬取
            reptileArtistService.asynReptile(1, new String[]{name});
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list = artistDao.listArtistByName(name);
        }
        return list;
    }
}
