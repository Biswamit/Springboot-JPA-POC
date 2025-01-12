package com.biswamit.springboot.jpa.rest.controller;

import com.biswamit.springboot.annotation.LogExecutionTime;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.O2OAddressUniSharedPkParentFetch;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.O2OEmployeeUniSharedPkNoChildFetch;
import com.biswamit.springboot.jpa.rest.service.EmployeeAddressService;
import com.biswamit.springboot.jpa.rest.service.uni.sharedpk.O2OUniSharedPkAddressService;
import com.biswamit.springboot.jpa.rest.service.uni.sharedpk.O2OUniSharedPkEmployeeService;
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
@RequestMapping("/api/jpa/uni/sharedpk")
public class SpringbootJpaRestO2OUniSharedPkController {
    @Autowired
    private O2OUniSharedPkEmployeeService o2OUniSharedPkEmployeeService;
    @Autowired
    private O2OUniSharedPkAddressService o2OUniSharedPkAddressService;
    @Autowired
    private EmployeeAddressService employeeAddressService;

    /**
     * Get O2OEmployeeUniSharedPkNoChildFetch employee by autoId
     *
     * @return
     */
    @Operation(summary = "Get a O2OEmployeeUniSharedPkNoChildFetch employee by autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the O2OEmployeeUniSharedPkNoChildFetch employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OEmployeeUniSharedPkNoChildFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid autoId supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "O2OEmployeeUniSharedPkNoChildFetch employee not found",
                    content = @Content)})
    @GetMapping(value = {"otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OEmployeeUniSharedPkNoChildFetch> getUniSharedPkO2OEmployeeByAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OEmployeeUniSharedPkNoChildFetch> employeeOptional = o2OUniSharedPkEmployeeService.getByAutoId(autoId);
        if (employeeOptional.isPresent()) {
            return new ResponseEntity<O2OEmployeeUniSharedPkNoChildFetch>(employeeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all O2OEmployeeUniSharedPkNoChildFetch employees
     *
     * @return
     */
    @Operation(summary = "Get all O2O employees with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OEmployeeUniSharedPkNoChildFetch employees",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OEmployeeUniSharedPkNoChildFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OEmployeeUniSharedPkNoChildFetch employees not found",
                    content = @Content)})
    @GetMapping(value = {"otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllUniSharedPkO2OEmployees(@ParameterObject Pageable pageable) {
        Page<O2OEmployeeUniSharedPkNoChildFetch> employeePage = o2OUniSharedPkEmployeeService.getAllEmployees(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalItems", employeePage.getTotalElements());
        response.put("totalPages", employeePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all O2OAddressUniSharedPkParentFetch employee addresses
     *
     * @return
     */
    @Operation(summary = "Get all O2OAddressUniSharedPkParentFetch addresses with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found audit logs",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OAddressUniSharedPkParentFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OAddressUniSharedPkParentFetch employee addresses not found",
                    content = @Content)})
    @GetMapping(value = {"otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllUniSharedPkO2OAddresses(@ParameterObject Pageable pageable) {
        Page<O2OAddressUniSharedPkParentFetch> addressPage = o2OUniSharedPkAddressService.getAllAddress(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("addresses", addressPage.getContent());
        response.put("currentPage", addressPage.getNumber());
        response.put("totalItems", addressPage.getTotalElements());
        response.put("totalPages", addressPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get O2OAddressUniSharedPkParentFetch employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OAddressUniSharedPkParentFetch employee address with autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OAddressUniSharedPkParentFetch",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OAddressUniSharedPkParentFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OAddressUniSharedPkParentFetch employee address not found",
                    content = @Content)})
    @GetMapping(value = {"otoaddresses/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OAddressUniSharedPkParentFetch> getUniSharedPkO2OEmployeeAddressWithAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OAddressUniSharedPkParentFetch> addressOptional = o2OUniSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OAddressUniSharedPkParentFetch>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Get O2OAddressUniSharedPkParentFetch employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OAddressUniSharedPkParentFetch employee address with employee autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OAddressUniSharedPkParentFetch",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OAddressUniSharedPkParentFetch.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OAddressUniSharedPkParentFetch employee address not found",
                    content = @Content)})
    @GetMapping(value = {"otoemployees/{autoId}/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OAddressUniSharedPkParentFetch> getUniSharedPkO2OEmployeeAddressWithEmployeeId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OAddressUniSharedPkParentFetch> addressOptional = o2OUniSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OAddressUniSharedPkParentFetch>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save O2OEmployeeUniSharedPkNoChildFetch employee
     *
     * @
     */
    @Operation(summary = "Create a O2OEmployeeUniSharedPkNoChildFetch Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the o2OEmployeeUniSharedPkNoChildFetch",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OEmployeeUniSharedPkNoChildFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OEmployeeUniSharedPkNoChildFetch supplied",
                    content = @Content)})
    @PostMapping(value = {"otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OEmployeeUniSharedPkNoChildFetch> createUniSharedPkO2OEmployee(@RequestBody O2OEmployeeUniSharedPkNoChildFetch employee) {
        employee.setEmployeeId(UUID.randomUUID());
        employee.setCreatedTime(ZonedDateTime.now());
        O2OEmployeeUniSharedPkNoChildFetch savedEmployee = (O2OEmployeeUniSharedPkNoChildFetch) o2OUniSharedPkEmployeeService.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    /**
     * Save O2OAddressUniSharedPkParentFetch address
     *
     * @
     */
    @Operation(summary = "Create a O2OAddressUniSharedPkParentFetch address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the O2OAddressUniSharedPkParentFetch address",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OAddressUniSharedPkParentFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OEmployeeUniSharedPkNoChildFetch address supplied",
                    content = @Content)})
    @PostMapping(value = {"otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OAddressUniSharedPkParentFetch> createUniSharedPkO2OAddress(@RequestBody O2OAddressUniSharedPkParentFetch address) {
        address.setCreatedTime(ZonedDateTime.now());
        Optional<O2OEmployeeUniSharedPkNoChildFetch> employeeOptional = o2OUniSharedPkEmployeeService.getByAutoId(address.getAutoId());
        if (employeeOptional.isPresent()) {
            address.setO2OEmployeeUniSharedPkNoChildFetch(employeeOptional.get());
            address.setAutoId(address.getAutoId());
            O2OAddressUniSharedPkParentFetch savedAddress = (O2OAddressUniSharedPkParentFetch) o2OUniSharedPkAddressService.save(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Delete O2OEmployeeUniSharedPkNoChildFetch employee
     *
     * @
     */
    @Operation(summary = "Delete a O2OEmployeeUniSharedPkNoChildFetch employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the o2OEmployeeUniSharedPkNoChildFetch employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OEmployeeUniSharedPkNoChildFetch.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OEmployeeUniSharedPkNoChildFetch supplied",
                    content = @Content)})
    @DeleteMapping(value = {"otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    public ResponseEntity<O2OEmployeeUniSharedPkNoChildFetch> deleteUniSharedPkO2OEmployee(@RequestBody O2OEmployeeUniSharedPkNoChildFetch employee) {
        O2OEmployeeUniSharedPkNoChildFetch deletedEmployee = (O2OEmployeeUniSharedPkNoChildFetch) o2OUniSharedPkEmployeeService.deleteByAutoId(employee.getAutoId());
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }
}
