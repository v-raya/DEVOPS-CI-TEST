package gov.ca.cwds.fixture;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @author CWDS API Team
 *
 */
@SuppressWarnings("javadoc")
public class CmsAddressResourceBuilder {

  String existingAddressId = "ABC1234567";
  DateTime lastUpdatedTime = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
      .parseDateTime("2004-03-31T09:45:58.000-0800");
  String city = "Sacramento";
  Long emergencyNumber = 123456789L;
  Integer emergencyExtension = 1234;
  Boolean frgAdrtB = false;
  Short governmentEntityCd = (short) 99;
  Long messageNumber = 987654321L;
  Integer messageExtension = 6789;
  String headerAddress = "Senior Residence";
  Long primaryNumber = 897453127L;
  Integer primaryExtension = 21;
  Short state = (short) 99;
  String streetName = "2870";
  String streetNumber = "2751";
  Integer zip = 95833;
  String addressDescription = "Resident";
  Short zip4 = (short) 12;
  String postDirCd = "NE";
  String preDirCd = "NE";
  Short streetSuffixCd = (short) 99;
  Short unitDesignationCd = (short) 140;
  String unitNumber = "120";

  public gov.ca.cwds.rest.api.domain.cms.Address buildCmsAddress() {
    return new gov.ca.cwds.rest.api.domain.cms.Address(existingAddressId, lastUpdatedTime, city,
        emergencyNumber, emergencyExtension, frgAdrtB, governmentEntityCd, messageNumber,
        messageExtension, headerAddress, primaryNumber, primaryExtension, state, streetName,
        streetNumber, zip, addressDescription, zip4, postDirCd, preDirCd, streetSuffixCd,
        unitDesignationCd, unitNumber);
  }

  public String getExistingAddressId() {
    return existingAddressId;
  }

  public CmsAddressResourceBuilder setExistingAddressId(String existingAddressId) {
    this.existingAddressId = existingAddressId;
    return this;
  }

  public DateTime getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public CmsAddressResourceBuilder setLastUpdatedTime(DateTime lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
    return this;
  }

  public Boolean getFrgAdrtB() {
    return frgAdrtB;
  }

  public CmsAddressResourceBuilder setFrgAdrtB(Boolean frgAdrtB) {
    this.frgAdrtB = frgAdrtB;
    return this;
  }

  public Integer getZip() {
    return zip;
  }

  public CmsAddressResourceBuilder setZip(Integer zip) {
    this.zip = zip;
    return this;
  }

  public String getCity() {
    return city;
  }

  public CmsAddressResourceBuilder setCity(String city) {
    this.city = city;
    return this;
  }

  public Long getEmergencyNumber() {
    return emergencyNumber;
  }

  public CmsAddressResourceBuilder setEmergencyNumber(Long emergencyNumber) {
    this.emergencyNumber = emergencyNumber;
    return this;
  }

  public Integer getEmergencyExtension() {
    return emergencyExtension;
  }

  public CmsAddressResourceBuilder setEmergencyExtension(Integer emergencyExtension) {
    this.emergencyExtension = emergencyExtension;
    return this;
  }

  public Short getGovernmentEntityCd() {
    return governmentEntityCd;
  }

  public CmsAddressResourceBuilder setGovernmentEntityCd(Short governmentEntityCd) {
    this.governmentEntityCd = governmentEntityCd;
    return this;
  }

  public Long getMessageNumber() {
    return messageNumber;
  }

  public CmsAddressResourceBuilder setMessageNumber(Long messageNumber) {
    this.messageNumber = messageNumber;
    return this;
  }

  public Integer getMessageExtension() {
    return messageExtension;
  }

  public CmsAddressResourceBuilder setMessageExtension(Integer messageExtension) {
    this.messageExtension = messageExtension;
    return this;
  }

  public String getHeaderAddress() {
    return headerAddress;
  }

  public CmsAddressResourceBuilder setHeaderAddress(String headerAddress) {
    this.headerAddress = headerAddress;
    return this;
  }

  public Long getPrimaryNumber() {
    return primaryNumber;
  }

  public CmsAddressResourceBuilder setPrimaryNumber(Long primaryNumber) {
    this.primaryNumber = primaryNumber;
    return this;
  }

  public Integer getPrimaryExtension() {
    return primaryExtension;
  }

  public CmsAddressResourceBuilder setPrimaryExtension(Integer primaryExtension) {
    this.primaryExtension = primaryExtension;
    return this;
  }

  public Short getState() {
    return state;
  }

  public CmsAddressResourceBuilder setState(Short state) {
    this.state = state;
    return this;
  }

  public String getStreetName() {
    return streetName;
  }

  public CmsAddressResourceBuilder setStreetName(String streetName) {
    this.streetName = streetName;
    return this;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public CmsAddressResourceBuilder setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

  public String getAddressDescription() {
    return addressDescription;
  }

  public CmsAddressResourceBuilder setAddressDescription(String addressDescription) {
    this.addressDescription = addressDescription;
    return this;
  }

  public Short getZip4() {
    return zip4;
  }

  public CmsAddressResourceBuilder setZip4(Short zip4) {
    this.zip4 = zip4;
    return this;
  }

  public String getPostDirCd() {
    return postDirCd;
  }

  public CmsAddressResourceBuilder setPostDirCd(String postDirCd) {
    this.postDirCd = postDirCd;
    return this;
  }

  public String getPreDirCd() {
    return preDirCd;
  }

  public CmsAddressResourceBuilder setPreDirCd(String preDirCd) {
    this.preDirCd = preDirCd;
    return this;
  }

  public Short getStreetSuffixCd() {
    return streetSuffixCd;
  }

  public CmsAddressResourceBuilder setStreetSuffixCd(Short streetSuffixCd) {
    this.streetSuffixCd = streetSuffixCd;
    return this;
  }

  public Short getUnitDesignationCd() {
    return unitDesignationCd;
  }

  public CmsAddressResourceBuilder setUnitDesignationCd(Short unitDesignationCd) {
    this.unitDesignationCd = unitDesignationCd;
    return this;
  }

  public String getUnitNumber() {
    return unitNumber;
  }

  public CmsAddressResourceBuilder setUnitNumber(String unitNumber) {
    this.unitNumber = unitNumber;
    return this;
  }

}
