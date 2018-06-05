package gov.ca.cwds.rest.api.contact;

import static gov.ca.cwds.data.persistence.cms.CmsPersistentObject.CMS_ID_LEN;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import gov.ca.cwds.data.persistence.contact.DeliveredServiceEntity;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.DomainChef;
import gov.ca.cwds.rest.api.domain.DomainObject;
import gov.ca.cwds.rest.api.domain.ReportingDomain;
import gov.ca.cwds.rest.api.domain.SystemCodeCategoryId;
import gov.ca.cwds.rest.validation.ValidSystemCodeId;
import io.dropwizard.jackson.JsonSnakeCase;
import io.dropwizard.validation.OneOf;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@link DomainObject} representing an DeliveredSevice
 * 
 * @author CWDS API Team
 */
@JsonSnakeCase
@JsonPropertyOrder({"id", "cftLeadAgencyType", "coreServiceIndicator", "communicationMethodType",
    "contactLocationType", "contactVisitCode", "countySpecificCode", "detailText",
    "detailTextContinuation", "endDate", "endTime", "primaryDeliveredServiceId",
    "hardCopyDocumentOnFileCode", "otherParticipantsDesc", "providedByCode", "providedById",
    "serviceContactType", "startDate", "startTime", "statusCode", "supervisionCode",
    "wraparoundServiceIndicator"})
public class DeliveredServiceDomain extends ReportingDomain implements Request, Response {

  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_HARD_COPY_DOUMENT_ON_FILE_CODE = "N";
  private static final String DEFAULT_CONTACT_VISIT_CODE = "C";

  @Size(max = CMS_ID_LEN)
  @NotNull
  @ApiModelProperty(required = true, readOnly = true, value = "", example = "ABC1234567")
  private String id;

  @JsonProperty("child_family_team_lead_agency")
  @ApiModelProperty(required = false, readOnly = false, example = "4212")
  private Short cftLeadAgencyType;

  @JsonProperty("core_service")
  @ApiModelProperty(required = false, readOnly = false)
  private Boolean coreServiceIndicator;

  @JsonProperty("communication_method")
  @ValidSystemCodeId(required = false, category = SystemCodeCategoryId.COMMUNICATION_METHOD)
  @ApiModelProperty(required = false, readOnly = false,
      value = "Delivered service communication method type system code ID e.g) 408 -> In-Person",
      example = "408")
  private Integer communicationMethodType;

  @JsonProperty("contact_location")
  @ValidSystemCodeId(required = false, category = SystemCodeCategoryId.CONTACT_LOCATION)
  @ApiModelProperty(required = false, readOnly = false,
      value = "Delivered service contact location type system code ID e.g) 415 -> CWS Office",
      example = "415")
  private Integer contactLocationType;

  @NotEmpty
  @JsonProperty("contact_visit")
  @Size(max = 1)
  @OneOf(value = {"C", "N", "V"}, ignoreCase = false, ignoreWhitespace = true)
  @ApiModelProperty(required = true, readOnly = false, value = "", example = "C")
  private String contactVisitCode;

  @NotEmpty
  @JsonProperty("county")
  @Size(min = 2, max = 2)
  @ApiModelProperty(required = true, readOnly = false, value = "", example = "99")
  private String countySpecificCode;

