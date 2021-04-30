package com.wenxianm.service.task.impl;

import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.music.TopListDTO;
import com.wenxianm.model.enums.music.TopListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenxianm.service.common.impl.BaseServiceImpl;
import com.wenxianm.service.music.reptile.IReptileSongService;
import com.wenxianm.service.task.IReptileMusicTaskService;

/**
 * 定时任务-爬取音乐
 *
 * @Author: [caiwenxian]
 * @Date: [2018-02-11 10:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class ReptileMusicTaskServiceImpl extends BaseServiceImpl implements IReptileMusicTaskService {

    @Autowired
    IReptileSongService reptileSongService;


    public void reptileAllTopList() {
        try {
            for (TopListType t : TopListType.values()) {
                TopListDTO dto = new TopListDTO(t);
                reptileSongService.reptileTopSongs(dto);
            }
        } catch (SerException e) {
            logger.info("更新排行榜出错：" + e.getMessage());
        }

    }
}
