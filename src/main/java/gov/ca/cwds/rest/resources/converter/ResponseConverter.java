package gov.ca.cwds.rest.resources.converter;

import javax.ws.rs.core.Response;

public class ResponseConverter {

  public Response withDataResponse(gov.ca.cwds.rest.api.Response serviceResponse) {
    return serviceResponse != null ? javax.ws.rs.core.Response.ok(serviceResponse).build()
        : notFound();
  }

  public Response withOKResponse(gov.ca.cwds.rest.api.Response serviceResponse) {
    return serviceResponse != null ? javax.ws.rs.core.Response.status(Response.Status.OK).build()
        : notFound();
  }

  public Response withCreatedResponse(gov.ca.cwds.rest.api.Response serviceResponse) {
    return javax.ws.rs.core.Response.status(Response.Status.CREATED).entity(serviceResponse)
        .build();
  }

  private Response notFound() {
    return javax.ws.rs.core.Response.status(Response.Status.NOT_FOUND).entity((Object) null)
        .build();
  }

}
