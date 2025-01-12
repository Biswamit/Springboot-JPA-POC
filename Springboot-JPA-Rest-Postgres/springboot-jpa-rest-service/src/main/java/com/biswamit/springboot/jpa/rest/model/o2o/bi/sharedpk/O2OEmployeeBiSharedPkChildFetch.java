package com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "oto_employee_table")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@ConverterRegistration(converter = ZdtPropertyConverter.class,autoApply = true)
public class O2OEmployeeBiSharedPkChildFetch {

    @JsonProperty("autoId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id", unique = true, nullable = false)
    private Long autoId;

    //@UuidGenerator(style = UuidGenerator.Style.TIME)
    //@GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty("employeeId")
    @NotBlank(message = "O2OEmployeeBiSharedPkChildFetch employeeId cannot be null or empty.")
    @Size(min = 3, max = 64, message = "employee employeeId length should be between 3 and 64 characters")
    @Column(name = "employee_id", unique = true, nullable = true)
    private UUID employeeId;

    @JsonProperty("employeeFName")
    @Size(min = 3, max = 128, message = "O2OEmployeeBiSharedPkChildFetch employeeFName length should be between 3 and 128 characters")
    @Column(name = "employee_fname", unique = false, nullable = false)
    private String employeeFName;

    @JsonProperty("employeeLName")
    @Size(min = 3, max = 128, message = "O2OEmployeeBiSharedPkChildFetch employeeLName length should be between 3 and 128 characters")
    @Column(name = "employee_lname", unique = false, nullable = false)
    private String employeeLName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    @JsonProperty("createdTime")
    @NotBlank(message = "O2OEmployeeBiSharedPkChildFetch createdTime cannot be null or empty.")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "created_time", unique = false, nullable = true)
    //@Convert(converter = ZdtPropertyConverter.class)
    //@Type(TimestampTZType.class)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    @JsonProperty("updatedTime")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "updated_time", unique = false, nullable = true)
    //@Convert(converter = ZdtPropertyConverter.class)
    //@Type(TimestampTZType.class)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime updatedTime;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "o2OBiSharedPkEmployee")
    private O2OAddressBiSharedPkNoParentFetch o2OBiSharedPkAddress;
}
