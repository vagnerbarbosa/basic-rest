package riodopeixe.rest.resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.cache.Cache;
import riodopeixe.rest.dataset.CellPhoneDataSet;
import riodopeixe.rest.dataset.CellPhoneDataSetImpl;
import riodopeixe.rest.model.CellPhone;
import riodopeixe.rest.model.Invoice;
import riodopeixe.rest.model.ProductRegistration;

/**
 *
 * @author vagner
 */
@Path("/celular")
public class CellPhoneResource implements Serializable {
    
    ObjectMapper mapper = new ObjectMapper();
    CellPhoneDataSet cellPhoneDataSet;

    public CellPhoneResource() {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        cellPhoneDataSet = new CellPhoneDataSetImpl();
    }
    
    /**
     *
     * @param code
     * @param color
     * @param volts
     * @return
     */
    @GET
    @GZIP
    @Path("{code}/{color}/{volts}")
    @Cache(mustRevalidate = true, maxAge = 3600) 
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ProductRegistration getCellPhoneByRef(@PathParam("code") int code, @PathParam("color") int color, @PathParam("volts") int volts) {
        System.out.println("Geting CellPhone by ref: " + code + " - " + color + " - " + volts);
        ProductRegistration cellPhone = cellPhoneDataSet.getCellPhonebyRef(code, color, volts);
        return cellPhone;
    }    
    
}
