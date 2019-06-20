package controller.knowledge;

import exception.ActException;
import exception.SerException;
import model.dto.knowledge.ArticleCommentDTO;
import model.dto.knowledge.ArticleDTO;
import model.enums.knowledge.ArticleStatus;
import model.po.common.PagePO;
import model.po.knowledge.ArticleCommentPO;
import model.to.ArticleTO;
import model.vo.knowledge.ArticleVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.common.Result;
import service.common.impl.ActResult;
import service.knowledge.IArticleCommentService;
import service.knowledge.IKnowledgeService;

import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private IArticleCommentService articleCommentService;

    /**
     * 跳转文章列表
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("")
    @ResponseBody
    public ModelAndView articlePage() throws SerException {
        ModelAndView modelAndView = new ModelAndView("/knowledge/knowledge");
//        throw new SerException("code");
        return modelAndView;
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
    public ModelAndView articleDetailsPage(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("/knowledge/article-details");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    /**
     * 跳转文章发布
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/article/publish")
    @ResponseBody
    public ModelAndView articlePublishPage() {
        ModelAndView modelAndView = new ModelAndView("/knowledge/knowledge-publish");
        return modelAndView;
    }

    /**
     * 发布文章
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/publish")
    @ResponseBody
    public Result publish(ArticleTO to) {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null || !subject.isAuthenticated()) {
            return ActResult.error(Result.MSG_NOT_LOGIN_OPERATE);
        }
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
    public Result saveDraft(ArticleTO to) {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null || !subject.isAuthenticated()) {
            return ActResult.error(Result.MSG_NOT_LOGIN_OPERATE);
        }
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
    public Result listArticle(@PathVariable Integer page, ArticleDTO dto, HttpServletResponse response) throws SerException {
        try {
            dto.setPage(page);
            dto.setTitle(dto.getTitle() == "" ? null : dto.getTitle());
            dto.setStatus(ArticleStatus.PUBLISH.getCode());
            PagePO pagePO = knowledgeService.ListArticle(dto);
            return ActResult.data(pagePO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException("code");
        }
//        return ActResult.success("success");
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
    public Result getArticle(@PathVariable String id) {
        try {
            ArticleVO vo = knowledgeService.getArticle(id);
            return ActResult.data(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.success("success");
    }

    /**
     * 更新文章观阅数
     *
     * @param
     * @return class
     * @version v1
     */
    @PutMapping("/article/browseAmount/{id}")
    @ResponseBody
    public Result updateArticleBrowseAmount(@PathVariable String id) {
        try {
            knowledgeService.updateArticleBrowseAmount(id);
            return ActResult.success("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.success("success");
    }


    /**
     * 获取文章评论
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/article/comment/{page}")
    @ResponseBody
    public Result listArticleComment(@PathVariable Integer page, ArticleCommentDTO dto) {
        try {
            dto.setPage(page);
            dto.setLimit(20);
            PagePO pagePO = articleCommentService.listArticleComment(dto);
            return ActResult.data(pagePO);
        } catch (Exception e) {
            e.printStackTrace();
            return ActResult.success(e.getMessage());
        }
    }

    /**
     * 添加文章评论
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/article/comment")
    @ResponseBody
    public Result addArticleComment(ArticleCommentPO po) throws ActException {
        try {
            articleCommentService.addComment(po);
            return ActResult.success();
        } catch (SerException e) {
            e.printStackTrace();
            return ActResult.error(e.getCode(), e.getMessage());
        }
    }

    /**
     * 添加文章评论回复
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/article/comment/reply")
    @ResponseBody
    public Result addArticleCommentReply(ArticleCommentPO po) throws ActException {
        try {
            if (StringUtils.isBlank(po.getParentId())) {
                return ActResult.error("评论失败");
            }
            articleCommentService.addComment(po);
            return ActResult.success();
        } catch (SerException e) {
            e.printStackTrace();
            return ActResult.error(e.getCode(), e.getMessage());
        }
    }

}
