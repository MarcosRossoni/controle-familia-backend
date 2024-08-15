package com.endpoint.provider;

import com.endpoint.provider.localdate.LocalDateModule;
import com.endpoint.provider.localdatetime.LocalDateTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.inject.Singleton;

@Singleton
public class ObjectMapperProvider implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JsonOrgModule());
        objectMapper.registerModule(new LocalDateModule());
        objectMapper.registerModule(new LocalDateTimeModule());

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
    }

    public ObjectMapper getContext() {
        ObjectMapper objectMapper = new ObjectMapper();
        customize(objectMapper);
        return objectMapper;
    }
}
