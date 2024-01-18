package lk.ijse.pos_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String oId;
    private String oDate;
    private double oTotal;
    private double oSubTotal;
    private int oDiscount;
    private double oBalance;

    private List<ItemDTO> Items;
    private CustomerDTO customerDTO;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public double getoTotal() {
        return oTotal;
    }

    public void setoTotal(double oTotal) {
        this.oTotal = oTotal;
    }

    public double getoSubTotal() {
        return oSubTotal;
    }

    public void setoSubTotal(double oSubTotal) {
        this.oSubTotal = oSubTotal;
    }

    public int getoDiscount() {
        return oDiscount;
    }

    public void setoDiscount(int oDiscount) {
        this.oDiscount = oDiscount;
    }

    public double getoBalance() {
        return oBalance;
    }

    public void setoBalance(double oBalance) {
        this.oBalance = oBalance;
    }

    public List<ItemDTO> getItems() {
        return Items;
    }

    public void setItems(List<ItemDTO> items) {
        Items = items;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
