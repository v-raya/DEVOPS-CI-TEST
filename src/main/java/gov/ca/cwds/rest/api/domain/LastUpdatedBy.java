package gov.ca.cwds.rest.api.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.ca.cwds.data.persistence.cms.StaffPerson;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.cms.LegacyTable;
import gov.ca.cwds.rest.api.domain.investigation.CmsRecordDescriptor;
import gov.ca.cwds.rest.util.CmsRecordUtils;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@link DomainObject} representing a last updated by person.
 * 
 * @author CWDS API Team
 */
public class LastUpdatedBy extends ReportingDomain implements Request, Response {

  /**
   * Default
   */
  private static final long serialVersionUID = 1L;

  @NotNull
  @JsonProperty("legacy_descriptor")
  private CmsRecordDescriptor legacyDescriptor;

  @JsonProperty("first_name")
  @Size(min = 1, max = 20)
  @ApiModelProperty(required = true, readOnly = false, value = "", example = "John")
  private String firstName;

  @JsonProperty("middle_initial")
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "Q")
  @Size(max = 1, message = "size must be 1")
  private String middleInitial;

  @JsonProperty("last_name")
  @Size(min = 1, max = 25)
  @ApiModelProperty(required = true, readOnly = false, value = "", example = "Smith")
  private String lastName;

  @JsonProperty("name_suffix")
  @Size(max = 4)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "SR.")
  private String suffixTitle;

  @JsonProperty("prefix_title")
  @Size(max = 6)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "MR.")
  private String prefixTitle;

  /**
   * Constructor
   * 
   * @param legacyDescriptor The CmsRecordDescriptor
   * @param firstName The first name
   * @param lastName The last name
   * @param middleInitial The middle name
   * @param suffixTitle The name suffix.
   * @param prefixTitle The prefix_title
   */
  @JsonCreator
  public LastUpdatedBy(@JsonProperty("legacy_descriptor") CmsRecordDescriptor legacyDescriptor,
      @JsonProperty("first_name") String firstName,
      @JsonProperty("middle_initial") String middleInitial,
      @JsonProperty("last_name") String lastName, @JsonProperty("suffix_title") String suffixTitle,
      @JsonProperty("prefix_title") String prefixTitle) {
    super();
    this.legacyDescriptor = legacyDescriptor;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.suffixTitle = suffixTitle;
    this.prefixTitle = prefixTitle;
  }


  public LastUpdatedBy(StaffPerson persistedStaffPerson) {
    super();
    this.legacyDescriptor = CmsRecordUtils
        .createLegacyDescriptor(persistedStaffPerson.getPrimaryKey(), LegacyTable.STAFF_PERSON);
    this.firstName = persistedStaffPerson.getFirstName();
    this.middleInitial = persistedStaffPerson.getMiddleInitial();
    this.lastName = persistedStaffPerson.getLastName();
    this.suffixTitle = persistedStaffPerson.getNameSuffix();
    this.prefixTitle = persistedStaffPerson.getNamePrefix();
  }


  public LastUpdatedBy() {
    // default
  }


  /**
   * @return the legacyDescriptor
   */
  public CmsRecordDescriptor getLegacyDescriptor() {
    return legacyDescriptor;
  }


  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }


  /**
   * @return the middleInitial
   */
  public String getMiddleInitial() {
    return middleInitial;
  }


  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }


  /**
   * @return the suffixTitle
   */
  public String getSuffixTitle() {
    return suffixTitle;
  }


  /**
   * @return the prefixTitle
   */
  public String getPrefixTitle() {
    return prefixTitle;
  }


  /**
   * {@inheritDoc}
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  /**
   * {@inheritDoc}
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, false);
  }

}
