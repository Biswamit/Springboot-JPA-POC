package com.biswamit.springboot.jpa.rest.db.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class AdditionalProperty implements Serializable {

    @JsonProperty("StringProp")
    private String stringProp;

    //2023-11-25 02:15:33.105034+05:30
    //Cannot deserialize value of type `java.time.ZoneDateTime` from String "2023-11-25 02:15:33.105034+05:30": Failed to deserialize java.time.ZoneDateTime: (java.time.format.DateTimeParseException) Text '2023-11-25 02:15:33.105034+05:30' could not be parsed at index 26
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSzz")
    //@Type(TimestampTZType.class)
    @JsonProperty("DateProp")
    private ZonedDateTime dateProp;

    @JsonProperty("BooleanProp")
    private Boolean booleanProp;

    @JsonProperty("EnumProp")
    private String enumProp;

    @JsonProperty("SizeProp")
    private Long sizeProp;

    @JsonProperty("HashProp")
    private String hashProp;

}
