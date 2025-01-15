package com.biswamit.springboot.jpa.rest.controller;

import com.biswamit.springboot.annotation.LogExecutionTime;
import com.biswamit.springboot.jpa.rest.model.EmployeeAddressModel;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.c2p.O2OC2PAddressUniSharedPk;
import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.c2p.O2OC2PEmployeeUniSharedPk;
import com.biswamit.springboot.jpa.rest.service.EmployeeAddressService;
import com.biswamit.springboot.jpa.rest.service.uni.sharedpk.c2p.O2OC2PUniSharedPkAddressService;
import com.biswamit.springboot.jpa.rest.service.uni.sharedpk.c2p.O2OC2PUniSharedPkEmployeeService;
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
@RequestMapping("/api/jpa/uni/sharedpk")
@Tag(name = "O2O-Uni-C2P-SPK-Controller", description = "API Showcasing Child-To-Parent Unidirectional between Employee to Address in One To One with Shared-Primary-Key <img src=\"/static/O2O-C2P-Uni-Spk----Employee-AddressTable.png\"/>")
public class SpringbootJpaRestO2OC2PUniSharedPkController {
    @Autowired
    private O2OC2PUniSharedPkEmployeeService o2OC2PUniSharedPkEmployeeService;
    @Autowired
    private O2OC2PUniSharedPkAddressService o2OC2PUniSharedPkAddressService;
    @Autowired
    private EmployeeAddressService employeeAddressService;

