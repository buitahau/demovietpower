package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.BaseDao;
import vietpower.com.dao.CustomerDao;
import vietpower.com.dao.MachineDao;
import vietpower.com.dao.ProductBaseDao;
import vietpower.com.model.*;
import vietpower.com.service.BaseService;
import vietpower.com.service.CustomerService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("customerServiceImpl")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private MachineDao machineDao;

    @Override
    public List<Customer> findAll(Long machineId) {
        return customerDao.findAll(machineId);
    }

    @Override
    public Customer findById(Long machineId, Long customerId) {
        return customerDao.findById(machineId, customerId);
    }

    @Override
    public void save(Customer customer) {
        customerDao.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public Customer addOrUpdate(Customer customer) {
        if(customer.getCustomerId() != null && customer.getCustomerId() > 0){
            Customer dbItem = this.customerDao.findById(customer.getMachine().getMachineId(), customer.getCustomerId());
            dbItem.setName(customer.getName());
            dbItem.setPhone(customer.getPhone());
            dbItem.setEmail(customer.getEmail());
            dbItem.setAddress(customer.getAddress());
            dbItem.setNote(customer.getNote());
            this.customerDao.update(dbItem);
            return dbItem;

        } else {
            Machine machine = this.machineDao.findById(customer.getMachine().getMachineId());
            customer.setMachine(machine);
            this.customerDao.persist(customer);
            return customer;
        }
    }

    @Override
    public ResponseMessage delete(Customer customer) {
        if(customer.getCustomerId() != null && customer.getCustomerId() > 0) {
            Customer dbItem = this.customerDao.findById(customer.getMachine().getMachineId(), customer.getCustomerId());
            if(dbItem.getMachine().getMachineId().equals(customer.getMachine().getMachineId())){
                this.customerDao.delete(dbItem);
                return new ResponseMessage(ResponseMessage.RESPONSE_SUCCESS_TYPE, "Delete successful!");
            } else {
                return new ResponseMessage(ResponseMessage.RESPONSE_ERROR_TYPE, "The customer not belong the shop.");
            }
        } else {
            return new ResponseMessage(ResponseMessage.RESPONSE_ERROR_TYPE, "Cannot find the customer!");
        }
    }
}
