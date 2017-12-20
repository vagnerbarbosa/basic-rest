package riodopeixe.rest.resource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

/**
 *
 * @author vagner
 */
@ApplicationPath("/webservice")
public class ApplicationConfig extends Application {

    private final Set<Object> singletons = new HashSet<>();
    private final HashSet<Class<?>> classes = new HashSet<>();
    private final List<String> allowedDomains = Arrays.asList("http://192.168.19.250:8080", "http://192.168.19.33:8080", "http://192.168.19.33:3000");


    public ApplicationConfig() {       
       
        BeanConfig conf = new BeanConfig();
        conf.setTitle("Rio do Peixe Rest-WebService");
        conf.setDescription("API de integração entre Sabium, Termometro de Vendas e Jarvis.");
        conf.setVersion("v2.00A rev.00007");
        conf.setHost("localhost:8080");
        conf.setBasePath("/riodopeixe-rest/");
        conf.setSchemes(new String[] { "http" });
        conf.setResourcePackage("riodopeixe.rest.resource");
        conf.setScan(true);        

        CorsFilter corsFilter = new CorsFilter();
        //corsFilter.getAllowedOrigins().addAll(allowedDomains);
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        singletons.add(corsFilter);

        classes.add(HelloWorldResource.class);
        classes.add(InvoiceResource.class);        
        classes.add(SalesOrderResource.class);
        classes.add(SalesResource.class);
        classes.add(SupplierResource.class);
        classes.add(SysInvoiceResource.class);
        classes.add(TonerResource.class);
        classes.add(ApiListingResource.class);
        classes.add(SwaggerSerializers.class);
    }
    
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public HashSet<Class<?>> getClasses(){
      return classes;
    }    

    
}
