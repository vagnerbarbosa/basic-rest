package vg.sales.weather.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.cache.Cache;
import vg.sales.weather.datasource.TonerDataSet;
import vg.sales.weather.datasource.TonerDataSetImpl;
import vg.sales.weather.model.Toner;

/**
 *
 * @author vagner
 */
@Path("/toner")
public class TonerResource {

    static String xmlString = null;
    private final TonerDataSet tonerDataSet;

    public TonerResource() {
        this.tonerDataSet = new TonerDataSetImpl();
    }

    /**
     *
     * @return
     */
    @GET
    @GZIP
    @Path("/listar")
    @Cache(mustRevalidate = true, maxAge = 3600)   
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})    
    public ArrayList<Toner> listarToners() {
        System.out.println("Recuperando toners...");
        ArrayList<Toner> tonerList = (ArrayList<Toner>) tonerDataSet.listarToners();
        return tonerList;
    }
    
    /**
     *
     * @param ref
     * @return
     */    
    @GET
    @GZIP
    @Path("recuperar/{ref}")
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Toner recuperarTonersPorRef(@PathParam("ref") Integer ref) {
        System.out.println("Recuperando toner...");
        Toner tonerList = (Toner) tonerDataSet.getTonerPorRef(ref);
        return tonerList;
    }

    @POST
    @GZIP
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Path("adicionar/{adicionar}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void persistirToner(Toner toner) {
        System.out.println("Persistindo toner...");
        tonerDataSet.pesistirToner(toner);
    }    
        
    @GET
    @GZIP
    @Path("remover/{ref}")
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void removerToner(@PathParam("ref") Integer ref) {
        System.out.println("Removendo toner...");
        tonerDataSet.removerToner(ref);
    }
    
    @POST
    @GZIP
    @Path("alterar/{alterar}")
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void alterarToner(Toner toner) {
        System.out.println("Alterando toner...");
        tonerDataSet.alterarToner(toner);
    }
    
    @GET
    @GZIP
    @Path("/data-entrada/{datainicial}/{datafinal}")
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Toner> listarTonersPorDataEntrada(@PathParam("datainicial") String dataInicial, @PathParam("datafinal") String dataFinal) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dataInicial2;
        Date dataFinal2;
        ArrayList<Toner> tonerList = null;
        try {
            dataInicial2 = sdf.parse(dataInicial);
            dataFinal2 = sdf.parse(dataFinal);
            System.out.println("Recuperando toners...");
            tonerList = (ArrayList<Toner>) tonerDataSet.listarTonersPorDataEntrada(dataInicial2, dataFinal2);  
        } catch (ParseException ex) {
            Logger.getLogger(TonerResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tonerList;
    }

    @GET
    @GZIP
    @Path("/data-saida/{datainicial}/{datafinal}")
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Toner> listarTonersPorDataSaida(@PathParam("datainicial") String dataInicial, @PathParam("datafinal") String dataFinal) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dataInicial2;
        Date dataFinal2;
        ArrayList<Toner> tonerList = null;
        try {
            dataInicial2 = sdf.parse(dataInicial);
            dataFinal2 = sdf.parse(dataFinal);
            System.out.println("Recuperando toners...");
            tonerList = (ArrayList<Toner>) tonerDataSet.listarTonersPorDataSaida(dataInicial2, dataFinal2);
        } catch (ParseException ex) {
            Logger.getLogger(TonerResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tonerList;
    }     
}
