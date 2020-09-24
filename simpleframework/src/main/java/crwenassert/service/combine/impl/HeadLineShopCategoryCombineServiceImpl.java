package crwenassert.service.combine.impl;

import crwenassert.entity.bo.HeadLine;
import crwenassert.entity.bo.ShopCategory;
import crwenassert.entity.dto.MainPageInfoDTO;
import crwenassert.entity.dto.Result;
import crwenassert.service.combine.HeadLineShopCategoryCombineService;
import crwenassert.service.solo.HeadLineService;
import crwenassert.service.solo.ShopCategoryService;
import top.simpleframework.core.annotation.Service;
import top.simpleframework.inject.annotation.Autowired;

import java.util.List;

/**
 * ClassName: HeadLineShopCategoryCombineServiceImpl
 * Description:
 * date: 2020/7/31 21:04
 *
 * @author crwen
 * @create 2020-07-31-21:04
 * @since JDK 1.8
 */
@Service
public class HeadLineShopCategoryCombineServiceImpl
        implements HeadLineShopCategoryCombineService {

    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        // 获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);
        // 获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<HeadLine>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);

        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(headLineResult, shopCategoryResult);
        
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<HeadLine>> shopCategoryResult) {

        return null;
    }
}
