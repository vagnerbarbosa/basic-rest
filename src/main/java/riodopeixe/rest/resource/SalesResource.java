package riodopeixe.rest.resource;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;

import org.jboss.resteasy.annotations.cache.Cache;
import riodopeixe.rest.dataset.SalesDataSetImpl;
import riodopeixe.rest.model.Sales;

/**
 *
 * @author vagner
 */
@Path("/sales")
public class SalesResource {
        
    static String xmlString = null;
    SalesDataSetImpl salesDataSet;

    public SalesResource() {
        this.salesDataSet = new SalesDataSetImpl();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Cache(mustRevalidate = true)
    @GZIP
    public ArrayList<Sales> getSalesToday() {
        ArrayList<Sales> salesList = null;

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Fortaleza");
            System.out.println("Geting Sales... " + ZonedDateTime.now(fusoHorarioDeSaoPaulo));
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatador = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
            today.format(formatador); //08/04/2014
            salesList = (ArrayList<Sales>) salesDataSet.listSales(Date.valueOf(today), Date.valueOf(today));            
 
        return salesList;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Cache(mustRevalidate = true)
    @GZIP
    @Path("{datainicial}/{datafinal}")
    public ArrayList<Sales> getSalesByDate(@PathParam("datainicial") String dataInicial, @PathParam("datafinal") String dataFinal) {
        ArrayList<Sales> salesList = null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.US);  
            LocalDate dateOne = LocalDate.parse(dataInicial, formatter);
            LocalDate dateTwo = LocalDate.parse(dataFinal, formatter);

            salesList = (ArrayList<Sales>) salesDataSet.listSales(Date.valueOf(dateOne), Date.valueOf(dateTwo));            
 
        return salesList;
    }    
}
