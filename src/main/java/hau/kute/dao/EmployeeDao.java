package hau.kute.dao;

import hau.kute.model.Employee;

import java.util.List;

/**
 * Created by HauKute on 7/24/2018.
 */
public interface EmployeeDao {
    Employee findById(int id);

    void saveEmployee(Employee employee);

    List<Employee> findAllEmployees();

    void deleteEmployeeBySsn(String ssn);

    Employee findEmployeeBySsn(String ssn);
}
