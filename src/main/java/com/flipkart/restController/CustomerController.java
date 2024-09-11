package com.flipkart.restController;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.flipkart.business.FlipFitGymCustomerBusiness;
import com.flipkart.business.interfaces.IFlipFitGymCustomer;
import com.flipkart.exceptions.InvalidChoiceException;
import com.flipkart.model.FlipFitBooking;
import com.flipkart.model.FlipFitGymCentre;
import com.flipkart.model.FlipFitGymCustomer;
import com.flipkart.model.FlipFitUser;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON) // All methods produce JSON by default
public class CustomerController {

    private final IFlipFitGymCustomer flipFitCustomerBusiness;
    private FlipFitGymCustomer flipFitGymCustomer;

    @Inject
    public CustomerController(FlipFitGymCustomerBusiness flipFitGymCustomerService) {
        this.flipFitCustomerBusiness = flipFitGymCustomerService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON) // Expects JSON data in request body
    public FlipFitGymCustomer login(FlipFitUser user) {
        FlipFitGymCustomer customer = flipFitCustomerBusiness.login(user);
        this.flipFitGymCustomer = customer;

        System.out.println("Customer Logged IN");
        return flipFitGymCustomer;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON) // Expects JSON data in request body
    public FlipFitGymCustomer register(FlipFitGymCustomer flipFitGymCustomer) {
        FlipFitGymCustomer customer = flipFitCustomerBusiness.registerCustomer(flipFitGymCustomer);
        this.flipFitGymCustomer = customer;
        return flipFitGymCustomer;
    }

    // @GET
    // @Path("/viewBookings")
    // public List<FlipFitBooking> viewBookings() {
    // return
    // flipFitCustomerBusiness.viewBookedSlots(flipFitGymCustomer.getUserId());
    // }

    @GET
    @Path("/viewBookings/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlipFitBooking> viewBookings(@PathParam("id") int userId) {
        // Fetch bookings for the provided userId
        return flipFitCustomerBusiness.viewBookedSlots(userId);
    }

    @GET
    @Path("/viewCentres")
    public List<FlipFitGymCentre> viewCentres() {
        return flipFitCustomerBusiness.viewCentres();
    }

    @PUT
    @Path("/editDetails")
    @Consumes(MediaType.APPLICATION_JSON) // Expects JSON data in request body
    public FlipFitGymCustomer editDetails(FlipFitGymCustomer flipFitGymCustomer) throws InvalidChoiceException {
        return flipFitCustomerBusiness.editDetails(flipFitGymCustomer);
    }

}
