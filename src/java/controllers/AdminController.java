/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import items.Car;
import items.CarAgency;
import items.CarType;
import items.Location;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import records.AdminReservationManager;
import reservationPortalSystem.Admin;
import reservationPortalSystem.IAdminReservationItemManager;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class AdminController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String req = (String)request.getParameter("req");

        if (req == null){
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }else if (req.equals("addAgencyPage")){
            request.setAttribute("mode", "addAgencyPage");
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }else if (req.equals("addAgency")){
            IAdminReservationItemManager manager= ReservationPortalSystem.getInstance().getItemManager();
            CarAgency agency =  getCarAgencyInfo(request);

            if (agency == null){
                request.setAttribute("mode", "addAgencyPage");
            } else{
                manager.addCarAgency(agency);
            }
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }else if(req.equals("addCarPage")){
            request.setAttribute("mode", "addCarPage");
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }else if (req.equals("addCar")){
            IAdminReservationItemManager manager= ReservationPortalSystem.getInstance().getItemManager();
            Car newCar = getCarInfo(request);

            if (newCar == null){
                request.setAttribute("mode", "addCarPage");
            }else{
                manager.addItem(newCar);
            }
            
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }else if (req.equals("unClearedRecords")){
            AdminReservationManager manager = (AdminReservationManager)request.getSession().getAttribute("reservationManager");
            request.setAttribute("mode", "unClearedRecords");
            request.setAttribute("result", manager.getUnClearedRecords());
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }else if (req.equals("clear")){
            AdminReservationManager manager = (AdminReservationManager)request.getSession().getAttribute("reservationManager");
            String id = request.getParameter("id");
            manager.clearRecord(id);
            request.setAttribute("mode", "unClearedRecords");
            request.setAttribute("result", manager.getUnClearedRecords());
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }else if (req.equals("overdueRecords")){
            AdminReservationManager manager = (AdminReservationManager)request.getSession().getAttribute("reservationManager");
            request.setAttribute("mode", req);
            request.setAttribute("result", manager.getOverDueRecords());
            getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
        }
    } 


    /**
     * get car parameters from request and adds it to the repository of cars
     * @param request the http request
     */
    private Car getCarInfo(HttpServletRequest request){
        IAdminReservationItemManager manager= ReservationPortalSystem.getInstance().getItemManager();
        Car newCar = new Car();

        if (request.getParameter("carModel")==null || ((String)request.getParameter("carModel")).length() == 0){
            request.setAttribute("error", "car model is missing");
            return null;
        }

        newCar.setCarModel((String)request.getParameter("carModel"));

        if (request.getParameter("price")==null || ((String)request.getParameter("price")).length() == 0){
            request.setAttribute("error", "price is missing");
            return null;
        }

        double price = 0.0;
        int quantity = 0;
        try {
            price = Double.parseDouble((String)request.getParameter("price"));
            if (price <= 0) throw new Exception();
        } catch (Exception e) {
            request.setAttribute("error", "please check price field");
            return null;
        }

        try {
            quantity = Integer.parseInt((String)request.getParameter("quantity"));
            if (quantity <= 0) throw new Exception();
        } catch (Exception e) {
            request.setAttribute("error", "please check quantity field");
            return null;
        }

        try {
            CarType.valueOf((String)request.getParameter("carType"));
        } catch (Exception e) {
            request.setAttribute("error", "please check car type");
            return null;
        }

        CarAgency agency =manager.getCarAgency((String)request.getParameter("carAgency"));
        if (agency == null){
            request.setAttribute("error", "please check car agency");
            return null;
        }

        newCar.setRentPrice(price);
        newCar.setQuantity(quantity);
        newCar.setCarType(CarType.valueOf((String)request.getParameter("carType")));
        newCar.setMyAgency(agency);        
        newCar.setProvider((Admin)request.getSession().getAttribute("user"));
        return newCar;
    }

    
    /**
     * get car agency parameters from request and adds it to the repository of car agencyies
     * @param request the http request
     */
    private CarAgency getCarAgencyInfo(HttpServletRequest request){
        CarAgency agency = new CarAgency();

        if ((String)request.getParameter("name")==null || ((String)request.getParameter("name")).length() == 0){
            request.setAttribute("error", "agency name is missing");
            return null;
        }
        agency.setName((String)request.getParameter("name"));

        if ((String)request.getParameter("description")==null || ((String)request.getParameter("description")).length() == 0){
            request.setAttribute("error", "agency description is missing");
            return null;
        }

        agency.setDescription((String)request.getParameter("description"));
        int numberOfLocations = Integer.parseInt((String)request.getParameter("numberOfLocations"));

        if (numberOfLocations == 0){
            request.setAttribute("error", "please enter at least one supported location");
            return null;
        }
        
        Location newLocation;
        String[] locTokens;
        for (int i=1;i<=numberOfLocations;i++){
            newLocation = new Location();
            locTokens = ((String)request.getParameter("loc" + i)).split(",");
            newLocation.setCountry(locTokens[0].trim());
            newLocation.setCity(locTokens[1].trim());
            newLocation.setStreet(locTokens[2].trim());
            agency.addLocation(newLocation);
        }
        return agency;
    }




    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
