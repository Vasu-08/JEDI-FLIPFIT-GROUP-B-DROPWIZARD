package com.flipkart.app;

/**
 * Hello world!
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.business.BookingsBusiness;
import com.flipkart.business.FlipFitAdminBusiness;
import com.flipkart.business.FlipFitGymCentreBusiness;
import com.flipkart.business.FlipFitGymCustomerBusiness;
import com.flipkart.business.FlipFitGymOwnerBusiness;
import com.flipkart.dao.FlipFitAdminDAOImpl;
import com.flipkart.dao.FlipFitBookingDAOImpl;
import com.flipkart.dao.FlipFitGymCentreDAOImpl;
import com.flipkart.dao.FlipFitGymCustomerDAOImpl;
import com.flipkart.dao.FlipFitGymOwnerDAOImpl;
import com.flipkart.restController.AdminController;
import com.flipkart.restController.BookingController;
import com.flipkart.restController.CustomerController;
import com.flipkart.restController.GymController;
import com.flipkart.restController.OwnerController;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FlipFitApplication extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlipFitApplication.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        FlipFitAdminDAOImpl flipFitAdminDAO = new FlipFitAdminDAOImpl();
        FlipFitAdminBusiness flipFitAdmin = new FlipFitAdminBusiness(flipFitAdminDAO);
        FlipFitGymCustomerDAOImpl flipFitCustomerDAO = new FlipFitGymCustomerDAOImpl();
        FlipFitGymCustomerBusiness flipFitCustomer = new FlipFitGymCustomerBusiness(flipFitCustomerDAO);
        FlipFitGymCentreDAOImpl flipFitCentreDAO = new FlipFitGymCentreDAOImpl();
        FlipFitGymCentreBusiness flipFitCentre = new FlipFitGymCentreBusiness(flipFitCentreDAO);

        FlipFitBookingDAOImpl flipFitBookingDAO = new FlipFitBookingDAOImpl();
        BookingsBusiness bookingBusiness = new BookingsBusiness(flipFitBookingDAO);

        FlipFitGymOwnerDAOImpl flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
        FlipFitGymOwnerBusiness gymOwnerBusiness = new FlipFitGymOwnerBusiness(flipFitGymOwnerDAO);

        e.jersey().register(new AdminController(flipFitAdmin));
        e.jersey().register(new CustomerController(flipFitCustomer));
        e.jersey().register(new GymController(flipFitCentre));
        e.jersey().register(new BookingController(bookingBusiness));
        e.jersey().register(new OwnerController(gymOwnerBusiness));
        System.out.println("HERE");
    }

    public static void main(String[] args) throws Exception {
        new FlipFitApplication().run(args);
    }
}