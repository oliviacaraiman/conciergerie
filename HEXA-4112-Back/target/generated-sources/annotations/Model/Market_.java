package Model;

import Model.Service;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-30T19:29:08")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-01T08:55:18")
>>>>>>> 138fc3309b83e07840659179feb6a0472447d48f
@StaticMetamodel(Market.class)
public class Market_ { 

    public static volatile ListAttribute<Market, Service> inProgressService;
    public static volatile SingularAttribute<Market, Long> id;
    public static volatile ListAttribute<Market, Service> openedService;
    public static volatile ListAttribute<Market, Service> closedService;

}