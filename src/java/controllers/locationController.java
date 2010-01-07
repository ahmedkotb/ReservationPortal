/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import items.CarAgency;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import items.Location;
import java.util.Collection;
import java.util.HashMap;
import javax.jdo.Query;
import reservationPortalSystem.ReservationPortalSystem;
/**
 *
 * @author ahmed
 */
public class locationController extends HttpServlet {
   
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
        try {
            String req = (String)request.getParameter("req");
            if (req.equals("countries")){
                Collection<String> countries = getCountries().keySet();
                for (String country:countries)
                    out.println("<option>" +country +"</option>");
            }else if (req.equals("cities")){
                String country = (String)request.getParameter("country");
                Collection<String> cities = getCities(country).keySet();
                for (String city:cities)
                    out.println("<option>"+ city + "</option>");
            }else if(req.equals("streets")){
                String country = (String)request.getParameter("country");
                String city = (String)request.getParameter("city");
                Collection<String> streets = getStreets(country,city).keySet();
                for (String street:streets)
                    out.println("<option>"+ street + "</option>");
            }
        } finally { 
            out.close();
        }
    }


    public HashMap<String,Boolean> getCountries(){
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(CarAgency.class);
        Collection<CarAgency> result = (Collection<CarAgency>) query.execute();
        HashMap<String,Boolean> countries = new HashMap<String,Boolean>();

        for(CarAgency agency :result){
            for (Location location : agency.getSupportedLocations()){
                if (countries.get(location.getCountry()) == null)
                    countries.put(location.getCountry(), Boolean.TRUE);
            }
        }
        query.close(this);
        Query query2 = ReservationPortalSystem.getInstance().getConnection().newQuery(Location.class);
        Collection<Location> result2 = (Collection<Location>) query2.execute();
        for (Location location : result2){
            if (countries.get(location.getCountry()) == null)
                countries.put(location.getCountry(), Boolean.TRUE);
        }

        return countries;
    }

    public HashMap<String,Boolean> getCities(String country){
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(CarAgency.class);
        Collection<CarAgency> result = (Collection<CarAgency>) query.execute();
        HashMap<String,Boolean> cities = new HashMap<String,Boolean>();

        for(CarAgency agency :result){
            for (Location location : agency.getSupportedLocations()){
                if (location.getCountry().equals(country))
                    cities.put(location.getCity(), Boolean.TRUE);
            }
        }
        query.close(this);
        Query query2 = ReservationPortalSystem.getInstance().getConnection().newQuery(Location.class);
        Collection<Location> result2 = (Collection<Location>) query2.execute();
        for (Location location : result2){
            if (location.getCountry().equals(country)){
                if (cities.get(location.getCity()) == null)
                    cities.put(location.getCity(), Boolean.TRUE);
            }
        }

        return cities;

    }

    public HashMap<String,Boolean> getStreets(String country,String city){
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(CarAgency.class);
        Collection<CarAgency> result = (Collection<CarAgency>) query.execute();
        HashMap<String,Boolean> streets = new HashMap<String,Boolean>();

        for(CarAgency agency :result){
            for (Location location : agency.getSupportedLocations()){
                if (location.getCountry().equals(country) && location.getCity().equals(city))
                    streets.put(location.getStreet(), Boolean.TRUE);
            }
        }

        query.close(this);
        Query query2 = ReservationPortalSystem.getInstance().getConnection().newQuery(Location.class);
        Collection<Location> result2 = (Collection<Location>) query2.execute();
        for (Location location : result2){
            if (location.getCity().equals(city)){
                if (streets.get(location.getStreet()) == null)
                    streets.put(location.getStreet(), Boolean.TRUE);
            }
        }
        return streets;
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
