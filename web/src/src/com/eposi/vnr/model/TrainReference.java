package com.eposi.vnr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Doan tau mau
 */
public class TrainReference implements Serializable {
    private static final long serialVersionUID = 3117009365839315239L;

    private String   id;
    private String name;
    private int    classification; // TAU KHACH (0) hoac TAU HANG(1), default: 0
    private long   departureTime; // giờ khởi hành

    // higher value higher priority, default = 0 = lowest priority
    //    600 - Ưu tiên thứ nhất : Tàu SE3/SE4 (Ưu tiên tuyệt đối).
    //    500 - Ưu tiên thứ hai : Tàu SE1/SE2, SE5/SE6, SE7/SE8,
    //    400 - Ưu tiên thứ ba : Các tàu TN, SE19/SE20, SE21/SE22 và Tàu khách nhanh.
    //    300 - Ưu tiên thứ tư : Tàu khách thường, tàu SBN, SY và HBN.
    //    200 - Ưu tiên thứ năm : Tàu GS, HGĐ, HSĐ.
    //    100 - Ưu tiên thứ sáu: Cuối cùng là tàu hàng không quy định trong Biều đồ chạy tàu.

    private int priority = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }
}
