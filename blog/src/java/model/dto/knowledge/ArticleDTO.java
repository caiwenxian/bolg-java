package model.dto.knowledge;

import model.dto.common.BaseDTO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class ArticleDTO extends BaseDTO {

    private String title;

    public ArticleDTO() {

    }

    public ArticleDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
