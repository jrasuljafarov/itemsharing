package com.itemsharing.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return filterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {

        if (isCorrelationIdPresent()) {
            log.debug("is-correlation-id found in tracking filter: {}", filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            log.debug("is-correlation-id generated in tracking filter: {}", filterUtils.getCorrelationId());
        }

        RequestContext requestContext = RequestContext.getCurrentContext();
        log.debug("Processing incoming request for {}", requestContext.getRequest().getRequestURI());

        return null;
    }

    private boolean isCorrelationIdPresent() {
        if (filterUtils.getCorrelationId() != null) {
            return true;
        }
        return false;
    }

    public String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }


}
