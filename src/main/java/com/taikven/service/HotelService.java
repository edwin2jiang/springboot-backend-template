package com.taikven.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taikven.entity.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * 
 * @since 2023-04-11
 */
public interface HotelService extends IService<Hotel> {
    public boolean saveWithPhotos(Hotel hotel);
    public IPage selectPage(int current, int size , String province, String city, String area, Date startDate, Date endDate);
}
