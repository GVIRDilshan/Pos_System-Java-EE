package lk.ijse.pos_system.bo.custom.impl;

import lk.ijse.pos_system.bo.custom.CustomerBO;
import lk.ijse.pos_system.dao.DAOFactory;
import lk.ijse.pos_system.dao.custom.CustomerDAO;
import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.entity.Customer;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        customerDAO.add(modelMapper.map(customerDTO, Customer.class));
        return true;

    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer customer = customerDAO.search(id);
        return modelMapper.map(customer, CustomerDTO.class);

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        customerDAO.update(modelMapper.map(customerDTO, Customer.class));
        return true;

    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        customerDAO.delete(id);
        return true;

    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> customerList = customerDAO.getAll();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customerList) {
            customerDTOList.add(modelMapper.map(customer, CustomerDTO.class));

        }

        return customerDTOList;

    }

}
