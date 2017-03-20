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
    private final EntityManager MANAGER2;
    private final TransactionManager TRANSACTION;

    public SupplierDataSetImpl() {
        MANAGER = Persistence.createEntityManagerFactory("PostgresDS").createEntityManager();
        MANAGER2 = Persistence.createEntityManagerFactory("SisNota").createEntityManager();
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
        MANAGER2.flush();
        MANAGER2.clear();
        MANAGER2.persist(supplier);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @Override
    public Supplier getSupplierByCnpj(String cnpj) {   
        Supplier supplier = null;
        try {        
        TRANSACTION.begin();
        String jpql = "SELECT p.idcnpj_cpf AS id, p.cnpj_cpf AS cnpj, COALESCE(p.nomefantasia, p.nome) AS companyName, COALESCE(e.endereco,'SEM ENDEREÇO CADASTRADO') AS address, COALESCE(e.numero, '0') AS number, COALESCE(c.cidade,'SEM CIDADE CADASTRADA') AS city, COALESCE(c.uf, '??') AS FU, COALESCE(p.cce_rg, '0') AS IE, COALESCE(e.bairro, 'SEM BAIRRO CADASTRADO') AS neighborhood FROM   glb.pessoa p   LEFT JOIN glb.endereco e on (p.idcnpj_cpf=e.idcnpj_cpf AND e.idtipoendereco = 1)   LEFT JOIN glb.cidade c   on (e.idcidade = c.idcidade)   LEFT JOIN sis.tipopessoa tp on (p.idtipopessoa = tp.idtipopessoa AND p.idtipopessoa = 2) WHERE   p.cnpj_cpf = :cnpj LIMIT 1";
        supplier = (Supplier) MANAGER.createNativeQuery(jpql, Supplier.class).setParameter("cnpj", cnpj).getSingleResult();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (getSupplierById(supplier.getId()) == null) {
            this.setSupplier(supplier);
        } else { updateSupplier(supplier);}
        return supplier;
    }    

//    /**
//     *
//     * @param cnpj
//     * @return
//     */
//    @Override
//    public Supplier getSupplierByCnpj(String cnpj) {   
//        Query query = null;
//        try {
//        TRANSACTION.begin();
//        query = MANAGER.createQuery("SELECT u FROM Supplier u WHERE u.cnpj = :cnpj");
//        query.setParameter("cnpj", cnpj);
//        TRANSACTION.commit();
//        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
//            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }  
//        return (Supplier) query.getSingleResult();
//    }

//    /**
//     *
//     * @return
//     */
//    @Override
//    public List<Supplier> getSuppliers() {
//         List<Supplier> suppliers = null;
//        try {        
//        TRANSACTION.begin();
//        String jpql = "SELECT p.idcnpj_cpf AS id, p.cnpj_cpf AS cnpj, COALESCE(p.nomefantasia, p.nome) AS companyName, COALESCE(e.endereco,'SEM ENDEREÇO CADASTRADO') AS address, COALESCE(e.numero, '0') AS number, COALESCE(c.cidade,'SEM CIDADE CADASTRADA') AS city, COALESCE(c.uf, '??') AS FU, COALESCE(p.cce_rg, '0') AS IE, COALESCE(e.bairro, 'SEM BAIRRO CADASTRADO') AS neighborhood FROM   glb.pessoa p   LEFT JOIN glb.endereco e on (p.idcnpj_cpf=e.idcnpj_cpf AND e.idtipoendereco = 1)   LEFT JOIN glb.cidade c   on (e.idcidade = c.idcidade)   LEFT JOIN sis.tipopessoa tp on (p.idtipopessoa = tp.idtipopessoa AND p.idtipopessoa = 2)";
//        suppliers = MANAGER.createNativeQuery(jpql, Supplier.class).getResultList();
//        TRANSACTION.commit();
//        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
//            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }           
//        return suppliers;
//    }    
    
    
    /**
     *
     * @return
     */
    @Override
    public List<Supplier> getSuppliers() {
        Query query = null;
        try {        
        TRANSACTION.begin();
        query = MANAGER2.createQuery("SELECT u FROM Supplier u");
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        MANAGER2.merge(supplier);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Supplier getSupplierById(Long id) {
        Supplier supplier = null;
        try {
            TRANSACTION.begin();
            supplier = MANAGER2.find(Supplier.class, id);
            TRANSACTION.commit();             
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplier;
    }

    @Override
    public void removeSupplier(Long id) {
        try {        
        TRANSACTION.begin();
        MANAGER2.remove(MANAGER2.find(Supplier.class, id));
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeSupplierByCnpj(String cnpj) {
        try {        
        TRANSACTION.begin();
        Query query = MANAGER2.createQuery("DELETE FROM Supplier f WHERE f.cnpj = :cnpj");
        query.setParameter("cnpj", cnpj).executeUpdate();        
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
