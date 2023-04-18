package com.taikven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taikven.consts.Code;
import com.taikven.entity.*;
import com.taikven.pageWrapper.HotelWrapper;
import com.taikven.service.HotelService;
import com.taikven.service.OrderService;
import com.taikven.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 
 * @since 2023-04-11
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;


    @Autowired
    PhotoService photoService;

    @Autowired
    OrderService orderService;
    /**
     * 酒店添加
     *
     * @param hotel
     * @return 20011成功 20010失败
     */
    @PostMapping
    @Transactional
    public Result save(@RequestBody Hotel hotel) {
        boolean flag = hotelService.saveWithPhotos(hotel);//保存基本信息
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    /**
     * 酒店修改
     *
     * @param hotel
     * @return 20031成功 20030失败
     */
    @PutMapping
    public Result update(@RequestBody Hotel hotel) {
        System.out.println(hotel);
        boolean flag=false;
        flag = hotelService.updateById(hotel);//更新基本信息


        //删掉之前所有照片
        List<Photo> photos=photoService.list(new QueryWrapper<Photo>().eq("hid",hotel.getHid()));
        photoService.removeByIds(photos);

        //给新图片设置uid和hid
        for (Photo photo : hotel.getPhotos()) {
            photo.setUid(hotel.getUid());
            photo.setHid(hotel.getHid());
            photo.setPid(null);
        }
        //添加修改后的照片集合
        photoService.saveBatch(hotel.getPhotos());


        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    /**
     * 酒店删除（逻辑删除）
     *
     * @param hid
     * @return 20021成功 20020失败
     */
    @DeleteMapping("/{hid}")
    public Result delete(@PathVariable("hid") Integer hid) {
        boolean flag = hotelService.removeById(hid);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    /**
     * 根据用户Uid查询名下所有酒店
     *
     * @param uid
     * @return Hotels 20041成功 20040失败
     */
    @GetMapping("/getByUid/{uid}")
    public Result getHotelsByUid(@PathVariable("uid") Integer uid) {
        QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        List<Hotel> hotels = hotelService.list(queryWrapper);
        return new Result(Code.GET_OK, hotels);
    }

    /**
     * 酒店根据ID查询
     *
     * @param hid
     * @return hotel 20041成功 20040失败
     */
    @GetMapping("/{hid}")
    public Result get(@PathVariable("hid") Integer hid) {
        System.out.println("asdasd");
        Hotel hotel = hotelService.getById(hid);
        if(hotel==null)
            return new Result(Code.GET_ERR,"酒店不存在");
        List<Photo> photos=photoService.list(new QueryWrapper<Photo>().eq("hid",hid));
        hotel.setPhotos(photos);
        return new Result(Code.GET_OK, hotel);
    }

    /**
     * 酒店查询所有信息（不分页）
     *
     * @return 20041成功 20040失败
     */
    @GetMapping
    public Result list() {
        List<Hotel> hotels = hotelService.list();
        for(Hotel hotel:hotels){
            List<Photo> photos=photoService.list(new QueryWrapper<Photo>().eq("hid",hotel.getHid()));
            hotel.setPhotos(photos);
        }
        return new Result(Code.GET_OK, hotels);
    }

    /**
     * 酒店条件查询
     * @param  hotelWrapper 包括current size province city area  当前页 每页数量 后面三个参数为查询条件，三个参数都为空时查询所有
     * @return IPage对象 20041成功 20040失败
     */
    @GetMapping("listWithCondition")
    public Result listWithCondition(HotelWrapper hotelWrapper){
        List<Hotel> hotels = hotelService.list(new QueryWrapper<Hotel>()
                .eq(hotelWrapper.getProvince()!=null,"province",hotelWrapper.getProvince())
                .eq(hotelWrapper.getCity()!=null,"city",hotelWrapper.getCity())
                .eq(hotelWrapper.getArea()!=null,"area",hotelWrapper.getArea()));
        System.out.println("时间"+hotelWrapper.getStartDate()+" "+hotelWrapper.getEndDate());
        List<Hotel> hotels2 = new ArrayList<>();
        System.out.println(hotelWrapper.getStartDate()+" "+hotelWrapper.getEndDate());

        for(Hotel hotel:hotels){
            if(hotelWrapper.getStartDate()==null||hotelWrapper.getEndDate()==null){
                List<Photo> photos = photoService.list(new QueryWrapper<Photo>().eq("hid", hotel.getHid()));
                hotel.setPhotos(photos);
                hotels2.add(hotel);
            }
            else {
                System.out.println("asdasd");
                List<Order> orders = orderService.getBaseMapper().selectList(new QueryWrapper<Order>()
                        .ge("start_date", hotelWrapper.getStartDate())
                        .le("end_date", hotelWrapper.getEndDate())
                        .eq("hid", hotel.getHid()));
                if (orders.size() == 0) {
                    List<Photo> photos = photoService.list(new QueryWrapper<Photo>().eq("hid", hotel.getHid()));
                    hotel.setPhotos(photos);
                    hotels2.add(hotel);
                }
            }
        }


        System.out.println("结束了");
        return new Result(Code.GET_OK, hotels2);
    }








}

