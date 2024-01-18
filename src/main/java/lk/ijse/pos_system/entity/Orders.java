package lk.ijse.pos_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@Data
//@ToString
@Getter
@Setter
public class Orders {
    private String oId;
    private Date oDate;
    private double oTotal;
    private double oSubTotal;
    private int oDiscount;
    private double oBalance;

    private Customer customer;
    private List<Item> items;

}
