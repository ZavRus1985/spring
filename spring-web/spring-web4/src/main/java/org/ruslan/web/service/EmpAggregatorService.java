package org.ruslan.web.service;

import org.ruslan.web.EmployeeAgregation;
import org.ruslan.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmpAggregatorService {

    EmployeeService employeeService;
    private final EmployeeRepository er;

    @Autowired
    public EmpAggregatorService(EmployeeService employeeService, EmployeeRepository er) {
        this.employeeService = employeeService;
        this.er = er;
    }

    //5. Добавить метод, группирующий работников по отделам с агрегацией по средней зарплате,
// максимальной, минимальной и сумме.
// Такой список (отдел – зарплата) необходимо отображать на HTML-странице.
// Выбор должен осуществляться с помощью параметра запроса

    public List<Object[]> aggregationByAverageSalary() {

        //employeeList.stream().collect(Collectors.groupingBy(
        //                Employee::getDepartment,
        //                Collectors.averagingDouble(Employee::getSalary)
        //        ));

       // List<EmployeeAgregation> empAgregations  = Arrays.stream(er.aggregationByAverageSalary().listIterator().next()).map(obj -> )
        return er.aggregationByAverageSalary();
    }


}
