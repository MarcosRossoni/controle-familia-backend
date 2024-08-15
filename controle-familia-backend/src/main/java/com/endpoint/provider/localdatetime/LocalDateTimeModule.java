package com.endpoint.provider.localdatetime;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

public class LocalDateTimeModule extends SimpleModule {

    public LocalDateTimeModule() {
        super("LocalDateTimeModule", new Version(1, 0, 0, null, null, null));
        addSerializer(new LocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    }
}
