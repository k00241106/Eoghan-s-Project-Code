/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GeneralUser;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Eoghan
 */
public class GeneralUserServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "upload";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = null;
        List<FileItem> items = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            items = ProcessMultiFormData(request);
            action = this.getFormData(items, "action");
        } else {
            action = request.getParameter("action");
        }
        System.out.println("action = " + action);
        HttpSession session = request.getSession();

        String nextPage = "";
        switch (action) {
            case "RequestAddGeneralUser":
                nextPage = "/AddGeneralUser.html";
                break;
            case "addGeneralUser":
                nextPage = processAddGeneralUser(request, session, items);
                break;
            case "home":
                nextPage = "/HomePage.jsp";
                break;
            case "EditGeneralUser":
                nextPage = processGetGeneralUserDetailsByID(request, session);
                break;
            case "deleteGeneralUser":
                nextPage = processDeleteGeneralUser(request, session);
                break;
            case "saveGeneralUser":
                nextPage = processSaveGeneralUser(request, session, items);
                break;
            case "RequestAllGeneralUser":
                nextPage = processRequestAllGeneralUsers(session);
                break;
            default:

        }

        gotoPage(nextPage, request, response);
    }

    private List<FileItem> ProcessMultiFormData(HttpServletRequest request) {
        List<FileItem> items = null;
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // Parse the request
            items = upload.parseRequest(request);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
        return items;
    }

    private String getFormData(List<FileItem> items, String itemName) {
        // Process the uploaded items

        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                if (item.getFieldName().equals(itemName)) {
                    return item.getString();
                }
            }
        }
        return null;
    }

    private String processAddGeneralUser(HttpServletRequest request, HttpSession session, List<FileItem> items) throws NumberFormatException {
        //debud info
        System.out.println("In processAddGeneralUser");
        System.out.println("Again!");

        //add detail are coming form a multipart form
        String generalUserIDString = this.getFormData(items, "generalUserID");
        System.out.println("Now!");
        //Make generalUserID an int
        System.out.println("Now 2!");
        
        int generalUserID = Integer.parseInt(generalUserIDString);
        System.out.println("Now 3!");
        String username = this.getFormData(items, "username");
        String firstName = this.getFormData(items, "firstName");
        String surName = this.getFormData(items, "surName");
        String email = this.getFormData(items, "email");
        String password = this.getFormData(items, "password");

        System.out.println("just defor crash");
        GeneralUser g = new GeneralUser(generalUserID, username, firstName, surName, email, password);
        System.out.println("Contructor created");
        
        if (g.createGeneralUser()) {
            //send the user a message to say it was added-> view
            String message = "General User " + g.getUsername() + " was added to the system.";
            request.setAttribute("message", message);
            //display the page again - need a new list to reflect deleted general
            System.out.println("About to return");
            return this.processRequestAllGeneralUsers(session);
        }
        return null;
    }

    private String processRequestAllGeneralUsers(HttpSession session) {
        String nextPage;
        System.out.println("in display all General Users");
        ArrayList<GeneralUser> allGeneralUsersList = new ArrayList<>();
        GeneralUser g1 = new GeneralUser();
        allGeneralUsersList = g1.findAllGeneralUsers();
        session.setAttribute("AllGeneralUsers", allGeneralUsersList);
        nextPage = "/DisplayAllGeneralUsers.jsp";
        return nextPage;
    }

    private String processGetGeneralUserDetailsByID(HttpServletRequest request, HttpSession session) throws NumberFormatException {
        String nextPage;
        System.out.println("Edit General User");
        //get general details
        GeneralUser generalUserDetails = new GeneralUser();
        //get generalID from request
        String generalUserIDString = (String) request.getParameter("generalUserID");
        System.out.println("Edit general user for generalUserID =" + generalUserIDString);
        int generalUserID = Integer.parseInt(generalUserIDString);
        generalUserDetails = generalUserDetails.findGeneralUserByID(generalUserID);
        generalUserDetails.print();
        session.setAttribute("GeneralUser", generalUserDetails);
        nextPage = "/UpdateGeneralUser.jsp";
        return nextPage;
    }

    private String processDeleteGeneralUser(HttpServletRequest request, HttpSession session) {
        String nextPage;
        System.out.println("Delete generalUser");
        //get general details
        GeneralUser generalUserDetails = new GeneralUser();
        //get generalUserID from request
        String generalUserIDString = (String) request.getParameter("generalUserID");
        int generalUserID = Integer.parseInt(generalUserIDString);
        System.out.println("delete general user for generalUserID =" + generalUserID);
        generalUserDetails.deleteGeneralUserByID(generalUserID);
        //display the page again - need a new list to reflect deleted general User
        return this.processRequestAllGeneralUsers(session);
    }

    private String processSaveGeneralUser(HttpServletRequest request, HttpSession session, List<FileItem> items) throws NumberFormatException {
        String nextPage;
        System.out.println("Save generaluser");
        //get general user details 
        //get new general user details from request
        //get information from the user - id, 

        //add detail are coming form a multipart form
        String generalUserIDString = this.getFormData(items, "generalUserID");
        String username = this.getFormData(items, "username");
        String firstName = this.getFormData(items, "firstName");
        String surName = this.getFormData(items, "surName");
        String email = this.getFormData(items, "email");
        String password = this.getFormData(items, "password");

        //convert generaluserID to int
        int generalUserID = Integer.parseInt(generalUserIDString);

        //get the geenral user details
        GeneralUser generalUserDetails = new GeneralUser(generalUserID, username, firstName, surName, email, password);
        String message = null;
        if (generalUserDetails.updateGeneralUser()) {
            message = "General User updated";
        } else {
            message = "Error on General User updated";
        }
        request.setAttribute("message", message);
        //display the page again - need a new list to reflect deleted general user
        return this.processRequestAllGeneralUsers(session);
    }

    private void gotoPage(String url, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
