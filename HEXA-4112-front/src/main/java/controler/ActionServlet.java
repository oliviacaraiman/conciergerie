package controler;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.JpaUtil;
import Model.Person;
import actions.ActionChangePrivilegedContact;
import actions.ActionAnswerAnAd;
import actions.ActionCheckEmail;
import actions.ActionComputePrice;
import actions.ActionConnection;
import actions.ActionCreation;
import actions.ActionDeclineAnswerAd;
import actions.ActionGetAdsByPerson;
import actions.ActionDeconnection;
import actions.ActionDeleteInterest;
import actions.ActionDeletePerson;
import actions.ActionDeleteAd;
import actions.ActionGetAdDetails;
import actions.ActionGetInformationPerson;
import actions.ActionGetInterestsPerson;
import actions.ActionRateBeneficiary;
import actions.ActionRateBidder;
import actions.ActionRegistration;
import actions.ActionReportAd;
import actions.ActionShowTimeline;
import actions.ActionValidateAnswerAd;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vue.SerialisationJSON;

/**
 * Servlet implementation class ActionServlet
 * 
 * @author HEXA-4112
 */

@WebServlet(name="ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Gets the parameter todo and handles the session
     * 
     * @param request contains the parameter send by the front-end
     * @param response contains the information to send to the user
     * @throws ServletException
     * @throws IOException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String todo = request.getParameter("todo");
        
        HttpSession session = request.getSession(true); 
        SerialisationJSON serialisationJSON = new SerialisationJSON();
        
        switch (todo) {
            case "generationCode":
                ActionCheckEmail actionCheckEmail = new ActionCheckEmail();
                try {
                    actionCheckEmail.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                serialisationJSON.executeGenerationCode(request, response);
                
                break;
                
            case "inscription":
                ActionRegistration ar = new ActionRegistration();
                try {
                    ar.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Person personRegistered = (Person) request.getAttribute("person");
                if (personRegistered != null){
                    request.setAttribute("registered", true);
                    session.setAttribute("idPerson", personRegistered.getId());
                } else {
                    request.setAttribute("registered", false);
                }
                
                serialisationJSON.executeInscription(request, response);
                
                break;
                
            case "connexion":
                ActionConnection actionConnection = new ActionConnection();
                try {
                    actionConnection.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Person personConnected = (Person) request.getAttribute("person");
                if (personConnected != null){
                    request.setAttribute("connected", true);
                    session.setAttribute("idPerson", personConnected.getId());
                } else {
                    request.setAttribute("connected", false);
                }
                
                serialisationJSON.executeConnexion(request,response);
                
                break;
                
            case "deposerAnnonce":
                if (session.getAttribute("idPerson") != null){
                    ActionCreation actionCreation = new ActionCreation();

                    try {
                        actionCreation.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeDeposerAnnonce(request, response);
                    
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }

                break;
            
            case "recupererInfoPersonne":
                if (session.getAttribute("idPerson") != null){
                    ActionGetInformationPerson agip = new ActionGetInformationPerson();

                    try {
                        agip.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeRecupererInfoPersonne(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "enregistreContactPrivilegie":
                if (session.getAttribute("idPerson") != null){
                    ActionChangePrivilegedContact acpv = new ActionChangePrivilegedContact();

                    try {
                        acpv.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeEnregistreContactPrivilegie(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
            
            case "afficherFilActualite":
                if (session.getAttribute("idPerson") != null){
                    ActionShowTimeline astl = new ActionShowTimeline();

                    try {
                        astl.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeShowTimeline(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "getAnnoncesPersonne":
                if (session.getAttribute("idPerson") != null){
                    ActionGetAdsByPerson agabp = new ActionGetAdsByPerson();

                    try {
                        agabp.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeGetAnnoncesPersonne(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
            
            case "repondreAnnonce":
                if (session.getAttribute("idPerson") != null){
                    ActionAnswerAnAd actionAnswerAnAd = new ActionAnswerAnAd();

                    try {
                        actionAnswerAnAd.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeRepondreAnnonce(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "calculPrix":
                if (session.getAttribute("idPerson") != null){
                    ActionComputePrice actionComputePrice = new ActionComputePrice();

                    try {
                        actionComputePrice.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeCalculPrix(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
            
            case "detailsAnnonce":
                if (session.getAttribute("idPerson") != null){
                    ActionGetAdDetails actionGetAdDetails = new ActionGetAdDetails();

                    try {
                        actionGetAdDetails.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeDetailsAnnonce(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "getInteretsPersonne":
                if (session.getAttribute("idPerson") != null){
                    ActionGetInterestsPerson actionGetInterestsPerson = new ActionGetInterestsPerson();

                    try {
                        actionGetInterestsPerson.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    serialisationJSON.executeGetInteretsPersonne(request, response);
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                 
                break;

            case "validerReponseAnnonce":
                if (session.getAttribute("idPerson") != null){
                    ActionValidateAnswerAd actionValidateAnswerAd = new ActionValidateAnswerAd();

                    try {
                        actionValidateAnswerAd.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    serialisationJSON.executeValiderReponseAnnonce(request, response);
           
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "supprimerInteret":
                if (session.getAttribute("idPerson") != null){
                    ActionDeleteInterest actionDeleteInterest = new ActionDeleteInterest();

                    try {
                        actionDeleteInterest.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    serialisationJSON.executeSupprimerInteret(request, response);
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
            
                break;
            
            case "declinerReponseAnnonce":
                if (session.getAttribute("idPerson") != null){
                    ActionDeclineAnswerAd actionDeclineAnswerAd = new ActionDeclineAnswerAd();

                    try {
                        actionDeclineAnswerAd.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeDeclinerReponseAnnonce(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "supprimerPersonne":
                if (session.getAttribute("idPerson") != null){
                    ActionDeletePerson actionDeletePerson = new ActionDeletePerson();

                    try {
                        actionDeletePerson.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    serialisationJSON.executeSupprimerPersonne(request, response);
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
            
            case "supprimerAnnonce":
                if (session.getAttribute("idPerson") != null){
                    ActionDeleteAd actionDeleteAd = new ActionDeleteAd();

                    try {
                        actionDeleteAd.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    serialisationJSON.executeSupprimerAnnonce(request, response);
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "noterBeneficiaire":
                if (session.getAttribute("idPerson") != null){
                    ActionRateBeneficiary actionRateBeneficiary = new ActionRateBeneficiary();

                    try {
                        actionRateBeneficiary.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeNoterBeneficiaire(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "noterOffrant":
                if (session.getAttribute("idPerson") != null){
                    ActionRateBidder actionRateBidder = new ActionRateBidder();

                    try {
                        actionRateBidder.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeNoterOffrant(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "signalerAnnonce":
                if (session.getAttribute("idPerson") != null){
                    ActionReportAd actionReportAd = new ActionReportAd();

                    try {
                        actionReportAd.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    serialisationJSON.executeSignalerAnnonce(request, response);
                
                } else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
                
            case "seDeconnecter":
                if (session.getAttribute("idPerson") != null) {
                    ActionDeconnection ad = new ActionDeconnection();
                    
                    try {
                        ad.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    serialisationJSON.executeSeDeconnecter(request, response);
                }
                else {
                    request.setAttribute("error", false);
                    serialisationJSON.executeErrorNotConnected(request, response);
                }
                
                break;
            }
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }
    
    @Override
    public void destroy() {
        super.destroy();
        JpaUtil.destroy();
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet principale : lien entre le back et le front";
    }
    
}