package com.epam.movierating.controller.content;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

public class HttpRequestHelper {
    private RequestContent content;

    public HttpRequestHelper(RequestContent content) {
        this.content = content;
    }

    public void getDataFromHttpRequest(HttpServletRequest request)
    {
        setRequestAttributes(request);
        setRequestParameters(request);
        setSessionAttributes(request);
    }


    public void addDataToHttpRequest(HttpServletRequest request) {
        Map<String, Object> requestAttributes = content.getRequestAttributes();
        Map<String, Object> sessionAttributes = content.getSessionAttributes();
        requestAttributes.forEach((key, value)-> request.setAttribute(key, value));
        sessionAttributes.forEach((key, value)-> request.getSession().setAttribute(key, value));
    }

    public void setRequestAttributes(HttpServletRequest request) {
        Enumeration<String> requestAttributeNames = request.getAttributeNames();

        while (requestAttributeNames.hasMoreElements()) {
            String name = requestAttributeNames.nextElement();
            Object attribute = request.getAttribute(name);
            content.setRequestAttribute(name, attribute);
        }
    }

    private void setRequestParameters(HttpServletRequest request)
    {
        Map<String, String[]> parameterMap = request.getParameterMap();
        content.setRequestParameters(parameterMap);
    }

    private void setSessionAttributes(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();

        while (sessionAttributeNames.hasMoreElements()) {
            String name = sessionAttributeNames.nextElement();
            Object sessionAttribute = session.getAttribute(name);
            content.setSessionAttributes(name, sessionAttribute);
        }
    }
}
