package ngusd.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ngusd.dao.ConfigurationDAO;
import ngusd.model.configuration.Configuration;

import org.apache.log4j.Logger;

@Path("/rest/configuration/")
public class ConfigurationResource {
	
	private static final Logger LOGGER = Logger.getLogger(ConfigurationResource.class);
	
	@GET
	@Produces({ "application/json", "application/xml" })
	public Configuration getConfiguration() {
		return ConfigurationDAO.getConfiguration();
	}
	
	@POST
	@Consumes({ "application/json", "application/xml" })
	public void updateConfiguration(final Configuration configuration) {
		LOGGER.info("Saving configuration.");
		ConfigurationDAO.updateConfiguration(configuration);
	}
	
}
