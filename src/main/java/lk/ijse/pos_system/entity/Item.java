package lk.ijse.pos_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Item {
    private String iCode;
    private String iName;
    private double iPrice;
    private int iQty;

}
