package controller.music;

import exception.ErrorCode;
import exception.SerException;
import model.dto.music.TopListDTO;
import model.enums.music.TopListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.common.Result;
import service.common.impl.ActResult;
import service.music.ITopListService;
import service.music.reptile.IReptileSongService;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-26 14:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/toplist")
public class TopListController {


}
