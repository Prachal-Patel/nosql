package api ;

import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class AppServer extends Application {

    public static void startup() {
        try {
            Component server = new Component() ;
            server.getServers().add(Protocol.HTTP, 9090) ;
            server.getDefaultHost().attach(new AppServer()) ;
            server.start() ;
        } catch ( Exception e ) {
            System.out.println( e ) ;
        }
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext()) ;      
        router.attach( "/", PingResource.class ) ;  
        router.attach( "/db", RecordResource.class ) ;
        router.attach( "/db/{key}", RecordResource.class ) ;        
        return router;
    }


}



        