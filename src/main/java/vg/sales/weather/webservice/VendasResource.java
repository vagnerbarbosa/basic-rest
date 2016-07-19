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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import vg.integrator.component.datasource.ConnectionException;
import vg.integrator.component.datasource.VendasDataSetImpl;
import vg.integrator.component.model.Vendas;

/**
 *
 * @author vagner
 */
@Path("/vendas")
public class VendasResource {
    
    static final String API_VERSION = "1.01A rev.18729";    
    static String xmlString = null;
    ObjectMapper mapper = new ObjectMapper();
    VendasDataSetImpl vendasDataSet;

    public VendasResource() throws ConnectionException, SQLException {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        this.vendasDataSet = new VendasDataSetImpl();
    }
    
    @Path("/version")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String returnVersion() {
        return "<p>Version: " + API_VERSION + "</p>";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Vendas> getVendas() {
        ArrayList<Vendas> vendasList = null;
        try {
            System.out.println("Get Vendas...");
            LocalDate hoje = LocalDate.now();
            DateTimeFormatter formatador = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
            hoje.format(formatador); //08/04/2014
            vendasList = (ArrayList<Vendas>) vendasDataSet.listarVendas(Date.valueOf(hoje), Date.valueOf(hoje));            
        } catch (SQLException ex) {
            Logger.getLogger(VendasResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendasList;
    }    
    
    
    
}
