package riodopeixe.rest.resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.cache.Cache;
import riodopeixe.rest.dataset.ImeSaleDataSetImpl;
import riodopeixe.rest.dataset.ImeiSaleDataSet;
import riodopeixe.rest.model.ImeiSale;

/**
 * Classe de recurso para objetos do tipo Invoice.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 03/06/2016
 *
 * @version 1.0
 */
@Path("/imei-sale")
public class ImeiSaleResource {

    ImeiSaleDataSet imeiSaleDataSet;
    ObjectMapper mapper = new ObjectMapper();

    /**
     *
     */
    public ImeiSaleResource() {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);        
        this.imeiSaleDataSet = new ImeSaleDataSetImpl();
    }

    /**
     *
     * @param imei
     * @return
     */
    @GET
    @GZIP
    @Path("{imei}")
    @Cache(mustRevalidate = true, maxAge = 3600) 
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ImeiSale  getImeiSale(@PathParam("imei") String imei) {
        System.out.println("Geting Invoice by Imei/Id: " + imei);
        ImeiSale imeiSale = imeiSaleDataSet.imeiSaleCheck(imei);
        return imeiSale;
    }

}
