package com.biswamit.springboot.jpa.rest.model.o2o.bi.parentfk.p2c;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
//@ToString(exclude = {"o2OBiSharedPkEmployee"})
@Table(name = "oto_p2c_bi_pfk_address_table")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@ConverterRegistration(converter = ZdtPropertyConverter.class,autoApply = true)
public class O2OP2CAddressBiParentFk {

    @JsonProperty("autoId")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id", unique = true, nullable = false)
    private Long autoId;

    @JsonProperty("address")
    @Size(min = 3, max = 128, message = "O2OP2CAddressBiSharedPk address length should be between 3 and 128 characters")
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

    @JsonBackReference
    @OneToOne(mappedBy = "o2OP2CAddressBiParentFk")
    private O2OP2CEmployeeBiParentFk o2OP2CEmployeeBiParentFk;
}
