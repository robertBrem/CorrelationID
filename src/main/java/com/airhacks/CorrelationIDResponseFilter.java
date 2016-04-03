package com.airhacks;


import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class CorrelationIDResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, String> requestHeaders = requestContext.getHeaders();
        List<String> correlationIds = requestHeaders.get(CorrelationIDRequestFilter.CORRELATION_ID_KEY);
        if (correlationIds == null || correlationIds.isEmpty()) {
            throw new IllegalStateException("no " + CorrelationIDRequestFilter.CORRELATION_ID_KEY + " is set!");
        }
        String correlationId = correlationIds.get(0);
        MultivaluedMap<String, Object> responseHeaders = responseContext.getHeaders();
        responseHeaders.add(CorrelationIDRequestFilter.CORRELATION_ID_KEY, correlationId);
        System.out.println("response: " + responseHeaders.get(CorrelationIDRequestFilter.CORRELATION_ID_KEY).get(0));
    }
}
