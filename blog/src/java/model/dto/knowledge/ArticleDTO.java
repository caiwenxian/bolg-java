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

    private int status;

    public ArticleDTO() {

    }

    public ArticleDTO(String title) {
        this.title = title;
    }

    public ArticleDTO(String title, int status) {
        this.title = title;
        this.status = status;
    }

    public ArticleDTO(Integer limit, Integer page, String title, int status) {
        super(limit, page);
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
