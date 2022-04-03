package pl.edu.agh.odt;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.ext.jackson.JacksonConverter;
import pl.edu.agh.odt.resource.SearchResource;

public class OdtApp {

    public static void main(String[] args) throws Exception {
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter());
        new Server(Protocol.HTTP, 8182, SearchResource.class).start();
    }

}
