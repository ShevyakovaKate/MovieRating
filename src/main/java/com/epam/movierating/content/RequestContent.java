package com.epam.movierating.content;

import java.util.HashMap;
import java.util.Map;

public class RequestContent {
    private static final String PARAM_COMMAND = "command";
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private String url;

    public RequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        url = null;
    }

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void addRequestAttribute(String name, Object attribute) {
        requestAttributes.put(name, attribute);
    }

    public String getRequestParameterByName(String parameterName) {
        String[] parameters = requestParameters.get(parameterName);
        if(parameters != null)
        {
            return parameters[0];
        }
        return null;
    }

    public String getCommandName() {
        return requestParameters.get(PARAM_COMMAND)[0].toUpperCase();
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }


    public void setSessionAttributes(String name, Object attribute) {
        sessionAttributes.put(name, attribute);
    }

}
