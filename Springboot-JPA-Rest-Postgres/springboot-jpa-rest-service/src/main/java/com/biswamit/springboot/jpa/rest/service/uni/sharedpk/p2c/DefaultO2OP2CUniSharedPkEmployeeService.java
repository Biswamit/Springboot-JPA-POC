/*
 * Copyright (C) 2021 McAfee, LLC  All Rights Reserved.
 */
package com.biswamit.springboot.jpa.rest.service.uni.sharedpk.p2c;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.p2c.O2OP2CUniSharedPkEmployeeRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.p2c.O2OP2CEmployeeUniSharedPk;
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
public class DefaultO2OP2CUniSharedPkEmployeeService implements O2OP2CUniSharedPkEmployeeService<O2OP2CEmployeeUniSharedPk, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OP2CUniSharedPkEmployeeService.class);
    private O2OP2CUniSharedPkEmployeeRepository employeeRepository;

    @Autowired
    public DefaultO2OP2CUniSharedPkEmployeeService(O2OP2CUniSharedPkEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OP2CEmployeeUniSharedPk> getByAutoId(Long autoId) {
        LOG.debug("Returning employee with autoId {}", autoId);
        return Optional.ofNullable(employeeRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OP2CEmployeeUniSharedPk.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param employeeId
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OP2CEmployeeUniSharedPk> getByEmployeeIdAndAutoId(UUID employeeId, Long autoId) {
        LOG.debug("Returning employee with employeeId {} and autoId {}", employeeId, autoId);
        return Optional.ofNullable(employeeRepository.findByEmployeeIdAndAutoId(employeeId, autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OP2CEmployeeUniSharedPk.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OP2CEmployeeUniSharedPk> getAllEmployees(Pageable pageable) {
        LOG.debug("Returning all employees");
        Page<O2OP2CEmployeeUniSharedPk> productPage = employeeRepository.findAll(pageable);
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
    public O2OP2CEmployeeUniSharedPk save(O2OP2CEmployeeUniSharedPk employee) {
        LOG.debug("Saving employee {}", employee);
        O2OP2CEmployeeUniSharedPk savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    /**
     * @param employees
     * @return
     */
    @Override
    public List<O2OP2CEmployeeUniSharedPk> saveAll(List<O2OP2CEmployeeUniSharedPk> employees) {
        LOG.debug("Saving employees {}", employees);
        List<O2OP2CEmployeeUniSharedPk> savedEmployees = employeeRepository.saveAll(employees);
        return savedEmployees;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OP2CEmployeeUniSharedPk deleteByAutoId(Long autoId) {
        LOG.debug("Deleting employee with autoId {}", autoId);
        O2OP2CEmployeeUniSharedPk deletedEmployee = employeeRepository.deleteByAutoId(autoId);
        return deletedEmployee;
    }
}
