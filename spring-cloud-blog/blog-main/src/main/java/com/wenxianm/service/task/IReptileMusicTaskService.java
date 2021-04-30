package com.wenxianm.service.task;

import com.wenxianm.exception.SerException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-11 11:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IReptileMusicTaskService {

    /**
     * 更新所有排行榜
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileAllTopList() throws SerException;
}
