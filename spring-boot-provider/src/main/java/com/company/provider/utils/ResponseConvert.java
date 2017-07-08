package com.company.provider.utils;

import com.company.provider.enums.ReturnCodeEnum;
import com.company.provider.model.response.BaseResponse;

/**
 * <p>
 * 工具类
 * </p>
 *
 * @author wangliang
 * @since 2017/7/6
 */
public class ResponseConvert {

    public static BaseResponse convert(boolean back) {
        BaseResponse response = new BaseResponse();
        if (back) {
            response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1000.getValue());
        } else {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1005.getValue());
        }
        return response;
    }

}
