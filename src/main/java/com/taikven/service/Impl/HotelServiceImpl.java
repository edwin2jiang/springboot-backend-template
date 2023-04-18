package com.taikven.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taikven.entity.Hotel;
import com.taikven.entity.Order;
import com.taikven.entity.Photo;
import com.taikven.mapper.HotelMapper;
import com.taikven.mapper.OrderMapper;
import com.taikven.mapper.PhotoMapper;
import com.taikven.service.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taikven.service.OrderService;
import com.taikven.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 
 * @since 2023-04-11
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    PhotoMapper photoMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    PhotoService photoService;


    @Transactional
    public boolean saveWithPhotos(Hotel hotel){
        boolean flag = false;
        if(hotelMapper.insert(hotel)>0) flag=true;//保存基本信息
        List<Photo> photos = hotel.getPhotos();

        for (Photo photo : photos) {
            photo.setHid(hotel.getHid());
            photo.setUid(hotel.getUid());
        }
        boolean flag2=photoService.saveBatch(hotel.getPhotos());//保存所有照片
        if(flag2==false) flag=false;
        return flag;
    }

    public IPage selectPage(int current, int size , String province, String city, String area, Date startDate,Date endDate) {
//        IPage page = new Page(current,size);
//        QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
//
////        queryWrapper.eq(province!=null,"province",province)
////                .eq(city!=null,"city",city)
////                .eq(area!=null,"area",area);
//
//
//        hotelMapper.selectPage(page,queryWrapper);
//        List<Hotel> hotels=page.getRecords();
//
//
//
//        for (Hotel hotel : hotels) {
//            System.out.println(hotel);
//            List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>()
//                    .ge(startDate!=null,"startDate",startDate)
//                    .le(endDate!=null,"endDate",endDate)
//                    .eq("hid",hotel.getHid()));
//
//
//            if(orders.size()!=0) {
//                hotels.remove(hotel);
//
//            }
//            else hotel.setPhotos(photoMapper.selectList(new QueryWrapper<Photo>().eq("hid",hotel.getHid())));
//
//        }
//
//
//
//        page.setRecords(hotels);
//
//        return page;
        return null;
    }
}
