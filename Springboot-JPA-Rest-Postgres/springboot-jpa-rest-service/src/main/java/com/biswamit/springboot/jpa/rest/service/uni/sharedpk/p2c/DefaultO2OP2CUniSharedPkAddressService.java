package com.biswamit.springboot.jpa.rest.service.uni.sharedpk.p2c;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.p2c.O2OP2CUniSharedPkAddressRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.p2c.O2OP2CAddressUniSharedPk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultO2OP2CUniSharedPkAddressService implements O2OP2CUniSharedPkAddressService<O2OP2CAddressUniSharedPk, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OP2CUniSharedPkAddressService.class);
    private O2OP2CUniSharedPkAddressRepository addressRepository;

    @Autowired
    public DefaultO2OP2CUniSharedPkAddressService(O2OP2CUniSharedPkAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OP2CAddressUniSharedPk> getByEmployeeAutoId(Long autoId) {
        LOG.debug("Returning address with autoId {}", autoId);
        return Optional.ofNullable(addressRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OP2CAddressUniSharedPk> getAllAddress(Pageable pageable) {
        LOG.debug("Returning all addresses");
        Page<O2OP2CAddressUniSharedPk> addressPage = addressRepository.findAll(pageable);
        if (addressPage.isEmpty()) {
            throw new JpaRestNotFoundException(JpaRestErrorCode.AddressNotFound);
        }
        return addressPage;
    }


    /**
     * @param address
     * @return
     */
    @Override
    public O2OP2CAddressUniSharedPk save(O2OP2CAddressUniSharedPk address) {
        LOG.debug("Saving address {}", address);
        O2OP2CAddressUniSharedPk savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    /**
     * @param addresses
     * @return
     */
    @Override
    public List<O2OP2CAddressUniSharedPk> saveAll(List<O2OP2CAddressUniSharedPk> addresses) {
        LOG.debug("Saving addresses {}", addresses);
        List<O2OP2CAddressUniSharedPk> savedAddresses = addressRepository.saveAll(addresses);
        return savedAddresses;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OP2CAddressUniSharedPk deleteByAutoId(Long autoId) {
        LOG.debug("Deleting address with autoId {}", autoId);
        O2OP2CAddressUniSharedPk deletedAddress = addressRepository.deleteByAutoId(autoId);
        return deletedAddress;
    }


}
