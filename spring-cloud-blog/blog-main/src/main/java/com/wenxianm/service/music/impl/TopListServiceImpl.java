package com.wenxianm.service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wenxianm.dao.java.music.ITopListDao;
import com.wenxianm.dao.java.music.ITopListDetailsDao;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.constant.NetseaseUrl;
import com.wenxianm.model.dto.music.TopListDTO;
import com.wenxianm.model.enums.music.Origin;
import com.wenxianm.model.enums.music.TopListType;
import com.wenxianm.model.po.music.ArtistPO;
import com.wenxianm.model.po.music.SongInfoPO;
import com.wenxianm.model.po.music.TopListDetailsPO;
import com.wenxianm.model.po.music.TopListPO;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.wenxianm.service.common.impl.BaseServiceImpl;
import com.wenxianm.service.music.IArtistService;
import com.wenxianm.service.music.ISongService;
import com.wenxianm.service.music.ITopListService;
import com.wenxianm.utils.HttpClientHelper;
import com.wenxianm.utils.RandomUtil;

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
public class TopListServiceImpl extends BaseServiceImpl implements ITopListService {
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
            logger.info("排行榜-歌曲关联已存在");
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
