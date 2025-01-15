package com.biswamit.springboot.jpa.rest.model.o2o.bi.sharedpk.p2c;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Jacksonized
//@ToString(exclude = {"o2OBiSharedPkEmployee"})
@Table(name = "oto_p2c_bi_spk_address_table")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@ConverterRegistration(converter = ZdtPropertyConverter.class,autoApply = true)
@Schema(name = "O2OP2CAddressBiSharedPk", description = "O2OP2CAddressBiSharedPk Model")
public class O2OP2CAddressBiSharedPk {

    @Schema(description = "AutoId of the Employee",name = "autoId",type = "long",example = "1")
    @JsonProperty("autoId")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id", unique = true, nullable = false)
    private Long autoId;

    @Schema(description = "Location detail",name = "location",type = "string",example = "Pacific Palisades,LA")
    @JsonProperty("location")
    @Size(min = 2, max = 100, message = "O2OP2CAddressBiSharedPk location length should be between 2 and 100 characters")
    @Column(name = "location", unique = false, nullable = false)
    private String location;

    @Schema(description = "CreatedTime(Timestamp with Timezone)",name = "createdTime",example = "2025-01-16 00:25:41.771 +0530")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    @JsonProperty("createdTime")
    @NotBlank(message = "Audit Log createdTime cannot be null or empty.")
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

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "auto_id",referencedColumnName="auto_id")
    private O2OP2CEmployeeBiSharedPk o2OBiSharedPkEmployee;
}
