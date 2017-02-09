package riodopeixe.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.cache.Cache;
/**
 *
 * @author vagner
 */
@Path("/")
public class HelloWorldResource {
    
    static final String API_VERSION = "2.00A rev.00004";
    
    @GET
    @GZIP
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Produces(MediaType.TEXT_HTML)
    public String returnVersion() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"pt-br\">\n"
                + "    <head>\n"
                + "        <title>Rio do Peixe Rest-WebService</title>\n"
                + "        \n"
                + "        <meta charset=\"utf-8\">\n"
                + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "        <link rel=\"stylesheet\" href=\"/riodopeixe-rest/bower_components/bootstrap/dist/css/bootstrap.min.css\">        \n"
                + "    </head>\n"
                + "    <body>\n"
                + "    <div class=\"page-header\">\n"                 
                + "        <h1 class=\"text-center\"> \n"
                + "        Rio do Peixe Rest-WebService <small>v" + API_VERSION
                + "        </small></h1>\n"
                + "        \n"
                + "        <script type=\"text/javascript\" src=\"/riodopeixe-rest/bower_components/jquery/dist/jquery.slim.min.js\"></script>\n"
                + "        <script type=\"text/javascript\" src=\"/riodopeixe-rest/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>\n"
                + "        </div>\n"                               
                + "    </body>\n"
                + "</html>";
    }
    
    
}
