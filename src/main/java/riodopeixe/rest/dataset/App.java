package riodopeixe.rest.dataset;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

/**
 *
 * @author vagner
 */
public class App {
    
           private final EntityManager MANAGER;
             private final EntityManager MANAGER2;
            private final TransactionManager TRANSACTION;

    public App() {
                    MANAGER = Persistence.createEntityManagerFactory("PostgresDS").createEntityManager();
        MANAGER2 = Persistence.createEntityManagerFactory("SisNota").createEntityManager();
        TRANSACTION = com.arjuna.ats.jta.TransactionManager.transactionManager(); 
    }
            
            
   
    
    public static void main(String[] args) {
        CellPhoneDataSet cellPhoneDataSet = new CellPhoneDataSetImpl();
        System.out.println(cellPhoneDataSet.getCellPhonebyRef(2905, 30, 2));
    }
    
}
