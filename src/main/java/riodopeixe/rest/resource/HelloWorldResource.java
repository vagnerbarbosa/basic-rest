package riodopeixe.rest.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api
@Path("/")
public class HelloWorldResource {
    
    static final String API_VERSION = "2.00A rev.00007";
    
    @GET
    @GZIP
    @Cache(mustRevalidate = true, maxAge = 3600)
    @Produces(MediaType.TEXT_HTML)
    	@ApiOperation(
		value = "Mostra a versão da API.",  
		produces = MediaType.TEXT_HTML,
                tags = "Recurso HelloWorld")
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
                + "        <ul class=\"nav nav-pills\" ><li role=\"presentation\" class=\"active\"><a href=\"http://192.168.19.250:8080/riodopeixe-rest/doc/\"><span class=\"glyphicon glyphicon-book\" aria-hidden=\"true\"></span> Documentação da API</a></li><ul></small> </h1>\n"
                + "        \n"
                + "        <script type=\"text/javascript\" src=\"/riodopeixe-rest/bower_components/jquery/dist/jquery.slim.min.js\"></script>\n"
                + "        <script type=\"text/javascript\" src=\"/riodopeixe-rest/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>\n"
                + "        </div>\n"                               
                + "    </body>\n"
                + "</html>";
    }
    
    
}
