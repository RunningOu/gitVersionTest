package com.IpManage.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    private Integer code=200;

    @ApiModelProperty("请求是否成功")
    private boolean success=true;

    @ApiModelProperty("请求信息")
    private String msg;

    @ApiModelProperty("请求结果集")
    private T data;

    /**
     * 判断是否是成功结果
     * JsonIgnore使之不在json序列化结果当中
     *
     * @return 是否为成功结果
     */
    public boolean isSuccess() {
        return BaseResultEnum.SUCCESS.getCode() == this.code;
    }

}