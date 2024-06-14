package com.endpoint.provider.localdate;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;

public class LocalDateModule extends SimpleModule {

    public LocalDateModule() {
        super("LocalDateModule", new Version(1, 0, 0, null, null, null));
        addSerializer(new LocalDateSerializer());
        addDeserializer(LocalDate.class, new LocalDateDeserializer());
    }
}
