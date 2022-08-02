package com.example.controlefamiliabackend.converter;

import com.example.controlefamiliabackend.enuns.CodigoBancos;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CodigoBancoConverter implements AttributeConverter<CodigoBancos, String> {

    @Override
    public String convertToDatabaseColumn(CodigoBancos codigoBancos) {
        if (codigoBancos == null){
            return null;
        }
        return codigoBancos.getCodigoBanco();
    }

    @Override
    public CodigoBancos convertToEntityAttribute(String codigo) {
        if (codigo == null){
            return null;
        }

        return Stream.of(CodigoBancos.values())
                .filter(c -> c.getCodigoBanco().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
