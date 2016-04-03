package com.airhacks.correlationid.order.boundary;

import com.airhacks.CorrelationIDRequestFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Path("orders")
public class OrderResource {

    @Resource
    ManagedExecutorService mes;

    private Client client;
    private WebTarget paymentTarget;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        paymentTarget = client.target("http://localhost:8080/correlationid/resources/payments");
    }

    @GET
    public void getOrders(@Suspended AsyncResponse response, @Context HttpHeaders httpHeaders) {
        CompletableFuture
                .supplyAsync(this::getOrders)
                .thenApply(new Function<String, String>() {
                    @Override
                    public String apply(String message) {
                        List<String> requestHeader = httpHeaders.getRequestHeader(CorrelationIDRequestFilter.CORRELATION_ID_KEY);
                        String paymentResponse = paymentTarget
                                .request()
                                .header(CorrelationIDRequestFilter.CORRELATION_ID_KEY, requestHeader.get(0))
                                .get(String.class);
                        return message + " with payments: " + paymentResponse;
                    }
                })
                .thenAccept(response::resume);
    }

    public String getOrders() {
        return "Order one";
    }

}
