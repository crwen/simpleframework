package crwenassert.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: HeadLine
 * Description:
 * date: 2020/7/31 19:04
 *
 * @author crwen
 * @create 2020-07-31-19:04
 * @since JDK 1.8
 */
@Data
public class HeadLine {
    private Long  lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
