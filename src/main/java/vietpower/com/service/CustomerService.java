package vietpower.com.service;

import vietpower.com.model.Customer;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface CustomerService {
    List<Customer> findAll(Long machineId);
    Customer findById(Long machineId, Long customerId);

    void save(Customer customer);

    void update(Customer customer);

    Customer addOrUpdate(Customer customer);

    void delete(Customer customer);
}
