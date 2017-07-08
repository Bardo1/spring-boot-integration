package com.company.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.company.provider.entity.User;
import com.company.provider.enums.ReturnCodeEnum;
import com.company.provider.mapper.UserMapper;
import com.company.provider.model.request.UserPageParam;
import com.company.provider.model.response.BaseResponse;
import com.company.provider.model.response.PageDataResponse;
import com.company.provider.model.response.PageResponse;
import com.company.provider.service.IUserService;
import com.company.provider.utils.BackResponseUtil;
import com.company.provider.utils.ResponseConvert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * service实现类
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public UserServiceImpl() {
        super();
    }

    public UserServiceImpl(UserMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public BaseResponse add(User model) {
        boolean back = this.insert(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
    }

    @Override
    public BaseResponse delete(User model) {
        BaseResponse baseResponse;
        if (null == model || StringUtils.isEmpty(model.getId())) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.deleteById(model.getId());
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse update(User model) {
        BaseResponse baseResponse;
        if (null == model || StringUtils.isEmpty(model.getId())) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.updateById(model);
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse query(User model) {
        BaseResponse baseResponse;
        if (null == model || StringUtils.isEmpty(model.getId())) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            User data = this.selectById(model.getId());
            if (null != data) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(data);
            } else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }
        return baseResponse;
    }

    @Override
    public PageDataResponse list(UserPageParam pageParam) {
        return null;
    }

    @Override
    public PageResponse page(UserPageParam pageParam) {
        PageResponse pageResponse;
        Page<User> page = new Page<User>();
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
            page.setCurrent(pageParam.getPageNumber());
            page.setSize(pageParam.getPageSize());
        }
        EntityWrapper<User> ew = new EntityWrapper<User>();
        Page<User> selectPage = this.selectPage(page, ew);
        if (CollectionUtils.isNotEmpty(selectPage.getRecords())) {
            pageResponse = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        }else {
            pageResponse = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1002.getCode());
        }
        return pageResponse;
    }

    @Override
    public boolean deleteAll() {
        return retBool(baseMapper.deleteAll());
    }

    @Override
    public List<User> selectListBySQL() {
        return baseMapper.selectListBySQL();
    }

}
