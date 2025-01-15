package com.biswamit.springboot.jpa.rest.service.bi.sharedpk.p2c;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface O2OP2CBiSharedPkEmployeeService<T, EmployeeAutoId> {

    Optional<T> getByAutoId(EmployeeAutoId autoId);

    Optional<T> getByEmployeeIdAndAutoId(UUID employeeId, EmployeeAutoId autoId);

    Page<T> getAllEmployees(Pageable pageable);

    T save(T employee);

    List<T> saveAll(List<T> employees);

    T deleteByAutoId(EmployeeAutoId autoId);
}
