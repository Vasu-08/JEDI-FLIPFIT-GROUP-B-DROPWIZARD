package com.flipkart.restController;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.flipkart.business.FlipFitGymOwnerBusiness;
import com.flipkart.business.interfaces.IFlipFitGymOwner;
import com.flipkart.exceptions.InvalidChoiceException;
import com.flipkart.model.FlipFitGymCentre;
import com.flipkart.model.FlipFitGymOwner;
import com.flipkart.model.FlipFitSlots;

@Path("/owner")
@Produces(MediaType.APPLICATION_JSON)

public class OwnerController {
    private final IFlipFitGymOwner flipFitGymOwnerBusiness;
    private FlipFitGymOwner flipFitOwner;

    @Inject
    public OwnerController(FlipFitGymOwnerBusiness flipFitGymOwnerService) {
        this.flipFitGymOwnerBusiness = flipFitGymOwnerService;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitGymOwner register(FlipFitGymOwner flipFitGymOwner) {
        FlipFitGymOwner owner = flipFitGymOwnerBusiness.registerOwner(flipFitGymOwner);
        this.flipFitOwner = owner;
        return owner;
    }

    @POST
    @Path("/addCentre")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitGymCentre addGymCentre(FlipFitGymCentre flipFitGymCentre) throws InvalidChoiceException {
        // flipFitGymCentre.setOwnerID(flipFitOwner.getUserId());
        return flipFitGymOwnerBusiness.addCentre(flipFitGymCentre);
    }

    @POST
    @Path("/addSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitSlots addSlot(FlipFitSlots flipFitSlot) throws InvalidChoiceException {
        // flipFitGymCentre.setOwnerID(flipFitOwner.getUserId());
        return flipFitGymOwnerBusiness.addSlot(flipFitSlot);
    }

    @GET
    @Path("/viewCentres")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FlipFitGymCentre> viewCentres(FlipFitGymOwner owner) {
        return flipFitGymOwnerBusiness.viewCentres(owner);
    }

    // @GET
    // @Path("/viewCustomers/{centreID}")
    // @Consumes(MediaType.APPLICATION_JSON)
    // public List<FlipFitUser> viewFlipFitCustomers(@PathParam("centreID") int
    // centreID){
    // System.out.println("centreID: " + centreID);
    // FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
    // flipFitGymCentre.setCentreID(centreID);
    // return flipFitGymOwnerBusiness.viewFlipFitCustomers(flipFitGymCentre);
    // }
    @PUT
    @Path("/editDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitGymOwner editDetails(FlipFitGymOwner flipFitGymOwner) throws InvalidChoiceException {
        return flipFitGymOwnerBusiness.editDetails(flipFitGymOwner);
    }
}
