package crwenassert.entity.dto;

import crwenassert.entity.bo.HeadLine;
import crwenassert.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * ClassName: MainPageInfoDTO
 * Description:
 * date: 2020/7/31 21:02
 *
 * @author crwen
 * @create 2020-07-31-21:02
 * @since JDK 1.8
 */
@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
