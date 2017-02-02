package riodopeixe.rest.resource;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.cache.Cache;
import riodopeixe.rest.dataset.InvoiceDataSet;
import riodopeixe.rest.dataset.InvoiceDataSetImpl;
import riodopeixe.rest.dataset.SupplierDataSet;
import riodopeixe.rest.dataset.SupplierDataSetImpl;
import riodopeixe.rest.model.Invoice;

/**
 * Classe de recurso para objetos do tipo Invoice.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 03/06/2016
 *
 * @version 1.0
 */
@Path("/nota")
public class InvoiceResource {

    static final String API_VERSION = "1.01A rev.18729";
    static String xmlString = null;
    InvoiceDataSet invoiceDataSet;
    SupplierDataSet supplierDataSet;

    /**
     *
     */
    public InvoiceResource() {
        this.invoiceDataSet = new InvoiceDataSetImpl();
        this.supplierDataSet = new SupplierDataSetImpl();
    }


    /**
     *
     * @return
     */
    @GET
    @GZIP
    @Cache(mustRevalidate = true, maxAge = 3600) 
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Invoice> getInvoices() {
        System.out.println("Get Invoices...");
        ArrayList<Invoice> notaList = (ArrayList<Invoice>) invoiceDataSet.getInvoices();
        return notaList;
    }

    /**
     *
     * @param imei
     * @return
     */
    @GET
    @GZIP
    @Path("{imei}")
    @Cache(mustRevalidate = true, maxAge = 3600) 
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Invoice getInvoceByImei(@PathParam("imei") String imei) {
        System.out.println("Geting Invoice by Imei/Id: " + imei);
        Invoice notaFiscal = invoiceDataSet.getInvoiceByGenericSearch(imei);
        return notaFiscal;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GET
    @GZIP
    @Path("registro/{registro : \\d+}")    
    @Cache(mustRevalidate = true, maxAge = 3600)     
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Invoice getInvoceById(@PathParam("registro") String id) {
        System.out.println("Geting Invoice by Imei: " + id);
        Invoice notaFiscal = invoiceDataSet.getInvoiceById(Integer.valueOf(id));
        return notaFiscal;
    }    

    /**
     *
     * @param invoice
     * @return
     */
    @PUT
    @GZIP
    @Path("{numero}")    
    @Cache(mustRevalidate = true, maxAge = 3600)     
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Invoice updateInvoiceByNumber(Invoice invoice) {
        System.out.println("Update invoced, returned: " + invoice.toString());
        invoiceDataSet.updateInvoice(invoice);
        return invoice;
    }

    /**
     *
     * @param id
     */
    @GZIP
    @DELETE    
    @Path("{numero}")    
    @Cache(mustRevalidate = true, maxAge = 3600)     
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteInvoiceById(@PathParam("numero") Integer id) {
        System.out.println("Deleting Invoce by ID: " + id);
        invoiceDataSet.removeInvoice(id);
    }

    /**
     *
     * @param invoice
     * @return
     */
    @POST
    @GZIP
    @Path("/add")    
    @Cache(mustRevalidate = true, maxAge = 3600)     
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Invoice invoicePersist(Invoice invoice) {
        System.out.println("Adding Invoice with ID: " + invoice.getNumber());
        if (invoice.getNumber() != null) {
            System.out.println("Inside invoicePersist, returned: " + invoice.toString());
            invoiceDataSet.setInvoice(invoice);
        }
        return invoice;
    }
}
