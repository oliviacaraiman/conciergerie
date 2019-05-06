/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import Services.Services;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author X
 */
public class ActionDeleteAd extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        String idAnnonce  = request.getParameter("idAnnonce");
        Long idAnnonceLong = Long.valueOf(idAnnonce);
        
        Services services = new Services();
        boolean deleted = services.deleteService(idAnnonceLong);
        
        request.setAttribute("deleted", deleted);
    }
    
}
