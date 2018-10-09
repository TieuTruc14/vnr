package com.eposi.vnr.data;

import com.eposi.common.util.AbstractBean;
import com.eposi.vnr.model.Station;
//import com.eposi.vnr.model.Stop;
//import com.eposi.vnr.model.Train;
import com.eposi.vnr.persitence.VnrDao;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SampleDataDemo extends AbstractBean {
    private static final long serialVersionUID = 4015857465129913904L;

    private List<Station> stations = new ArrayList<Station>();
//    private List<Train> trains = new ArrayList<Train>();

 /*   public Stop getStop(Train train, int stationId) {
        for (Stop stop : train.getStops()) {
            if (stop.getStation().getId() == stationId) {
                return stop;
            }
        }

        return null;
    }
*/
//    public Train getTrain(int id) {
//        for (Train train : trains) {
//            if (train.getId() == id) {
//                return train;
//            }
//        }
//
//        return null;
//    }

    public Station getStation(String id) {
        for (Station station : stations) {
            if (station.getId() == id) {
                return station;
            }
        }

        return null;
    }

    public List<Station> getStations() {
        return stations;
    }

//    public List<Train> getTrains() {
//        return trains;
//    }

    public void init() {
//        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
//
//        Date date = new Date();
//        DateTime dateTime = new DateTime(date) ;
//
//        ////////////////////////////////////////////////////////////////////////////////////
//        // stations
//        stations = beanVnrDao.getStations();
//
//        trains.clear();
//        trainId = 1;
//        addDailyPlan(date);
//        addDailyPlan(dateTime.plusDays(1).toDate());
    }

    private static int trainId = 1;

    public void addDailyPlan(Date startDate) {

        startDate = DateUtils.truncate(startDate, Calendar.DATE);

        ////////////////////////////////////////////////////////////////////////////////////
        // trains

        ////////////////////////////////////////////
        // TAU KHACH
/*
        // SE1
        Train train = new Train();
        train.setId(trainId++);
        train.setName("SE1");
        train.setPriority(500);
        train.setDepartureTime(startDate.getTime() + 68400000);
        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 960000,     train.getDepartureTime() + 960000, false, false)     ); // Văn Điển
        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1440000,    train.getDepartureTime() + 1440000, false, false)    ); // Thường Tín
        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Chợ Tía
        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2280000,    train.getDepartureTime() + 2280000, false, false)    ); // Phú Xuyên
        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2940000,    train.getDepartureTime() + 2940000, false, false)    ); // Đồng Văn
        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Phủ Lý
        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 4380000,    train.getDepartureTime() + 4380000, false, false)    ); // Bình Lục
        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4740000, false, false)    ); // Cầu Họ
        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Đặng Xá
        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 5700000,    train.getDepartureTime() + 5880000, true, false)    ); // Nam Định
        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6360000,    train.getDepartureTime() + 6360000, false, false)    ); // Trình Xuyên
        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 6780000,    train.getDepartureTime() + 6780000, false, false)    ); // Núi Gôi
        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 7200000,    train.getDepartureTime() + 7200000, false, false)    ); // Cát Đằng
        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 7740000,    train.getDepartureTime() + 7920000, true, false)    ); // Ninh Bình
        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8400000,    train.getDepartureTime() + 8400000, false, false)    ); // Cầu Yên
        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 8700000,    train.getDepartureTime() + 8700000, false, false)    ); // Gềnh
        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 9300000,    train.getDepartureTime() + 9300000, false, false)    ); // Đồng Giao
        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 9780000,    train.getDepartureTime() + 9780000, false, false)    ); // Bỉm Sơn
        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 10380000,   train.getDepartureTime() + 10380000, false, false)   ); // Đò Lèn
        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 10920000,   train.getDepartureTime() + 10920000, false, false)   ); // Nghĩa Trang
        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 11880000,   train.getDepartureTime() + 12060000, true, false)   ); // Thanh Hóa
        trains.add(train);

//        // SE2*/
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE2");
//        train.setPriority(510);
//        train.setDepartureTime(startDate.getTime() + 1260000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 180000, true, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 1140000,    train.getDepartureTime() + 1140000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 1680000,    train.getDepartureTime() + 1680000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 2340000,    train.getDepartureTime() + 2340000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 2880000,    train.getDepartureTime() + 3240000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 3840000,    train.getDepartureTime() + 3840000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 4140000,    train.getDepartureTime() + 4140000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 4620000,    train.getDepartureTime() + 4800000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 5340000,    train.getDepartureTime() + 5340000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 5760000,    train.getDepartureTime() + 5760000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6180000,    train.getDepartureTime() + 6180000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 6660000,    train.getDepartureTime() + 6840000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 7320000,    train.getDepartureTime() + 7320000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 7800000,    train.getDepartureTime() + 7800000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 8160000,    train.getDepartureTime() + 8160000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 8820000,    train.getDepartureTime() + 8820000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 9600000,    train.getDepartureTime() + 9600000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 10260000,   train.getDepartureTime() + 10260000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 10680000,   train.getDepartureTime() + 10680000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 11100000,   train.getDepartureTime() + 11100000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 11580000,   train.getDepartureTime() + 11580000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 11880000,   train.getDepartureTime() + 11880000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 12600000,   train.getDepartureTime() + 12600000, false, false)   ); // Hà Nội
//        trains.add(train);

//        // SE3
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE3");
//        train.setPriority(600);
//        train.setDepartureTime(startDate.getTime() + 82800000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 900000,     train.getDepartureTime() + 900000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1380000,    train.getDepartureTime() + 1380000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1800000,    train.getDepartureTime() + 1800000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2220000,    train.getDepartureTime() + 2220000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2820000,    train.getDepartureTime() + 2820000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3420000,    train.getDepartureTime() + 3420000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 3960000,    train.getDepartureTime() + 3960000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 4320000,    train.getDepartureTime() + 4320000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4740000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 5160000,    train.getDepartureTime() + 5160000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 5520000,    train.getDepartureTime() + 5520000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 5940000,    train.getDepartureTime() + 5940000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 6300000,    train.getDepartureTime() + 6300000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 6720000,    train.getDepartureTime() + 6720000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 7080000,    train.getDepartureTime() + 7080000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 7380000,    train.getDepartureTime() + 7380000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 7920000,    train.getDepartureTime() + 7920000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 8400000,    train.getDepartureTime() + 8400000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 9000000,    train.getDepartureTime() + 9000000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 9480000,    train.getDepartureTime() + 9480000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 10260000,   train.getDepartureTime() + 10260000, false, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // SE4
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE4");
//        train.setPriority(610);
//        train.setDepartureTime(startDate.getTime() + 4800000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 180000, false, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 840000,     train.getDepartureTime() + 1380000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 1920000,    train.getDepartureTime() + 1920000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 2520000,    train.getDepartureTime() + 2520000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 3000000,    train.getDepartureTime() + 3000000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 3540000,    train.getDepartureTime() + 3540000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 3840000,    train.getDepartureTime() + 3840000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 4200000,    train.getDepartureTime() + 4200000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 4680000,    train.getDepartureTime() + 4680000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 5100000,    train.getDepartureTime() + 5100000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 5520000,    train.getDepartureTime() + 5520000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 5880000,    train.getDepartureTime() + 5880000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 6300000,    train.getDepartureTime() + 6300000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 6720000,    train.getDepartureTime() + 6720000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 7080000,    train.getDepartureTime() + 7080000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 7680000,    train.getDepartureTime() + 7680000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 8400000,    train.getDepartureTime() + 8400000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 9000000,    train.getDepartureTime() + 9000000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 9420000,    train.getDepartureTime() + 9420000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 9840000,    train.getDepartureTime() + 9840000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 10320000,   train.getDepartureTime() + 10320000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 10680000,   train.getDepartureTime() + 10680000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 11400000,   train.getDepartureTime() + 11400000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // SE5
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE5");
//        train.setPriority(500);
//        train.setDepartureTime(startDate.getTime() + 32400000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 960000,     train.getDepartureTime() + 960000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1440000,    train.getDepartureTime() + 1440000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2280000,    train.getDepartureTime() + 2280000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2940000,    train.getDepartureTime() + 2940000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3780000,    train.getDepartureTime() + 3960000, true, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 4680000,    train.getDepartureTime() + 4680000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 5040000,    train.getDepartureTime() + 5040000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 5520000,    train.getDepartureTime() + 5520000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 6000000,    train.getDepartureTime() + 6180000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6660000,    train.getDepartureTime() + 6660000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 7080000,    train.getDepartureTime() + 7080000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 7500000,    train.getDepartureTime() + 7500000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 8040000,    train.getDepartureTime() + 8220000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8700000,    train.getDepartureTime() + 8700000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 9000000,    train.getDepartureTime() + 9000000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 9600000,    train.getDepartureTime() + 9600000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 10140000,   train.getDepartureTime() + 10320000, true, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 10980000,   train.getDepartureTime() + 11400000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 12000000,   train.getDepartureTime() + 12000000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 12960000,   train.getDepartureTime() + 13140000, true, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // SE6
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE6");
//        train.setPriority(510);
//        train.setDepartureTime(startDate.getTime() + 56400000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 180000, false, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 1140000,    train.getDepartureTime() + 1140000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 1680000,    train.getDepartureTime() + 1680000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 2400000,    train.getDepartureTime() + 2580000, true, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 3180000,    train.getDepartureTime() + 3180000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 4020000,    train.getDepartureTime() + 4020000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 4500000,    train.getDepartureTime() + 4680000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 5640000,    train.getDepartureTime() + 5640000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6060000,    train.getDepartureTime() + 6060000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 6540000,    train.getDepartureTime() + 6720000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 7200000,    train.getDepartureTime() + 7200000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 7680000,    train.getDepartureTime() + 7680000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 8040000,    train.getDepartureTime() + 8040000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 8760000,    train.getDepartureTime() + 8940000, true, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 9780000,    train.getDepartureTime() + 9780000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 10440000,   train.getDepartureTime() + 10440000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 10860000,   train.getDepartureTime() + 10860000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 11280000,   train.getDepartureTime() + 11280000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 11760000,   train.getDepartureTime() + 11760000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 12120000,   train.getDepartureTime() + 12840000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 13560000,   train.getDepartureTime() + 13560000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // SE7
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE7");
//        train.setPriority(500);
//        train.setDepartureTime(startDate.getTime() + 22500000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 960000,     train.getDepartureTime() + 960000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1440000,    train.getDepartureTime() + 1440000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2280000,    train.getDepartureTime() + 2280000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2940000,    train.getDepartureTime() + 2940000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3780000,    train.getDepartureTime() + 3960000, true, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 4680000,    train.getDepartureTime() + 4680000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 5040000,    train.getDepartureTime() + 5040000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 5520000,    train.getDepartureTime() + 5520000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 6000000,    train.getDepartureTime() + 6180000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6660000,    train.getDepartureTime() + 6660000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 7080000,    train.getDepartureTime() + 7080000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 7500000,    train.getDepartureTime() + 7500000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 8040000,    train.getDepartureTime() + 8220000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8700000,    train.getDepartureTime() + 8700000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 9000000,    train.getDepartureTime() + 9000000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 9600000,    train.getDepartureTime() + 9600000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 10080000,   train.getDepartureTime() + 10080000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 10680000,   train.getDepartureTime() + 10680000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 11220000,   train.getDepartureTime() + 11220000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 12180000,   train.getDepartureTime() + 12360000, true, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // SE8
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE8");
//        train.setPriority(510);
//        train.setDepartureTime(startDate.getTime() + 41940000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 180000, true, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 1140000,    train.getDepartureTime() + 1140000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 1680000,    train.getDepartureTime() + 1680000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 2340000,    train.getDepartureTime() + 2340000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 2880000,    train.getDepartureTime() + 2880000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 3420000,    train.getDepartureTime() + 3420000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 4200000,    train.getDepartureTime() + 4380000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 4920000,    train.getDepartureTime() + 4920000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 5340000,    train.getDepartureTime() + 5340000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 5760000,    train.getDepartureTime() + 5760000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 6240000,    train.getDepartureTime() + 6420000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 6900000,    train.getDepartureTime() + 6900000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 7380000,    train.getDepartureTime() + 7380000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 7740000,    train.getDepartureTime() + 7740000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 8460000,    train.getDepartureTime() + 8460000, true, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 9480000,    train.getDepartureTime() + 9480000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 10140000,   train.getDepartureTime() + 10140000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 10560000,   train.getDepartureTime() + 10560000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 10980000,   train.getDepartureTime() + 10980000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 11460000,   train.getDepartureTime() + 11460000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 11760000,   train.getDepartureTime() + 11760000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 12480000,   train.getDepartureTime() + 12480000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // TN1
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("TN1");
//        train.setPriority(400);
//        train.setDepartureTime(startDate.getTime() + 47700000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 720000,     train.getDepartureTime() + 900000, true, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 1260000,    train.getDepartureTime() + 1260000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1740000,    train.getDepartureTime() + 1740000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 2160000,    train.getDepartureTime() + 2160000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2580000,    train.getDepartureTime() + 2580000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 3300000,    train.getDepartureTime() + 3900000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4920000, true, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 5580000,    train.getDepartureTime() + 5580000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 6000000,    train.getDepartureTime() + 6000000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 6480000,    train.getDepartureTime() + 6480000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 6960000,    train.getDepartureTime() + 7140000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 7620000,    train.getDepartureTime() + 7620000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 8100000,    train.getDepartureTime() + 8100000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 8520000,    train.getDepartureTime() + 8520000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 9060000,    train.getDepartureTime() + 9240000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 9720000,    train.getDepartureTime() + 9720000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 10020000,   train.getDepartureTime() + 10020000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 10680000,   train.getDepartureTime() + 12060000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 12600000,   train.getDepartureTime() + 12780000, true, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 13440000,   train.getDepartureTime() + 13440000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 13980000,   train.getDepartureTime() + 13980000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 14940000,   train.getDepartureTime() + 15120000, true, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // TN2
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("TN2");
//        train.setPriority(410);
//        train.setDepartureTime(startDate.getTime() + 82440000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 360000, true, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 1320000,    train.getDepartureTime() + 1320000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 2580000,    train.getDepartureTime() + 2760000, true, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 3360000,    train.getDepartureTime() + 4200000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4740000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 5040000,    train.getDepartureTime() + 5040000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 5520000,    train.getDepartureTime() + 5700000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 6300000,    train.getDepartureTime() + 6960000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 7440000,    train.getDepartureTime() + 7440000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 7860000,    train.getDepartureTime() + 7860000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 8340000,    train.getDepartureTime() + 8520000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 9060000,    train.getDepartureTime() + 9060000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 9540000,    train.getDepartureTime() + 9540000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 9900000,    train.getDepartureTime() + 9900000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 10620000,   train.getDepartureTime() + 10800000, true, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 11700000,   train.getDepartureTime() + 11700000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 12360000,   train.getDepartureTime() + 12360000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 12840000,   train.getDepartureTime() + 12840000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 13320000,   train.getDepartureTime() + 13320000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 13800000,   train.getDepartureTime() + 13800000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 14100000,   train.getDepartureTime() + 14100000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 14820000,   train.getDepartureTime() + 14820000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // SE19
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE19");
//        train.setPriority(400);
//        train.setDepartureTime(startDate.getTime() + 70500000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 960000,    train.getDepartureTime() + 960000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1440000,    train.getDepartureTime() + 1440000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2280000,    train.getDepartureTime() + 2280000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2940000,    train.getDepartureTime() + 2940000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 4380000,    train.getDepartureTime() + 4380000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4740000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 5700000,    train.getDepartureTime() + 5880000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6360000,    train.getDepartureTime() + 6360000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 6780000,    train.getDepartureTime() + 6780000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 7200000,    train.getDepartureTime() + 7200000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 7740000,    train.getDepartureTime() + 7920000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8340000,    train.getDepartureTime() + 8340000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 8640000,   train.getDepartureTime() + 8640000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 9240000,   train.getDepartureTime() + 9240000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 9720000,   train.getDepartureTime() + 9720000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 10320000,   train.getDepartureTime() + 10320000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 10860000,   train.getDepartureTime() + 10860000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 11760000,   train.getDepartureTime() + 12120000, true, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // SE20
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE20");
//        train.setPriority(410);
//        train.setDepartureTime(startDate.getTime() + 17040000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 300000, true, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 1320000,    train.getDepartureTime() + 1320000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 2580000,    train.getDepartureTime() + 2760000, true, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 3360000,    train.getDepartureTime() + 3360000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 3900000,    train.getDepartureTime() + 3900000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 4200000,    train.getDepartureTime() + 4200000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 4680000,    train.getDepartureTime() + 4860000, true, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 5400000,    train.getDepartureTime() + 5400000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 5820000,    train.getDepartureTime() + 5820000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6240000,    train.getDepartureTime() + 6240000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 6720000,    train.getDepartureTime() + 7020000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 7500000,    train.getDepartureTime() + 7500000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 7980000,    train.getDepartureTime() + 7980000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 8340000,    train.getDepartureTime() + 8340000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 9060000,   train.getDepartureTime() + 9600000, true, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 10500000,   train.getDepartureTime() + 10500000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 11160000,   train.getDepartureTime() + 11160000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 11580000,   train.getDepartureTime() + 11580000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 12000000,   train.getDepartureTime() + 12000000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 12480000,   train.getDepartureTime() + 12480000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 12780000,   train.getDepartureTime() + 12780000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 13560000,   train.getDepartureTime() + 13560000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // SE23
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE23");
//        train.setPriority(400);
//        train.setDepartureTime(startDate.getTime() + 72300000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 960000,    train.getDepartureTime() + 960000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1440000,    train.getDepartureTime() + 1440000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2280000,    train.getDepartureTime() + 2280000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2940000,    train.getDepartureTime() + 2940000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 4380000,    train.getDepartureTime() + 4380000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4740000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 5700000,    train.getDepartureTime() + 5880000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6420000,    train.getDepartureTime() + 6420000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 6840000,    train.getDepartureTime() + 6840000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 7260000,    train.getDepartureTime() + 7260000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 7740000,    train.getDepartureTime() + 7740000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8160000,    train.getDepartureTime() + 8160000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 8460000,   train.getDepartureTime() + 8460000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 9060000,   train.getDepartureTime() + 9060000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 9540000,   train.getDepartureTime() + 9540000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 10140000,   train.getDepartureTime() + 10140000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 10740000,   train.getDepartureTime() + 11640000, true, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 12600000,   train.getDepartureTime() + 13440000, true, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // SE24
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("SE24");
//        train.setPriority(410);
//        train.setDepartureTime(startDate.getTime() + 6000000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 2160000, true, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 3180000,    train.getDepartureTime() + 3180000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 4380000,    train.getDepartureTime() + 4380000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 4920000,    train.getDepartureTime() + 4920000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 5460000,    train.getDepartureTime() + 5460000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 5760000,    train.getDepartureTime() + 5760000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 6180000,    train.getDepartureTime() + 6180000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 6660000,    train.getDepartureTime() + 6660000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 7080000,    train.getDepartureTime() + 7080000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 7500000,    train.getDepartureTime() + 7500000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 7980000,    train.getDepartureTime() + 7980000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 8520000,    train.getDepartureTime() + 8520000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 9000000,    train.getDepartureTime() + 9000000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 9360000,    train.getDepartureTime() + 9360000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 10020000,   train.getDepartureTime() + 10020000, false, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 10800000,   train.getDepartureTime() + 10800000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 11460000,   train.getDepartureTime() + 11460000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 11880000,   train.getDepartureTime() + 11880000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 12300000,   train.getDepartureTime() + 12300000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 12780000,   train.getDepartureTime() + 12780000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 13080000,   train.getDepartureTime() + 13080000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 13800000,   train.getDepartureTime() + 13800000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // NA1
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("NA1");
//        train.setPriority(400);
//        train.setDepartureTime(startDate.getTime() + 77400000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 960000,    train.getDepartureTime() + 960000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1440000,    train.getDepartureTime() + 1440000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2280000,    train.getDepartureTime() + 2280000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2940000,    train.getDepartureTime() + 2940000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 4380000,    train.getDepartureTime() + 4380000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4740000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 5700000,    train.getDepartureTime() + 5880000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6420000,    train.getDepartureTime() + 6420000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 6840000,    train.getDepartureTime() + 6840000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 7260000,    train.getDepartureTime() + 7260000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 7740000,    train.getDepartureTime() + 7740000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8160000,    train.getDepartureTime() + 8160000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 8460000,   train.getDepartureTime() + 8460000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 9060000,   train.getDepartureTime() + 9060000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 9600000,   train.getDepartureTime() + 10620000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 11340000,   train.getDepartureTime() + 12120000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 12780000,   train.getDepartureTime() + 15840000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 16800000,   train.getDepartureTime() + 16980000, true, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // NA2
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("NA2");
//        train.setPriority(410);
//        train.setDepartureTime(startDate.getTime() + 85380000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 180000, true, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 1140000,    train.getDepartureTime() + 1140000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 1800000,    train.getDepartureTime() + 1800000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 2460000,    train.getDepartureTime() + 2460000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 3000000,    train.getDepartureTime() + 3000000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 3540000,    train.getDepartureTime() + 3540000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 3900000,    train.getDepartureTime() + 4680000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 5820000,    train.getDepartureTime() + 5820000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 6360000,    train.getDepartureTime() + 6360000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6900000,    train.getDepartureTime() + 6900000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 7440000,    train.getDepartureTime() + 7620000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 8100000,    train.getDepartureTime() + 8100000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 8700000,    train.getDepartureTime() + 8700000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 9180000,    train.getDepartureTime() + 9180000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 9960000,    train.getDepartureTime() + 9960000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 10860000,   train.getDepartureTime() + 10860000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 11640000,   train.getDepartureTime() + 11640000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 12180000,   train.getDepartureTime() + 12180000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 12720000,   train.getDepartureTime() + 14100000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 14700000,   train.getDepartureTime() + 14700000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 15060000,   train.getDepartureTime() + 15060000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 15780000,   train.getDepartureTime() + 15780000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // NA3
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("NA3");
//        train.setPriority(400);
//        train.setDepartureTime(startDate.getTime() + 79200000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 660000,     train.getDepartureTime() + 660000, false, false)     ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 960000,    train.getDepartureTime() + 960000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1440000,    train.getDepartureTime() + 1440000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1860000,    train.getDepartureTime() + 1860000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2280000,    train.getDepartureTime() + 2280000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 2940000,    train.getDepartureTime() + 2940000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 4380000,    train.getDepartureTime() + 4380000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 4740000,    train.getDepartureTime() + 4740000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 5700000,    train.getDepartureTime() + 5880000, true, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6360000,    train.getDepartureTime() + 6360000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 6780000,    train.getDepartureTime() + 6780000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 7200000,    train.getDepartureTime() + 7200000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 7680000,    train.getDepartureTime() + 7680000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8100000,    train.getDepartureTime() + 8460000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 8880000,   train.getDepartureTime() + 9900000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 10560000,   train.getDepartureTime() + 12180000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 12720000,   train.getDepartureTime() + 14700000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 15360000,   train.getDepartureTime() + 15360000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 15960000,   train.getDepartureTime() + 16560000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 17520000,   train.getDepartureTime() + 17700000, true, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // NA4
//        train = new Train();
//        train.setId(trainId++);
//        train.setName("NA4");
//        train.setPriority(410);
//        train.setDepartureTime(startDate.getTime() + 2460000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 180000, true, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 1140000,    train.getDepartureTime() + 1500000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 2160000,    train.getDepartureTime() + 3120000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 3840000,    train.getDepartureTime() + 3840000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 4380000,    train.getDepartureTime() + 4380000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 4920000,    train.getDepartureTime() + 4920000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 5220000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 5640000,    train.getDepartureTime() + 5640000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 6120000,    train.getDepartureTime() + 6120000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 6540000,    train.getDepartureTime() + 6540000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 6960000,    train.getDepartureTime() + 6960000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 7380000,    train.getDepartureTime() + 7380000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 7800000,    train.getDepartureTime() + 7800000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 8280000,    train.getDepartureTime() + 8280000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 8700000,    train.getDepartureTime() + 10200000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 10980000,    train.getDepartureTime() + 10980000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 11760000,   train.getDepartureTime() + 11760000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 12420000,   train.getDepartureTime() + 12420000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 12840000,   train.getDepartureTime() + 12840000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 13260000,   train.getDepartureTime() + 13260000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 13740000,   train.getDepartureTime() + 13740000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 14040000,   train.getDepartureTime() + 14040000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 14820000,   train.getDepartureTime() + 14820000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        ////////////////////////////////////////////
//        // TAU HANG
//
//        // SBN1
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("SBN1");
//        train.setPriority(300);
//        train.setDepartureTime(startDate.getTime() + 84600000);
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 420000,     train.getDepartureTime() + 420000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1140000,    train.getDepartureTime() + 1140000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1740000,    train.getDepartureTime() + 1740000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2400000,    train.getDepartureTime() + 2400000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 3300000,    train.getDepartureTime() + 3300000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 4200000,    train.getDepartureTime() + 4200000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 5100000,    train.getDepartureTime() + 5100000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 5640000,    train.getDepartureTime() + 5640000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 6360000,    train.getDepartureTime() + 7140000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 7740000,    train.getDepartureTime() + 10080000, false, false)   ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 10680000,   train.getDepartureTime() + 12300000, false, false)   ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 13020000,   train.getDepartureTime() + 13020000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 13680000,   train.getDepartureTime() + 14640000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 15360000,   train.getDepartureTime() + 15360000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 15780000,   train.getDepartureTime() + 15780000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 16140000,   train.getDepartureTime() + 16140000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 17340000,   train.getDepartureTime() + 17340000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 18180000,   train.getDepartureTime() + 18180000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 19020000,   train.getDepartureTime() + 19020000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 19920000,   train.getDepartureTime() + 20340000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 21660000,   train.getDepartureTime() + 23460000, false, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // SBN2
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("SBN2");
//        train.setPriority(310);
//        train.setDepartureTime(startDate.getTime() + 24120000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 2280000, false, false)    ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 3720000,    train.getDepartureTime() + 3720000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 4620000,    train.getDepartureTime() + 4620000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 5580000,    train.getDepartureTime() + 5580000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 6840000,    train.getDepartureTime() + 8160000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 9480000,    train.getDepartureTime() + 9480000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 9900000,    train.getDepartureTime() + 9900000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 10440000,   train.getDepartureTime() + 10440000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 11100000,   train.getDepartureTime() + 11100000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 11700000,   train.getDepartureTime() + 11700000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 12360000,   train.getDepartureTime() + 12360000, false, false)   ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 12960000,   train.getDepartureTime() + 12960000, false, false)   ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 13620000,   train.getDepartureTime() + 13980000, false, false)   ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 14820000,   train.getDepartureTime() + 14820000, false, false)   ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 15420000,   train.getDepartureTime() + 15420000, false, false)   ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 16440000,   train.getDepartureTime() + 16440000, false, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 17520000,   train.getDepartureTime() + 17520000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 18600000,   train.getDepartureTime() + 18600000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 19380000,   train.getDepartureTime() + 19380000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 20100000,   train.getDepartureTime() + 20100000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 20880000,   train.getDepartureTime() + 20880000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 21360000,   train.getDepartureTime() + 21360000, false, false)   ); // Giáp Bát
//        trains.add(train);
//
//        // HBN1
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("HBN1");
//        train.setPriority(300);
//        train.setDepartureTime(startDate.getTime() + 19800000);
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 300000,     train.getDepartureTime() + 300000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1020000,    train.getDepartureTime() + 1380000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 2100000,    train.getDepartureTime() + 2100000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2760000,    train.getDepartureTime() + 2760000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 3600000,    train.getDepartureTime() + 3600000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 4440000,    train.getDepartureTime() + 4440000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 5400000,    train.getDepartureTime() + 5760000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 6360000,    train.getDepartureTime() + 6360000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 7080000,    train.getDepartureTime() + 7080000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 7680000,    train.getDepartureTime() + 7680000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 8280000,    train.getDepartureTime() + 8280000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 9000000,    train.getDepartureTime() + 11280000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 11940000,   train.getDepartureTime() + 11940000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 12540000,   train.getDepartureTime() + 12540000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 13020000,   train.getDepartureTime() + 13020000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 13500000,   train.getDepartureTime() + 13980000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 15300000,   train.getDepartureTime() + 15300000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 16140000,   train.getDepartureTime() + 16140000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 16980000,   train.getDepartureTime() + 16980000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 17760000,   train.getDepartureTime() + 17760000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 19080000,   train.getDepartureTime() + 22500000, false, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // HBN2
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("HBN2");
//        train.setPriority(310);
//        train.setDepartureTime(startDate.getTime() + 73680000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 1800000, false, false)    ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 3240000,    train.getDepartureTime() + 3240000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 4260000,    train.getDepartureTime() + 5280000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 6360000,    train.getDepartureTime() + 8340000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 9600000,    train.getDepartureTime() + 9600000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 10800000,   train.getDepartureTime() + 10800000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 11340000,   train.getDepartureTime() + 12060000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 12720000,   train.getDepartureTime() + 13380000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 14160000,   train.getDepartureTime() + 14160000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 14880000,   train.getDepartureTime() + 15240000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 16020000,   train.getDepartureTime() + 17460000, false, false)   ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 18180000,   train.getDepartureTime() + 19980000, false, false)   ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 20640000,   train.getDepartureTime() + 21960000, false, false)   ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 22800000,   train.getDepartureTime() + 24780000, false, false)   ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 25500000,   train.getDepartureTime() + 28920000, false, false)   ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 30060000,   train.getDepartureTime() + 30060000, false, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 31140000,   train.getDepartureTime() + 31140000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 32220000,   train.getDepartureTime() + 32220000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 33000000,   train.getDepartureTime() + 33000000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 33720000,   train.getDepartureTime() + 33720000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 34500000,   train.getDepartureTime() + 34500000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 34980000,   train.getDepartureTime() + 34980000, false, false)   ); // Giáp Bát
//        trains.add(train);
//
//        // HBN3
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("HBN3");
//        train.setPriority(300);
//        train.setDepartureTime(startDate.getTime() + 38400000);
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 420000,     train.getDepartureTime() + 420000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1200000,    train.getDepartureTime() + 2100000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 2880000,    train.getDepartureTime() + 2880000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 3720000,    train.getDepartureTime() + 4500000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 5400000,    train.getDepartureTime() + 5400000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 6240000,    train.getDepartureTime() + 6240000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 7080000,    train.getDepartureTime() + 7080000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 7560000,    train.getDepartureTime() + 7560000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 8160000,    train.getDepartureTime() + 8160000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 8640000,    train.getDepartureTime() + 8640000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 9120000,    train.getDepartureTime() + 9480000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 10200000,   train.getDepartureTime() + 10200000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 10740000,   train.getDepartureTime() + 10740000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 11340000,   train.getDepartureTime() + 11340000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 11760000,   train.getDepartureTime() + 11760000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 12120000,   train.getDepartureTime() + 12120000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 13320000,   train.getDepartureTime() + 13320000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 14160000,   train.getDepartureTime() + 14160000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 15000000,   train.getDepartureTime() + 15000000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 15780000,   train.getDepartureTime() + 15780000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 16980000,   train.getDepartureTime() + 18780000, false, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // HBN4
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("HBN4");
//        train.setPriority(310);
//        train.setDepartureTime(startDate.getTime() + 18960000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 1800000, false, false)    ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 3240000,    train.getDepartureTime() + 3240000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 4260000,    train.getDepartureTime() + 5220000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 6300000,    train.getDepartureTime() + 6300000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 7440000,    train.getDepartureTime() + 7440000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 8640000,    train.getDepartureTime() + 8640000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 9060000,   train.getDepartureTime() + 9060000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 9600000,   train.getDepartureTime() + 9600000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 10440000,   train.getDepartureTime() + 11220000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 11940000,   train.getDepartureTime() + 11940000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 12600000,   train.getDepartureTime() + 12600000, false, false)   ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 13200000,   train.getDepartureTime() + 13200000, false, false)   ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 13740000,   train.getDepartureTime() + 13740000, false, false)   ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 14460000,   train.getDepartureTime() + 14460000, false, false)   ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 15060000,   train.getDepartureTime() + 15060000, false, false)   ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 16200000,   train.getDepartureTime() + 17580000, false, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 18780000,   train.getDepartureTime() + 18780000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 19860000,   train.getDepartureTime() + 19860000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 20640000,   train.getDepartureTime() + 20640000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 21360000,   train.getDepartureTime() + 21360000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 22140000,   train.getDepartureTime() + 22140000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 22620000,   train.getDepartureTime() + 22620000, false, false)   ); // Giáp Bát
//        trains.add(train);
//
//        // SY1
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("SY1");
//        train.setPriority(300);
//        train.setDepartureTime(startDate.getTime() + 1020000);
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Hà Nội
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 780000,     train.getDepartureTime() + 4380000, false, false)    ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 4800000,    train.getDepartureTime() + 4800000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 5520000,    train.getDepartureTime() + 5520000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 6180000,    train.getDepartureTime() + 6180000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 6960000,    train.getDepartureTime() + 7560000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 8520000,    train.getDepartureTime() + 10020000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 10980000,   train.getDepartureTime() + 12600000, false, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 13560000,   train.getDepartureTime() + 14520000, false, false)   ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 15120000,   train.getDepartureTime() + 15120000, false, false)   ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 15840000,   train.getDepartureTime() + 15840000, false, false)   ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 16440000,   train.getDepartureTime() + 16440000, false, false)   ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 16980000,   train.getDepartureTime() + 16980000, false, false)   ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 17640000,   train.getDepartureTime() + 17640000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 18180000,   train.getDepartureTime() + 18180000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 18780000,   train.getDepartureTime() + 18780000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 19260000,   train.getDepartureTime() + 19260000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 19740000,   train.getDepartureTime() + 20100000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 21420000,   train.getDepartureTime() + 21420000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 22260000,   train.getDepartureTime() + 22260000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 23100000,   train.getDepartureTime() + 23100000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 23880000,   train.getDepartureTime() + 23880000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 25200000,   train.getDepartureTime() + 27000000, false, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // SY2
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("SY2");
//        train.setPriority(310);
//        train.setDepartureTime(startDate.getTime() + 61260000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 1800000, false, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 3240000,    train.getDepartureTime() + 3240000, false, false)    ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 4140000,    train.getDepartureTime() + 4140000, false, false)    ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 5100000,    train.getDepartureTime() + 5100000, false, false)    ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 6240000,    train.getDepartureTime() + 6240000, false, false)    ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 7440000,    train.getDepartureTime() + 7440000, false, false)    ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 7860000,    train.getDepartureTime() + 7860000, false, false)    ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 8400000,    train.getDepartureTime() + 8400000, false, false)    ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 9060000,    train.getDepartureTime() + 9420000, false, false)    ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 10140000,    train.getDepartureTime() + 10140000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 10800000,    train.getDepartureTime() + 10800000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 11400000,    train.getDepartureTime() + 11400000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 12060000,    train.getDepartureTime() + 12540000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 13380000,    train.getDepartureTime() + 14160000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 14880000,    train.getDepartureTime() + 15600000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 16740000,   train.getDepartureTime() + 16740000, false, false)   ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 17940000,   train.getDepartureTime() + 21060000, false, false)   ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 22260000,   train.getDepartureTime() + 22260000, false, false)   ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 23160000,   train.getDepartureTime() + 25260000, false, false)   ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 26100000,   train.getDepartureTime() + 26100000, false, false)   ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 26880000,   train.getDepartureTime() + 26880000, false, false)   ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 27360000,   train.getDepartureTime() + 30960000, false, false)   ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(1),    train.getDepartureTime() + 31740000,   train.getDepartureTime() + 31740000, false, false)   ); // Hà Nội
//        trains.add(train);
//
//        // GS1
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("GS1");
//        train.setPriority(200);
//        train.setDepartureTime(startDate.getTime() + 51300000);
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 420000,     train.getDepartureTime() + 420000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1320000,    train.getDepartureTime() + 1800000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 2640000,    train.getDepartureTime() + 2640000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 3420000,    train.getDepartureTime() + 3420000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 4500000,    train.getDepartureTime() + 4500000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 5580000,    train.getDepartureTime() + 5580000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 6600000,    train.getDepartureTime() + 6600000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 7200000,    train.getDepartureTime() + 7200000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 7920000,    train.getDepartureTime() + 7920000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 8460000,    train.getDepartureTime() + 8460000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 9060000,    train.getDepartureTime() + 9060000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 9840000,    train.getDepartureTime() + 10920000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 11640000,   train.getDepartureTime() + 11640000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 12300000,   train.getDepartureTime() + 12300000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 12840000,   train.getDepartureTime() + 12840000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 13260000,   train.getDepartureTime() + 13260000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 14760000,   train.getDepartureTime() + 16380000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 17340000,   train.getDepartureTime() + 17340000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 18300000,   train.getDepartureTime() + 18300000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 19200000,   train.getDepartureTime() + 19200000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 20640000,   train.getDepartureTime() + 22560000, false, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // GS2
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("GS2");
//        train.setPriority(210);
//        train.setDepartureTime(startDate.getTime() + 26580000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 1800000, false, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 3240000,    train.getDepartureTime() + 3240000, false, false)     ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 4140000,    train.getDepartureTime() + 4140000, false, false)     ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 5220000,    train.getDepartureTime() + 6180000, false, false)     ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 7440000,    train.getDepartureTime() + 8700000, false, false)     ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 10020000,    train.getDepartureTime() + 10020000, false, false)     ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 10440000,    train.getDepartureTime() + 10440000, false, false)     ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 10980000,    train.getDepartureTime() + 10980000, false, false)     ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 11640000,    train.getDepartureTime() + 11640000, false, false)     ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 12360000,   train.getDepartureTime() + 13080000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 13860000,   train.getDepartureTime() + 13860000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 14460000,   train.getDepartureTime() + 14460000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 15000000,   train.getDepartureTime() + 15000000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 15720000,   train.getDepartureTime() + 15720000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 16320000,   train.getDepartureTime() + 16320000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 17460000,   train.getDepartureTime() + 18240000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 19440000,   train.getDepartureTime() + 19440000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 20520000,   train.getDepartureTime() + 20520000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 21300000,   train.getDepartureTime() + 21300000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 22140000,   train.getDepartureTime() + 23040000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 23940000,   train.getDepartureTime() + 23940000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 24300000,   train.getDepartureTime() + 24300000, false, false)    ); // Giáp Bát
//        trains.add(train);
//
//        // HGD1
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("HGD1");
//        train.setPriority(200);
//        train.setDepartureTime(startDate.getTime() + 60000000);
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 0,          train.getDepartureTime() + 0, false, false)          ); // Giáp Bát
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 420000,     train.getDepartureTime() + 420000, false, false)     ); // Văn Điển
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 1200000,    train.getDepartureTime() + 1200000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 1920000,    train.getDepartureTime() + 1920000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 2700000,    train.getDepartureTime() + 2700000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 3780000,    train.getDepartureTime() + 3780000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 4980000,    train.getDepartureTime() + 5520000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 6660000,    train.getDepartureTime() + 6660000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 7260000,    train.getDepartureTime() + 7260000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 7980000,    train.getDepartureTime() + 7980000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 8520000,    train.getDepartureTime() + 8520000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 9120000,    train.getDepartureTime() + 9120000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 9840000,    train.getDepartureTime() + 9840000, false, false)   ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 10500000,   train.getDepartureTime() + 10500000, false, false)   ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 11160000,   train.getDepartureTime() + 11160000, false, false)   ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 11700000,   train.getDepartureTime() + 11700000, false, false)   ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 12120000,   train.getDepartureTime() + 12120000, false, false)   ); // Gềnh
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 13620000,   train.getDepartureTime() + 13980000, false, false)   ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 14820000,   train.getDepartureTime() + 14820000, false, false)   ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 15780000,   train.getDepartureTime() + 15780000, false, false)   ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 16740000,   train.getDepartureTime() + 17100000, false, false)   ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 18540000,   train.getDepartureTime() + 29220000, false, false)   ); // Thanh Hóa
//        trains.add(train);
//
//        // HGD2
//        train = new Train();
//        train.setClassification(1); // tau hang
//        train.setId(trainId++);
//        train.setName("HGD2");
//        train.setPriority(210);
//        train.setDepartureTime(startDate.getTime() + 66660000);
//        train.getStops().add(new Stop( getStation(23),   train.getDepartureTime() + 0,          train.getDepartureTime() + 1800000, false, false)     ); // Thanh Hóa
//        train.getStops().add(new Stop( getStation(22),   train.getDepartureTime() + 3240000,    train.getDepartureTime() + 4020000, false, false)     ); // Nghĩa Trang
//        train.getStops().add(new Stop( getStation(21),   train.getDepartureTime() + 5040000,    train.getDepartureTime() + 5040000, false, false)     ); // Đò Lèn
//        train.getStops().add(new Stop( getStation(20),   train.getDepartureTime() + 6000000,    train.getDepartureTime() + 6000000, false, false)     ); // Bỉm Sơn
//        train.getStops().add(new Stop( getStation(19),   train.getDepartureTime() + 7140000,    train.getDepartureTime() + 7140000, false, false)     ); // Đồng Giao
//        train.getStops().add(new Stop( getStation(18),   train.getDepartureTime() + 8340000,    train.getDepartureTime() + 8340000, false, false)     ); // Gềnh
//        train.getStops().add(new Stop( getStation(17),   train.getDepartureTime() + 8760000,    train.getDepartureTime() + 8760000, false, false)     ); // Cầu Yên
//        train.getStops().add(new Stop( getStation(16),   train.getDepartureTime() + 9300000,    train.getDepartureTime() + 9840000, false, false)     ); // Ninh Bình
//        train.getStops().add(new Stop( getStation(15),   train.getDepartureTime() + 10500000,    train.getDepartureTime() + 11220000, false, false)     ); // Cát Đằng
//        train.getStops().add(new Stop( getStation(14),   train.getDepartureTime() + 11940000,   train.getDepartureTime() + 12660000, false, false)    ); // Núi Gôi
//        train.getStops().add(new Stop( getStation(13),   train.getDepartureTime() + 13440000,   train.getDepartureTime() + 13440000, false, false)    ); // Trình Xuyên
//        train.getStops().add(new Stop( getStation(12),   train.getDepartureTime() + 14040000,   train.getDepartureTime() + 14040000, false, false)    ); // Nam Định
//        train.getStops().add(new Stop( getStation(11),   train.getDepartureTime() + 14580000,   train.getDepartureTime() + 14580000, false, false)    ); // Đặng Xá
//        train.getStops().add(new Stop( getStation(10),   train.getDepartureTime() + 15300000,   train.getDepartureTime() + 17460000, false, false)    ); // Cầu Họ
//        train.getStops().add(new Stop( getStation(9),    train.getDepartureTime() + 18180000,   train.getDepartureTime() + 18180000, false, false)    ); // Bình Lục
//        train.getStops().add(new Stop( getStation(8),    train.getDepartureTime() + 19320000,   train.getDepartureTime() + 19740000, false, false)    ); // Phủ Lý
//        train.getStops().add(new Stop( getStation(7),    train.getDepartureTime() + 20940000,   train.getDepartureTime() + 21420000, false, false)    ); // Đồng Văn
//        train.getStops().add(new Stop( getStation(6),    train.getDepartureTime() + 22620000,   train.getDepartureTime() + 22620000, false, false)    ); // Phú Xuyên
//        train.getStops().add(new Stop( getStation(5),    train.getDepartureTime() + 23400000,   train.getDepartureTime() + 23400000, false, false)    ); // Chợ Tía
//        train.getStops().add(new Stop( getStation(4),    train.getDepartureTime() + 24120000,   train.getDepartureTime() + 24120000, false, false)    ); // Thường Tín
//        train.getStops().add(new Stop( getStation(3),    train.getDepartureTime() + 25020000,   train.getDepartureTime() + 25740000, false, false)    ); // Văn Điển
//        train.getStops().add(new Stop( getStation(2),    train.getDepartureTime() + 26220000,   train.getDepartureTime() + 26220000, false, false)    ); // Giáp Bát
//        trains.add(train);
    }

    public void shutdown() {

    }

}

