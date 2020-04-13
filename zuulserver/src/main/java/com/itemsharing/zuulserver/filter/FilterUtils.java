package com.itemsharing.zuulserver.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "is-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "is-user-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";

    public String getCorrelationId() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.getRequest().getHeader(CORRELATION_ID) != null) {
            return requestContext.getRequest().getHeader(CORRELATION_ID);
        } else {
            return requestContext.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public String getAuthToken() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.getRequest().getHeader(AUTH_TOKEN) != null) {
            return requestContext.getRequest().getHeader(AUTH_TOKEN);
        } else {
            return requestContext.getZuulRequestHeaders().get(AUTH_TOKEN);
        }
    }

    public void setAuthToken(String authToken) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(AUTH_TOKEN, authToken);
    }

    public String getUserId() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.getRequest().getHeader(USER_ID) != null) {
            return requestContext.getRequest().getHeader(USER_ID);
        } else {
            return requestContext.getZuulRequestHeaders().get(USER_ID);
        }
    }

    public void setUserId(String userId) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(USER_ID, userId);
    }


}
