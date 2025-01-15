package com.biswamit.springboot.jpa.rest.service.bi.sharedpk.p2c;

import com.biswamit.springboot.jpa.rest.db.repository.o2o.bi.sharedpk.p2c.O2OP2CBiSharedPkAddressRepository;
import com.biswamit.springboot.jpa.rest.exception.JpaRestErrorCode;
import com.biswamit.springboot.jpa.rest.exception.JpaRestNotFoundException;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.p2c.O2OP2CAddressBiSharedPk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultO2OP2CBiSharedPkAddressService implements O2OP2CBiSharedPkAddressService<O2OP2CAddressBiSharedPk, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultO2OP2CBiSharedPkAddressService.class);
    private O2OP2CBiSharedPkAddressRepository addressRepository;

    @Autowired
    public DefaultO2OP2CBiSharedPkAddressService(O2OP2CBiSharedPkAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public Optional<O2OP2CAddressBiSharedPk> getByEmployeeAutoId(Long autoId) {
        LOG.debug("Returning address with autoId {}", autoId);
        return Optional.ofNullable(addressRepository.findByAutoId(autoId).orElseThrow(() -> new JpaRestNotFoundException(String.format(JpaRestErrorCode.AddressNotFound.getMessage(), autoId))));
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<O2OP2CAddressBiSharedPk> getAllAddress(Pageable pageable) {
        LOG.debug("Returning all addresses");
        Page<O2OP2CAddressBiSharedPk> addressPage = addressRepository.findAll(pageable);
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
    public O2OP2CAddressBiSharedPk save(O2OP2CAddressBiSharedPk address) {
        LOG.debug("Saving address {}", address);
        O2OP2CAddressBiSharedPk savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    /**
     * @param addresses
     * @return
     */
    @Override
    public List<O2OP2CAddressBiSharedPk> saveAll(List<O2OP2CAddressBiSharedPk> addresses) {
        LOG.debug("Saving addresses {}", addresses);
        List<O2OP2CAddressBiSharedPk> savedAddresses = addressRepository.saveAll(addresses);
        return savedAddresses;
    }

    /**
     * @param autoId
     * @return
     */
    @Override
    public O2OP2CAddressBiSharedPk deleteByAutoId(Long autoId) {
        LOG.debug("Deleting address with autoId {}", autoId);
        O2OP2CAddressBiSharedPk deletedAddress = addressRepository.deleteByAutoId(autoId);
        return deletedAddress;
    }


}
