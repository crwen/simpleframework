package crwenassert.controller.superadmin;

import crwenassert.entity.bo.HeadLine;
import crwenassert.entity.dto.Result;
import crwenassert.service.solo.HeadLineService;
import top.simpleframework.core.annotation.Controller;
import top.simpleframework.inject.annotation.Autowired;
import top.simpleframework.mvc.annotation.RequestMapping;
import top.simpleframework.mvc.annotation.RequestParam;
import top.simpleframework.mvc.annotation.ResponseBody;
import top.simpleframework.mvc.type.ModelAndView;
import top.simpleframework.mvc.type.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: HeadLineOperationController
 * Description:
 * date: 2020/7/31 21:41
 *
 * @author crwen
 * @create 2020-07-31-21:41
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/headline")
public class HeadLineOperationController {

    @Autowired
    private HeadLineService headLineService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addHeadLine(@RequestParam("lineName") String lineName,
                                    @RequestParam("lineLink") String lineLink,
                                    @RequestParam("lineImg") String lineImg,
                                    @RequestParam("priority") Integer priority) {

        HeadLine headLine = new HeadLine();
        headLine.setLineName(lineName);
        headLine.setLineLink(lineLink);
        headLine.setLineImg(lineImg);
        headLine.setPriority(priority);
        Result<Boolean> result = headLineService.addHeadLine(headLine);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView("addHeadline.jsp");
        modelAndView.addViewData("result", result);
        return modelAndView;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public void removeHeadLine() {
        // TODO 参数校验以及请求参数转化
        System.out.println("删除HeadLine");
        //return headLineService.removeHeadLine(1);
    }

    public Result<Boolean> modifyHeadLine(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 参数校验以及请求参数转化
        return headLineService.modifyHeadLine(new HeadLine());
    }

    public Result<HeadLine> queryHeadLineById(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 参数校验以及请求参数转化
        return headLineService.queryHeadLineById(1);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<HeadLine>> queryHeadLine() {
        // TODO 参数校验以及请求参数转化
        return headLineService.queryHeadLine(new HeadLine(), 1, 4);
    }


}
