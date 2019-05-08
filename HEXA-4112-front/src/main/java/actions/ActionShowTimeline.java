/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import Model.Service;
import Services.Services;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author X
 */
public class ActionShowTimeline extends Action {
    
    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {

        HttpSession session = request.getSession();
        Long idPerson = (Long) session.getAttribute("idPerson");
        
        String objectName = request.getParameter("objet");
        String priceUnit = request.getParameter("unitePrix"); 
        String category = request.getParameter("categorie");
        String location = request.getParameter("localisation");
        String availabilityDate = request.getParameter("date");
        String availabilityTime = request.getParameter("time");
        String duration = request.getParameter("duree");
        String durationUnit = request.getParameter("uniteDuree");
        String nbPts = request.getParameter("nbPts");
        String typeService = request.getParameter("type");
        
        Services services = new Services(); 
        Pair<List<Service>,Integer> listOfServices = services.findAllServicesWithFilter(idPerson, objectName, category, location,availabilityDate, availabilityTime, duration, durationUnit, nbPts, priceUnit, typeService);
        
        request.setAttribute("listOfServices", listOfServices.getKey());
        request.setAttribute("idPerson", idPerson);
        request.setAttribute("nbPropositions", listOfServices.getValue());
    }
}
