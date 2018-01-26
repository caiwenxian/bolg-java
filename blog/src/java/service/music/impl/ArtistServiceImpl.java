package service.music.impl;

import dao.java.music.IArtistDao;
import exception.SerException;
import model.po.music.ArtistPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.IArtistService;
import utils.RandomUtil;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ArtistServiceImpl implements IArtistService {

    @Autowired
    IArtistDao artistDao;

    public void addArtist(ArtistPO po) throws SerException {

        if (StringUtils.isBlank(po.getArtistId())) {
            throw new SerException("歌手artistId为空");
        }
        ArtistPO old = artistDao.getArtistByArtistId(po.getArtistId());
        if (old != null) {
            System.out.println("歌手已存在");
            return;
        }
        po.setId(RandomUtil.getUid());
        artistDao.addArtist(po);
    }
}
