package crwenassert.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: ShopCategory
 * Description:
 * date: 2020/7/31 19:07
 *
 * @author crwen
 * @create 2020-07-31-19:07
 * @since JDK 1.8
 */
@Data
public class ShopCategory {

    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;

}
