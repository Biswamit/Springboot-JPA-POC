/*
 * Copyright (C) 2021 McAfee, LLC  All Rights Reserved.
 */
package com.biswamit.springboot.jpa.rest.service.bi.sharedpk;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.bi.sharedpk.O2OBiSharedPkEmployeeRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.O2OEmployeeBiSharedPkChildFetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultO2OBiSharedPkEmployeeService implements O2OBiSharedPkEmployeeService<O2OEmployeeBiSharedPkChildFetch, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OBiSharedPkEmployeeService.class);
    private O2OBiSharedPkEmployeeRepository employeeRepository;

    @Autowired
    public DefaultO2OBiSharedPkEmployeeService(O2OBiSharedPkEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OEmployeeBiSharedPkChildFetch> getByAutoId(Long autoId) {
        LOG.debug("Returning employee with autoId {}", autoId);
        return Optional.ofNullable(employeeRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OEmployeeBiSharedPkChildFetch.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param employeeId
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OEmployeeBiSharedPkChildFetch> getByEmployeeIdAndAutoId(UUID employeeId, Long autoId) {
        LOG.debug("Returning employee with employeeId {} and autoId {}", employeeId, autoId);
        return Optional.ofNullable(employeeRepository.findByEmployeeIdAndAutoId(employeeId, autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OEmployeeBiSharedPkChildFetch.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OEmployeeBiSharedPkChildFetch> getAllEmployees(Pageable pageable) {
        LOG.debug("Returning all employees");
        Page<O2OEmployeeBiSharedPkChildFetch> emplpoyeePage = employeeRepository.findAll(pageable);
        if (emplpoyeePage.isEmpty()) {
            throw new JpaRestNotFoundException(JpaRestErrorCode.EmployeeNotFound);
        }
        return emplpoyeePage;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public O2OEmployeeBiSharedPkChildFetch save(O2OEmployeeBiSharedPkChildFetch employee) {
        LOG.debug("Saving employee {}", employee);
        O2OEmployeeBiSharedPkChildFetch savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    /**
     * @param employees
     * @return
     */
    @Override
    public List<O2OEmployeeBiSharedPkChildFetch> saveAll(List<O2OEmployeeBiSharedPkChildFetch> employees) {
        LOG.debug("Saving employees {}", employees);
        List<O2OEmployeeBiSharedPkChildFetch> savedO2OEmployeeUniSharedPkNoChildFetches = employeeRepository.saveAll(employees);
        return savedO2OEmployeeUniSharedPkNoChildFetches;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OEmployeeBiSharedPkChildFetch deleteByAutoId(Long autoId) {
        LOG.debug("Deleting employee with autoId {}", autoId);
        O2OEmployeeBiSharedPkChildFetch deletedEmployee = employeeRepository.deleteByAutoId(autoId);
        return deletedEmployee;
    }
}
