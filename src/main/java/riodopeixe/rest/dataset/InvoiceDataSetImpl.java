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
import riodopeixe.rest.model.Invoice;

/**
 * Classe de pesistência para objetos do tipo Invoice.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 03/06/2016
 *
 * @version 1.0
 */
public class InvoiceDataSetImpl implements InvoiceDataSet {

    private final EntityManager MANAGER;
    private final TransactionManager TRANSACTION;

    public InvoiceDataSetImpl() {
        MANAGER = Persistence.createEntityManagerFactory("SisNota").createEntityManager();
        this.TRANSACTION = com.arjuna.ats.jta.TransactionManager.transactionManager();
    }

    /**
     *
     * @param invoice
     */
    @Override
    public void setInvoice(Invoice invoice) {
        try {        
        TRANSACTION.begin();
        MANAGER.persist(invoice);
        MANAGER.flush();
        MANAGER.clear();        
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     *
     * @param id
     */
    @Override
    public void removeInvoice(Integer id) {
        try {
        TRANSACTION.begin();
        MANAGER.remove(MANAGER.find(Invoice.class, id));
        MANAGER.flush();
        MANAGER.clear();         
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Invoice getInvoiceById(Integer id) {
        Invoice invoice = null;
        try {        
        TRANSACTION.begin();
        invoice = MANAGER.find(Invoice.class, id);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return invoice;        
    }

    /**
     *
     * @param imei
     * @return
     */
    @Override
    public Invoice getInvoiceByImei(String imei) {
        List<Invoice> nfs = null;
        try {        
        TRANSACTION.begin();
        String jpql = "SELECT notafiscal.id, notafiscal.dataEmissao, notafiscal.dataEntrada, notafiscal.id_fornecedor, notafiscal.numero, fornecedor.cnpj, fornecedor.ie, fornecedor.uf, fornecedor.bairro, fornecedor.cidade, fornecedor.endereco, fornecedor.numero, fornecedor.razao_social, imei_por_nota.imei FROM imei_por_nota INNER JOIN notafiscal ON (notafiscal.id = imei_por_nota.invoice_id) INNER JOIN fornecedor ON (notafiscal.id_fornecedor = fornecedor.id) WHERE imei_por_nota.invoice_id = (SELECT notafiscal.id FROM notafiscal INNER JOIN imei_por_nota ON (notafiscal.id = imei_por_nota.invoice_id) WHERE imei_por_nota.imei iLike :imei)";
        nfs = MANAGER.createNativeQuery(jpql, Invoice.class).setParameter("imei", imei).getResultList();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        Invoice nf = new Invoice();
        nf.setId(nfs.get(0).getId());
        nf.setNumber(nfs.get(0).getNumber());
        nf.setIssuanceDate(nfs.get(0).getIssuanceDate());
        nf.setDateEntry(nfs.get(0).getDateEntry());
        List<String> imeis = new ArrayList<>();
        for (int i = 0; i < nfs.size(); i++) {
            imeis.add(nfs.get(i).getImei().get(i));
        }
        nf.setImei(imeis);
        nf.setCnpjFornecedor(nfs.get(0).getCnpjFornecedor());
        return nf;
    }

    @Override
    public Invoice getInvoiceByNumber(Integer number) {
        List<Invoice> nfs = null;
        try {        
        TRANSACTION.begin();
        String jpql = "SELECT notafiscal.id, notafiscal.dataEmissao, notafiscal.dataEntrada, notafiscal.id_fornecedor, notafiscal.numero, fornecedor.cnpj, fornecedor.ie, fornecedor.uf, fornecedor.bairro, fornecedor.cidade, fornecedor.endereco, fornecedor.numero, fornecedor.razao_social, imei_por_nota.imei FROM imei_por_nota INNER JOIN notafiscal ON (notafiscal.id = imei_por_nota.invoice_id) INNER JOIN fornecedor ON (notafiscal.id_fornecedor = fornecedor.id) WHERE notafiscal.numero = :numero";
        nfs = MANAGER.createNativeQuery(jpql, Invoice.class).setParameter("numero", number).getResultList();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }          
        Invoice nf = new Invoice();
        nf.setId(nfs.get(0).getId());
        nf.setNumber(nfs.get(0).getNumber());
        nf.setIssuanceDate(nfs.get(0).getIssuanceDate());
        nf.setDateEntry(nfs.get(0).getDateEntry());
        List<String> imeis = new ArrayList<>();
        for (int i = 0; i < nfs.size(); i++) {
            imeis.add(nfs.get(i).getImei().get(i));
        }
        nf.setImei(imeis);
        nf.setCnpjFornecedor(nfs.get(0).getCnpjFornecedor());
        return nf;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> invoice = null;
        try {        
        TRANSACTION.begin();
        Query query = MANAGER.createQuery("SELECT u FROM Invoice u");
        invoice = query.getResultList();
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }          
        return invoice;
    }

    /**
     *
     * @param invoice
     */
    @Override
    public void updateInvoice(Invoice invoice) {
        try {          
        TRANSACTION.begin();
        MANAGER.merge(invoice);
        TRANSACTION.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }

    @Override
    public Invoice getInvoiceByGenericSearch(String search) {
        Invoice invoice;
        try {
            invoice = this.getInvoiceById(Integer.valueOf(search));
        } catch(NumberFormatException n) {
            invoice = this.getInvoiceByImei(search);
        }
        return invoice;
    }

}
