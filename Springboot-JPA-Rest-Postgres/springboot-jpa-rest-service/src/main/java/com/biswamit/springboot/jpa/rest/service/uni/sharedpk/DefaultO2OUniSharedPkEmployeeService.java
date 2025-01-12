/*
 * Copyright (C) 2021 McAfee, LLC  All Rights Reserved.
 */
package com.biswamit.springboot.jpa.rest.service.uni.sharedpk;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.O2OUniSharedPkEmployeeRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.O2OEmployeeUniSharedPkNoChildFetch;
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
public class DefaultO2OUniSharedPkEmployeeService implements O2OUniSharedPkEmployeeService<O2OEmployeeUniSharedPkNoChildFetch, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OUniSharedPkEmployeeService.class);
    private O2OUniSharedPkEmployeeRepository employeeRepository;

    @Autowired
    public DefaultO2OUniSharedPkEmployeeService(O2OUniSharedPkEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OEmployeeUniSharedPkNoChildFetch> getByAutoId(Long autoId) {
        LOG.debug("Returning employee with autoId {}", autoId);
        return Optional.ofNullable(employeeRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OEmployeeUniSharedPkNoChildFetch.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param employeeId
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OEmployeeUniSharedPkNoChildFetch> getByEmployeeIdAndAutoId(UUID employeeId, Long autoId) {
        LOG.debug("Returning employee with employeeId {} and autoId {}", employeeId, autoId);
        return Optional.ofNullable(employeeRepository.findByEmployeeIdAndAutoId(employeeId, autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OEmployeeUniSharedPkNoChildFetch.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OEmployeeUniSharedPkNoChildFetch> getAllEmployees(Pageable pageable) {
        LOG.debug("Returning all employees");
        Page<O2OEmployeeUniSharedPkNoChildFetch> productPage = employeeRepository.findAll(pageable);
        if (productPage.isEmpty()) {
            throw new JpaRestNotFoundException(JpaRestErrorCode.EmployeeNotFound);
        }
        return productPage;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public O2OEmployeeUniSharedPkNoChildFetch save(O2OEmployeeUniSharedPkNoChildFetch employee) {
        LOG.debug("Saving employee {}", employee);
        O2OEmployeeUniSharedPkNoChildFetch savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    /**
     * @param employees
     * @return
     */
    @Override
    public List<O2OEmployeeUniSharedPkNoChildFetch> saveAll(List<O2OEmployeeUniSharedPkNoChildFetch> employees) {
        LOG.debug("Saving employees {}", employees);
        List<O2OEmployeeUniSharedPkNoChildFetch> savedEmployees = employeeRepository.saveAll(employees);
        return savedEmployees;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OEmployeeUniSharedPkNoChildFetch deleteByAutoId(Long autoId) {
        LOG.debug("Deleting employee with autoId {}", autoId);
        O2OEmployeeUniSharedPkNoChildFetch deletedEmployee = employeeRepository.deleteByAutoId(autoId);
        return deletedEmployee;
    }
}
