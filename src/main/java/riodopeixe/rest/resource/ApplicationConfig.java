package riodopeixe.rest.resource;

import java.util.HashSet;
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

    private final Set<Object> singletons = new HashSet<Object>();
    private final HashSet<Class<?>> classes = new HashSet<Class<?>>();  

    public ApplicationConfig() {
        CorsFilter corsFilter = new CorsFilter();
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
