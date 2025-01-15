package com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.p2c;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Jacksonized
//@ToString(exclude = {"o2OBiSharedPkAddress"})
@Table(name = "oto_p2c_bi_spk_employee_table")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@ConverterRegistration(converter = ZdtPropertyConverter.class,autoApply = true)
@Schema(name = "O2OC2PEmployeeUniSharedPk", description = "O2OC2PEmployeeUniSharedPk Model")
public class O2OP2CEmployeeBiSharedPk {

    @Schema(description = "AutoId of the Employee",name = "autoId",type = "long",example = "1")
    @JsonProperty("autoId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id", unique = true, nullable = false)
    private Long autoId;

    @Schema(description = "Employee UUID",name = "employeeId",type = "string",example = "7696c0c4-80b3-4e3f-8b48-471d6a830beb")
    //@UuidGenerator(style = UuidGenerator.Style.TIME)
    //@GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty("employeeId")
    @NotBlank(message = "O2OP2CEmployeeBiSharedPk employeeId cannot be null or empty.")
    @Column(name = "employee_id", unique = true, nullable = true)
    private UUID employeeId;

    @Schema(description = "Employee Firstname",name = "employeeFName",type = "string",example = "Biswamit")
    @JsonProperty("employeeFName")
    @Size(min = 2, max = 50, message = "O2OP2CEmployeeBiSharedPk employeeFName length should be between 2 and 50 characters")
    @Column(name = "employee_fname", unique = false, nullable = false)
    private String employeeFName;

    @Schema(description = "Employee Lastname",name = "employeeLName",type = "string",example = "Sarkar")
    @JsonProperty("employeeLName")
    @Size(min = 2, max = 50, message = "O2OP2CEmployeeBiSharedPk employeeLName length should be between 2 and 50 characters")
    @Column(name = "employee_lname", unique = false, nullable = false)
    private String employeeLName;

    @Schema(description = "CreatedTime(Timestamp with Timezone)",name = "createdTime",example = "2025-01-16 00:25:41.771 +0530")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    @JsonProperty("createdTime")
    @NotBlank(message = "O2OP2CEmployeeBiSharedPk createdTime cannot be null or empty.")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "created_time", unique = false, nullable = true)
    //@Convert(converter = ZdtPropertyConverter.class)
    //@Type(TimestampTZType.class)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime createdTime;

    @Schema(description = "UpdatedTime(Timestamp with Timezone)",name = "updatedTime",example = "2025-01-16 00:25:41.771 +0530")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    @JsonProperty("updatedTime")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "updated_time", unique = false, nullable = true)
    //@Convert(converter = ZdtPropertyConverter.class)
    //@Type(TimestampTZType.class)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime updatedTime;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "o2OBiSharedPkEmployee")
    private O2OP2CAddressBiSharedPk o2OBiSharedPkAddress;
}
