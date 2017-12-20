package riodopeixe.rest.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.cache.Cache;
import riodopeixe.rest.dataset.SalesOrderDataSetImpl;
import riodopeixe.rest.model.Product;
import riodopeixe.rest.model.SalesOrder;

/**
 *
 * @author vagner
 */
@Api(tags = "Recurso SalesOrder")
@Path("/sales-order")
public class SalesOrderResource {
       
    static String xmlString = null;
    SalesOrderDataSetImpl salesOrderDataSet;

    public SalesOrderResource() {
        this.salesOrderDataSet = new SalesOrderDataSetImpl();
    }
       
    @Path("/{branchNumber}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Cache(mustRevalidate = true)
    @GZIP
    @ApiOperation(
		value = "Recupera vários dados a respeito dos Pedidos de Vendas por Filial que possuem alguma pendência de entrega ou montagem.",  
		produces = MediaType.APPLICATION_JSON)    
    public ArrayList<SalesOrder> getSalesOrder(@PathParam("branchNumber") Integer branchNumber) {
        ArrayList<SalesOrder> salesList = null;          
            salesList = (ArrayList<SalesOrder>) salesOrderDataSet.listSalesOrder(branchNumber);
        return salesList;
    } 
    
    @Path("/products/{branchNumber}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Cache(mustRevalidate = true)
    @GZIP
    @ApiOperation(
		value = "Recupera vários dados a respeito dos produtos dos Pedidos de Vendas por Filial que possuem alguma pendência de entrega ou montagem.",  
		produces = MediaType.APPLICATION_JSON)      
    public ArrayList<Product> getSalesProducts(@PathParam("branchNumber") Integer branchNumber) {
        ArrayList<Product> salesList = null;          
            salesList = (ArrayList<Product>) salesOrderDataSet.listSalesProducts(branchNumber);
        return salesList;
    }    
    
    

    
}
