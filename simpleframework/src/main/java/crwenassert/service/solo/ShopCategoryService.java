package crwenassert.service.solo;

import crwenassert.entity.bo.HeadLine;
import crwenassert.entity.bo.ShopCategory;
import crwenassert.entity.dto.Result;

import java.util.List;

/**
 * ClassName: ShopCategoryService
 * Description:
 * date: 2020/7/31 20:49
 *
 * @author crwen
 * @create 2020-07-31-20:49
 * @since JDK 1.8
 */
public interface ShopCategoryService {
    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(int shopCategoryId);
    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
    Result<HeadLine> queryShopCategoryById(int shopCategoryId);
    Result<List<HeadLine>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
