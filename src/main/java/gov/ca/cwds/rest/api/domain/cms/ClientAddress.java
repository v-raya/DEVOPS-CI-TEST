package gov.ca.cwds.rest.api.domain.cms;

import static gov.ca.cwds.data.persistence.cms.CmsPersistentObject.CMS_ID_LEN;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.DomainChef;
import gov.ca.cwds.rest.api.domain.DomainObject;
import gov.ca.cwds.rest.api.domain.ReportingDomain;
import gov.ca.cwds.rest.api.domain.SystemCodeCategoryId;
import gov.ca.cwds.rest.validation.ValidSystemCodeId;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@link DomainObject} representing an address
 * 
 * @author CWDS API Team
 */
public class ClientAddress extends ReportingDomain implements Request, Response {

  private static final long serialVersionUID = 1L;

  @Size(max = CMS_ID_LEN)
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "ABC1234567")
  private String existingClientAddressId;

  @NotNull
  @ApiModelProperty(required = true, readOnly = false, example = "32")
  @ValidSystemCodeId(required = true, category = SystemCodeCategoryId.ADDRESS_TYPE)
  private Short addressType;

  @NotNull
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "123456ABC")
  @Size(max = CMS_ID_LEN)
  private String bookingOrInmateId;

  @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd",
      example = "2016-01-01")
  private String effectiveEndDate;

  @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd",
      example = "2016-01-01")
  private String effectiveStartDate;

  @NotEmpty
  @Size(max = CMS_ID_LEN)
  @ApiModelProperty(required = true, readOnly = false, example = "1234567ABC")
  private String addressId;

  @NotEmpty
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(required = true, readOnly = false, example = "1234567ABC")
  private String clientId;

  @NotNull
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "1234567ABC")
  @Size(max = CMS_ID_LEN)
  private String referralId;

  @NotNull
  @ApiModelProperty(required = false, readOnly = false, value = "", example = "1234567ABC")
  @Size(max = 10)
  private String homelessInd;

  /**
   * default constructor
   */
  public ClientAddress() {
    // Default, no-op.
  }

  /**
   * @param addressType The addressType
   * @param bkInmtId The bkInmtId
   * @param effEndDt The effEndDt
   * @param effStartDt The effStartDt
   * @param fkAddress The fkAddress
   * @param fkClient The fkClient
   * @param homelessInd The homelessInd
   * @param fkReferral THe fkReferral
   */
  public ClientAddress(Short addressType, String bkInmtId, String effEndDt, String effStartDt,
      String fkAddress, String fkClient, String homelessInd, String fkReferral) {
    super();
    this.addressType = addressType;
    this.bookingOrInmateId = bkInmtId;
    this.effectiveEndDate = effEndDt;
    this.effectiveStartDate = effStartDt;
    this.addressId = fkAddress;
    this.clientId = fkClient;
    this.homelessInd = homelessInd;
    this.referralId = fkReferral;

  }

  /**
   * @param persistedClientAddress - persisted ClientAddress object
   * @param isExist - does the ClientAddress id exist
   */
  public ClientAddress(gov.ca.cwds.data.persistence.cms.ClientAddress persistedClientAddress,
      boolean isExist) {
    this.existingClientAddressId = isExist ? persistedClientAddress.getId() : "";
    this.addressType = persistedClientAddress.getAddressType();
    this.bookingOrInmateId = persistedClientAddress.getBkInmtId();
    this.effectiveEndDate = DomainChef.cookDate(persistedClientAddress.getEffEndDt());
    this.effectiveStartDate = DomainChef.cookDate(persistedClientAddress.getEffStartDt());
    this.addressId = persistedClientAddress.getFkAddress();
    this.clientId = persistedClientAddress.getFkClient();
    this.homelessInd = persistedClientAddress.getHomelessInd();
    this.referralId = persistedClientAddress.getFkReferral();

  }

  /**
   * @return the clientAddressId
   */
  public String getClientAddressId() {
    return existingClientAddressId;
  }

  /**
   * @return the addressType
   */
  public Short getAddressType() {
    return addressType;
  }

  /**
   * @return the bkInmtId
   */
  public String getBookingOrInmateId() {
    return bookingOrInmateId;
  }

  /**
   * @return the effEndDt
   */
  public String getEffectiveEndDate() {
    return effectiveEndDate;
  }

  /**
   * @return the effStartDt
   */
  public String getEffectiveStartDate() {
    return effectiveStartDate;
  }

  /**
   * @return the addressId
   */
  public String getAddressId() {
    return addressId;
  }

  /**
   * @return the clientId
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * @return the homelessInd
   */
  public String getHomelessInd() {
    return homelessInd;
  }

  /**
   * @return the referralId
   */
  public String getReferralId() {
    return referralId;
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
