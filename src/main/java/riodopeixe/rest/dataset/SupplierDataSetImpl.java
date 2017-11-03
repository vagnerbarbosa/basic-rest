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
import riodopeixe.rest.model.Supplier;

/**
 * Classe de pesistência para objetos do tipo Supplier.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 03/06/2016
 *
 * @version 1.0
 */
public class SupplierDataSetImpl implements SupplierDataSet {

    private final EntityManager MANAGER;
    private final TransactionManager TRANSACTION;

    public SupplierDataSetImpl() {
        MANAGER = Persistence.createEntityManagerFactory("SisNota").createEntityManager();
        this.TRANSACTION = com.arjuna.ats.jta.TransactionManager.transactionManager();
    }

    /**
     *
     * @param supplier
     */
    @Override
    public void setSupplier(Supplier supplier) {        
        try {        
        TRANSACTION.begin();
        MANAGER.flush();
        MANAGER.clear();
        MANAGER.persist(supplier);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    /**
     *
     * @param cnpj
     * @return
     */
    @Override
    public Supplier getSupplierByCnpj(String cnpj) {   
        Query query = null;
        try {
        TRANSACTION.begin();
        query = MANAGER.createQuery("SELECT u FROM Supplier u WHERE u.cnpj = :cnpj");
        query.setParameter("cnpj", cnpj);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return (Supplier) query.getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Supplier> getSuppliers() {
        Query query = null;
        try {        
        TRANSACTION.begin();
        query = MANAGER.createQuery("SELECT u FROM Supplier u");
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }       
        List<Supplier> suppliers = query.getResultList();
        return suppliers;
    }

    /**
     *
     * @param supplier
     */
    @Override
    public void updateSupplier(Supplier supplier) {
        try {        
        TRANSACTION.begin();
        MANAGER.merge(supplier);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Supplier getSupplierById(Long id) {
        MANAGER.getTransaction().begin();
        Supplier supplier = MANAGER.find(Supplier.class, id);
        MANAGER.getTransaction().commit();
        return supplier;
    }

    @Override
    public void removeSupplier(Long id) {
        try {        
        TRANSACTION.begin();
        MANAGER.remove(MANAGER.find(Supplier.class, id));
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeSupplierByCnpj(String cnpj) {
        try {        
        TRANSACTION.begin();
        Query query = MANAGER.createQuery("DELETE FROM Supplier f WHERE f.cnpj = :cnpj");
        query.setParameter("cnpj", cnpj).executeUpdate();        
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
