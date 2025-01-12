package com.biswamit.springboot.jpa.rest.service.bi.sharedpk;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.bi.sharedpk.O2OBiSharedPkAddressRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.O2OAddressBiSharedPkNoParentFetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultO2OBiSharedPkAddressService implements O2OBiSharedPkAddressService<O2OAddressBiSharedPkNoParentFetch, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OBiSharedPkAddressService.class);
    private O2OBiSharedPkAddressRepository addressRepository;

    @Autowired
    public DefaultO2OBiSharedPkAddressService(O2OBiSharedPkAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OAddressBiSharedPkNoParentFetch> getByEmployeeAutoId(Long autoId) {
        LOG.debug("Returning address with autoId {}", autoId);
        return Optional.ofNullable(addressRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.AddressNotFound.getMessage(), autoId))));
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OAddressBiSharedPkNoParentFetch> getAllAddress(Pageable pageable) {
        LOG.debug("Returning all addresses");
        Page<O2OAddressBiSharedPkNoParentFetch> addressPage = addressRepository.findAll(pageable);
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
    public O2OAddressBiSharedPkNoParentFetch save(O2OAddressBiSharedPkNoParentFetch address) {
        LOG.debug("Saving address {}", address);
        O2OAddressBiSharedPkNoParentFetch savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    /**
     * @param addresses
     * @return
     */
    @Override
    public List<O2OAddressBiSharedPkNoParentFetch> saveAll(List<O2OAddressBiSharedPkNoParentFetch> addresses) {
        LOG.debug("Saving addresses {}", addresses);
        List<O2OAddressBiSharedPkNoParentFetch> savedAddresses = addressRepository.saveAll(addresses);
        return savedAddresses;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OAddressBiSharedPkNoParentFetch deleteByAutoId(Long autoId) {
        LOG.debug("Deleting address with autoId {}", autoId);
        O2OAddressBiSharedPkNoParentFetch deletedAddress = addressRepository.deleteByAutoId(autoId);
        return deletedAddress;
    }


}
