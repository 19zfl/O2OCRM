package com.o2ocrm.basic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:AjaxResult
 * @Description: 操作数据
 * @Author:zfl19
 * @CreateDate:2024/3/9 14:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {

    /** 状态码 */
    private String code;

    /** 返回内容 */
    private String msg;

    /** 请求状态 */
    private Boolean success;

    /** 数据对象 */
    private Object data;

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static AjaxResult success() {
        return new AjaxResult("001", "请求成功！", true, null);
    }

    /**
     * 返回成功消息
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return new AjaxResult("002", "请求成功！", true, data);
    }

    /**
     * 返回错误消息
     * @param msg 返回内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg) {
        return new AjaxResult("003", msg, false, null);
    }

    /**
     * 返回错误消息
     * @param code 返回状态码
     * @param msg 返回内容
     * @return 错误消息
     */
    public static AjaxResult error(String code, String msg) {
        return new AjaxResult(code, msg, false, null);
    }

}
