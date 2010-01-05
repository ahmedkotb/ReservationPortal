package controllers;

import items.DoubleDate;
import items.Location;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import records.*;
import reservationPortalSystem.Customer;
import reservationPortalSystem.ICustomerReservationItemManager;
import reservationPortalSystem.Payment;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class customerController extends HttpServlet {

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
        String req = (String) request.getParameter("req");

        if (req == null) {
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        } else if (req.equals("searchCarPage")) {
            request.setAttribute("mode", "searchCarPage");
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        } else if (req.equals("searchCar")) {
            request.setAttribute("mode", "searchCar");
            HashMap hm = getSearchParameters(request);
            if (hm == null) {
                getServletContext().getRequestDispatcher("/customer/customer.jsp?req=searchCarPage&error=invaledData").forward(request, response);
            }
            request.setAttribute("result", ReservationPortalSystem.getInstance().getItemManager().search(hm));
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        } else if (req.equals("reserve")) {
            String id = (String) request.getParameter("id");
            if (id == null) {
                out.print("null id");
                return;
            }
            CustomerReservationManager reserveManager = (CustomerReservationManager) request.getSession().getAttribute("reservationManager");
            ICustomerReservationItemManager itemManager = ReservationPortalSystem.getInstance().getItemManager();
            ReservationRecord record = (ReservationRecord) request.getSession().getAttribute("record");
            if (record == null) {
                out.print("null record");
                return;
            }
            record.setMyReservationItem(itemManager.getItem(id));
            reserveManager.reserve(record);
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        } else if (req.equals("onHoldReservations")) {
            CustomerReservationManager reserveManager = (CustomerReservationManager) request.getSession().getAttribute("reservationManager");
            request.setAttribute("mode", "onHoldReservations");
            request.setAttribute("onHoldReservations", reserveManager.getOnHoldReservations());
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        } else if (req.equals("payPage")) {
            //get payment parameters
            request.setAttribute("mode", "payPage");
            CustomerReservationManager reserveManager = (CustomerReservationManager) request.getSession().getAttribute("reservationManager");
            request.getSession().setAttribute("record", reserveManager.getRecord((String) request.getParameter("id")));
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        } else if (req.equals("pay")) {
            //do the pay stuff here
            CustomerReservationManager reserveManager = (CustomerReservationManager) request.getSession().getAttribute("reservationManager");

             
            Payment newPayment = getPaymentInfo(request);
            if (newPayment == null){
                //TODO handle payment errors
                out.print("error in payment information");
                return;
            }
            reserveManager.pay((ReservationRecord) request.getSession().getAttribute("record"), newPayment);
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        }else if (req.equals("historyPage")){
            request.setAttribute("mode", req);
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        }else if (req.equals("history")){
            CustomerReservationManager reserveManager = (CustomerReservationManager) request.getSession().getAttribute("reservationManager");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            Date startDate;
            Date endDate;
            try {
                startDate = sdf.parse((String) request.getParameter("startDate"));
                endDate = sdf.parse((String)request.getParameter("endDate"));
            } catch (ParseException ex) {
                //send the error message here
                return;
            }
            request.setAttribute("result", reserveManager.getConfirmedReservations(startDate, endDate));
            request.setAttribute("mode", req);
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        }
    }

    private Payment getPaymentInfo(HttpServletRequest request){
        //credit card
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        if (request.getParameter("type").equals("credit card")) {
            CreditCard cc = new CreditCard();
            try {
                cc.setCardNumber(Integer.parseInt((String) request.getParameter("in0_1")));
                cc.setExpireDate(sdf.parse((String)request.getParameter("in0_2")));
                cc.setBillingAddress((String)request.getParameter("in0_3"));
            } catch (Exception ex) {
                return null;
            }
            return cc;
        } else { //bank draft
            BankDraft b = new BankDraft();
            try {
                b.setAccountNumber(Integer.parseInt((String) request.getParameter("in1_1")));
                b.setBankName((String)request.getParameter("in1_2"));
                b.setAccountNumber(Integer.parseInt((String) request.getParameter("in1_3")));
            } catch (Exception ex) {
                return null;
            }
            return b;
        }
    }

    private HashMap getSearchParameters(HttpServletRequest request) {
        String req = (String) request.getParameter("req");
        HashMap<String, Object> info = new HashMap();
        if (req.equals("searchCar")) {
            String carModel = (String) request.getParameter("carModel");
            String carType = (String) request.getParameter("carType");

            info.put("carModel", carModel.equals("") ? null : carModel);
            info.put("carType", carType.equals("") ? null : carType);


            Location pickyupLocation = new Location();
            pickyupLocation.setCity((String) request.getParameter("pickupCity"));
            pickyupLocation.setStreet((String) request.getParameter("pickupStreet"));
            pickyupLocation.setCountry((String) request.getParameter("pickupCountry"));

            Location returnLocation = new Location();
            returnLocation.setCity((String) request.getParameter("returnCity"));
            returnLocation.setStreet((String) request.getParameter("returnStreet"));
            returnLocation.setCountry((String) request.getParameter("returnCountry"));

            info.put("pickupLocation", pickyupLocation);
            info.put("returnLocation", returnLocation);

            //put the reservation extra info in the session in case the user decided to reserve an item
            ReservationRecord record = new CarReservation();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate;
            Date endDate;
            try {
                startDate = sdf.parse((String) request.getParameter("startDate"));
                endDate = sdf.parse((String) request.getParameter("endDate"));
            } catch (ParseException ex) {
                return null;
            }

            record.setMyDateInformation(new DoubleDate(startDate, endDate));
            record.setReserver((Customer) request.getSession().getAttribute("user"));
            ((CarReservation) record).setPickupLocation(pickyupLocation);
            ((CarReservation) record).setPickupLocation(returnLocation);
            request.getSession().setAttribute("record", record);
        }
        return info;
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
