/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import Services.Services;
import java.io.IOException;
import java.text.ParseException;
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author X
 */
public class ActionDeclineAnswerAd extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        String idReservation = request.getParameter("idReservation");
        Long idReservationLong = Long.valueOf(idReservation);
        
        Services services = new Services();
        boolean declineReservation = services.declineReservation(idReservationLong);
        
        request.setAttribute("declined", declineReservation);
    }
    
}