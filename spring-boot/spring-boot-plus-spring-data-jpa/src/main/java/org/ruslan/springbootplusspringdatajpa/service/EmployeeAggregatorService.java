package org.ruslan.springbootplusspringdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.ruslan.springbootplusspringdatajpa.model.EmployeeAgregation;
import org.ruslan.springbootplusspringdatajpa.repository.EmployeeAgregationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeAggregatorService {

    private final EmployeeService employeeService;
    private final EmployeeAgregationRepository employeeAgregationRepository;

    public List<EmployeeAgregation> getAllAverageBySalary() {

        return employeeAgregationRepository.findAllAverageBySalary();
    }

    public List<EmployeeAgregation> getAllAverageByMax() {

        return employeeAgregationRepository.findAllAverageByMax();
    }

    public List<EmployeeAgregation> getAllAverageByMin() {

        return employeeAgregationRepository.findAllAverageByMin();
    }

    public List<EmployeeAgregation> getAllAverageBySum() {

        return employeeAgregationRepository.findAllAverageBySum();
    }

}
