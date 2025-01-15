package com.biswamit.springboot.jpa.rest.controller;

import com.biswamit.springboot.annotation.LogExecutionTime;
import com.biswamit.springboot.jpa.rest.model.EmployeeAddressModel;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.p2c.O2OP2CAddressUniSharedPk;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.p2c.O2OP2CEmployeeUniSharedPk;
import com.biswamit.springboot.jpa.rest.service.EmployeeAddressService;
import com.biswamit.springboot.jpa.rest.service.uni.sharedpk.p2c.O2OP2CUniSharedPkAddressService;
import com.biswamit.springboot.jpa.rest.service.uni.sharedpk.p2c.O2OP2CUniSharedPkEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * One-To-One Employee to Address with Shared Primary Key
 */
@RestController
@RequestMapping("/api/jpa/uni/sharedpk")
@Tag(name = "O2O-Uni-P2C-SPK-Controller", description = "API Showcasing Parent-To-Child Unidirectional between Employee to Address in One To One with Shared-Primary-Key <img src=\"/static/O2O-P2C-Uni-Spk----Employee-AddressTable.png\"/>")
public class SpringbootJpaRestO2OP2CUniSharedPkController {
    @Autowired
    private O2OP2CUniSharedPkEmployeeService o2OP2CUniSharedPkEmployeeService;
    @Autowired
    private O2OP2CUniSharedPkAddressService o2OP2CUniSharedPkAddressService;
    @Autowired
    private EmployeeAddressService employeeAddressService;

