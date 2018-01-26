package service.music.impl;

import dao.java.music.IArtistDao;
import exception.SerException;
import model.po.music.ArtistPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.IArtistService;

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
        artistDao.addArtist(po);
    }
}
