package com.biswamit.springboot.jpa.rest.db.type;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.ZonedDateTime;

public class JsonBinaryType implements UserType<AdditionalProperty> {
    private static final Logger LOG = LoggerFactory.getLogger(JsonBinaryType.class);
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public int getSqlType() {
        return SqlTypes.JSON;
    }

    @Override
    public Class<AdditionalProperty> returnedClass() {
        return AdditionalProperty.class;
    }

    @Override
    public boolean equals(AdditionalProperty additionalProperty, AdditionalProperty j1) {
        return false;
    }

    @Override
    public int hashCode(AdditionalProperty additionalProperty) {
        return 0;
    }

    @Override
    public AdditionalProperty nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {

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
            LOG.error("Failed to convert String to AdditionalProperty : " + exp.getMessage(), exp);
            throw new RuntimeException("Failed to convert String to AdditionalProperty : " + exp.getMessage(), exp);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, AdditionalProperty value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {

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
            LOG.error("Failed to convert AdditionalProperty to json: " + exp.getMessage(), exp);
            throw new RuntimeException("Failed to convert AdditionalProperty to json: " + exp.getMessage(), exp);
        }

    }

    @Override
    public AdditionalProperty deepCopy(AdditionalProperty value) throws HibernateException {
        try {
            // use serialization to create a deep copy
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            oos.flush();
            oos.close();
            bos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            AdditionalProperty obj = (AdditionalProperty) new ObjectInputStream(bais).readObject();
            bais.close();
            return obj;
        } catch (ClassNotFoundException | IOException exp) {
            throw new HibernateException(exp);
        }
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(AdditionalProperty additionalProperty) {
        return additionalProperty;
    }

    @Override
    public AdditionalProperty assemble(Serializable cached, Object owner) throws HibernateException {
        return (AdditionalProperty) cached;
    }

}
