package service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.java.music.ITopListDao;
import dao.java.music.ITopListDetailsDao;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.dto.music.TopListDTO;
import model.enums.music.Origin;
import model.enums.music.TopListType;
import model.po.music.ArtistPO;
import model.po.music.SongInfoPO;
import model.po.music.TopListDetailsPO;
import model.po.music.TopListPO;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.music.IArtistService;
import service.music.ISongService;
import service.music.ITopListService;
import utils.HttpClientHelper;
import utils.RandomUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class TopListServiceImpl implements ITopListService {
    //
//    @Autowired
//    IArtistService artistService;
//    @Autowired
//    ISongService songService;
    @Autowired
    ITopListDao topListDao;
    @Autowired
    ITopListDetailsDao topListDetailsDao;

    @Override
    public void addTopList(TopListPO po) {
        TopListPO old = topListDao.getByTopListId(po.getTopListId());
        if (old == null) {
            po.setId(RandomUtil.getUid());
            topListDao.add(po);
            return;
        }
        old.setTrackUpdateTime(po.getTrackUpdateTime());
        topListDao.updateTrackUpDateTime(old);
    }

    @Override
    public void addTopListDetails(TopListDetailsPO po) {
        TopListDetailsPO old = topListDetailsDao.getByTopListIdAndSongId(po.getTopListId(), po.getSongId());
        if (null != old) {
            System.out.println("排行榜-歌曲关联已存在");
            return;
        }
        po.setId(RandomUtil.getUid());
        topListDetailsDao.add(po);
    }

    @Override
    public TopListPO getByTopListId(String topListId) throws SerException {
        TopListPO old = topListDao.getByTopListId(topListId);
        return old;
    }

    @Override
    public void deleteTopListDetails(String topListId) throws SerException {
        topListDetailsDao.delete(topListId);
    }
}
