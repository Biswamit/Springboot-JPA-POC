package com.biswamit.springboot.jpa.rest.db.repository.o2o.uni.sharedpk;

import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.O2OEmployeeUniSharedPkNoChildFetch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public interface O2OUniSharedPkEmployeeRepository extends JpaRepository<O2OEmployeeUniSharedPkNoChildFetch, Long>, JpaSpecificationExecutor<O2OEmployeeUniSharedPkNoChildFetch> {

    final String FILTER_EMPLOYEE_ON_EID_AID = "Select et.* from oto_employee_table et WHERE et.employee_id = :employeeId AND et.auto_id = :autoId";
    final String FILTER_EMPLOYEE_ON_CT_BT = "Select et.* from oto_employee_table pt WHERE et.created_time >= :fromTime AND et.created_time <= :toTime";
    final String FILTER_EMPLOYEE_LOG_ON_UT_BT = "Select et.* from oto_employee_table pt WHERE et.updated_time >= :fromTime AND et.updated_time <= :toTime";

    Optional<O2OEmployeeUniSharedPkNoChildFetch> findByAutoId(Long autoId);

    @Query(value = FILTER_EMPLOYEE_ON_EID_AID, nativeQuery = true)
    Optional<O2OEmployeeUniSharedPkNoChildFetch> findByEmployeeIdAndAutoId(UUID employeeId, Long autoId);

    O2OEmployeeUniSharedPkNoChildFetch deleteByAutoId(Long autoId);

    @Query(value = FILTER_EMPLOYEE_ON_CT_BT, nativeQuery = true)
    Page<O2OEmployeeUniSharedPkNoChildFetch> findAllBetweenCreatedTime(ZonedDateTime fromTime, ZonedDateTime toTime, Pageable pageable);

    @Query(value = FILTER_EMPLOYEE_LOG_ON_UT_BT, nativeQuery = true)
    Page<O2OEmployeeUniSharedPkNoChildFetch> findAllBetweenUpdatedTime(ZonedDateTime fromTime, ZonedDateTime toTime, Pageable pageable);
}
