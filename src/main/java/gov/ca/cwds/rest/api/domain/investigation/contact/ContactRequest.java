package gov.ca.cwds.rest.api.domain.investigation.contact;

import java.util.Set;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.domain.DomainObject;
import gov.ca.cwds.rest.api.domain.IndividualDeliveredService;
import gov.ca.cwds.rest.api.domain.SystemCodeCategoryId;
import gov.ca.cwds.rest.validation.ValidSystemCodeId;
import io.dropwizard.jackson.JsonSnakeCase;
import io.dropwizard.validation.OneOf;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@link DomainObject} representing a Contact Request
 * 
 * @author CWDS API Team
 */
@JsonSnakeCase
@JsonPropertyOrder({"startedAt", "endedAt", "purpose", "communicationMethod", "status", "services",
    "location", "note", "people"})
public class ContactRequest implements Request {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @NotEmpty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  @JsonProperty("started_at")
  @gov.ca.cwds.rest.validation.Date(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", required = true)
  @ApiModelProperty(required = true, readOnly = false, value = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
      example = "2010-04-27T23:30:14.000Z")
  private String startedAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  @JsonProperty("ended_at")
  @gov.ca.cwds.rest.validation.Date(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", required = false)
  @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
      example = "2010-04-28T23:30:14.000Z")
  private String endedAt;

  @JsonProperty("purpose")
  @ValidSystemCodeId(required = true, category = SystemCodeCategoryId.CONTACT_TYPE)
  @ApiModelProperty(required = true, readOnly = false,
      value = "Delivered service contact type system code ID e.g)  -> ", example = "433")
  private String purpose;

  @JsonProperty("communication_method")
  @ValidSystemCodeId(required = true, category = SystemCodeCategoryId.COMMUNICATION_METHOD)
  @ApiModelProperty(required = true, readOnly = false,
      value = "Delivered service communication method type system code ID e.g) 408 -> In-Person",
      example = "408")
  private String communicationMethod;

  @NotEmpty
  @JsonProperty("status")
  @Size(min = 1, max = 1)
  @OneOf(value = {"C", "A", "S"}, ignoreCase = false, ignoreWhitespace = true)
  @ApiModelProperty(required = true, readOnly = false, value = "", example = "C")
  private String status;


  @JsonProperty("services")
  @ApiModelProperty(required = false, readOnly = false)
  private Set<Integer> services;

  @JsonProperty("location")
  @ValidSystemCodeId(required = true, category = SystemCodeCategoryId.CONTACT_LOCATION)
  @ApiModelProperty(required = true, readOnly = false,
      value = "Delivered service contact location type system code ID e.g) 415 -> CWS Office",
      example = "415")
  private String location;

  @JsonProperty("note")
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "detail text")
  private String note;

  @NotEmpty
  @ApiModelProperty(required = true, readOnly = false)
  @JsonProperty("people")
  // ("contact_participants") INDIVIDUAL_DELIVERED_SERVICE
  private Set<IndividualDeliveredService> people;

  /**
   * @param startedAt started at
   * @param endedAt ended at
   * @param purpose purpose
   * @param communicationMethod communication method
   * @param status status
   * @param services services
   * @param location location
   * @param note note
   * @param people people
   */
  @JsonCreator
  public ContactRequest(@JsonProperty("started_at") String startedAt,
      @JsonProperty("ended_at") String endedAt, @JsonProperty("purpose") String purpose,
      @JsonProperty("communication_method") String communicationMethod,
      @JsonProperty("status") String status, @JsonProperty("services") Set<Integer> services,
      @JsonProperty("location") String location, @JsonProperty("note") String note,
      @JsonProperty("people") Set<IndividualDeliveredService> people) {
    this.startedAt = startedAt;
    this.endedAt = endedAt;
    this.purpose = purpose;
    this.communicationMethod = communicationMethod;
    this.status = status;
    this.services = services;
    this.location = location;
    this.note = note;
    this.people = people;
  }


  /**
   * @return the startedAt
   */
  public String getStartedAt() {
    return startedAt;
  }



  /**
   * @return the endedAt
   */
  public String getEndedAt() {
    return endedAt;
  }



  /**
   * @return the purpose
   */
  public String getPurpose() {
    return purpose;
  }



  /**
   * @return the communicationMethod
   */
  public String getCommunicationMethod() {
    return communicationMethod;
  }



  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }



  /**
   * @return the services
   */
  public Set<Integer> getServices() {
    return services;
  }



  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }



  /**
   * @return the note
   */
  public String getNote() {
    return note;
  }



  /**
   * @return the people
   */
  public Set<IndividualDeliveredService> getPeople() {
    return people;
  }



  /**
   * {@inheritDoc}
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public final int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  /**
   * {@inheritDoc}
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public final boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, false);
  }

}
