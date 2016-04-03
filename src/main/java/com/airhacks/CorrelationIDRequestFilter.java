package com.airhacks;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class CorrelationIDRequestFilter implements ContainerRequestFilter {

    public static final String CORRELATION_ID_KEY = "correlation-id";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        List<String> correlationids = headers.get(CORRELATION_ID_KEY);
        if (correlationids == null || correlationids.isEmpty()) {
            headers.add(CORRELATION_ID_KEY, requestContext.getUriInfo().getRequestUri() + "-" + Thread.currentThread().getName() + "-" + System.currentTimeMillis());
        }
        System.out.println("request: " + headers.get(CORRELATION_ID_KEY).get(0));
    }
}
