package riodopeixe.rest.dataset;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import riodopeixe.rest.model.CellPhone;
import riodopeixe.rest.model.Supplier;

/**
 *
 * @author vagner
 */
public class CellPhoneDataSetImpl implements CellPhoneDataSet {
    
    private final EntityManager MANAGER;
    private final EntityManager MANAGER2;
    private final TransactionManager TRANSACTION;

    public CellPhoneDataSetImpl() {
        MANAGER = Persistence.createEntityManagerFactory("PostgresDS").createEntityManager();
        MANAGER2 = Persistence.createEntityManagerFactory("SisNota").createEntityManager();
        this.TRANSACTION = com.arjuna.ats.jta.TransactionManager.transactionManager();
    }    

    @Override
    public List<CellPhone> listCellPhones() {
        List<CellPhone> cellPhones = null;
        try {        
        TRANSACTION.begin();
        Query query = MANAGER2.createQuery("SELECT u FROM CellPhone u");
        TRANSACTION.commit();
        cellPhones = query.getResultList();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(CellPhoneDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return cellPhones;
    }

    @Override
    public CellPhone getCellPhonebyRef(int cod, int color, int volt) {
        CellPhone cellPhone = null;
        try {        
        TRANSACTION.begin();
        String jpql = "";
        cellPhone = (CellPhone) MANAGER.createNativeQuery(jpql, Supplier.class).setParameter("cod", cod).setParameter("color", color).setParameter("volt", volt).getSingleResult();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalArgumentException |IllegalStateException ex) {
            //Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        if (getCellPhoneById(cellPhone.getId()) == null) { /** Verifica no Banco 2 se existe registro se não houver persiste */
            this.cellPhonePersist(cellPhone);
        } else { this.cellPhoneUpdate(cellPhone);}
        return cellPhone;
    }

    @Override
    public void cellPhonePersist(CellPhone cellPhone) {
        try {        
        TRANSACTION.begin();
        MANAGER2.flush();
        MANAGER2.clear();
        MANAGER2.persist(cellPhone);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(CellPhoneDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void cellPhoneRemove(int id) {
        try {        
        TRANSACTION.begin();
        MANAGER2.remove(MANAGER2.find(CellPhone.class, id));
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(CellPhoneDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cellPhoneUpdate(CellPhone cellPhone) {
        try {        
        TRANSACTION.begin();
        MANAGER2.merge(cellPhone);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(CellPhoneDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CellPhone getCellPhoneById(int id) {
        CellPhone cellPhone = null;
        try {
            TRANSACTION.begin();
            cellPhone = MANAGER2.find(CellPhone.class, id);
            TRANSACTION.commit();             
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(CellPhoneDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cellPhone;
    }
    
}
