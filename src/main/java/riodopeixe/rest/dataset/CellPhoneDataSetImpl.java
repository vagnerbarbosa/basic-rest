package riodopeixe.rest.dataset;

import java.util.ArrayList;
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
import riodopeixe.rest.model.Invoice;
import riodopeixe.rest.model.ProductRegistration;
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
    public ProductRegistration getCellPhonebyRef(int cod, int color, int volt) {
        ProductRegistration cellPhoneRegistration = null;
        try {        
        TRANSACTION.begin();
        String jpql = "SELECT p.idproduto, x.idgradex, y.idgradey, p.descricao || ' - ' || x.descricao || ' - ' || y.descricao AS descricao FROM glb.produto p INNER JOIN glb.produtograde pg ON (pg.idproduto = p.idproduto) INNER JOIN glb.gradex x ON (x.idgradex = pg.idgradex) INNER JOIN glb.gradey y ON (y.idgradey = pg.idgradey) WHERE p.idproduto = :idproduto AND x.idgradex = :idgradex AND y.idgradey = :idgradey";
        cellPhoneRegistration = (ProductRegistration) MANAGER.createNativeQuery(jpql, ProductRegistration.class).setParameter("idproduto", cod).setParameter("idgradex", color).setParameter("idgradey", volt).getSingleResult();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalArgumentException |IllegalStateException ex) {
            Logger.getLogger(CellPhoneDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if (cellPhoneRegistration != null) {
            CellPhone cellPhone = new CellPhone();
            cellPhone.setIdProduto(cellPhoneRegistration.getIdProduto());
            cellPhone.setColor(cellPhoneRegistration.getColor());
            cellPhone.setVolts(cellPhoneRegistration.getVolts());
            cellPhone.setDescription(cellPhoneRegistration.getDescription());
            this.cellPhonePersist(cellPhone);
        }

        return cellPhoneRegistration;
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
