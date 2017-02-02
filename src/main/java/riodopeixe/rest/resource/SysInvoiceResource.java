package riodopeixe.rest.resource;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import riodopeixe.rest.model.SysInvoice;
import riodopeixe.rest.jdbc.ConnectionException;
import riodopeixe.rest.jdbc.SysInvoiceDataSet;
import riodopeixe.rest.jdbc.SysInvoiceDataSetImpl;

/**
 * Classe de recurso para objetos do tipo SysInvoice.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 03/06/2016
 *
 * @version 1.0
 */
@Path("/nota-entrada")
public class SysInvoiceResource {

    static final String API_VERSION = "1.01A rev.18729";
    static String xmlString = null;
    SysInvoiceDataSet sysInvoiceDataSetImpl;

    /**
     *
     * @throws riodopeixe.rest.jdbc.ConnectionException
     */
    public SysInvoiceResource() throws ConnectionException {
        this.sysInvoiceDataSetImpl = new SysInvoiceDataSetImpl();
    }


    /**
     *
     * @param code
     * @return 
     * @throws java.sql.SQLException
     */
    @Path("{code}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<SysInvoice> getInvoices(@PathParam("code") String code) throws SQLException {
        System.out.println("Get Invoices...");        
        List<SysInvoice> notaList = sysInvoiceDataSetImpl.getSysInvoices(code);
        return notaList;
    }

   
}
