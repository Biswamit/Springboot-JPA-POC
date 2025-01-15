package com.biswamit.springboot.jpa.rest.service.uni.sharedpk.c2p;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.c2p.O2OC2PUniSharedPkAddressRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.c2p.O2OC2PAddressUniSharedPk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultO2OC2PUniSharedPkAddressService implements O2OC2PUniSharedPkAddressService<O2OC2PAddressUniSharedPk, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OC2PUniSharedPkAddressService.class);
    private O2OC2PUniSharedPkAddressRepository addressRepository;

    @Autowired
    public DefaultO2OC2PUniSharedPkAddressService(O2OC2PUniSharedPkAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OC2PAddressUniSharedPk> getByEmployeeAutoId(Long autoId) {
        LOG.debug("Returning address with autoId {}", autoId);
        return Optional.ofNullable(addressRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.EmployeeNotFound.getMessage(), autoId))));
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OC2PAddressUniSharedPk> getAllAddress(Pageable pageable) {
        LOG.debug("Returning all addresses");
        Page<O2OC2PAddressUniSharedPk> addressPage = addressRepository.findAll(pageable);
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
    public O2OC2PAddressUniSharedPk save(O2OC2PAddressUniSharedPk address) {
        LOG.debug("Saving address {}", address);
        O2OC2PAddressUniSharedPk savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    /**
     * @param addresses
     * @return
     */
    @Override
    public List<O2OC2PAddressUniSharedPk> saveAll(List<O2OC2PAddressUniSharedPk> addresses) {
        LOG.debug("Saving addresses {}", addresses);
        List<O2OC2PAddressUniSharedPk> savedAddresses = addressRepository.saveAll(addresses);
        return savedAddresses;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OC2PAddressUniSharedPk deleteByAutoId(Long autoId) {
        LOG.debug("Deleting address with autoId {}", autoId);
        O2OC2PAddressUniSharedPk deletedAddress = addressRepository.deleteByAutoId(autoId);
        return deletedAddress;
    }


}
