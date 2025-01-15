package com.biswamit.springboot.jpa.rest.service;

import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.c2p.O2OC2PEmployeeUniSharedPk;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultEmployeeAddressService implements EmployeeAddressService<O2OC2PEmployeeUniSharedPk, Long> {

    /**
     * @param aLong
     * @return
     */
    @Override
    public Optional<O2OC2PEmployeeUniSharedPk> getByAutoId(Long aLong) {
        return Optional.empty();
    }

    /**
     * @param address
     * @return
     */
    @Override
    public O2OC2PEmployeeUniSharedPk save(O2OC2PEmployeeUniSharedPk address) {
        return null;
    }

    /**
     * @param addresses
     * @return
     */
    @Override
    public List<O2OC2PEmployeeUniSharedPk> saveAll(List<O2OC2PEmployeeUniSharedPk> addresses) {
        return List.of();
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public O2OC2PEmployeeUniSharedPk deleteByAutoId(Long aLong) {
        return null;
    }
}
