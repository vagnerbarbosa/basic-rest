/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vg.sales.weather.webservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import vg.sales.weather.datasource.ConnectionException;
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
    ObjectMapper mapper = new ObjectMapper();
    SalesDataSetImpl salesDataSet;

    public SalesResource() throws ConnectionException, SQLException {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        this.salesDataSet = new SalesDataSetImpl();
    }
    
    @Path("/version")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String returnVersion() {
        return "<p>Version: " + API_VERSION + "</p>";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Sales> getSales() throws ConnectionException {
        ArrayList<Sales> salesList = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Fortaleza");
            System.out.println("Geting Sales... " + ZonedDateTime.now(fusoHorarioDeSaoPaulo));
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatador = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
            today.format(formatador); //08/04/2014
            salesList = (ArrayList<Sales>) salesDataSet.listSales(Date.valueOf(today), Date.valueOf(today));            
        } catch (SQLException ex) {
            Logger.getLogger(SalesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salesList;
    } 
}
