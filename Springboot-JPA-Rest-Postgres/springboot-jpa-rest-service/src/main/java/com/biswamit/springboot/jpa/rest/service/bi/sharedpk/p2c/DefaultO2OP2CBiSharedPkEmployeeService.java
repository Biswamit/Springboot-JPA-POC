/*
 * Copyright (C) 2021 McAfee, LLC  All Rights Reserved.
 */
package com.biswamit.springboot.jpa.rest.service.bi.sharedpk.p2c;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.bi.sharedpk.p2c.O2OP2CBiSharedPkEmployeeRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.p2c.O2OP2CEmployeeBiSharedPk;
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
public class DefaultO2OP2CBiSharedPkEmployeeService implements O2OP2CBiSharedPkEmployeeService<O2OP2CEmployeeBiSharedPk, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OP2CBiSharedPkEmployeeService.class);
    private O2OP2CBiSharedPkEmployeeRepository employeeRepository;

    @Autowired
    public DefaultO2OP2CBiSharedPkEmployeeService(O2OP2CBiSharedPkEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OP2CEmployeeBiSharedPk> getByAutoId(Long autoId) {
        LOG.debug("Returning employee with autoId {}", autoId);
        return Optional.ofNullable(employeeRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OP2CEmployeeBiSharedPk.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param employeeId
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OP2CEmployeeBiSharedPk> getByEmployeeIdAndAutoId(UUID employeeId, Long autoId) {
        LOG.debug("Returning employee with employeeId {} and autoId {}", employeeId, autoId);
        return Optional.ofNullable(employeeRepository.findByEmployeeIdAndAutoId(employeeId, autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
        /*return O2OP2CEmployeeBiSharedPk.builder().autoId(product.getAutoId()).productId(product.getProductId())
                        .createdTime(auditLog.getCreatedTime()).updatedTIme(auditLog.getUpdatedTime())).build();*/
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OP2CEmployeeBiSharedPk> getAllEmployees(Pageable pageable) {
        LOG.debug("Returning all employees");
        Page<O2OP2CEmployeeBiSharedPk> emplpoyeePage = employeeRepository.findAll(pageable);
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
    public O2OP2CEmployeeBiSharedPk save(O2OP2CEmployeeBiSharedPk employee) {
        LOG.debug("Saving employee {}", employee);
        O2OP2CEmployeeBiSharedPk savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    /**
     * @param employees
     * @return
     */
    @Override
    public List<O2OP2CEmployeeBiSharedPk> saveAll(List<O2OP2CEmployeeBiSharedPk> employees) {
        LOG.debug("Saving employees {}", employees);
        List<O2OP2CEmployeeBiSharedPk> savedO2OEmployeeUniSharedPkNoChildFetches = employeeRepository.saveAll(employees);
        return savedO2OEmployeeUniSharedPkNoChildFetches;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OP2CEmployeeBiSharedPk deleteByAutoId(Long autoId) {
        LOG.debug("Deleting employee with autoId {}", autoId);
        O2OP2CEmployeeBiSharedPk deletedEmployee = employeeRepository.deleteByAutoId(autoId);
        return deletedEmployee;
    }
}
