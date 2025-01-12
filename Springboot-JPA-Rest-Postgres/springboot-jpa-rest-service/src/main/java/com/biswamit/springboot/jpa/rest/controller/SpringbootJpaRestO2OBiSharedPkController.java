package com.biswamit.springboot.jpa.rest.controller;

import com.biswamit.springboot.annotation.LogExecutionTime;
import com.biswamit.springboot.jpa.rest.model.EmployeeAddressModel;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.O2OAddressBiSharedPkNoParentFetch;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.O2OEmployeeBiSharedPkChildFetch;
import com.biswamit.springboot.jpa.rest.service.EmployeeAddressService;
import com.biswamit.springboot.jpa.rest.service.bi.sharedpk.O2OBiSharedPkAddressService;
import com.biswamit.springboot.jpa.rest.service.bi.sharedpk.O2OBiSharedPkEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/jpa/bi/sharedpk/")
public class SpringbootJpaRestO2OBiSharedPkController {
    @Autowired
    private O2OBiSharedPkEmployeeService o2OBiSharedPkEmployeeService;
    @Autowired
    private O2OBiSharedPkAddressService o2OBiSharedPkAddressService;
    @Autowired
    private EmployeeAddressService employeeAddressService;

    /**
     * Get O2OEmployeeBiSharedPkChildFetch employee by autoId
     *
     * @return
     */
    @Operation(summary = "Get a O2OEmployeeBiSharedPkChildFetch employee by autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the O2OEmployeeBiSharedPkChildFetch employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OEmployeeBiSharedPkChildFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid autoId supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "O2OEmployeeBiSharedPkChildFetch employee not found",
                    content = @Content)})
    @GetMapping(value = {"otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OEmployeeBiSharedPkChildFetch> getBiSharedPkO2OEmployeeByAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OEmployeeBiSharedPkChildFetch> employeeOptional = o2OBiSharedPkEmployeeService.getByAutoId(autoId);
        if (employeeOptional.isPresent()) {
            return new ResponseEntity<O2OEmployeeBiSharedPkChildFetch>(employeeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all O2OEmployeeBiSharedPkChildFetch employees
     *
     * @return
     */
    @Operation(summary = "Get all O2O employees with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OEmployeeBiSharedPkChildFetch employees",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OEmployeeBiSharedPkChildFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OEmployeeBiSharedPkChildFetch employees not found",
                    content = @Content)})
    @GetMapping(value = {"otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllBiSharedPkO2OEmployees(@ParameterObject Pageable pageable) {
        Page<O2OEmployeeBiSharedPkChildFetch> employeePage = o2OBiSharedPkEmployeeService.getAllEmployees(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalItems", employeePage.getTotalElements());
        response.put("totalPages", employeePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all O2OAddressBiSharedPkNoParentFetch employee addresses
     *
     * @return
     */
    @Operation(summary = "Get all O2OAddressBiSharedPkNoParentFetch addresses with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found audit logs",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OAddressBiSharedPkNoParentFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OAddressBiSharedPkNoParentFetch employee addresses not found",
                    content = @Content)})
    @GetMapping(value = {"otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllBiSharedPkO2OAddresses(@ParameterObject Pageable pageable) {
        Page<O2OAddressBiSharedPkNoParentFetch> address = o2OBiSharedPkAddressService.getAllAddress(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("addresses", address.getContent());
        response.put("currentPage", address.getNumber());
        response.put("totalItems", address.getTotalElements());
        response.put("totalPages", address.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get O2OAddressBiSharedPkNoParentFetch employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OAddressBiSharedPkNoParentFetch employee address with autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OAddressBiSharedPkNoParentFetch",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OAddressBiSharedPkNoParentFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OAddressBiSharedPkNoParentFetch employee address not found",
                    content = @Content)})
    @GetMapping(value = {"otoaddresses/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OAddressBiSharedPkNoParentFetch> getBiSharedPkO2OEmployeeAddressWithAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OAddressBiSharedPkNoParentFetch> addressOptional = o2OBiSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OAddressBiSharedPkNoParentFetch>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Get O2OAddressBiSharedPkNoParentFetch employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OAddressBiSharedPkNoParentFetch employee address with employee autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OAddressBiSharedPkNoParentFetch",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OAddressBiSharedPkNoParentFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OAddressBiSharedPkNoParentFetch employee address not found",
                    content = @Content)})
    @GetMapping(value = {"otoemployees/{autoId}/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OAddressBiSharedPkNoParentFetch> getBiSharedPkO2OEmployeeAddressWithEmployeeId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OAddressBiSharedPkNoParentFetch> addressOptional = o2OBiSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OAddressBiSharedPkNoParentFetch>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save O2OEmployeeBiSharedPkChildFetch employee
     *
     * @
     */
    @Operation(summary = "Create a O2OEmployeeBiSharedPkChildFetch Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the o2OEmployeeUniSharedPkNoChildFetch",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OEmployeeBiSharedPkChildFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OEmployeeUniSharedPkNoChildFetch supplied",
                    content = @Content)})
    @PostMapping(value = {"otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OEmployeeBiSharedPkChildFetch> createBiSharedPkO2OEmployee(@RequestBody EmployeeAddressModel employeeAddressModel) {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        O2OAddressBiSharedPkNoParentFetch address = O2OAddressBiSharedPkNoParentFetch.builder()
                .address(employeeAddressModel.getAddress().getAddress()).createdTime(zonedDateTimeNow).build();
        O2OEmployeeBiSharedPkChildFetch employee = O2OEmployeeBiSharedPkChildFetch.builder().employeeId(UUID.randomUUID())
                .employeeFName(employeeAddressModel.getEmployeeFName()).employeeLName(employeeAddressModel.getEmployeeLName())
                .createdTime(zonedDateTimeNow).build();
        O2OEmployeeBiSharedPkChildFetch savedEmployee = (O2OEmployeeBiSharedPkChildFetch) o2OBiSharedPkEmployeeService.save(employee);
        address.setAutoId(savedEmployee.getAutoId());
        O2OAddressBiSharedPkNoParentFetch savedAddress = (O2OAddressBiSharedPkNoParentFetch) o2OBiSharedPkAddressService.save(address);
        savedEmployee.setO2OBiSharedPkAddress(savedAddress);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    /**
     * Save O2OAddressBiSharedPkNoParentFetch address
     *
     * @
     */
    @Operation(summary = "Create a O2OAddressBiSharedPkNoParentFetch address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the O2OAddressBiSharedPkNoParentFetch address",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OAddressBiSharedPkNoParentFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OEmployeeUniSharedPkNoChildFetch address supplied",
                    content = @Content)})
    @PostMapping(value = {"otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OAddressBiSharedPkNoParentFetch> createBiSharedPkO2OAddress(@RequestBody O2OAddressBiSharedPkNoParentFetch address) {
        address.setCreatedTime(ZonedDateTime.now());
        Optional<O2OEmployeeBiSharedPkChildFetch> employeeOptional = o2OBiSharedPkEmployeeService.getByAutoId(address.getAutoId());
        if (employeeOptional.isPresent()) {
            address.setO2OBiSharedPkEmployee(employeeOptional.get());
            address.setAutoId(address.getAutoId());
            O2OAddressBiSharedPkNoParentFetch savedAddress = (O2OAddressBiSharedPkNoParentFetch) o2OBiSharedPkAddressService.save(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Delete O2OEmployeeBiSharedPkChildFetch employee
     *
     * @
     */
    @Operation(summary = "Delete a O2OEmployeeBiSharedPkChildFetch employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the o2OEmployeeUniSharedPkNoChildFetch employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OEmployeeBiSharedPkChildFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OEmployeeUniSharedPkNoChildFetch supplied",
                    content = @Content)})
    @DeleteMapping(value = {"otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    public ResponseEntity<O2OEmployeeBiSharedPkChildFetch> deleteBiSharedPkO2OEmployee(@RequestBody O2OEmployeeBiSharedPkChildFetch employee) {
        O2OEmployeeBiSharedPkChildFetch deletedEmployee = (O2OEmployeeBiSharedPkChildFetch) o2OBiSharedPkEmployeeService.deleteByAutoId(employee.getAutoId());
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }
}
