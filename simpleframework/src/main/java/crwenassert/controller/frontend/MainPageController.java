package crwenassert.controller.frontend;

import crwenassert.entity.dto.MainPageInfoDTO;
import crwenassert.entity.dto.Result;
import crwenassert.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import top.simpleframework.core.annotation.Controller;
import top.simpleframework.inject.annotation.Autowired;
import top.simpleframework.mvc.annotation.RequestMapping;
import top.simpleframework.mvc.type.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: MainPageController
 * Description:
 * date: 2020/7/31 21:38
 *
 * @author crwen
 * @create 2020-07-31-21:38
 * @since JDK 1.8
 */
@Controller
@Getter
@RequestMapping(value = "/main")
public class MainPageController {
    @Autowired
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void throwException() {
        System.out.println("error.....");
        throw new RuntimeException("抛出异常测试");
    }
}
