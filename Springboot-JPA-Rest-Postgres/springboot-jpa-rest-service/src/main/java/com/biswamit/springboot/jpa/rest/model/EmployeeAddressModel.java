package com.biswamit.springboot.jpa.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeAddressModel {
    private String employeeFName;
    private String employeeLName;
    private Address address;
}
