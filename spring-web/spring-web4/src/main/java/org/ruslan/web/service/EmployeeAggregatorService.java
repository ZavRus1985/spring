package org.ruslan.web.service;

import org.ruslan.web.model.EmployeeAgregation;
import org.ruslan.web.repository.EmployeeAgregationRepository;
import org.ruslan.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeAggregatorService {

    private final EmployeeService employeeService;
    private final EmployeeAgregationRepository employeeAgregationRepository;

    @Autowired
    public EmployeeAggregatorService(EmployeeService employeeService, EmployeeAgregationRepository employeeAgregationRepository) {
        this.employeeService = employeeService;
        this.employeeAgregationRepository = employeeAgregationRepository;
    }

    //5. Добавить метод, группирующий работников по отделам с агрегацией по средней зарплате,
// максимальной, минимальной и сумме.
// Такой список (отдел – зарплата) необходимо отображать на HTML-странице.
// Выбор должен осуществляться с помощью параметра запроса

    public List<EmployeeAgregation> aggregationByAverageSalary() {

        //employeeList.stream().collect(Collectors.groupingBy(
        //                Employee::getDepartment,
        //                Collectors.averagingDouble(Employee::getSalary)
        //        ));

        List<Object[]> objects  = employeeAgregationRepository.aggregationByAverageSalary();
        return objects
                .stream()
                .map(arr -> new EmployeeAgregation((String) arr[0], (Double) arr[1]))
                .toList();
    }


    public List<EmployeeAgregation> aggregationByMaxSalary() {
        List<Object[]> objects  = employeeAgregationRepository.aggregationByMaxSalary();
        return objects
                .stream()
                .map(arr -> {
                    Integer valueInt =(Integer) arr[1];
                    return new EmployeeAgregation((String) arr[0], valueInt.doubleValue());
                })
                .toList();
    }

    public List<EmployeeAgregation> aggregationByMinSalary() {
        List<Object[]> objects  = employeeAgregationRepository.aggregationByMinSalary();
        return objects
                .stream()
                .map(arr -> {
                    Integer valueInt =(Integer) arr[1];
                    return new EmployeeAgregation((String) arr[0], valueInt.doubleValue());
                })
                .toList();
    }

    public List<EmployeeAgregation> aggregationBySumSalary() {
        List<Object[]> objects  = employeeAgregationRepository.aggregationBySumSalary();
        return objects
                .stream()
                .map(arr -> {
                    Long valueLong =(Long) arr[1];
                    return new EmployeeAgregation((String) arr[0], valueLong.doubleValue());
                })
                .toList();
    }
}
