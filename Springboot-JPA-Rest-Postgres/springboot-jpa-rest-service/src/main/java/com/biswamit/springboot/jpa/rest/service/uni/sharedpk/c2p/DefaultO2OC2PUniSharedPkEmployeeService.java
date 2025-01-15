/*
 * Copyright (C) 2021 McAfee, LLC  All Rights Reserved.
 */
package com.biswamit.springboot.jpa.rest.service.uni.sharedpk.c2p;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.c2p.O2OC2PUniSharedPkEmployeeRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.c2p.O2OC2PEmployeeUniSharedPk;
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
public class DefaultO2OC2PUniSharedPkEmployeeService implements O2OC2PUniSharedPkEmployeeService<O2OC2PEmployeeUniSharedPk, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OC2PUniSharedPkEmployeeService.class);
    private O2OC2PUniSharedPkEmployeeRepository employeeRepository;

    @Autowired
    public DefaultO2OC2PUniSharedPkEmployeeService(O2OC2PUniSharedPkEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OC2PEmployeeUniSharedPk> getByAutoId(Long autoId) {
        LOG.debug("Returning employee with autoId {}", autoId);
        return Optional.ofNullable(employeeRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OC2PEmployeeUniSharedPk.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param employeeId
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OC2PEmployeeUniSharedPk> getByEmployeeIdAndAutoId(UUID employeeId, Long autoId) {
        LOG.debug("Returning employee with employeeId {} and autoId {}", employeeId, autoId);
        return Optional.ofNullable(employeeRepository.findByEmployeeIdAndAutoId(employeeId, autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OC2PEmployeeUniSharedPk.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OC2PEmployeeUniSharedPk> getAllEmployees(Pageable pageable) {
        LOG.debug("Returning all employees");
        Page<O2OC2PEmployeeUniSharedPk> productPage = employeeRepository.findAll(pageable);
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
    public O2OC2PEmployeeUniSharedPk save(O2OC2PEmployeeUniSharedPk employee) {
        LOG.debug("Saving employee {}", employee);
        O2OC2PEmployeeUniSharedPk savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    /**
     * @param employees
     * @return
     */
    @Override
    public List<O2OC2PEmployeeUniSharedPk> saveAll(List<O2OC2PEmployeeUniSharedPk> employees) {
        LOG.debug("Saving employees {}", employees);
        List<O2OC2PEmployeeUniSharedPk> savedEmployees = employeeRepository.saveAll(employees);
        return savedEmployees;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OC2PEmployeeUniSharedPk deleteByAutoId(Long autoId) {
        LOG.debug("Deleting employee with autoId {}", autoId);
        O2OC2PEmployeeUniSharedPk deletedEmployee = employeeRepository.deleteByAutoId(autoId);
        return deletedEmployee;
    }
}
