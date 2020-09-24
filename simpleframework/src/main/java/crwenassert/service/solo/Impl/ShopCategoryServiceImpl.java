package crwenassert.service.solo.Impl;

import crwenassert.entity.bo.HeadLine;
import crwenassert.entity.bo.ShopCategory;
import crwenassert.entity.dto.Result;
import crwenassert.service.solo.ShopCategoryService;
import top.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * ClassName: ShopCategoryServiceImpl
 * Description:
 * date: 2020/7/31 20:56
 *
 * @author crwen
 * @create 2020-07-31-20:56
 * @since JDK 1.8
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<HeadLine> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
