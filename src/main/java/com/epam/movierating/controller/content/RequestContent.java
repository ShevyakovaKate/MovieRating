package com.epam.movierating.controller.content;

import java.util.HashMap;
import java.util.Map;

public class RequestContent {
    /**
     * Почему именно 3 поля и в каких именно случаях мне помогает реквест контент
     * зачем сделала переслойку, така архитектура для обработки ajax или jquery*/

    public static final String NUMBER_OF_PAGES = "noOfPages";
    public static final String CURRENT_PAGE = "current_page";
    public static final String PAGINATION_URL = "urlForPagination";
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

    public void setRequestAttribute(String name, Object attribute) {
        requestAttributes.put(name, attribute);
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public String getRequestParameterByName(String parameterName) {
        String[] parameters = requestParameters.get(parameterName);
        if (parameters != null) {
            return parameters[0];
        }
        return null;
    }


    private String buildUrlForPagination(String commandParameters) {
        String commandName = getCommandName();
        return "/controller?command="+commandName+commandParameters;
    }

    public void setRequestAttributesForPagination(int numberOfPages, int pageNumber, String parameters) {
        this.setRequestAttribute(NUMBER_OF_PAGES, numberOfPages);
        this.setRequestAttribute(CURRENT_PAGE, pageNumber);
        this.setRequestAttribute(PAGINATION_URL, buildUrlForPagination(parameters));
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getSessionAttributeByName(String attributeName) {
        return sessionAttributes.get(attributeName);
    }

    public void sessionInvalidate() {
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
    }

}
