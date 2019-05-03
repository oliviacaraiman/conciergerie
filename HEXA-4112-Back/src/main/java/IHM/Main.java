/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package IHM;

import DAO.JpaUtil;
import Model.Demand;
import Model.Offer;
import Model.Person;
import Model.Service;
import Model.VerificationToken;
import Services.Services;
import Utils.EmailSenderService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.EntityManager;

/**
 *
 * @author youss
 */
public class Main {
    public static void main (String[] args) throws ParseException{
        //Test de création de demande
        //On initialise
        JpaUtil.init();
        Services s = new Services();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        //EmailSenderService.sendVerificationEmail("oliviacaraiman@gmail.com");
       /* Person person = new Person("12345","+12345","fifi@gmail.com","fifi","/url/img.png","Residence A");
                
        
        
        Offer offer = new Offer(person, "Bricolage", null, "marteau",  formatDate.parse("09/05/2019 00:00")
                ,"Residence M", "prêt", 12, "Propose un marteau classique", "heures", "heures", 12);
        Demand demand = new Demand(person, "Bricolage",null, "marteau", formatDate.parse("12/05/2019 19:00")
                ,"Residence M", "prêt", 2, "Recherche marteau classique", "heures", "heures", 2);
        Offer offer2 = new Offer(person, "Bricolage", null, "four",  formatDate.parse("09/05/2019 00:00")
                ,"Residence M", "prêt", 50, "Propose un marteau classique", "heures", "heures", 50);
        
        System.out.println(s.createPerson(person));
        System.out.println(s.createDemand(demand));
        System.out.println(s.createOffer(offer2));
        System.out.println(s.createOffer(offer));
    
        //List<Service> listS = s.findAllServicesWithFilter("Bricolage", "Residence M", "10/05/2019", "19:30", "1", "heures", "3", "Offer");
       // List<Service> listS = s.findAllServicesWithFilter("Marteau","", "", "", "", "","","" ,"");
        //List<Service> listS = s.findAllServicesWithFilter(category, location, date, time, duration, units, nbPts, serviceType)
        System.out.println();
*/
       
       
       // boolean emailSent = s.sendVerificationEmail("oliviacaraiman@gmail.com");
       // System.out.println("--------------------------------"+emailSent+"-------------------");

        //A décommenter quand tu recois le code de vérification (et insérer le code de vérification
 
 //       Person p = s.registerPerson("Dupont", "Jean", "Password", "oliviacaraiman@gmail.com", "000000000000", "666585");

 
 
 // NOT WORKING
        VerificationToken t1 = new VerificationToken("e1","123");
        t1.setDate(formatDate.parse("02/04/2019 12:00"));
        VerificationToken t2 = new VerificationToken("e2","123");
        t2.setDate(formatDate.parse("01/05/2019 12:00"));
        VerificationToken t3 = new VerificationToken("e2","123");
        t3.setDate(formatDate.parse("03/05/2019 12:00"));
        
        s.createToken(t1);
        s.createToken(t2);
        s.createToken(t3);
        
        
        Task task = new Task("delete_token",s);
 
        //Task te2=new TimerExample("Task2");
        Timer t=new Timer();
        t.scheduleAtFixedRate(task, 15*1000, 60*1000);
        
////////////////

       JpaUtil.destroy();
    }
}

class Task extends TimerTask{
    private String job;
    private Services s;
    public Task(String job, Services s){
        this.job = job;
        this.s = s;
        
    }
    @Override
    public void run() {
        
        System.out.println(Thread.currentThread().getName() + " " + job + " the task has executed successfully " + new Date());
        if("delete_token".equalsIgnoreCase(job)){
           // Services s = new Services();
            s.deleteOldTokens(24*60*60*1000L);
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            
        }
       
    }
}