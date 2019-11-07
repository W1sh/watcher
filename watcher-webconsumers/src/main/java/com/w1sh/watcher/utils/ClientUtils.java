package com.w1sh.watcher.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1sh.watcher.clients.common.RequestParameter;
import com.w1sh.watcher.configurations.PropertiesConfiguration;
import com.w1sh.watcher.responses.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class ClientUtils {

    private final ObjectMapper objectMapper;
    private final PropertiesConfiguration propertiesConfiguration;

    public RequestParameter[] defaultPagedRequestParams(){
        RequestParameter[] requestParameters = new RequestParameter[3];
        requestParameters[0] = new RequestParameter("api_key", propertiesConfiguration.getTmdbKey());
        requestParameters[1] = new RequestParameter("language", "en-US");
        requestParameters[2] = new RequestParameter("page", "1");
        return requestParameters;
    }

    public RequestParameter[] defaultRequestParams(){
        RequestParameter[] requestParameters = new RequestParameter[2];
        requestParameters[0] = new RequestParameter("api_key", propertiesConfiguration.getTmdbKey());
        requestParameters[1] = new RequestParameter("language", "en-US");
        return requestParameters;
    }

    public <T> List<T> parse(String json) {
        try {
            Response<T> movieDTOResponse = objectMapper.readValue(json, new TypeReference<>() {});
            return movieDTOResponse.getResults();
        } catch (IOException e) {
            log.error("Failed while parsing json", e);
            return new ArrayList<>();
        }
    }
}
