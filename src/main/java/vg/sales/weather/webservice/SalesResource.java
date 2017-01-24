package vg.sales.weather.webservice;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;

import org.jboss.resteasy.annotations.cache.Cache;
import vg.sales.weather.datasource.SalesDataSetImpl;
import vg.sales.weather.model.Sales;

/**
 *
 * @author vagner
 */
@Path("/sales")
public class SalesResource {
    
    static final String API_VERSION = "1.01A rev.18729";    
    static String xmlString = null;
    SalesDataSetImpl salesDataSet;

    public SalesResource() {
        this.salesDataSet = new SalesDataSetImpl();
    }
    
    @Path("/version")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Cache(mustRevalidate = true)
    @GZIP
    public String returnVersion() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"pt-br\">\n"
                + "    <head>\n"
                + "        <title>Toner WebService</title>\n"
                + "        \n"
                + "        <meta charset=\"utf-8\">\n"
                + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "        <link rel=\"stylesheet\" href=\"/sales-weather/bower_components/bootstrap/dist/css/bootstrap.min.css\">        \n"
                + "    </head>\n"
                + "    <body>\n"
                + "    <div class=\"page-header\">\n"                 
                + "        <h1 class=\"text-center\"> \n"
                + "        Sales Weather Rest-WebService <small>v" + API_VERSION
                + "        </small></h1>\n"
                + "        \n"
                + "        <script type=\"text/javascript\" src=\"/sales-weather/bower_components/jquery/dist/jquery.slim.min.js\"></script>\n"
                + "        <script type=\"text/javascript\" src=\"/sales-weather/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>\n"
                + "        </div>\n"                               
                + "    </body>\n"
                + "</html>";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Cache(mustRevalidate = true)
    @GZIP
    public ArrayList<Sales> getSales() {
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
}
