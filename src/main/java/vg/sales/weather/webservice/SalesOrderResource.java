package vg.sales.weather.webservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import vg.sales.weather.datasource.ConnectionException;
import vg.sales.weather.datasource.SalesOrderDataSetImpl;
import vg.sales.weather.model.SalesOrder;

/**
 *
 * @author vagner
 */
@Path("/sales-order")
public class SalesOrderResource {
    
    static final String API_VERSION = "1.01A rev.18729";    
    static String xmlString = null;
    ObjectMapper mapper = new ObjectMapper();
    SalesOrderDataSetImpl salesOrderDataSet;

    public SalesOrderResource() throws ConnectionException, SQLException {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        this.salesOrderDataSet = new SalesOrderDataSetImpl();
    }
    
    @Path("/version")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String returnVersion() {
        return "<p>Version: " + API_VERSION + "</p>";
    }
    
    @Path("/{branchNumber}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<SalesOrder> getSalesOrder(@PathParam("branchNumber") Integer branchNumber) throws ConnectionException {
        ArrayList<SalesOrder> salesList = null;
        try {            
            salesList = (ArrayList<SalesOrder>) salesOrderDataSet.listSalesOrder(branchNumber);
        } catch (SQLException ex) {
            Logger.getLogger(SalesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salesList;
    } 
    
    

    
}
