package model.dto.music;

import model.dto.common.BaseDTO;
import model.enums.music.TopListType;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-18 17:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TopListDTO extends BaseDTO {

    /**
     * 排行榜类型
     */
    private TopListType topListType;

    public TopListType getTopListType() {
        return topListType;
    }

    public void setTopListType(TopListType topListType) {
        this.topListType = topListType;
    }

    public TopListDTO() {

    }

    public TopListDTO(TopListType topListType) {
        this.topListType = topListType;
    }

    @Override
    public String toString() {
        return "TopListDTO{" +
                "topListType=" + topListType +
                '}';
    }
}
