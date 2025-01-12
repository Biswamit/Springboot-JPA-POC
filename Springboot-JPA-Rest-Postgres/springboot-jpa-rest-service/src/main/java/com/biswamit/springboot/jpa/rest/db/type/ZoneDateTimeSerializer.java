package com.biswamit.springboot.jpa.rest.db.type;

import com.biswamit.springboot.jpa.rest.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;

public class ZoneDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
    private boolean useFormat6S2Z = true;

    public ZoneDateTimeSerializer(boolean useFormat6S2Z) {
        super();
        this.useFormat6S2Z = useFormat6S2Z;
    }

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (useFormat6S2Z)
            jsonGenerator.writeString(DateUtil.fromZDTtoStringWithDTF6S2Z(value));
        else
            jsonGenerator.writeString(DateUtil.fromZDTtoStringWith3S2Z(value));
    }
}
