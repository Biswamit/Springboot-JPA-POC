package com.biswamit.springboot.jpa.rest.service;

import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.O2OEmployeeUniSharedPkNoChildFetch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultEmployeeAddressService implements EmployeeAddressService<O2OEmployeeUniSharedPkNoChildFetch, Long> {

    /**
     * @param aLong
     * @return
     */
    @Override
    public Optional<O2OEmployeeUniSharedPkNoChildFetch> getByAutoId(Long aLong) {
        return Optional.empty();
    }

    /**
     * @param address
     * @return
     */
    @Override
    public O2OEmployeeUniSharedPkNoChildFetch save(O2OEmployeeUniSharedPkNoChildFetch address) {
        return null;
    }

    /**
     * @param addresses
     * @return
     */
    @Override
    public List<O2OEmployeeUniSharedPkNoChildFetch> saveAll(List<O2OEmployeeUniSharedPkNoChildFetch> addresses) {
        return List.of();
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public O2OEmployeeUniSharedPkNoChildFetch deleteByAutoId(Long aLong) {
        return null;
    }
}
