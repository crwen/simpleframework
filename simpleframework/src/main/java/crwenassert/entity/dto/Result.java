package crwenassert.entity.dto;

import lombok.Data;

/**
 * ClassName: Result
 * Description:
 * date: 2020/7/31 20:46
 *
 * @author crwen
 * @create 2020-07-31-20:46
 * @since JDK 1.8
 */
@Data
public class Result<T> {

    private int code;

    private String msg;

    private T data;
}
