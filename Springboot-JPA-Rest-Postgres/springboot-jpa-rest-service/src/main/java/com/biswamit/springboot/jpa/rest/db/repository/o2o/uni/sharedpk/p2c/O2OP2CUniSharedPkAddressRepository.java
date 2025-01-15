package com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk.p2c;

import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.p2c.O2OP2CAddressUniSharedPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public interface O2OP2CUniSharedPkAddressRepository extends JpaRepository<O2OP2CAddressUniSharedPk, Long>, JpaSpecificationExecutor<O2OP2CAddressUniSharedPk> {

    final String FILTER_ADDRESS_ON_EID_AID = "Select et.* from oto_p2c_uni_spk_address_table et WHERE et.employee_id = :employeeId AND et.auto_id = :autoId";
    final String FILTER_ADDRESS_ON_CT_BT = "Select et.* from oto_p2c_uni_spk_address_table pt WHERE et.created_time >= :fromTime AND et.created_time <= :toTime";
    final String FILTER_ADDRESS_LOG_ON_UT_BT = "Select et.* from oto_p2c_uni_spk_address_table pt WHERE et.updated_time >= :fromTime AND et.updated_time <= :toTime";

    Optional<O2OP2CAddressUniSharedPk> findByAutoId(Long autoId);

    @Query(value = FILTER_ADDRESS_ON_EID_AID, nativeQuery = true)
    Optional<O2OP2CAddressUniSharedPk> findByEmployeeAutoId(Long autoId);

    @Query(value = FILTER_ADDRESS_ON_EID_AID, nativeQuery = true)
    Optional<O2OP2CAddressUniSharedPk> findByEmployeeId(UUID employeeId);

    O2OP2CAddressUniSharedPk deleteByAutoId(Long autoId);

    @Query(value = FILTER_ADDRESS_ON_CT_BT, nativeQuery = true)
    Page<O2OP2CAddressUniSharedPk> findAllBetweenCreatedTime(ZonedDateTime fromTime, ZonedDateTime toTime, Pageable pageable);

    @Query(value = FILTER_ADDRESS_LOG_ON_UT_BT, nativeQuery = true)
    Page<O2OP2CAddressUniSharedPk> findAllBetweenUpdatedTime(ZonedDateTime fromTime, ZonedDateTime toTime, Pageable pageable);
}
