package com.biswamit.springboot.jpa.rest.controller;

import com.biswamit.springboot.annotation.LogExecutionTime;
import com.biswamit.springboot.jpa.rest.model.EmployeeAddressModel;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.p2c.O2OP2CAddressBiSharedPk;
import com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.p2c.O2OP2CEmployeeBiSharedPk;
import com.biswamit.springboot.jpa.rest.service.EmployeeAddressService;
import com.biswamit.springboot.jpa.rest.service.bi.sharedpk.p2c.O2OP2CBiSharedPkAddressService;
import com.biswamit.springboot.jpa.rest.service.bi.sharedpk.p2c.O2OP2CBiSharedPkEmployeeService;
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

@RestController
@RequestMapping("/api/jpa/bi/sharedpk")
@Tag(name = "O2O-Bi-P2C-SPK-Controller", description = "API Showcasing Parent-To-Child Bidirectional between Employee to Address in One To One with Shared-Primary-Key <img src=\"/static/O2O-P2C-Bi-Spk----Employee-AddressTable.png\"/>")
public class SpringbootJpaRestO2OP2CBiSharedPkController {
    @Autowired
    private O2OP2CBiSharedPkEmployeeService o2OP2CBiSharedPkEmployeeService;
    @Autowired
    private O2OP2CBiSharedPkAddressService o2OP2CBiSharedPkAddressService;
    @Autowired
    private EmployeeAddressService employeeAddressService;

    /**
     * Get O2OP2CEmployeeBiSharedPk employee by autoId
     *
     * @return
     */
    @Operation(summary = "Get a O2OP2CEmployeeBiSharedPk employee by autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the O2OP2CEmployeeBiSharedPk employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CEmployeeBiSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid autoId supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "O2OP2CEmployeeBiSharedPk employee not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OP2CEmployeeBiSharedPk> getBiSharedPkO2OEmployeeByAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OP2CEmployeeBiSharedPk> employeeOptional = o2OP2CBiSharedPkEmployeeService.getByAutoId(autoId);
        if (employeeOptional.isPresent()) {
            return new ResponseEntity<O2OP2CEmployeeBiSharedPk>(employeeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all O2OP2CEmployeeBiSharedPk employees
     *
     * @return
     */
    @Operation(summary = "Get all O2O employees with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OP2CEmployeeBiSharedPk employees",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CEmployeeBiSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CEmployeeBiSharedPk employees not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllBiSharedPkO2OEmployees(@ParameterObject Pageable pageable) {
        Page<O2OP2CEmployeeBiSharedPk> employeePage = o2OP2CBiSharedPkEmployeeService.getAllEmployees(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalItems", employeePage.getTotalElements());
        response.put("totalPages", employeePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all O2OP2CAddressBiSharedPk employee addresses
     *
     * @return
     */
    @Operation(summary = "Get all O2OP2CAddressBiSharedPk addresses with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found audit logs",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CAddressBiSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CAddressBiSharedPk employee addresses not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllBiSharedPkO2OAddresses(@ParameterObject Pageable pageable) {
        Page<O2OP2CAddressBiSharedPk> address = o2OP2CBiSharedPkAddressService.getAllAddress(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("addresses", address.getContent());
        response.put("currentPage", address.getNumber());
        response.put("totalItems", address.getTotalElements());
        response.put("totalPages", address.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get O2OP2CAddressBiSharedPk employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OP2CAddressBiSharedPk employee address with autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OP2CAddressBiSharedPk",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CAddressBiSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CAddressBiSharedPk employee address not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoaddresses/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OP2CAddressBiSharedPk> getBiSharedPkO2OEmployeeAddressWithAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OP2CAddressBiSharedPk> addressOptional = o2OP2CBiSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OP2CAddressBiSharedPk>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Get O2OP2CAddressBiSharedPk employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OP2CAddressBiSharedPk employee address with employee autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OP2CAddressBiSharedPk",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OP2CAddressBiSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OP2CAddressBiSharedPk employee address not found",
                    content = @Content)})
    @GetMapping(value = {"p2c/otoemployees/{autoId}/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OP2CAddressBiSharedPk> getBiSharedPkO2OEmployeeAddressWithEmployeeId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OP2CAddressBiSharedPk> addressOptional = o2OP2CBiSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OP2CAddressBiSharedPk>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save O2OP2CEmployeeBiSharedPk employee
     *
     * @
     */
    @Operation(summary = "Create a O2OP2CEmployeeBiSharedPk Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the o2OC2PEmployeeUniSharedPk",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CEmployeeBiSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OC2PEmployeeUniSharedPk supplied",
                    content = @Content)})
    @PostMapping(value = {"p2c/otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OP2CEmployeeBiSharedPk> createBiSharedPkO2OEmployee(@RequestBody EmployeeAddressModel employeeAddressModel) {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        O2OP2CAddressBiSharedPk address = O2OP2CAddressBiSharedPk.builder()
                .location(employeeAddressModel.getAddress().getLocation()).createdTime(zonedDateTimeNow).build();
        O2OP2CEmployeeBiSharedPk employee = O2OP2CEmployeeBiSharedPk.builder().employeeId(UUID.randomUUID())
                .employeeFName(employeeAddressModel.getEmployeeFName()).employeeLName(employeeAddressModel.getEmployeeLName())
                .createdTime(zonedDateTimeNow).build();
        O2OP2CEmployeeBiSharedPk savedEmployee = (O2OP2CEmployeeBiSharedPk) o2OP2CBiSharedPkEmployeeService.save(employee);
        address.setAutoId(savedEmployee.getAutoId());
        O2OP2CAddressBiSharedPk savedAddress = (O2OP2CAddressBiSharedPk) o2OP2CBiSharedPkAddressService.save(address);
        savedEmployee.setO2OBiSharedPkAddress(savedAddress);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    /**
     * Save O2OP2CAddressBiSharedPk address
     *
     * @
     */
    @Operation(summary = "Create a O2OP2CAddressBiSharedPk address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the O2OP2CAddressBiSharedPk address",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CAddressBiSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OC2PEmployeeUniSharedPk address supplied",
                    content = @Content)})
    @PostMapping(value = {"p2c/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OP2CAddressBiSharedPk> createBiSharedPkO2OAddress(@RequestBody O2OP2CAddressBiSharedPk address) {
        address.setCreatedTime(ZonedDateTime.now());
        Optional<O2OP2CEmployeeBiSharedPk> employeeOptional = o2OP2CBiSharedPkEmployeeService.getByAutoId(address.getAutoId());
        if (employeeOptional.isPresent()) {
            address.setO2OBiSharedPkEmployee(employeeOptional.get());
            address.setAutoId(address.getAutoId());
            O2OP2CAddressBiSharedPk savedAddress = (O2OP2CAddressBiSharedPk) o2OP2CBiSharedPkAddressService.save(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Delete O2OP2CEmployeeBiSharedPk employee
     *
     * @
     */
    @Operation(summary = "Delete a O2OP2CEmployeeBiSharedPk employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the o2OC2PEmployeeUniSharedPk employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OP2CEmployeeBiSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OC2PEmployeeUniSharedPk supplied",
                    content = @Content)})
    @DeleteMapping(value = {"p2c/otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    public ResponseEntity<O2OP2CEmployeeBiSharedPk> deleteBiSharedPkO2OEmployee(@RequestBody O2OP2CEmployeeBiSharedPk employee) {
        O2OP2CEmployeeBiSharedPk deletedEmployee = (O2OP2CEmployeeBiSharedPk) o2OP2CBiSharedPkEmployeeService.deleteByAutoId(employee.getAutoId());
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }
}
