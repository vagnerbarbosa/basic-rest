package riodopeixe.rest.dataset;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    private static final EntityManager MANAGER;

    static {
        MANAGER = Persistence.createEntityManagerFactory("sisnota").createEntityManager();
    }

    /**
     *
     * @param invoice
     */
    @Override
    public void setInvoice(Invoice invoice) {
        MANAGER.getTransaction().begin();
        MANAGER.flush();
        MANAGER.clear();
        MANAGER.persist(invoice);
        MANAGER.getTransaction().commit();
    }

    /**
     *
     * @param id
     */
    @Override
    public void removeInvoice(Integer id) {
        MANAGER.getTransaction().begin();
        MANAGER.remove(MANAGER.find(Invoice.class, id));
        MANAGER.getTransaction().commit();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Invoice getInvoiceById(Integer id) {
        MANAGER.getTransaction().begin();
        Invoice invoice = MANAGER.find(Invoice.class, id);
        MANAGER.getTransaction().commit();
        return invoice;
    }

    /**
     *
     * @param imei
     * @return
     */
    @Override
    public Invoice getInvoiceByImei(String imei) {
        MANAGER.getTransaction().begin();
        String jpql = "SELECT notafiscal.id, notafiscal.dataEmissao, notafiscal.dataEntrada, notafiscal.id_fornecedor, notafiscal.numero, fornecedor.cnpj, fornecedor.ie, fornecedor.uf, fornecedor.bairro, fornecedor.cidade, fornecedor.endereco, fornecedor.numero, fornecedor.razao_social, imei_por_nota.imei FROM imei_por_nota INNER JOIN notafiscal ON (notafiscal.id = imei_por_nota.invoice_id) INNER JOIN fornecedor ON (notafiscal.id_fornecedor = fornecedor.id) WHERE imei_por_nota.invoice_id = (SELECT notafiscal.id FROM notafiscal INNER JOIN imei_por_nota ON (notafiscal.id = imei_por_nota.invoice_id) WHERE imei_por_nota.imei iLike :imei)";
        List<Invoice> nfs = MANAGER.createNativeQuery(jpql, Invoice.class).setParameter("imei", imei).getResultList();
        MANAGER.getTransaction().commit();
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
        MANAGER.getTransaction().begin();
        String jpql = "SELECT notafiscal.id, notafiscal.dataEmissao, notafiscal.dataEntrada, notafiscal.id_fornecedor, notafiscal.numero, fornecedor.cnpj, fornecedor.ie, fornecedor.uf, fornecedor.bairro, fornecedor.cidade, fornecedor.endereco, fornecedor.numero, fornecedor.razao_social, imei_por_nota.imei FROM imei_por_nota INNER JOIN notafiscal ON (notafiscal.id = imei_por_nota.invoice_id) INNER JOIN fornecedor ON (notafiscal.id_fornecedor = fornecedor.id) WHERE notafiscal.numero = :numero";
        List<Invoice> nfs = MANAGER.createNativeQuery(jpql, Invoice.class).setParameter("numero", number).getResultList();
        MANAGER.getTransaction().commit();
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
        MANAGER.getTransaction().begin();
        Query query = MANAGER.createQuery("SELECT u FROM Invoice u");
        MANAGER.getTransaction().commit();
        List<Invoice> invoice = query.getResultList();
        return invoice;
    }

    /**
     *
     * @param invoice
     */
    @Override
    public void updateInvoice(Invoice invoice) {
        MANAGER.getTransaction().begin();
        MANAGER.merge(invoice);
        MANAGER.getTransaction().commit();
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
