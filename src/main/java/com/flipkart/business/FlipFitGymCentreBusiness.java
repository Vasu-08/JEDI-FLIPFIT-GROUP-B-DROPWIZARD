package com.flipkart.business;

import java.util.List;

import com.flipkart.business.interfaces.IFlipFitGymCentre;
import com.flipkart.dao.FlipFitGymCentreDAOImpl;
import com.flipkart.model.FlipFitGymCentre;
import com.flipkart.model.FlipFitSlots;

public class FlipFitGymCentreBusiness implements IFlipFitGymCentre {
    private final FlipFitGymCentreDAOImpl gymCentreDAO;

    public FlipFitGymCentreBusiness(FlipFitGymCentreDAOImpl FFCentre) {
        this.gymCentreDAO = FFCentre;
    }

    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre flipFitGymCentre) {
        System.out.println("Updating Gym Centre:> ");
        gymCentreDAO.updateGymCentre(flipFitGymCentre);
        return flipFitGymCentre;
    }

    public boolean deleteGymCentre(int centreId) {
        System.out.println("Deleting Gym Centre:> ");
        FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
        gymCentreDAO.deleteGymCentre(flipFitGymCentre);
        return true;
    }

    public List<FlipFitSlots> viewAvailableSlots(int centreId) {
        System.out.println("Viewing Available Slots:> ");

        FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
        flipFitGymCentre.setCentreID(centreId);
        return gymCentreDAO.viewAvailableSlots(flipFitGymCentre);
    }
}
