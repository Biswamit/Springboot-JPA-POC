package com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.c2p;

import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.c2p.O2OC2PAddressUniSharedPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public interface O2OC2PUniSharedPkAddressRepository extends JpaRepository<O2OC2PAddressUniSharedPk, Long>, JpaSpecificationExecutor<O2OC2PAddressUniSharedPk> {

    final String FILTER_ADDRESS_ON_EID_AID = "Select et.* from oto_c2p_uni_spk_address_table et WHERE et.employee_id = :employeeId AND et.auto_id = :autoId";
    final String FILTER_ADDRESS_ON_CT_BT = "Select et.* from oto_c2p_uni_spk_address_table pt WHERE et.created_time >= :fromTime AND et.created_time <= :toTime";
    final String FILTER_ADDRESS_LOG_ON_UT_BT = "Select et.* from oto_c2p_uni_spk_address_table pt WHERE et.updated_time >= :fromTime AND et.updated_time <= :toTime";

    Optional<O2OC2PAddressUniSharedPk> findByAutoId(Long autoId);

    @Query(value = FILTER_ADDRESS_ON_EID_AID, nativeQuery = true)
    Optional<O2OC2PAddressUniSharedPk> findByEmployeeAutoId(Long autoId);

    @Query(value = FILTER_ADDRESS_ON_EID_AID, nativeQuery = true)
    Optional<O2OC2PAddressUniSharedPk> findByEmployeeId(UUID employeeId);

    O2OC2PAddressUniSharedPk deleteByAutoId(Long autoId);

    @Query(value = FILTER_ADDRESS_ON_CT_BT, nativeQuery = true)
    Page<O2OC2PAddressUniSharedPk> findAllBetweenCreatedTime(ZonedDateTime fromTime, ZonedDateTime toTime, Pageable pageable);

    @Query(value = FILTER_ADDRESS_LOG_ON_UT_BT, nativeQuery = true)
    Page<O2OC2PAddressUniSharedPk> findAllBetweenUpdatedTime(ZonedDateTime fromTime, ZonedDateTime toTime, Pageable pageable);
}
