package crwenassert.service.solo;

import crwenassert.entity.bo.HeadLine;
import crwenassert.entity.dto.Result;

import java.util.List;

/**
 * ClassName: HeadLineService
 * Description:
 * date: 2020/7/31 20:49
 *
 * @author crwen
 * @create 2020-07-31-20:49
 * @since JDK 1.8
 */
public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
