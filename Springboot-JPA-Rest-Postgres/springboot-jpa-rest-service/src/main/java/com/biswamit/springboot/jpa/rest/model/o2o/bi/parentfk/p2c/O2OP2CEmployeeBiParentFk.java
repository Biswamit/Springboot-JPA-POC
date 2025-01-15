package com.biswamit.springboot.jpa.rest.model.o2o.bi.parentfk.p2c;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "oto_p2c_bi_pfk_employee_table")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@ConverterRegistration(converter = ZdtPropertyConverter.class,autoApply = true)
public class O2OP2CEmployeeBiParentFk {

    @JsonProperty("autoId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id", unique = true, nullable = false)
    private Long autoId;

    //@UuidGenerator(style = UuidGenerator.Style.TIME)
    //@GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty("employeeId")
    @NotBlank(message = "O2OP2CEmployeeBiSharedPk employeeId cannot be null or empty.")
    @Size(min = 3, max = 64, message = "employee employeeId length should be between 3 and 64 characters")
    @Column(name = "employee_id", unique = true, nullable = true)
    private UUID employeeId;

    @JsonProperty("employeeFName")
    @Size(min = 3, max = 128, message = "O2OP2CEmployeeBiSharedPk employeeFName length should be between 3 and 128 characters")
    @Column(name = "employee_fname", unique = false, nullable = false)
    private String employeeFName;

    @JsonProperty("employeeLName")
    @Size(min = 3, max = 128, message = "O2OP2CEmployeeBiSharedPk employeeLName length should be between 3 and 128 characters")
    @Column(name = "employee_lname", unique = false, nullable = false)
    private String employeeLName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    @JsonProperty("createdTime")
    @NotBlank(message = "O2OP2CEmployeeBiSharedPk createdTime cannot be null or empty.")
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "address_id",referencedColumnName="auto_id")
    private O2OP2CAddressBiParentFk o2OP2CAddressBiParentFk;
}
