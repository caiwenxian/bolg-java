package service.music.impl;

import dao.java.music.IArtistDao;
import exception.SerException;
import model.po.music.ArtistPO;
import org.springframework.beans.factory.annotation.Autowired;
import service.music.IArtistService;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ArtistServiceIMpl implements IArtistService {

    @Autowired
    IArtistDao iArtistDao;

    public void addArtist(ArtistPO po) throws SerException {
        iArtistDao.addArtist(po);
    }
}
