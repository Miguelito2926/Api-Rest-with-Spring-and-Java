package com.ednaldo.rest_api_spring_boot_and_java.serialization.converte;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJackosn2HttpMessageConvert extends AbstractJackson2HttpMessageConverter {
    public YamlJackosn2HttpMessageConvert() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/x-yaml"));
    }
}
