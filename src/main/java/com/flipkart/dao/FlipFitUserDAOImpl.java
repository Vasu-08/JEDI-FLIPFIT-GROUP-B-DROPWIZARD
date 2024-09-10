package com.flipkart.dao;

import java.sql.*;
import java.util.Random;

import com.flipkart.constant.DBConstants;
import com.flipkart.dao.interfaces.IFlipFitUserDAO;
import com.flipkart.model.FlipFitGymCustomer;
import com.flipkart.model.FlipFitUser;

public class FlipFitUserDAOImpl implements IFlipFitUserDAO {
    Random rand = new Random();

    @Override
    public FlipFitGymCustomer login(String emailID, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);


            PreparedStatement stmt = con.prepareStatement("SELECT * from FlipFitSchema.User where emailID =? and password =?");

            stmt.setString(1, emailID);
            stmt.setString(2, password);


            ResultSet rsUser = stmt.executeQuery();
//            System.out.println(rsUser.getString(4));
//            System.out.println(rsUser.getString(1));


            if (rsUser.next()) {
                stmt = con.prepareStatement("SELECT * from Customer where customerID = ?");
                int userid = rsUser.getInt("userID");
                stmt.setInt(1, userid);
                ResultSet rsCustomer = stmt.executeQuery();

                FlipFitGymCustomer flipFitGymCustomer = new FlipFitGymCustomer();

                if (rsCustomer.next()) {
                    flipFitGymCustomer.setCity(rsCustomer.getString("city"));
                    flipFitGymCustomer.setEmailID(emailID);
                    flipFitGymCustomer.setPinCode(rsCustomer.getString("pinCode"));
                    flipFitGymCustomer.setPassword(password);
                    flipFitGymCustomer.setPhoneNumber(rsUser.getString("phoneNumber"));
                    flipFitGymCustomer.setUserName(rsUser.getString("userName"));
                    flipFitGymCustomer.setUserId(rsUser.getInt("userID"));
                }

                return flipFitGymCustomer;
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Vasu bad");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addUser(FlipFitUser FFU) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("INSERT INTO User VALUES (?, ?, ?, ?, ?, ?)");

            // Generate random integers in range 0 to 999
            FFU.setUserID(rand.nextInt(1000));
            stmt.setInt(1, FFU.getUserID());
            stmt.setInt(2, FFU.getUserID());
            stmt.setInt(3, FFU.getRoleID());
            stmt.setString(5, FFU.getPhoneNumber());
            stmt.setString(4, FFU.getEmailID());
            stmt.setString(6, FFU.getPassword());

            int i = stmt.executeUpdate();
            System.out.println(i + " user added");

            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(FlipFitUser FFU) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("DELETE FROM User WHERE userID=(?)");

            stmt.setInt(1, FFU.getUserID());

            int i = stmt.executeUpdate();
            System.out.println(i + " user removed");

            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void changeUser(FlipFitUser FFU) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement(
                    ("UPDATE User SET userName = ?, roleID =? , emailId = ?, phoneNumber = ?, password = ? WHERE userID = ?"));

            stmt.setInt(1, FFU.getUserID());
            stmt.setInt(2, FFU.getUserID());
            stmt.setInt(3, FFU.getRoleID());
            stmt.setString(5, FFU.getPhoneNumber());
            stmt.setString(4, FFU.getEmailID());
            stmt.setString(6, FFU.getPassword());

            int i = stmt.executeUpdate();
            System.out.println(i + " user changed");

            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public FlipFitUser getUser(int userID) {
        FlipFitUser FFU = new FlipFitUser();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE userId = ?");
            stmt.setInt(1, userID);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            FFU.setUserName(rs.getString("userName"));
            FFU.setUserID(rs.getInt("userID"));
            FFU.setPassword(rs.getString("password"));
            FFU.setPhoneNumber(rs.getString("phoneNumber"));
            FFU.setRoleID(rs.getInt("roleID"));
            FFU.setEmailID(rs.getString("emailId"));

            int i = stmt.executeUpdate();
            System.out.println(i + " user fetched");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return FFU;
    }
}
