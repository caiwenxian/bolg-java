package service.task.impl;

import exception.SerException;
import model.dto.music.TopListDTO;
import model.enums.music.TopListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.reptile.IReptileSongService;
import service.task.IReptileMusicTaskService;

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
public class ReptileMusicTaskServiceImpl implements IReptileMusicTaskService{

    @Autowired
    IReptileSongService reptileSongService;


    public void reptileAllTopList() throws SerException{
        try {
            for (TopListType t : TopListType.values()) {
                TopListDTO dto = new TopListDTO(t);
                reptileSongService.reptileSongs(dto);
            }
        } catch (SerException e) {
            System.out.println("更新排行榜出错：" + e.getMessage());
        }

    }
}
