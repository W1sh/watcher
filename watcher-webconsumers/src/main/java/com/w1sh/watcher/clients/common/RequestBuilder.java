package com.w1sh.watcher.clients.common;

import com.w1sh.watcher.constants.TmdbConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestBuilder {

    private static final String DELIMITER_FIRST = "?";
    private static final String DELIMITER_SUBSEQUENT = "&";
    private static final String DELIMITER_EQUALITY = "=";

    private String requestUrl;
    private List<String> methods;
    private List<RequestParameter> params;

    private RequestBuilder() {
        this.methods = new ArrayList<>();
        this.params = new ArrayList<>();
    }

    public static RequestBuilder withBaseUrl(TmdbConstants urlConstant){
        RequestBuilder builder = new RequestBuilder();
        builder.requestUrl = urlConstant.getValue();
        return builder;
    }

    public RequestBuilder withMethods(TmdbConstants... constants){
        this.methods.addAll(Arrays.stream(constants).map(TmdbConstants::getValue).collect(Collectors.toList()));
        return this;
    }

    public RequestBuilder withRequestParams(RequestParameter... params){
        this.params.addAll(Arrays.asList(params));
        return this;
    }

    private void build(){
        StringBuilder baseUrlBuilder = new StringBuilder(this.requestUrl);

        for (int i = 0; i < this.methods.size(); i++) {
            baseUrlBuilder.append(this.methods.get(i));
            if (i < this.methods.size() - 1) baseUrlBuilder.append("/");
        }

        baseUrlBuilder.append(DELIMITER_FIRST);
        for (RequestParameter parameter : params){
            baseUrlBuilder.append(parameter.getKey());
            baseUrlBuilder.append(DELIMITER_EQUALITY);
            baseUrlBuilder.append(parameter.getValue());
            baseUrlBuilder.append(DELIMITER_SUBSEQUENT);
        }

        this.requestUrl = baseUrlBuilder.toString().substring(0, baseUrlBuilder.toString().length() - 1);
    }

    public String getRequestUrl() {
        build();
        return requestUrl;
    }

    public List<String> getMethods() {
        return methods;
    }

    public List<RequestParameter> getParams() {
        return params;
    }
}
