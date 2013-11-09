package ngusd.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ngusd.manager.UserScenarioDocuManager;

@Path("/rest/admin/")
public class AdminResource {
	
	@GET
	@Path("update")
	@Produces({ "application/xml", "application/json" })
	public void updateAll() {
		UserScenarioDocuManager.INSTANCE.updateAll();
	}
}
