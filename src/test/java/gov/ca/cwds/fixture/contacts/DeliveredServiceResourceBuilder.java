package gov.ca.cwds.fixture.contacts;

import gov.ca.cwds.rest.api.contact.DeliveredServiceDomain;

/**
 * @author CWDS API Team
 *
 */
public class DeliveredServiceResourceBuilder {
  private String id = "ABC1234567";
  private Short cftLeadAgencyType = (short) 4212;
  private Boolean coreServiceIndicator = false;
  private Integer communicationMethodType = 409;
  private Integer contactLocationType = 415;
  private String contactVisitCode = "C";
  private String countySpecificCode = "99";
  private String detailText = "ABC1234567";
  private String detailTextContinuation = "ABC12345t7";
  private String endDate = "2000-01-01";
  private String endTime = "16:41:49";
  private String primaryDeliveredServiceId = "ABC1at0875";
  private String hardCopyDocumentOnFileCode = "N";
  private String otherParticipantsDesc = "description of the world";
  private String providedByCode = "S";
  private String providedById = "ABC097r534";
  private Integer serviceContactType = 408;
  private String startDate = "1992-01-01";
  private String startTime = "16:41:49";
  private String statusCode = "C";
  private String supervisionCode = "C";
  private Boolean wraparoundServiceIndicator = false;

  public DeliveredServiceDomain buildDeliveredServiceResource() {
    return new DeliveredServiceDomain(id, cftLeadAgencyType, coreServiceIndicator,
        communicationMethodType, contactLocationType, contactVisitCode, countySpecificCode,
        detailText, detailTextContinuation, endDate, endTime, primaryDeliveredServiceId,
        hardCopyDocumentOnFileCode, otherParticipantsDesc, providedByCode, providedById,
        serviceContactType, startDate, startTime, statusCode, supervisionCode,
        wraparoundServiceIndicator);
  }

  public String getId() {
    return id;
  }

  public DeliveredServiceResourceBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public Short getCftLeadAgencyType() {
    return cftLeadAgencyType;
  }

  public DeliveredServiceResourceBuilder setCftLeadAgencyType(Short cftLeadAgencyType) {
    this.cftLeadAgencyType = cftLeadAgencyType;
    return this;
  }

  public Boolean getCoreServiceIndicator() {
    return coreServiceIndicator;
  }

  public DeliveredServiceResourceBuilder setCoreServiceIndicator(Boolean coreServiceIndicator) {
    this.coreServiceIndicator = coreServiceIndicator;
    return this;
  }

  public Integer getCommunicationMethodType() {
    return communicationMethodType;
  }

  public DeliveredServiceResourceBuilder setCommunicationMethodType(
      Integer communicationMethodType) {
    this.communicationMethodType = communicationMethodType;
    return this;
  }

  public Integer getContactLocationType() {
    return contactLocationType;
  }

  public DeliveredServiceResourceBuilder setContactLocationType(Integer contactLocationType) {
    this.contactLocationType = contactLocationType;
    return this;
  }

  public String getContactVisitCode() {
    return contactVisitCode;
  }

  public DeliveredServiceResourceBuilder setContactVisitCode(String contactVisitCode) {
    this.contactVisitCode = contactVisitCode;
    return this;
  }

  public String getCountySpecificCode() {
    return countySpecificCode;
  }

  public DeliveredServiceResourceBuilder setCountySpecificCode(String countySpecificCode) {
    this.countySpecificCode = countySpecificCode;
    return this;
  }

  public String getDetailText() {
    return detailText;
  }

  public DeliveredServiceResourceBuilder setDetailText(String detailText) {
    this.detailText = detailText;
    return this;
  }

  public String getDetailTextContinuation() {
    return detailTextContinuation;
  }

  public DeliveredServiceResourceBuilder setDetailTextContinuation(String detailTextContinuation) {
    this.detailTextContinuation = detailTextContinuation;
    return this;
  }

  public String getEndDate() {
    return endDate;
  }

  public DeliveredServiceResourceBuilder setEndDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  public String getEndTime() {
    return endTime;
  }

  public DeliveredServiceResourceBuilder setEndTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  public String getPrimaryDeliveredServiceId() {
    return primaryDeliveredServiceId;
  }

  public DeliveredServiceResourceBuilder setPrimaryDeliveredServiceId(
      String primaryDeliveredServiceId) {
    this.primaryDeliveredServiceId = primaryDeliveredServiceId;
    return this;
  }

  public String getHardCopyDocumentOnFileCode() {
    return hardCopyDocumentOnFileCode;
  }

  public DeliveredServiceResourceBuilder setHardCopyDocumentOnFileCode(
      String hardCopyDocumentOnFileCode) {
    this.hardCopyDocumentOnFileCode = hardCopyDocumentOnFileCode;
    return this;
  }

  public String getOtherParticipantsDesc() {
    return otherParticipantsDesc;
  }

  public DeliveredServiceResourceBuilder setOtherParticipantsDesc(String otherParticipantsDesc) {
    this.otherParticipantsDesc = otherParticipantsDesc;
    return this;
  }

  public String getProvidedByCode() {
    return providedByCode;
  }

  public DeliveredServiceResourceBuilder setProvidedByCode(String providedByCode) {
    this.providedByCode = providedByCode;
    return this;
  }

  public String getProvidedById() {
    return providedById;
  }

  public DeliveredServiceResourceBuilder setProvidedById(String providedById) {
    this.providedById = providedById;
    return this;
  }

  public Integer getServiceContactType() {
    return serviceContactType;
  }

  public DeliveredServiceResourceBuilder setServiceContactType(Integer serviceContactType) {
    this.serviceContactType = serviceContactType;
    return this;
  }

  public String getStartDate() {
    return startDate;
  }

  public DeliveredServiceResourceBuilder setStartDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  public String getStartTime() {
    return startTime;
  }

  public DeliveredServiceResourceBuilder setStartTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public DeliveredServiceResourceBuilder setStatusCode(String statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public String getSupervisionCode() {
    return supervisionCode;
  }

  public DeliveredServiceResourceBuilder setSupervisionCode(String supervisionCode) {
    this.supervisionCode = supervisionCode;
    return this;
  }

  public Boolean getWraparoundServiceIndicator() {
    return wraparoundServiceIndicator;
  }

  public DeliveredServiceResourceBuilder setWraparoundServiceIndicator(
      Boolean wraparoundServiceIndicator) {
    this.wraparoundServiceIndicator = wraparoundServiceIndicator;
    return this;
  }

}