    /**
     * Get O2OP2CEmployeeUniSharedPk employee by autoId
     *
     * @return
     */
    @Operation(summary = "Get a O2OP2CEmployeeUniSharedPk employee by autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the O2OP2CEmployeeUniSharedPk employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid autoId supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "O2OP2CEmployeeUniSharedPk employee not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OP2CEmployeeUniSharedPk> getP2CUniSharedPkO2OEmployeeByAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OP2CEmployeeUniSharedPk> employeeOptional = o2OP2CUniSharedPkEmployeeService.getByAutoId(autoId);
        if (employeeOptional.isPresent()) {
            return new ResponseEntity<O2OP2CEmployeeUniSharedPk>(employeeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all O2OP2CEmployeeUniSharedPk employees
     *
     * @return
     */
    @Operation(summary = "Get all O2O employees with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OP2CEmployeeUniSharedPk employees",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CEmployeeUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CEmployeeUniSharedPk employees not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllP2CUniSharedPkO2OEmployees(@ParameterObject Pageable pageable) {
        Page<O2OP2CEmployeeUniSharedPk> employeePage = o2OP2CUniSharedPkEmployeeService.getAllEmployees(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalItems", employeePage.getTotalElements());
        response.put("totalPages", employeePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all O2OP2CAddressUniSharedPk employee addresses
     *
     * @return
     */
    @Operation(summary = "Get all O2OP2CAddressUniSharedPk addresses with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found audit logs",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CAddressUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CAddressUniSharedPk employee addresses not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllP2CUniSharedPkO2OAddresses(@ParameterObject Pageable pageable) {
        Page<O2OP2CAddressUniSharedPk> addressPage = o2OP2CUniSharedPkAddressService.getAllAddress(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("addresses", addressPage.getContent());
        response.put("currentPage", addressPage.getNumber());
        response.put("totalItems", addressPage.getTotalElements());
        response.put("totalPages", addressPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get O2OP2CAddressUniSharedPk employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OP2CAddressUniSharedPk employee address with autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OP2CAddressUniSharedPk",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CAddressUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CAddressUniSharedPk employee address not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoaddresses/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OP2CAddressUniSharedPk> getP2CUniSharedPkO2OEmployeeAddressWithAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OP2CAddressUniSharedPk> addressOptional = o2OP2CUniSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OP2CAddressUniSharedPk>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Get O2OP2CAddressUniSharedPk employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OP2CAddressUniSharedPk employee address with employee autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OP2CAddressUniSharedPk",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CAddressUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CAddressUniSharedPk employee address not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoemployees/{autoId}/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OP2CAddressUniSharedPk> getP2CUniSharedPkO2OEmployeeAddressWithEmployeeId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OP2CAddressUniSharedPk> addressOptional = o2OP2CUniSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OP2CAddressUniSharedPk>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save O2OP2CEmployeeUniSharedPk employee
     *
     * @
     */
    @Operation(summary = "Create a O2OP2CEmployeeUniSharedPk Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the o2OP2CEmployeeUniSharedPkNoChildFetch",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OP2CEmployeeUniSharedPkNoChildFetch supplied",
                    content = @Content)})
    @PostMapping(value = {"p2c/otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OP2CEmployeeUniSharedPk> createP2CUniSharedPkO2OEmployee(@RequestBody O2OP2CEmployeeUniSharedPk employee) {
        employee.setEmployeeId(UUID.randomUUID());
        employee.setCreatedTime(ZonedDateTime.now());
        O2OP2CEmployeeUniSharedPk savedEmployee = (O2OP2CEmployeeUniSharedPk) o2OP2CUniSharedPkEmployeeService.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    /**
     * Save O2OP2CAddressUniSharedPk address
     *
     * @
     */
    @Operation(summary = "Create a O2OP2CAddressUniSharedPk address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the O2OP2CAddressUniSharedPk address",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CAddressUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OP2CEmployeeUniSharedPkNoChildFetch address supplied",
                    content = @Content)})
    @PostMapping(value = {"p2c/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OP2CAddressUniSharedPk> createP2CUniSharedPkO2OAddress(@RequestBody O2OP2CAddressUniSharedPk address) {
        Optional<O2OP2CEmployeeUniSharedPk> employeeOptional = o2OP2CUniSharedPkEmployeeService.getByAutoId(address.getAutoId());
        if (employeeOptional.isPresent()) {
            O2OP2CEmployeeUniSharedPk employee = employeeOptional.get();
            //employee.setO2OP2CAddressUniSharedPk(address);
            address.setAutoId(employee.getAutoId());
            address.setCreatedTime(ZonedDateTime.now());
            O2OP2CAddressUniSharedPk savedAddress = (O2OP2CAddressUniSharedPk) o2OP2CUniSharedPkAddressService.save(address);
            //O2OP2CEmployeeUniSharedPk savedEmployee = (O2OP2CEmployeeUniSharedPk) o2OP2CUniSharedPkEmployeeService.save(employee);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Delete O2OP2CEmployeeUniSharedPk employee
     *
     * @
     */
    @Operation(summary = "Delete a O2OP2CEmployeeUniSharedPk employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the o2OP2CEmployeeUniSharedPkNoChildFetch employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OP2CEmployeeUniSharedPkNoChildFetch supplied",
                    content = @Content)})
    @DeleteMapping(value = {"p2c/otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    public ResponseEntity<O2OP2CEmployeeUniSharedPk> deleteP2CUniSharedPkO2OEmployee(@RequestBody O2OP2CEmployeeUniSharedPk employee) {
        O2OP2CEmployeeUniSharedPk deletedEmployee = (O2OP2CEmployeeUniSharedPk) o2OP2CUniSharedPkEmployeeService.deleteByAutoId(employee.getAutoId());
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }

    /**
     * Save O2OP2CAddressUniSharedPk address with O2OP2CEmployeeUniSharedPk employee in one go
     *
     * @
     */
    @Operation(summary = "Create a O2OP2CEmployeeUniSharedPk employee and O2OP2CAddressUniSharedPk in one go")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the o2OAddressUniSharedPkParentFetch with o2OP2CEmployeeUniSharedPkNoChildFetch",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OAddressUniSharedPkParentFetch with o2OP2CEmployeeUniSharedPkNoChildFetch supplied",
                    content = @Content)})
    @PostMapping(value = {"p2c/otoaddresseswithemployee"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OP2CEmployeeUniSharedPk> createP2CUniSharedPkO2OEmployeeWithAddress(@RequestBody EmployeeAddressModel employeeAddressModel) {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        O2OP2CAddressUniSharedPk address = O2OP2CAddressUniSharedPk.builder()
                .location(employeeAddressModel.getAddress().getLocation()).createdTime(zonedDateTimeNow).build();
        O2OP2CEmployeeUniSharedPk employee = O2OP2CEmployeeUniSharedPk.builder().employeeId(UUID.randomUUID())
                .employeeFName(employeeAddressModel.getEmployeeFName()).employeeLName(employeeAddressModel.getEmployeeLName())
                .createdTime(zonedDateTimeNow).build();
        O2OP2CEmployeeUniSharedPk savedEmployee = (O2OP2CEmployeeUniSharedPk) o2OP2CUniSharedPkEmployeeService.save(employee);
        address.setAutoId(savedEmployee.getAutoId());
        O2OP2CAddressUniSharedPk savedAddress = (O2OP2CAddressUniSharedPk) o2OP2CUniSharedPkAddressService.save(address);
        employee.setO2OP2CAddressUniSharedPk(savedAddress);
        O2OP2CEmployeeUniSharedPk mergedEmployee = (O2OP2CEmployeeUniSharedPk) o2OP2CUniSharedPkEmployeeService.save(employee);
        return new ResponseEntity<>(mergedEmployee, HttpStatus.CREATED);
    }
}
