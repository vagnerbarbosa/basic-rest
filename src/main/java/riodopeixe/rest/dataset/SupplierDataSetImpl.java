package riodopeixe.rest.dataset;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    private static final EntityManager MANAGER;

    static {
        MANAGER = Persistence.createEntityManagerFactory("sisnota").createEntityManager();
    }

    /**
     *
     * @param supplier
     */
    @Override
    public void setSupplier(Supplier supplier) {        
        MANAGER.getTransaction().begin();
        MANAGER.flush();
        MANAGER.clear();
        MANAGER.persist(supplier);
        MANAGER.getTransaction().commit();
    }

    /**
     *
     * @param cnpj
     * @return
     */
    @Override
    public Supplier getSupplierByCnpj(String cnpj) {        
        MANAGER.getTransaction().begin();
        Query query = MANAGER.createQuery("SELECT u FROM Supplier u WHERE u.cnpj = :cnpj");
        query.setParameter("cnpj", cnpj);
        MANAGER.getTransaction().commit();
        return (Supplier) query.getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Supplier> getSuppliers() {         
        MANAGER.getTransaction().begin();
        Query query = MANAGER.createQuery("SELECT u FROM Supplier u");
        MANAGER.getTransaction().commit();        
        List<Supplier> suppliers = query.getResultList();
        return suppliers;
    }

    /**
     *
     * @param supplier
     */
    @Override
    public void updateSupplier(Supplier supplier) {
        MANAGER.getTransaction().begin();
        MANAGER.merge(supplier);
        MANAGER.getTransaction().commit();
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
        MANAGER.getTransaction().begin();
        MANAGER.remove(MANAGER.find(Supplier.class, id));
        MANAGER.getTransaction().commit();
    }

    @Override
    public void removeSupplierByCnpj(String cnpj) {
        MANAGER.getTransaction().begin();
        Query query = MANAGER.createQuery("DELETE FROM Supplier f WHERE f.cnpj = :cnpj");
        query.setParameter("cnpj", cnpj).executeUpdate();        
        MANAGER.getTransaction().commit();
    }

}
