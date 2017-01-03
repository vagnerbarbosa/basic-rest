package vg.sales.weather.webservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import vg.sales.weather.datasource.SalesOrderDataSetImpl;
import vg.sales.weather.model.Product;
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

    public SalesOrderResource() {
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
    public ArrayList<SalesOrder> getSalesOrder(@PathParam("branchNumber") Integer branchNumber) {
        ArrayList<SalesOrder> salesList = null;          
            salesList = (ArrayList<SalesOrder>) salesOrderDataSet.listSalesOrder(branchNumber);
        return salesList;
    } 
    
    @Path("/products/{branchNumber}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Product> getSalesProducts(@PathParam("branchNumber") Integer branchNumber) {
        ArrayList<Product> salesList = null;          
            salesList = (ArrayList<Product>) salesOrderDataSet.listSalesProducts(branchNumber);
        return salesList;
    }    
    
    

    
}
