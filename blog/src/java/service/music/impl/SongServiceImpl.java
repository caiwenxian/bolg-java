package service.music.impl;

import exception.SerException;
import model.po.music.SongInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import service.music.ISongService;

/**
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SongServiceImpl implements ISongService {

    @Autowired

    public void addSong(SongInfoPO po) throws SerException {

    }

    public String reptileMp3Url(String songId) throws SerException {
        return null;
    }
}
