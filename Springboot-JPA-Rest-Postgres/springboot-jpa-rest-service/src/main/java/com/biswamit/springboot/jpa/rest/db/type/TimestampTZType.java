package com.biswamit.springboot.jpa.rest.db.type;

import com.biswamit.springboot.jpa.rest.model.o2o.uni.sharedpk.c2p.O2OC2PEmployeeUniSharedPk;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.ZonedDateTime;

public class TimestampTZType implements UserType<O2OC2PEmployeeUniSharedPk> {
    private static final Logger LOG = LoggerFactory.getLogger(TimestampTZType.class);
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public int getSqlType() {
        return Types.TIMESTAMP_WITH_TIMEZONE;
    }

    @Override
    public Class<O2OC2PEmployeeUniSharedPk> returnedClass() {
        return O2OC2PEmployeeUniSharedPk.class;
    }

    @Override
    public boolean equals(O2OC2PEmployeeUniSharedPk O2OC2PEmployeeUniSharedPk, O2OC2PEmployeeUniSharedPk j1) {
        return false;
    }

    @Override
    public int hashCode(O2OC2PEmployeeUniSharedPk O2OC2PEmployeeUniSharedPk) {
        return 0;
    }

    @Override
    public O2OC2PEmployeeUniSharedPk nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {
        /*try {
            OffsetDateTime offsetDateTime = rs.getObject(position, OffsetDateTime.class);
            return offsetDateTime == null || rs.wasNull() ? null : new Date(offsetDateTime.toInstant().toEpochMilli());
        } catch (final Exception exp) {
            Log.error("Failed to convert OffsetDateTime to string: " + exp.getMessage(), exp);
            throw new RuntimeException("Failed to convert AdditionalProperty to json: " + exp.getMessage(), exp);
        }*/

        final String cellContent = rs.getString(position);
        if (cellContent == null) {
            return null;
        }
        try {
            //MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
            //MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            //MAPPER.registerModule(new JavaTimeModule());
            MAPPER.registerModule(new SimpleModule().addDeserializer(ZonedDateTime.class, new ZoneDateTimeDeserializer(true)));
            return MAPPER.readValue(cellContent.getBytes(StandardCharsets.UTF_8), returnedClass());
        } catch (final Exception exp) {
            LOG.error("Failed to convert String to O2OC2PEmployeeUniSharedPk : " + exp.getMessage(), exp);
            throw new RuntimeException("Failed to convert String to O2OC2PEmployeeUniSharedPk : " + exp.getMessage(), exp);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, O2OC2PEmployeeUniSharedPk value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        /*try {

            if (value == null) {
                st.setNull(index, Types.TIMESTAMP_WITH_TIMEZONE);
            } else {
                OffsetDateTime offsetDateTime = Instant
                        .ofEpochMilli(((Date) value).getTime())
                        .atOffset(ZoneOffset.UTC);

                st.setObject(index, offsetDateTime);
            }
        } catch (final Exception exp) {
            Log.error("Failed to convert O to json: " + exp.getMessage(), exp);
            throw new RuntimeException("Failed to convert AdditionalProperty to json: " + exp.getMessage(), exp);
        }*/

        if (value == null) {
            st.setNull(index, Types.OTHER);
            return;
        }
        try {
            //MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
            //MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            //MAPPER.registerModule(new JavaTimeModule());
            MAPPER.registerModule(new SimpleModule().addSerializer(ZonedDateTime.class, new ZoneDateTimeSerializer(true)));
            final StringWriter writer = new StringWriter();
            MAPPER.writeValue(writer, value);
            writer.flush();
            st.setObject(index, writer.toString(), Types.OTHER);
        } catch (final Exception exp) {
            LOG.error("Failed to convert O2OC2PEmployeeUniSharedPk to String: " + exp.getMessage(), exp);
            throw new RuntimeException("Failed to convert O2OC2PEmployeeUniSharedPk to String: " + exp.getMessage(), exp);
        }

    }

    @Override
    public O2OC2PEmployeeUniSharedPk deepCopy(O2OC2PEmployeeUniSharedPk O2OC2PEmployeeUniSharedPk) {
        return O2OC2PEmployeeUniSharedPk;
    }


    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(O2OC2PEmployeeUniSharedPk O2OC2PEmployeeUniSharedPk) {
        return (Serializable) O2OC2PEmployeeUniSharedPk;
    }

    @Override
    public O2OC2PEmployeeUniSharedPk assemble(Serializable serializable, Object o) {
        return (O2OC2PEmployeeUniSharedPk) serializable;
    }


}
