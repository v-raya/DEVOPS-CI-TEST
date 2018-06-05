package gov.ca.cwds.fixture;

import gov.ca.cwds.rest.api.domain.cms.CrossReport;

/**
 * @author CWDS API Team
 *
 */
@SuppressWarnings("javadoc")
public class CmsCrossReportResourceBuilder {
  String thirdId = "";
  Short crossReportMethodType = 2095;
  Boolean filedOutOfStateIndicator = false;
  Boolean governmentOrgCrossRptIndicatorVar = false;
  String informTime = "";
  String recipientBadgeNumber = "";
  Integer recipientPhoneExtensionNumber = 0;
  Long recipientPhoneNumber = 0L;
  String informDate = "2017-10-01";
  String recipientPositionTitleDesc = "";
  String referenceNumber = "";
  String referralId = "1234567ABC";
  String lawEnforcementId = null;
  String staffPersonId = "0X5";
  String description = "";
  String recipientName = "";
  String outStateLawEnforcementAddr = "";
  String countySpecificCode = "20";
  Boolean lawEnforcementIndicator = false;
  Boolean outStateLawEnforcementIndicator = false;
  Boolean satisfyCrossReportIndicator = false;

  public CmsCrossReportResourceBuilder setThirdId(String thirdId) {
    this.thirdId = thirdId;
    return this;
  }

  public CmsCrossReportResourceBuilder setCrossReportMethodType(Short crossReportMethodType) {
    this.crossReportMethodType = crossReportMethodType;
    return this;
  }

  public CmsCrossReportResourceBuilder setFiledOutOfStateIndicator(
      Boolean filedOutOfStateIndicator) {
    this.filedOutOfStateIndicator = filedOutOfStateIndicator;
    return this;
  }

  public CmsCrossReportResourceBuilder setGovernmentOrgCrossRptIndicatorVar(
      Boolean governmentOrgCrossRptIndicatorVar) {
    this.governmentOrgCrossRptIndicatorVar = governmentOrgCrossRptIndicatorVar;
    return this;
  }

  public CmsCrossReportResourceBuilder setInformTime(String informTime) {
    this.informTime = informTime;
    return this;
  }

  public CmsCrossReportResourceBuilder setRecipientBadgeNumber(String recipientBadgeNumber) {
    this.recipientBadgeNumber = recipientBadgeNumber;
    return this;
  }

  public CmsCrossReportResourceBuilder setRecipientPhoneExtensionNumber(
      Integer recipientPhoneExtensionNumber) {
    this.recipientPhoneExtensionNumber = recipientPhoneExtensionNumber;
    return this;
  }

  public CmsCrossReportResourceBuilder setRecipientPhoneNumber(Long recipientPhoneNumber) {
    this.recipientPhoneNumber = recipientPhoneNumber;
    return this;
  }

  public CmsCrossReportResourceBuilder setInformDate(String informDate) {
    this.informDate = informDate;
    return this;
  }

  public CmsCrossReportResourceBuilder setRecipientPositionTitleDesc(
      String recipientPositionTitleDesc) {
    this.recipientPositionTitleDesc = recipientPositionTitleDesc;
    return this;
  }

  public CmsCrossReportResourceBuilder setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
    return this;
  }

  public CmsCrossReportResourceBuilder setReferralId(String referralId) {
    this.referralId = referralId;
    return this;
  }

  public CmsCrossReportResourceBuilder setLawEnforcementId(String lawEnforcementId) {
    this.lawEnforcementId = lawEnforcementId;
    return this;
  }

  public CmsCrossReportResourceBuilder setStaffPersonId(String staffPersonId) {
    this.staffPersonId = staffPersonId;
    return this;
  }

  public CmsCrossReportResourceBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  public CmsCrossReportResourceBuilder setRecipientName(String recipientName) {
    this.recipientName = recipientName;
    return this;
  }

  public CmsCrossReportResourceBuilder setOutStateLawEnforcementAddr(
      String outStateLawEnforcementAddr) {
    this.outStateLawEnforcementAddr = outStateLawEnforcementAddr;
    return this;
  }

  public CmsCrossReportResourceBuilder setCountySpecificCode(String countySpecificCode) {
    this.countySpecificCode = countySpecificCode;
    return this;
  }

  public CmsCrossReportResourceBuilder setLawEnforcementIndicator(Boolean lawEnforcementIndicator) {
    this.lawEnforcementIndicator = lawEnforcementIndicator;
    return this;
  }

  public CmsCrossReportResourceBuilder setOutStateLawEnforcementIndicator(
      Boolean outStateLawEnforcementIndicator) {
    this.outStateLawEnforcementIndicator = outStateLawEnforcementIndicator;
    return this;
  }

  public CmsCrossReportResourceBuilder setSatisfyCrossReportIndicator(
      Boolean satisfyCrossReportIndicator) {
    this.satisfyCrossReportIndicator = satisfyCrossReportIndicator;
    return this;
  }

  public CrossReport build() {
    return new CrossReport(thirdId, crossReportMethodType, filedOutOfStateIndicator,
        governmentOrgCrossRptIndicatorVar, informTime, recipientBadgeNumber,
        recipientPhoneExtensionNumber, recipientPhoneNumber, informDate, recipientPositionTitleDesc,
        referenceNumber, referralId, lawEnforcementId, staffPersonId, description, recipientName,
        outStateLawEnforcementAddr, countySpecificCode, lawEnforcementIndicator,
        outStateLawEnforcementIndicator, satisfyCrossReportIndicator);
  }

}
