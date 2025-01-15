package com.biswamit.springboot.jpa.rest.service.uni.sharedpk.c2p;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface O2OC2PUniSharedPkAddressService<T, EmployeeAutoId> {
    Optional<T> getByEmployeeAutoId(EmployeeAutoId autoId);

    Page<T> getAllAddress(Pageable pageable);

    T save(T address);

    List<T> saveAll(List<T> addresses);

    T deleteByAutoId(EmployeeAutoId autoId);
}
