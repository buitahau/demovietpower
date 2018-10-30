package hau.kute.service;

import hau.kute.model.Employee;

import java.util.List;

/**
 * Created by HauKute on 7/21/2018.
 */
public interface EmployeeService {
    List<Employee> findAllEmployees();

    boolean isEmployeeSsnUnique(Integer id, String ssn);

    void saveEmployee(Employee employee);

    Employee findEmployeeBySsn(String ssn);

    void updateEmployee(Employee employee);

    void deleteEmployeeBySsn(String ssn);

    Employee findById(int id);
}
