package crwenassert.service.combine;

import crwenassert.entity.dto.MainPageInfoDTO;
import crwenassert.entity.dto.Result;

/**
 * ClassName: HeadLineShopCategoryCombineService
 * Description:
 * date: 2020/7/31 21:00
 *
 * @author crwen
 * @create 2020-07-31-21:00
 * @since JDK 1.8
 */
public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
