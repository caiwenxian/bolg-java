package service.music;

import exception.SerException;
import model.po.music.ArtistPO;

/**
 * 歌手业务
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IArtistService {

    /**
     * 添加歌手
     *
     * @param
     * @return class
     * @version v1
     */
    void addArtist(ArtistPO po) throws SerException;
}
