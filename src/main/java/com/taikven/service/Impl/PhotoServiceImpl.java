package com.taikven.service.Impl;

import com.taikven.entity.Photo;
import com.taikven.mapper.PhotoMapper;
import com.taikven.service.PhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 
 * @since 2023-04-11
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

}
