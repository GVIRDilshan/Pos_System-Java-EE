package lk.ijse.pos_system.bo.custom;

import lk.ijse.pos_system.bo.SuperBO;
import lk.ijse.pos_system.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(CustomerDTO customerDTO) throws Exception;
    CustomerDTO searchCustomer(String id) throws Exception;
    boolean updateCustomer(CustomerDTO customerDTO) throws Exception;
    boolean deleteCustomer(String id) throws Exception;
    List<CustomerDTO> getAllCustomers() throws Exception;

}
