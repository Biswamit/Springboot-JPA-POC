package com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk;

import com.fasterxml.jackson.annotation.JsonFormat;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Jacksonized
@Table(name = "oto_address_table")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@ConverterRegistration(converter = ZdtPropertyConverter.class,autoApply = true)
public class O2OAddressUniSharedPkParentFetch {

    @JsonProperty("autoId")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id", unique = true, nullable = false)
    private Long autoId;

    @JsonProperty("address")
    @Size(min = 3, max = 128, message = "O2OAddressUniSharedPkParentFetch address length should be between 3 and 128 characters")
    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    @JsonProperty("createdTime")
    @NotBlank(message = "Audit Log createdTime cannot be null or empty.")
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

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn(name = "auto_id", referencedColumnName = "auto_id")
    private O2OEmployeeUniSharedPkNoChildFetch o2OEmployeeUniSharedPkNoChildFetch;
}
