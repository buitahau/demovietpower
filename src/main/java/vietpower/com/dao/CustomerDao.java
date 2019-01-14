package vietpower.com.dao;

import vietpower.com.model.Customer;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface CustomerDao {
    void persist(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);

    List<Customer> findAll(Long machineId);

    Customer findById(Long machineId, Long customerId);
}
