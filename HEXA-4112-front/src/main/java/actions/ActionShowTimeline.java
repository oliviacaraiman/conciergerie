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
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class linking the front and back to display the timeline, 
 * i.e. retrieve all ads that meet the user's criteria
 * 
 * @author HEXA-4112
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
        Map.Entry listOfServices = services.findAllServicesWithFilter(idPerson, objectName, category, location,availabilityDate, availabilityTime, duration, durationUnit, nbPts, priceUnit, typeService);
        
        request.setAttribute("listOfServices", listOfServices.getKey());
        request.setAttribute("idPerson", idPerson);
        request.setAttribute("nbPropositions", listOfServices.getValue());
    }
}
