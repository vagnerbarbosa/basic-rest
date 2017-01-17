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
import static vg.sales.weather.webservice.SalesResource.API_VERSION;

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
        return "<!DOCTYPE html>\n"
                + "<html lang=\"pt-br\">\n"
                + "    <head>\n"
                + "        <title>Toner WebService</title>\n"
                + "        \n"
                + "        <meta charset=\"utf-8\">\n"
                + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "        <link rel=\"stylesheet\" href=\"/toners-webservice/bower_components/bootstrap/dist/css/bootstrap.min.css\">        \n"
                + "    </head>\n"
                + "    <body>\n"
                + "    <div class=\"page-header\">\n"                 
                + "        <h1 class=\"text-center\"> \n"
                + "        Sales Weather Rest-WebService <small>v" + API_VERSION
                + "        </small></h1>\n"
                + "        \n"
                + "        <script type=\"text/javascript\" src=\"/toners-webservice/bower_components/jquery/dist/jquery.slim.min.js\"></script>\n"
                + "        <script type=\"text/javascript\" src=\"/toners-webservice/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>\n"
                + "        </div>\n"                               
                + "    </body>\n"
                + "</html>";
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
