package com.airhacks.correlationid.payment.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("payments")
public class PaymentResource {

    @GET
    public String getPayments() {
        return "payment one";
    }

}