    /**
     * Get O2OC2PEmployeeUniSharedPk employee by autoId
     *
     * @return
     */
    @Operation(summary = "Get a O2OC2PEmployeeUniSharedPk employee by autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the O2OC2PEmployeeUniSharedPk employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OC2PEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid autoId supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "O2OC2PEmployeeUniSharedPk employee not found",
                    content = @Content)})
    @GetMapping(value = {"c2p/otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OC2PEmployeeUniSharedPk> getC2PUniSharedPkO2OEmployeeByAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OC2PEmployeeUniSharedPk> employeeOptional = o2OC2PUniSharedPkEmployeeService.getByAutoId(autoId);
        if (employeeOptional.isPresent()) {
            return new ResponseEntity<O2OC2PEmployeeUniSharedPk>(employeeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all O2OC2PEmployeeUniSharedPk employees
     *
     * @return
     */
    @Operation(summary = "Get all O2O employees with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OC2PEmployeeUniSharedPk employees",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OC2PEmployeeUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OC2PEmployeeUniSharedPk employees not found",
                    content = @Content)})
    @GetMapping(value = {"c2p/otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllC2PUniSharedPkO2OEmployees(@ParameterObject Pageable pageable) {
        Page<O2OC2PEmployeeUniSharedPk> employeePage = o2OC2PUniSharedPkEmployeeService.getAllEmployees(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalItems", employeePage.getTotalElements());
        response.put("totalPages", employeePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all O2OC2PAddressUniSharedPk employee addresses
     *
     * @return
     */
    @Operation(summary = "Get all O2OC2PAddressUniSharedPk addresses with pageNumber and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found audit logs",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OC2PAddressUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OC2PAddressUniSharedPk employee addresses not found",
                    content = @Content)})
    @GetMapping(value = {"c2p/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getAllC2PUniSharedPkO2OAddresses(@ParameterObject Pageable pageable) {
        Page<O2OC2PAddressUniSharedPk> addressPage = o2OC2PUniSharedPkAddressService.getAllAddress(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("addresses", addressPage.getContent());
        response.put("currentPage", addressPage.getNumber());
        response.put("totalItems", addressPage.getTotalElements());
        response.put("totalPages", addressPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get O2OC2PAddressUniSharedPk employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OC2PAddressUniSharedPk employee address with autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OC2PAddressUniSharedPk",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OC2PAddressUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OC2PAddressUniSharedPk employee address not found",
                    content = @Content)})
    @GetMapping(value = {"c2p/otoaddresses/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OC2PAddressUniSharedPk> getC2PUniSharedPkO2OEmployeeAddressWithAutoId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OC2PAddressUniSharedPk> addressOptional = o2OC2PUniSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OC2PAddressUniSharedPk>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Get O2OC2PAddressUniSharedPk employee address
     *
     * @return
     */
    @Operation(summary = "Get O2OC2PAddressUniSharedPk employee address with employee autoId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found O2OC2PAddressUniSharedPk",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = O2OC2PAddressUniSharedPk.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "O2OC2PAddressUniSharedPk employee address not found",
                    content = @Content)})
    @GetMapping(value = {"c2p/otoemployees/{autoId}/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @LogExecutionTime
    public ResponseEntity<O2OC2PAddressUniSharedPk> getC2PUniSharedPkO2OEmployeeAddressWithEmployeeId(@PathVariable(value = "autoId") Long autoId) {
        Optional<O2OC2PAddressUniSharedPk> addressOptional = o2OC2PUniSharedPkAddressService.getByEmployeeAutoId(autoId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<O2OC2PAddressUniSharedPk>(addressOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save O2OC2PEmployeeUniSharedPk employee
     *
     * @
     */
    @Operation(summary = "Create a O2OC2PEmployeeUniSharedPk Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the o2OC2PEmployeeUniSharedPk",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OC2PEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OC2PEmployeeUniSharedPk supplied",
                    content = @Content)})
    @PostMapping(value = {"c2p/otoemployees"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OC2PEmployeeUniSharedPk> createC2PUniSharedPkO2OEmployee(@RequestBody O2OC2PEmployeeUniSharedPk employee) {
        employee.setEmployeeId(UUID.randomUUID());
        employee.setCreatedTime(ZonedDateTime.now());
        O2OC2PEmployeeUniSharedPk savedEmployee = (O2OC2PEmployeeUniSharedPk) o2OC2PUniSharedPkEmployeeService.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    /**
     * Save O2OC2PAddressUniSharedPk address
     *
     * @
     */
    @Operation(summary = "Create a O2OC2PAddressUniSharedPk address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the O2OC2PAddressUniSharedPk address",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OC2PAddressUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OC2PEmployeeUniSharedPk address supplied",
                    content = @Content)})
    @PostMapping(value = {"c2p/otoaddresses"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OC2PAddressUniSharedPk> createC2PUniSharedPkO2OAddress(@RequestBody O2OC2PAddressUniSharedPk address) {
        address.setCreatedTime(ZonedDateTime.now());
        Optional<O2OC2PEmployeeUniSharedPk> employeeOptional = o2OC2PUniSharedPkEmployeeService.getByAutoId(address.getAutoId());
        if (employeeOptional.isPresent()) {
            address.setO2OC2PEmployeeUniSharedPk(employeeOptional.get());
            address.setAutoId(address.getAutoId());
            O2OC2PAddressUniSharedPk savedAddress = (O2OC2PAddressUniSharedPk) o2OC2PUniSharedPkAddressService.save(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Delete O2OC2PEmployeeUniSharedPk employee
     *
     * @
     */
    @Operation(summary = "Delete a O2OC2PEmployeeUniSharedPk employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the o2OC2PEmployeeUniSharedPk employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OC2PEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OC2PEmployeeUniSharedPk supplied",
                    content = @Content)})
    @DeleteMapping(value = {"c2p/otoemployees/{autoId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    public ResponseEntity<O2OC2PEmployeeUniSharedPk> deleteC2PUniSharedPkO2OEmployee(@RequestBody O2OC2PEmployeeUniSharedPk employee) {
        O2OC2PEmployeeUniSharedPk deletedEmployee = (O2OC2PEmployeeUniSharedPk) o2OC2PUniSharedPkEmployeeService.deleteByAutoId(employee.getAutoId());
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }

    /**
     * Save O2OC2PAddressUniSharedPk address with O2OC2PEmployeeUniSharedPk employee in one go
     *
     * @
     */
    @Operation(summary = "Create a O2OC2PEmployeeUniSharedPk employee and O2OC2PAddressUniSharedPk in one go")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the o2OAddressUniSharedPkParentFetch with o2OC2PEmployeeUniSharedPk",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = O2OC2PEmployeeUniSharedPk.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid o2OAddressUniSharedPkParentFetch with o2OC2PEmployeeUniSharedPk supplied",
                    content = @Content)})
    @PostMapping(value = {"c2p/otoaddresseswithemployee"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @LogExecutionTime
    @Transactional
    public ResponseEntity<O2OC2PAddressUniSharedPk> createC2PUniSharedPkO2OEmployeeWithAddress(@RequestBody EmployeeAddressModel employeeAddressModel) {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        O2OC2PAddressUniSharedPk address = O2OC2PAddressUniSharedPk.builder()
                .location(employeeAddressModel.getAddress().getLocation()).createdTime(zonedDateTimeNow).build();
        O2OC2PEmployeeUniSharedPk employee = O2OC2PEmployeeUniSharedPk.builder().employeeId(UUID.randomUUID())
                .employeeFName(employeeAddressModel.getEmployeeFName()).employeeLName(employeeAddressModel.getEmployeeLName())
                .createdTime(zonedDateTimeNow).build();
        O2OC2PEmployeeUniSharedPk savedEmployee = (O2OC2PEmployeeUniSharedPk) o2OC2PUniSharedPkEmployeeService.save(employee);
        address.setAutoId(savedEmployee.getAutoId());
        address.setO2OC2PEmployeeUniSharedPk(savedEmployee);
        O2OC2PAddressUniSharedPk savedAddress = (O2OC2PAddressUniSharedPk) o2OC2PUniSharedPkAddressService.save(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }
}
