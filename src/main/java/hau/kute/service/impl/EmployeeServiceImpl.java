package hau.kute.service.impl;

import hau.kute.dao.EmployeeDao;
import hau.kute.model.Employee;
import hau.kute.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HauKute on 7/24/2018.
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @Override
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }

    @Override
    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
        Employee employee = findEmployeeBySsn(ssn);
        return (employee == null) || ((id != null) && (employee.getId() == id));
    }

    @Override
    public void saveEmployee(Employee employee) {
        dao.saveEmployee(employee);
    }

    @Override
    public Employee findEmployeeBySsn(String ssn) {
        return dao.findEmployeeBySsn(ssn);
    }

    /*
    * Since the method is running with Transaction, No need to call hibernate update explicitly.
    * Just fetch the entity from db and update it with proper values within transaction.
    * It will be updated in db once transaction ends.
    */
    @Override
    public void updateEmployee(Employee employee) {
        Employee entity = dao.findById(employee.getId());
        if(entity != null){
            employee.setName(employee.getName());
            employee.setJoiningDate(employee.getJoiningDate());
            employee.setSalary(employee.getSalary());
            employee.setSsn(employee.getSsn());
        }
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }

    @Override
    public Employee findById(int id) {
        return dao.findById(id);
    }
}
