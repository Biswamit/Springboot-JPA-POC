package com.biswamit.springboot.jpa.rest.service.uni.sharedpk;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.O2OUniSharedPkAddressRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.O2OAddressUniSharedPkParentFetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultO2OUniSharedPkAddressService implements O2OUniSharedPkAddressService<O2OAddressUniSharedPkParentFetch, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OUniSharedPkAddressService.class);
    private O2OUniSharedPkAddressRepository addressRepository;

    @Autowired
    public DefaultO2OUniSharedPkAddressService(O2OUniSharedPkAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OAddressUniSharedPkParentFetch> getByEmployeeAutoId(Long autoId) {
        LOG.debug("Returning address with autoId {}", autoId);
        return Optional.ofNullable(addressRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OAddressUniSharedPkParentFetch> getAllAddress(Pageable pageable) {
        LOG.debug("Returning all addresses");
        Page<O2OAddressUniSharedPkParentFetch> addressPage = addressRepository.findAll(pageable);
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
    public O2OAddressUniSharedPkParentFetch save(O2OAddressUniSharedPkParentFetch address) {
        LOG.debug("Saving address {}", address);
        O2OAddressUniSharedPkParentFetch savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    /**
     * @param addresses
     * @return
     */
    @Override
    public List<O2OAddressUniSharedPkParentFetch> saveAll(List<O2OAddressUniSharedPkParentFetch> addresses) {
        LOG.debug("Saving addresses {}", addresses);
        List<O2OAddressUniSharedPkParentFetch> savedAddresses = addressRepository.saveAll(addresses);
        return savedAddresses;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OAddressUniSharedPkParentFetch deleteByAutoId(Long autoId) {
        LOG.debug("Deleting address with autoId {}", autoId);
        O2OAddressUniSharedPkParentFetch deletedAddress = addressRepository.deleteByAutoId(autoId);
        return deletedAddress;
    }


}
