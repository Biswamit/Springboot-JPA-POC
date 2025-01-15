package com.biswamit.springboot.jpa.rest.service.uni.sharedpk.p2c;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface O2OP2CUniSharedPkAddressService<T, EmployeeAutoId> {
    Optional<T> getByEmployeeAutoId(EmployeeAutoId autoId);

    Page<T> getAllAddress(Pageable pageable);

    T save(T address);

    List<T> saveAll(List<T> addresses);

    T deleteByAutoId(EmployeeAutoId autoId);
}
