package gov.ca.cwds.rest.resources;

import static gov.ca.cwds.rest.core.Api.RESOURCE_APPLICATION;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import gov.ca.cwds.ObjectMapperUtils;
import gov.ca.cwds.rest.api.ApiException;
import io.swagger.annotations.Api;


/**
 * A resource providing a basic information about the CWDS API.
 * 
 * @author CWDS API Team
 */
@Api(value = RESOURCE_APPLICATION, hidden = true)
@Path(RESOURCE_APPLICATION)
public class ApplicationResource {

  private String applicationName;
  private String version;

  private static final ObjectMapper MAPPER = ObjectMapperUtils.createObjectMapper();

  /**
   * Constructor
   * 
   * @param applicationName The name of the application
   * @param version The version of the API
   */
  @Inject
  public ApplicationResource(@Named("app.name") String applicationName,
      @Named("app.version") String version) {
    this.applicationName = applicationName;
    this.version = version;
  }

  /**
   * Get the name of the application.
   * 
   * @return the application data
   */
  @GET
  public String get() {
    ImmutableMap<String, String> map = ImmutableMap.<String, String>builder()
        .put("Application", applicationName).put("Version", version).build();
    try {
      return MAPPER.writeValueAsString(map);
    } catch (JsonProcessingException e) {
      throw new ApiException("Unable to parse application data", e);
    }
  }
}
