package com.wenxianm.model.dto.music;

import com.wenxianm.model.dto.common.BaseDTO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-09 09:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MusicDTO extends BaseDTO {

    /**
     * keyword
     */
    private String keyword;

    public MusicDTO() {
    }

    public MusicDTO(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
