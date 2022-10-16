package com.example.ex_006_draxler;

import com.example.ex_006_draxler.db.CustomerDatabaseStatic;
import com.example.ex_006_draxler.pojo.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.net.URI;
import java.util.List;

@Path("/customers")
public class CustomerResource {
    @GET
    @Produces("application/json")
    public List<Customer> allCustomers() {
        return CustomerDatabaseStatic.getInstance().getCustomers();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) {
        try {
            Customer c = CustomerDatabaseStatic.getInstance().findCustomerById(id);
            return Response.ok(URI.create("customers/" + c.getId())).entity(c).build();
        } catch (NotFoundException nfe) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes("application/json")
    public Response addCustomer(Customer c) {
        try {
            CustomerDatabaseStatic.getInstance().addCustomer(c);
            return Response.created(URI.create("customers/" + c.getId())).entity(CustomerDatabaseStatic.getInstance().findCustomerById(c.getId())).build();
        } catch (KeyAlreadyExistsException kaee) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @Path("/{id}")
    public Response removeCustomer(@PathParam("id") int id) {
        try {
            Customer c = CustomerDatabaseStatic.getInstance().removeCustomer(id);
            return Response.ok(URI.create("customers/" + c.getId())).entity(c).build();
        } catch (NotFoundException nfe) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}