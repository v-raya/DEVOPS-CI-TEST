package gov.ca.cwds.rest.api.domain.investigation;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.DomainObject;
import gov.ca.cwds.rest.api.domain.ReportingDomain;
import io.dropwizard.jackson.JsonSnakeCase;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@link DomainObject} representing a Simple Person With Relationship
 * 
 * @author CWDS API Team
 */
@JsonSnakeCase
@JsonPropertyOrder({"relationship", "last_name", "first_name", "sealed", "sensitive"})
public class SimplePersonWithRelationship extends ReportingDomain implements Response {

  private static final long serialVersionUID = 1L;

  @NotEmpty
  @JsonProperty("relationship")
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "Father/Son (Birth)")
  private String relationship;


  @NotEmpty
  @JsonProperty("last_name")
  @Size(min = 1, max = 25)
  @ApiModelProperty(required = true, readOnly = false, value = "", example = "Jones")
  private String lastName;

  @JsonProperty("first_name")
  @Size(min = 1, max = 20)
  @ApiModelProperty(required = true, readOnly = false, value = "", example = "Bob")
  private String firstName;

  /**
   * Constructor
   * 
   * @param relationship the relationship
   * @param lastName last name
   * @param firstName first name
   */
  public SimplePersonWithRelationship(@JsonProperty("relationship") String relationship,
      @JsonProperty("last_name") String lastName, @JsonProperty("first_name") String firstName) {
    this.relationship = relationship;
    this.lastName = lastName;
    this.firstName = firstName;

  }

  /**
   * @return the relationship
   */
  public String getRelationship() {
    return relationship;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
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
