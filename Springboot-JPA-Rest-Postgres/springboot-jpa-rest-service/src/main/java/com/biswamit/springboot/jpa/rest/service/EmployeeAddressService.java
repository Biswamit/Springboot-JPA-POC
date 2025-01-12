package com.biswamit.springboot.jpa.rest.service;

import java.util.List;
import java.util.Optional;

public interface EmployeeAddressService<T, EmployeeAutoId> {
    Optional<T> getByAutoId(EmployeeAutoId autoId);

    T save(T address);

    List<T> saveAll(List<T> addresses);

    T deleteByAutoId(EmployeeAutoId autoId);
}