  @JsonProperty("detail_text")
  @Size(max = 32700)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "detail1")
  private String detailText;

  @JsonProperty("detail_text_continuation")
  @Size(max = 32700)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "continued2")
  private String detailTextContinuation;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @JsonProperty("end_date")
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = true)
  @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd",
      example = "2000-01-01")
  private String endDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT)
  @JsonProperty("end_time")
  @gov.ca.cwds.rest.validation.Date(format = TIME_FORMAT, required = false)
  @ApiModelProperty(required = false, readOnly = false, value = "Assignment end time",
      example = "16:41:49")
  private String endTime;

  @JsonProperty("primary_delivered_service_id")
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "ABC1234567")
  private String primaryDeliveredServiceId;

  @NotEmpty
  @JsonProperty("document_on_file")
  @OneOf(value = {"N", "U", "Y"}, ignoreCase = false, ignoreWhitespace = true)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "N")
  private String hardCopyDocumentOnFileCode;

  @JsonProperty("other_participants")
  @Size(max = 100)
  @ApiModelProperty(required = false, readOnly = false, value = "",
      example = "Attorney, Doctor and etc")
  private String otherParticipantsDesc;

  @JsonProperty("provided_by")
  @Size(min = 1, max = 1)
  @OneOf(value = {"S", "O", "V", "X"}, ignoreCase = false, ignoreWhitespace = true)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "S")
  private String providedByCode;

  @JsonProperty("provided_by_id")
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "ABC1234567")
  private String providedById;

  @JsonProperty("service_contact")
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "0")
  private Integer serviceContactType;

  @NotEmpty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @JsonProperty("start_date")
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = true)
  @ApiModelProperty(required = true, readOnly = false, value = "yyyy-MM-dd", example = "2000-01-01")
  private String startDate;

  @NotEmpty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT)
  @JsonProperty("start_time")
  @gov.ca.cwds.rest.validation.Date(format = TIME_FORMAT, required = false)
  @ApiModelProperty(required = true, readOnly = false, value = "Contact end time",
      example = "16:41:49")
  private String startTime;

  @NotEmpty
  @JsonProperty("status")
  @Size(min = 1, max = 1)
  @OneOf(value = {"C", "A", "S"}, ignoreCase = false, ignoreWhitespace = true)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "C")
  private String statusCode;

  @JsonProperty("supervision")
  @Size(min = 1, max = 1)
  @OneOf(value = {"C", "S", "O", "N"}, ignoreCase = false, ignoreWhitespace = true)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "C")
  private String supervisionCode;

  @NotNull
  @JsonProperty("wrap_around_service")
  private Boolean wraparoundServiceIndicator;

  /**
   * @param id - id
   * @param cftLeadAgencyType - cftLeadAgencyType
   * @param coreServiceIndicator - coreServiceIndicator
   * @param communicationMethodType - communicationMethodType
   * @param contactLocationType - contactLocationType
   * @param contactVisitCode - contactVisitCode
   * @param countySpecificCode - countySpecificCode
   * @param detailText - detailText
   * @param detailTextContinuation - detailTextContinuation
   * @param endDate - endDate
   * @param endTime - endTime
   * @param primaryDeliveredServiceId - primaryDeliveredServiceId
   * @param hardCopyDocumentOnFileCode - hardCopyDocumentOnFileCode
   * @param otherParticipantsDesc - otherParticipantsDesc
   * @param providedByCode - providedByCode
   * @param providedById - providedById
   * @param serviceContactType - serviceContactType
   * @param startDate - startDate
   * @param startTime - startTime
   * @param statusCode - statusCode
   * @param supervisionCode - supervisionCode
   * @param wraparoundServiceIndicator - wraparoundServiceIndicator
   */
  @JsonCreator
  public DeliveredServiceDomain(@JsonProperty("id") String id,
      @JsonProperty("child_family_team_lead_agency") Short cftLeadAgencyType,
      @JsonProperty("core_service") Boolean coreServiceIndicator,
      @JsonProperty("communication_type") Integer communicationMethodType,
      @JsonProperty("contact_location") Integer contactLocationType,
      @JsonProperty("contact_visit") String contactVisitCode,
      @JsonProperty("county") String countySpecificCode,
      @JsonProperty("detail_text") String detailText,
      @JsonProperty("detail_text_continuation") String detailTextContinuation,
      @JsonProperty("end_date") String endDate, @JsonProperty("end_time") String endTime,
      @JsonProperty("primary_delivered_service_id") String primaryDeliveredServiceId,
      @JsonProperty("document_on_file") String hardCopyDocumentOnFileCode,
      @JsonProperty("other_participant") String otherParticipantsDesc,
      @JsonProperty("provied_by") String providedByCode,
      @JsonProperty("provided_by_id") String providedById,
      @JsonProperty("service_contact") Integer serviceContactType,
      @JsonProperty("start_date") String startDate, @JsonProperty("start_time") String startTime,
      @JsonProperty("status") String statusCode,
      @JsonProperty("supervision") String supervisionCode,
      @JsonProperty("wrap_around_service") Boolean wraparoundServiceIndicator) {
    super();
    this.id = id;
    this.cftLeadAgencyType = cftLeadAgencyType;
    this.coreServiceIndicator = coreServiceIndicator;
    this.communicationMethodType = communicationMethodType;
    this.contactLocationType = contactLocationType;
    this.contactVisitCode = contactVisitCode;
    this.countySpecificCode = countySpecificCode;
    this.detailText = detailText;
    this.detailTextContinuation = detailTextContinuation;
    this.endDate = endDate;
    this.endTime = endTime;
    this.primaryDeliveredServiceId = primaryDeliveredServiceId;
    this.hardCopyDocumentOnFileCode = hardCopyDocumentOnFileCode;
    this.otherParticipantsDesc = otherParticipantsDesc;
    this.providedByCode = providedByCode;
    this.providedById = providedById;
    this.serviceContactType = serviceContactType;
    this.startDate = startDate;
    this.startTime = startTime;
    this.statusCode = statusCode;
    this.supervisionCode = supervisionCode;
    this.wraparoundServiceIndicator = wraparoundServiceIndicator;
  }

  /**
   * Construct from persistence layer CMS Delivered Service
   * 
   * @param persistedDeliverdService persisted delivered service
   */
  public DeliveredServiceDomain(
      gov.ca.cwds.data.persistence.contact.DeliveredServiceEntity persistedDeliverdService) {
    this.id = persistedDeliverdService.getId();
    this.cftLeadAgencyType = persistedDeliverdService.getCftLeadAgencyType();
    this.coreServiceIndicator =
        DomainChef.uncookBooleanString(persistedDeliverdService.getCoreServiceIndicator());
    this.communicationMethodType =
        Integer.valueOf(persistedDeliverdService.getCommunicationMethodType());
    this.contactLocationType = Integer.valueOf(persistedDeliverdService.getContactLocationType());
    this.contactVisitCode = persistedDeliverdService.getContactVisitCode();
    this.countySpecificCode = persistedDeliverdService.getCountySpecificCode();
    this.detailText = persistedDeliverdService.getDetailText();
    this.detailTextContinuation = persistedDeliverdService.getDetailTextContinuation();
    this.endDate = DomainChef.cookDate(persistedDeliverdService.getEndDate());
    this.endTime = DomainChef.cookTime(persistedDeliverdService.getEndTime());
    this.primaryDeliveredServiceId = persistedDeliverdService.getPrimaryDeliveredServiceId();
    this.hardCopyDocumentOnFileCode = persistedDeliverdService.getHardCopyDocumentOnFileCode();
    this.otherParticipantsDesc = persistedDeliverdService.getOtherParticipantsDesc();
    this.providedByCode = persistedDeliverdService.getProvidedByCode();
    this.providedById = persistedDeliverdService.getProvidedById();
    this.serviceContactType = Integer.valueOf(persistedDeliverdService.getServiceContactType());
    this.startDate = DomainChef.cookDate(persistedDeliverdService.getStartDate());
    this.startTime = DomainChef.cookTime(persistedDeliverdService.getStartTime());
    this.statusCode = persistedDeliverdService.getStatusCode();
    this.supervisionCode = persistedDeliverdService.getSupervisionCode();
    this.wraparoundServiceIndicator =
        DomainChef.uncookBooleanString(persistedDeliverdService.getWraparoundServiceIndicator());
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @return the cftLeadAgencyType
   */
  public Short getCftLeadAgencyType() {
    return cftLeadAgencyType;
  }

  /**
   * @return the coreServiceIndicator
   */
  public Boolean getCoreServiceIndicator() {
    return coreServiceIndicator;
  }

  /**
   * @return the communicationMethodType
   */
  public Integer getCommunicationMethodType() {
    return communicationMethodType;
  }

  /**
   * @return the contactLocationType
   */
  public Integer getContactLocationType() {
    return contactLocationType;
  }

  /**
   * @return the contactVisitCode
   */
  public String getContactVisitCode() {
    return contactVisitCode;
  }

  /**
   * @return the countySpecificCode
   */
  public String getCountySpecificCode() {
    return countySpecificCode;
  }

  /**
   * @return the detailText
   */
  public String getDetailText() {
    return detailText;
  }

  /**
   * @return the detailTextContinuation
   */
  public String getDetailTextContinuation() {
    return detailTextContinuation;
  }

  /**
   * @return the endDate
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * @return the endTime
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * @return the primaryDeliveredServiceId
   */
  public String getPrimaryDeliveredServiceId() {
    return primaryDeliveredServiceId;
  }

  /**
   * @return the hardCopyDocumentOnFileCode
   */
  public String getHardCopyDocumentOnFileCode() {
    return hardCopyDocumentOnFileCode;
  }

  /**
   * @return the otherParticipantsDesc
   */
  public String getOtherParticipantsDesc() {
    return otherParticipantsDesc;
  }

  /**
   * @return the providedByCode
   */
  public String getProvidedByCode() {
    return providedByCode;
  }

  /**
   * @return the providedById
   */
  public String getProvidedById() {
    return providedById;
  }

  /**
   * @return the serviceContactType
   */
  public Integer getServiceContactType() {
    return serviceContactType;
  }

  /**
   * @return the startDate
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * @return the startTime
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * @return the statusCode
   */
  public String getStatusCode() {
    return statusCode;
  }

  /**
   * @return the supervisionCode
   */
  public String getSupervisionCode() {
    return supervisionCode;
  }

  /**
   * @return the wrapAroundIndicator
   */
  public Boolean getWraparoundServiceIndicator() {
    return wraparoundServiceIndicator;
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

  /**
   * Delivered Service Domain Object created with some default values to match UI Specificaton
   * 
   * @param communicationMethod the communication method code
   * @param location the contact location code
   * @param countySpecificCode the county
   * @param longTextId the long text id to detail text
   * @param longTextContinuationId the long text id to detail text continuation
   * @param endDate the end date
   * @param endTime the end time
   * @param serviceContactType the service contact type
   * @param startDate the start date
   * @param startTime the start time
   * @param statusCode the status code
   * @return the Delivered Service Domain created with default values
   */
  public static DeliveredServiceDomain createWithDefaultsForFieldsNotPopulatedByUI(
      Integer communicationMethod, Integer location, String countySpecificCode, String longTextId,
      String longTextContinuationId, String endDate, String endTime, Integer serviceContactType,
      String startDate, String startTime, String statusCode) {

    return new DeliveredServiceDomain("id", null, Boolean.FALSE, communicationMethod, location,
        DEFAULT_CONTACT_VISIT_CODE, countySpecificCode, longTextId, longTextContinuationId, endDate,
        endTime, "", DEFAULT_HARD_COPY_DOUMENT_ON_FILE_CODE, "", "", "", serviceContactType,
        startDate, startTime, statusCode, "", Boolean.FALSE);
  }

  /**
   * Delivered Service Domain Object updated with some values provided by UI, and remaining fields
   * populated from the persisted object. This is because the UI does not expose all the legacy
   * fields to the user.
   * 
   * @param deliveredServiceEntity the persisted delivered service
   * @param communicationMethod the communication method code
   * @param location the contact location code
   * @param countySpecificCode the county
   * @param longTextId the long text id to detail text
   * @param longTextContinuationId the long text id to detail text continuation
   * @param endDate the end date
   * @param endTime the end time
   * @param serviceContactType the service contact type
   * @param startDate the start date
   * @param startTime the start time
   * @param statusCode the status code
   * @return the Delivered Service Domain created with default values
   */
  public static DeliveredServiceDomain updateWithDeliveredServiceEntityValuesForFieldsNotPopulatedByUI(
      DeliveredServiceEntity deliveredServiceEntity, Integer communicationMethod, Integer location,
      String countySpecificCode, String longTextId, String longTextContinuationId, String endDate,
      String endTime, Integer serviceContactType, String startDate, String startTime,
      String statusCode) {

    return new DeliveredServiceDomain(deliveredServiceEntity.getId(), null,
        DomainChef.uncookBooleanString(deliveredServiceEntity.getCoreServiceIndicator()),
        communicationMethod, location, deliveredServiceEntity.getContactVisitCode(),
        countySpecificCode, longTextId, longTextContinuationId, endDate, endTime,
        deliveredServiceEntity.getPrimaryDeliveredServiceId(),
        deliveredServiceEntity.getHardCopyDocumentOnFileCode(),
        deliveredServiceEntity.getOtherParticipantsDesc(),
        deliveredServiceEntity.getProvidedByCode(), deliveredServiceEntity.getProvidedById(),
        serviceContactType, startDate, startTime, statusCode, "",
        DomainChef.uncookBooleanString(deliveredServiceEntity.getWraparoundServiceIndicator()));
  }

}
