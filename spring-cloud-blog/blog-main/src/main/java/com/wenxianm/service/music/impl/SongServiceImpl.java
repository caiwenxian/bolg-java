package com.wenxianm.service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wenxianm.dao.java.music.ISongDao;
import com.wenxianm.exception.ErrorMessage;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.constant.Common;
import com.wenxianm.model.constant.NetseaseUrl;
import com.wenxianm.model.dto.common.BaseDTO;
import com.wenxianm.model.enums.music.TopListType;
import com.wenxianm.model.po.common.Page;
import com.wenxianm.model.po.common.PagePO;
import com.wenxianm.model.po.common.PaginationPO;
import com.wenxianm.model.po.music.RecommendSongListPO;
import com.wenxianm.model.po.music.SongInfoPO;
import com.wenxianm.model.po.music.SongListDetailsPO;
import com.wenxianm.model.po.music.SongListPO;
import com.wenxianm.model.vo.music.TopListVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.wenxianm.service.common.impl.BaseServiceImpl;
import com.wenxianm.service.music.ISongService;
import com.wenxianm.service.music.reptile.IReptileSongService;
import com.wenxianm.utils.HttpClientHelper;
import com.wenxianm.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class SongServiceImpl extends BaseServiceImpl implements ISongService {


    @Autowired
    ISongDao songDao;
    @Autowired
    IReptileSongService reptileSongService;

    @Override
    public void addSong(SongInfoPO po) throws SerException {
        synchronized (this) {
            SongInfoPO old = songDao.getBySongId(po.getSongId());
            if (null != old) {
//            throw new SerException(ErrorMessage.MUSIC_IS_EXIST);
                logger.info(ErrorMessage.MUSIC_IS_EXIST);
                if (StringUtils.isNotBlank(old.getMp3Url())) {
                    return;
                }
                reptileSongService.reptileMp3Url(old.getSongId());
                return;
            }
            po.setId(RandomUtil.getUid());

            songDao.add(po);
        }

        //爬取mp3url
        reptileSongService.reptileMp3Url(po.getSongId());
    }

    @Override
    public SongInfoPO getBySongId(String songId) throws SerException {
        SongInfoPO po = songDao.getBySongId(songId);
        return po;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = SerException.class)
    public void update(SongInfoPO po) throws SerException {
        songDao.update(po);
    }

    @Override
    public void addMp3Url(String songId, String mp3Url) throws SerException {
        SongInfoPO po = this.getBySongId(songId);
        if (null == po) {
//            throw new SerException(ErrorMessage.NOT_FOUND);
            logger.info(ErrorMessage.MUSIC_NOT_EXIST);
            return;
        }
        po.setMp3Url(mp3Url);
        this.update(po);
    }

    @Override
    public List<SongInfoPO> listSongByName(String name) throws SerException {
        List<SongInfoPO> list = songDao.listSongByName(name);
        if (list.size() == 0) { //若本地数据库找不到，则进行爬取
            reptileSongService.asynReptile(1, new String[]{name});
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list = songDao.listSongByName(name);
        }
        return list;
    }

    @Override
    public PagePO<SongInfoPO> listSongByNameByPage(String name, int page, int limit) throws SerException {
        int totalSize = songDao.countSongByName(name);
        page = page == 0 ? 1 : page;
        int startRow = (page - 1) * limit;
        int endRow = page * limit;
        List<SongInfoPO> list = songDao.listSongByNameByPage(name, startRow, endRow);
        if (list.size() == 0) { //若本地数据库找不到，则进行爬取
            reptileSongService.asynReptile(1, new String[]{name});
            try {
                Thread.currentThread().sleep(Common.threadSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list = songDao.listSongByName(name);
        }
        PagePO<SongInfoPO> pagePO = new PagePO<SongInfoPO>(totalSize, list);
        return pagePO;
    }

    @Override
    public List<SongInfoPO> listAllTopList() throws SerException {
//        List ids = new ArrayList();
//        for (TopListType topListType : TopListType.values()) {
//            ids.add(topListType.getId());
//        }
//        List<SongInfoPO> list = songDao.listTopList(ids);
        return null;
    }

    @Override
    public TopListVO listTopListByTopListId(String topListId) throws SerException {
        List ids = new ArrayList();
        ids.add(topListId);
        List<SongInfoPO> list = songDao.listTopList(ids);
        TopListVO topListVO = new TopListVO(TopListType.getName(topListId), topListId, list);
        return topListVO;
    }

    @Override
    public PagePO listHotSong(BaseDTO dto) throws SerException {
        String id = TopListType.B.getId();
        int count = songDao.countTopList(id);
        int page = dto.getPage();
        page = page < 1 ? 1 : page;
        int startRow = (page - 1) * dto.getLimit();
        int endRow = page * dto.getLimit();
        List<SongInfoPO> list = songDao.listTopListByPage(id, startRow, endRow);
        PagePO pagePO = new PagePO(count, list);

        return pagePO;
    }

    @Override
    public void addSongList(SongListPO po) throws SerException {
        try {
            SongListPO old = songDao.getSongListBySongListId(po.getSongListId());
            if (old != null) {
                return;
            }
            po.setId(RandomUtil.getUid());
            songDao.addSongList(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSongList(String songListId, String trackUpdateTime) throws SerException {
        songDao.updateSongList(songListId, trackUpdateTime);
    }

    @Override
    public void addRecommendSongList(RecommendSongListPO po) throws SerException {
        RecommendSongListPO old = songDao.getRecommendSongListBySongListId(po.getSongListId());
        if (old != null) {
            return;
        }
        po.setId(RandomUtil.getUid());
        songDao.addRecommendSongList(po);
    }

    @Override
    public void addSongListDetails(SongListDetailsPO po) throws SerException {
        SongListDetailsPO old = songDao.getSongListDetails(po.getSongListId(), po.getSongId());
        if (old != null) {
            return;
        }
        po.setId(RandomUtil.getUid());
        songDao.addSongListDetails(po);
    }
}
