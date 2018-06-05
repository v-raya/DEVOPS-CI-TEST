package gov.ca.cwds.fixture.investigation;

import gov.ca.cwds.rest.api.domain.investigation.CmsRecordDescriptor;
import gov.ca.cwds.rest.api.domain.investigation.InvestigationAddress;

@SuppressWarnings("javadoc")
public class InvestigationAddressEntityBuilder {
  protected CmsRecordDescriptor cmsRecordDescriptor;
  protected String streetAddress = "741 Evergreen Ct";
  protected String city = "Springfield";
  protected Short state = 1828;
  protected String zip = "95757";
  protected Short type = 32;

  public InvestigationAddress build() {
    cmsRecordDescriptor =
        new CmsRecordDescriptor("1234567ABC", "001-2000-3399-415790", "CLIENT_T", "Client");
    return new InvestigationAddress(cmsRecordDescriptor, streetAddress, city, state, zip, type);

  }

  public InvestigationAddressEntityBuilder setCmsRecordDescriptor(
      CmsRecordDescriptor cmsRecordDescriptor) {

    this.cmsRecordDescriptor = cmsRecordDescriptor;
    return this;
  }

  public InvestigationAddressEntityBuilder setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
    return this;
  }

  public InvestigationAddressEntityBuilder setCity(String city) {
    this.city = city;
    return this;
  }

  public InvestigationAddressEntityBuilder setState(Short state) {
    this.state = state;
    return this;
  }

  public InvestigationAddressEntityBuilder setZip(String zip) {
    this.zip = zip;
    return this;
  }

  public InvestigationAddressEntityBuilder setType(Short type) {
    this.type = type;
    return this;
  }

  public CmsRecordDescriptor getCmsRecordDescriptor() {
    return cmsRecordDescriptor;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public String getCity() {
    return city;
  }

  public Short getState() {
    return state;
  }

  public String getZip() {
    return zip;
  }

  public Short getType() {
    return type;
  }



}
