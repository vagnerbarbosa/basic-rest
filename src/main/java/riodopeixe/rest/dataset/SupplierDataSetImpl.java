package riodopeixe.rest.dataset;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.Formatter;
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
        String jpql = "SELECT p.idcnpj_cpf AS id, p.cnpj_cpf AS cnpj, p.cnpj_cpf || ' - ' || COALESCE(p.nomefantasia, p.nome) AS nomeFornecedor, COALESCE(e.endereco,'SEM ENDEREÇO CADASTRADO') AS endereco, COALESCE(e.numero, '0') AS numero, COALESCE(c.cidade,'SEM CIDADE CADASTRADA') AS cidade, COALESCE(c.uf, '??') AS uf, COALESCE(p.cce_rg, '0') AS IE, COALESCE(e.bairro, 'SEM BAIRRO CADASTRADO') AS bairro FROM   glb.pessoa p   LEFT JOIN glb.endereco e on (p.idcnpj_cpf=e.idcnpj_cpf AND e.idtipoendereco = 1)   LEFT JOIN glb.cidade c   on (e.idcidade = c.idcidade)   LEFT JOIN sis.tipopessoa tp on (p.idtipopessoa = tp.idtipopessoa AND p.idtipopessoa = 2) WHERE   p.cnpj_cpf = :cnpj LIMIT 1";
        supplier = (Supplier) MANAGER.createNativeQuery(jpql, Supplier.class).setParameter("cnpj", cnpj).getSingleResult();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalArgumentException |IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        if (getSupplierById(supplier.getId()) == null) { /** Verifica no Banco 2 se existe registro se não houver persiste */
            this.setSupplier(supplier);
        } else { this.updateSupplier(supplier);}
        return supplier;
    }      
    
    /**
     *
     * @return
     */
    @Override
    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = null;
        try {        
        TRANSACTION.begin();
        Query query = MANAGER2.createQuery("SELECT u FROM fornecedor u");
        suppliers = query.getResultList();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }               
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
        Query query = MANAGER2.createQuery("DELETE FROM fornecedor f WHERE f.cnpj = :cnpj");
        query.setParameter("cnpj", cnpj).executeUpdate();        
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(SupplierDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
