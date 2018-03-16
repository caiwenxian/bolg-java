package controller.knowledge;

import model.dto.knowledge.ArticleDTO;
import model.enums.knowledge.ArticleStatus;
import model.po.common.PagePO;
import model.to.ArticleTO;
import model.vo.knowledge.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.common.Result;
import service.common.impl.ActResult;
import service.knowledge.IKnowledgeService;

import java.util.logging.Logger;

/**
 * 知识专栏控制器
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
@RequestMapping("/knowledge")
@Controller
public class KnowledgeController {

    final static Logger LOGGER = Logger.getLogger(KnowledgeController.class.getName());

    @Autowired
    private IKnowledgeService knowledgeService;

    /**
     * 发布文章
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/publish")
    @ResponseBody
    public Result publish(ArticleTO to){
        try {
            to.setStatus(ArticleStatus.PUBLISH);
            knowledgeService.addArticle(to);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.success("success");
    }

    /**
     * 保存文章草稿
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/draft")
    @ResponseBody
    public Result saveDraft(ArticleTO to){
        try {
            to.setStatus(ArticleStatus.DRAFT);
            knowledgeService.addArticle(to);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.success("success");
    }

    /**
     * 获取文章列表
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/articles/{page}")
    @ResponseBody
    public Result listArticle(@PathVariable Integer page, ArticleDTO dto){
        try {
            dto.setPage(page);
            dto.setTitle(dto.getTitle() == "" ? null : dto.getTitle());
            PagePO pagePO = knowledgeService.ListArticle(dto);
            return ActResult.data(pagePO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.success("success");
    }

    /**
     * 获取文章
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/article/{id}")
    @ResponseBody
    public Result getArticle(@PathVariable String id){
        try {
            ArticleVO vo = knowledgeService.getArticle(id);
            return ActResult.data(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.success("success");
    }

    /**
     * 跳转文章详细
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/article/details/{id}")
    @ResponseBody
    public ModelAndView articleDetailsPage(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("/blog/view/article-details.html");
        modelAndView.addObject("id", id);
        return modelAndView;
    }


}
