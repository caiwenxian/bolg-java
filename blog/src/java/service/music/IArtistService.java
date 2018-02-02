package service.music;

import exception.SerException;
import model.po.music.ArtistPO;
import model.po.music.HotArtistPO;

import java.util.List;

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

    /**
     * 添加热门歌手
     *
     * @param
     * @return class
     * @version v1
     */
    void addHotArtist(List<HotArtistPO> pos) throws SerException;

    /**
     * 根据姓名获取歌手列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<ArtistPO> listArtistByName(String name) throws SerException;

}
