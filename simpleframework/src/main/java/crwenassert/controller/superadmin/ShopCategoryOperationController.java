package crwenassert.controller.superadmin;

import crwenassert.entity.bo.HeadLine;
import crwenassert.entity.bo.ShopCategory;
import crwenassert.entity.dto.Result;
import crwenassert.service.solo.ShopCategoryService;
import top.simpleframework.core.annotation.Controller;
import top.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: ShopCategoryOperationController
 * Description:
 * date: 2020/7/31 21:47
 *
 * @author crwen
 * @create 2020-07-31-21:47
 * @since JDK 1.8
 */
@Controller
public class ShopCategoryOperationController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    Result<Boolean> addShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 参数校验以及请求参数转化
        return shopCategoryService.addShopCategory(new ShopCategory());
    }

    Result<Boolean> removeShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 参数校验以及请求参数转化
        return shopCategoryService.removeShopCategory(1);
    }

    Result<Boolean> modifyShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 参数校验以及请求参数转化
        return shopCategoryService.modifyShopCategory(new ShopCategory());
    }
    Result<HeadLine> queryShopCategoryById(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 参数校验以及请求参数转化
        return shopCategoryService.queryShopCategoryById(1);
    }
    Result<List<HeadLine>> queryShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        // TODO 参数校验以及请求参数转化
        return shopCategoryService.queryShopCategory(new ShopCategory(), 1, 4);
    }

}
