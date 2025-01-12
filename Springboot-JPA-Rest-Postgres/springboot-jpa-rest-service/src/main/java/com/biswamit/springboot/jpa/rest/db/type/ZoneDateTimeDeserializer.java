package com.biswamit.springboot.jpa.rest.db.type;

import com.biswamit.springboot.jpa.rest.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;

public class ZoneDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    private boolean useFormat6S2Z = true;

    public ZoneDateTimeDeserializer(boolean useFormat6S2Z) {
        super();
        this.useFormat6S2Z = useFormat6S2Z;
    }

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String dateAsString = jsonParser.getText();
        if (useFormat6S2Z)
            return DateUtil.fromStringToZDTWithDTF6S2Z(dateAsString);
        else
            return DateUtil.fromStringToZDTWith3S2Z(dateAsString);
    }
}
