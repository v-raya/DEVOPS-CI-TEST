package gov.ca.cwds.data.persistence.cms;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.ca.cwds.data.persistence.junit.template.PersistentTestTemplate;
import gov.ca.cwds.fixture.ReporterResourceBuilder;
import gov.ca.cwds.rest.api.domain.DomainChef;

/**
 * @author CWDS API Team
 */
@SuppressWarnings("javadoc")
public class ReporterTest implements PersistentTestTemplate {
  private static final ObjectMapper MAPPER = SystemCodeTestHarness.MAPPER;
  private String lastUpdatedId = "0XA";
  private Date lastUpdatedTime = new Date();

  /*
   * Constructor test
   */
  @Override
  @Test
  public void testEmptyConstructor() throws Exception {
    assertThat(Reporter.class.newInstance(), is(notNullValue()));
  }

  @Override
  @Test
  public void testConstructorUsingDomain() throws Exception {
    gov.ca.cwds.rest.api.domain.cms.Reporter domain = new ReporterResourceBuilder().build();
    Reporter pers = new Reporter(domain, lastUpdatedId, lastUpdatedTime);

    assertThat(pers.getReferralId(), is(equalTo(domain.getReferralId())));
    assertThat(pers.getBadgeNumber(), is(equalTo(domain.getBadgeNumber())));
    assertThat(pers.getCityName(), is(equalTo(domain.getCityName())));
    assertThat(pers.getColltrClientRptrReltnshpType(),
        is(equalTo(domain.getColltrClientRptrReltnshpType())));
    assertThat(pers.getCommunicationMethodType(), is(equalTo(domain.getCommunicationMethodType())));
    assertThat(pers.getConfidentialWaiverIndicator(),
        is(equalTo(DomainChef.cookBoolean(domain.getConfidentialWaiverIndicator()))));
    assertThat(pers.getDrmsMandatedRprtrFeedback(),
        is(equalTo(domain.getDrmsMandatedRprtrFeedback())));
    assertThat(pers.getEmployerName(), is(equalTo(domain.getEmployerName())));
    assertThat(pers.getFeedbackDate(),
        is(equalTo(DomainChef.uncookDateString(domain.getFeedbackDate()))));
    assertThat(pers.getFeedbackRequiredIndicator(),
        is(equalTo(DomainChef.cookBoolean(domain.getFeedbackRequiredIndicator()))));
    assertThat(pers.getFirstName(), is(equalTo(domain.getFirstName())));
    assertThat(pers.getLastName(), is(equalTo(domain.getLastName())));
    assertThat(pers.getMandatedReporterIndicator(),
        is(equalTo(DomainChef.cookBoolean(domain.getMandatedReporterIndicator()))));
    assertThat(pers.getMessagePhoneExtensionNumber(),
        is(equalTo(domain.getMessagePhoneExtensionNumber())));
    assertThat(pers.getMessagePhoneNumber(), is(equalTo(domain.getMessagePhoneNumber())));
    assertThat(pers.getMiddleInitialName(), is(equalTo(domain.getMiddleInitialName())));
    assertThat(pers.getNamePrefixDescription(), is(equalTo(domain.getNamePrefixDescription())));
    assertThat(pers.getPrimaryPhoneNumber(), is(equalTo(domain.getPrimaryPhoneNumber())));
    assertThat(pers.getPrimaryPhoneExtensionNumber(),
        is(equalTo(domain.getPrimaryPhoneExtensionNumber())));
    assertThat(pers.getStateCodeType(), is(equalTo(domain.getStateCodeType())));
    assertThat(pers.getStreetName(), is(equalTo(domain.getStreetName())));
    assertThat(pers.getStreetNumber(), is(equalTo(domain.getStreetNumber())));
    assertThat(pers.getSuffixTitleDescription(), is(equalTo(domain.getSuffixTitleDescription())));
    assertThat(pers.getZipNumber(),
        is(equalTo(DomainChef.uncookZipcodeString(domain.getZipcode()))));
    assertThat(pers.getLawEnforcementId(), is(equalTo(domain.getLawEnforcementId())));
    assertThat(pers.getZipSuffixNumber(), is(equalTo(domain.getZipSuffixNumber())));
    assertThat(pers.getCountySpecificCode(), is(equalTo(domain.getCountySpecificCode())));
    assertThat(pers.getBirthDate(), is(equalTo(null)));
  }

  @Override
  @Test
  public void testPersistentConstructor() throws Exception {

    gov.ca.cwds.rest.api.domain.cms.Reporter vp = new ReporterResourceBuilder().build();

    Reporter persistent = new Reporter(vp.getReferralId(), vp.getBadgeNumber(), vp.getCityName(),
        vp.getColltrClientRptrReltnshpType(), vp.getCommunicationMethodType(),
        DomainChef.cookBoolean(vp.getConfidentialWaiverIndicator()),
        vp.getDrmsMandatedRprtrFeedback(), vp.getEmployerName(),
        DomainChef.uncookDateString(vp.getFeedbackDate()),
        DomainChef.cookBoolean(vp.getFeedbackRequiredIndicator()), vp.getFirstName(),
        vp.getLastName(), DomainChef.cookBoolean(vp.getMandatedReporterIndicator()),
        vp.getMessagePhoneExtensionNumber(), vp.getMessagePhoneNumber(), vp.getMiddleInitialName(),
        vp.getNamePrefixDescription(), vp.getPrimaryPhoneNumber(),
        vp.getPrimaryPhoneExtensionNumber(), vp.getStateCodeType(), vp.getStreetName(),
        vp.getStreetNumber(), vp.getSuffixTitleDescription(),
        DomainChef.uncookZipcodeString(vp.getZipcode()), vp.getLawEnforcementId(),
        vp.getZipSuffixNumber(), vp.getCountySpecificCode());

    assertThat(persistent.getReferralId(), is(equalTo(vp.getReferralId())));
    assertThat(persistent.getBadgeNumber(), is(equalTo(vp.getBadgeNumber())));
    assertThat(persistent.getCityName(), is(equalTo(vp.getCityName())));
    assertThat(persistent.getCity(), is(equalTo(persistent.getCityName())));
    assertThat(persistent.getColltrClientRptrReltnshpType(),
        is(equalTo(vp.getColltrClientRptrReltnshpType())));
    assertThat(persistent.getCommunicationMethodType(),
        is(equalTo(vp.getCommunicationMethodType())));
    assertThat(persistent.getConfidentialWaiverIndicator(),
        is(equalTo(DomainChef.cookBoolean(vp.getConfidentialWaiverIndicator()))));
    assertThat(persistent.getDrmsMandatedRprtrFeedback(),
        is(equalTo(vp.getDrmsMandatedRprtrFeedback())));
    assertThat(persistent.getEmployerName(), is(equalTo(vp.getEmployerName())));
    assertThat(persistent.getFeedbackDate(),
        is(equalTo(DomainChef.uncookDateString(vp.getFeedbackDate()))));
    assertThat(persistent.getFeedbackRequiredIndicator(),
        is(equalTo(DomainChef.cookBoolean(vp.getFeedbackRequiredIndicator()))));
    assertThat(persistent.getFirstName(), is(equalTo(vp.getFirstName())));
    assertThat(persistent.getLastName(), is(equalTo(vp.getLastName())));
    assertThat(persistent.getMandatedReporterIndicator(),
        is(equalTo(DomainChef.cookBoolean(vp.getMandatedReporterIndicator()))));
    assertThat(persistent.getMessagePhoneExtensionNumber(),
        is(equalTo(vp.getMessagePhoneExtensionNumber())));
    assertThat(persistent.getMessagePhoneNumber(), is(equalTo(vp.getMessagePhoneNumber())));
    assertThat(persistent.getMiddleInitialName(), is(equalTo(vp.getMiddleInitialName())));
    assertThat(persistent.getNamePrefixDescription(), is(equalTo(vp.getNamePrefixDescription())));
    assertThat(persistent.getPrimaryPhoneNumber(), is(equalTo(vp.getPrimaryPhoneNumber())));
    assertThat(persistent.getPrimaryPhoneExtensionNumber(),
        is(equalTo(vp.getPrimaryPhoneExtensionNumber())));
    assertThat(persistent.getStateCodeType(), is(equalTo(vp.getStateCodeType())));
    assertThat(persistent.getState(), is(equalTo(persistent.getStateCodeType().toString())));
    assertThat(persistent.getStreetName(), is(equalTo(vp.getStreetName())));
    assertThat(persistent.getStreetNumber(), is(equalTo(vp.getStreetNumber())));
    assertThat(persistent.getStreetAddress(),
        is(equalTo(StringUtils.trimToEmpty(persistent.getStreetNumber()) + " "
            + StringUtils.trimToEmpty(persistent.getStreetName()))));
    assertThat(persistent.getSuffixTitleDescription(), is(equalTo(vp.getSuffixTitleDescription())));
    assertThat(persistent.getNameSuffix(), is(equalTo(vp.getSuffixTitleDescription())));
    assertThat(persistent.getZipNumber(),
        is(equalTo(DomainChef.uncookZipcodeString(vp.getZipcode()))));
    assertThat(persistent.getLawEnforcementId(), is(equalTo(vp.getLawEnforcementId())));
    assertThat(persistent.getZipSuffixNumber(), is(equalTo(vp.getZipSuffixNumber())));
    assertThat(persistent.getCountySpecificCode(), is(equalTo(vp.getCountySpecificCode())));
    assertThat(persistent.getCounty(), is(equalTo(persistent.getCountySpecificCode())));

  }


  @Test
  public void testZipCodeSuccess() {
    gov.ca.cwds.rest.api.domain.cms.Reporter vp = new ReporterResourceBuilder().build();

    Reporter persistent = new Reporter(vp.getReferralId(), vp.getBadgeNumber(), vp.getCityName(),
        vp.getColltrClientRptrReltnshpType(), vp.getCommunicationMethodType(),
        DomainChef.cookBoolean(vp.getConfidentialWaiverIndicator()),
        vp.getDrmsMandatedRprtrFeedback(), vp.getEmployerName(),
        DomainChef.uncookDateString(vp.getFeedbackDate()),
        DomainChef.cookBoolean(vp.getFeedbackRequiredIndicator()), vp.getFirstName(),
        vp.getLastName(), DomainChef.cookBoolean(vp.getMandatedReporterIndicator()),
        vp.getMessagePhoneExtensionNumber(), vp.getMessagePhoneNumber(), vp.getMiddleInitialName(),
        vp.getNamePrefixDescription(), vp.getPrimaryPhoneNumber(),
        vp.getPrimaryPhoneExtensionNumber(), vp.getStateCodeType(), vp.getStreetName(),
        vp.getStreetNumber(), vp.getSuffixTitleDescription(),
        DomainChef.uncookZipcodeString(vp.getZipcode()), vp.getLawEnforcementId(),
        vp.getZipSuffixNumber(), vp.getCountySpecificCode());

    StringBuilder buf = new StringBuilder();

    if (persistent.getZipNumber() != null) {
      buf.append(persistent.getZipNumber());
    }
    if (persistent.getZipSuffixNumber() != null) {
      buf.append('-').append(persistent.getZipSuffixNumber());
    }

    assertThat(persistent.getZip(), is(equalTo(buf.toString())));
  }

  @Test
  public void testSerializeAndDeserialize()
      throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
    Reporter vp = validReporter();
    Reporter persistent = new Reporter(vp.getReferralId(), vp.getBadgeNumber(), vp.getCity(),
        vp.getColltrClientRptrReltnshpType(), vp.getCommunicationMethodType(),
        vp.getConfidentialWaiverIndicator(), vp.getDrmsMandatedRprtrFeedback(),
        vp.getEmployerName(), vp.getFeedbackDate(), vp.getFeedbackRequiredIndicator(),
        vp.getFirstName(), vp.getLastName(), vp.getMandatedReporterIndicator(),
        vp.getMessagePhoneExtensionNumber(), vp.getMessagePhoneNumber(), vp.getMiddleInitialName(),
        vp.getNamePrefixDescription(), vp.getPrimaryPhoneNumber(),
        vp.getPrimaryPhoneExtensionNumber(), vp.getStateCodeType(), vp.getStreetName(),
        vp.getStreetNumber(), vp.getSuffixTitleDescription(), vp.getZipNumber(),
        vp.getLawEnforcementId(), vp.getZipSuffixNumber(), vp.getCountySpecificCode());

    final String expected = MAPPER.writeValueAsString((MAPPER.readValue(
        fixture("fixtures/persistent/Reporter/valid/validWithSysCodes.json"), Reporter.class)));

    assertThat(MAPPER.writeValueAsString(persistent)).isEqualTo(expected);
  }

  @Test
  public void testNullStateCodeReturnsNull() throws Exception {
    gov.ca.cwds.rest.api.domain.cms.Reporter domain =
        new ReporterResourceBuilder().setStateCodeType(null).build();
    Reporter pers = new Reporter(domain, lastUpdatedId, lastUpdatedTime);
    assertThat(pers.getState(), is(equalTo(null)));

  }

  @Test
  public void shouldNotContainPhoneNumbersWhenBlank(){
    Long phoneNumber = 0L;
    Integer extension = new Integer(0);
    gov.ca.cwds.rest.api.domain.cms.Reporter domain =
        new ReporterResourceBuilder()
            .setPrimaryPhoneNumber(phoneNumber)
            .setPrimaryPhoneExtensionNumber(extension)
            .setMessagePhoneNumber(phoneNumber)
            .setMessagePhoneExtensionNumber(extension)
            .build();

    Reporter reporterEntity = new Reporter(domain, lastUpdatedId, lastUpdatedTime);
    assertEquals(0, reporterEntity.getPhones().length);
  }

  @Test
  public void shouldContainBothPhoneNumbersWhenNotBlank(){
    Long primaryPhoneNumber = 123L;
    Long messagePhoneNumber = 987L;
    Integer primaryExtension = new Integer(0);
    Integer messageExtension = new Integer(0);
    gov.ca.cwds.rest.api.domain.cms.Reporter domain =
        new ReporterResourceBuilder()
            .setPrimaryPhoneNumber(primaryPhoneNumber)
            .setPrimaryPhoneExtensionNumber(primaryExtension)
            .setMessagePhoneNumber(messagePhoneNumber)
            .setMessagePhoneExtensionNumber(messageExtension)
            .build();

    Reporter reporterEntity = new Reporter(domain, lastUpdatedId, lastUpdatedTime);
    assertEquals(2, reporterEntity.getPhones().length);
    assertEquals(reporterEntity.getPhones()[0].getPhoneNumber(), "123");
    assertEquals(reporterEntity.getPhones()[1].getPhoneNumber(), "987");
  }

  @Test
  public void shouldEqualAReporterWithSameValues(){
    gov.ca.cwds.rest.api.domain.cms.Reporter fred = new ReporterResourceBuilder().setEmployerName("Fred").build();
    Reporter fred1Entity = new Reporter(fred, lastUpdatedId, lastUpdatedTime);
    gov.ca.cwds.rest.api.domain.cms.Reporter bill = new ReporterResourceBuilder().setEmployerName("Fred").build();
    Reporter fred2Entity = new Reporter(bill, lastUpdatedId, lastUpdatedTime);
    assertEquals("Expecting reporters to be equal", fred1Entity, fred2Entity);

  }
  @Test
  public void shouldNotEqualAReporterWithDifferentValues(){
    gov.ca.cwds.rest.api.domain.cms.Reporter fred = new ReporterResourceBuilder().setEmployerName("Fred").build();
    Reporter fredEntity = new Reporter(fred, lastUpdatedId, lastUpdatedTime);
    gov.ca.cwds.rest.api.domain.cms.Reporter bill = new ReporterResourceBuilder().setEmployerName("Bill").build();
    Reporter billEntity = new Reporter(bill, lastUpdatedId, lastUpdatedTime);
    assertNotEquals("Expecting reporters to not be equal", fredEntity, billEntity);

  }

  @Test
  public void shouldHaveSameHashCodesForReportersWithSameValues(){
    gov.ca.cwds.rest.api.domain.cms.Reporter fred = new ReporterResourceBuilder().setEmployerName("Fred").build();
    Reporter fred1Entity = new Reporter(fred, lastUpdatedId, lastUpdatedTime);
    gov.ca.cwds.rest.api.domain.cms.Reporter bill = new ReporterResourceBuilder().setEmployerName("Fred").build();
    Reporter fred2Entity = new Reporter(bill, lastUpdatedId, lastUpdatedTime);
    assertEquals("Expecting reporters to have same hash code", fred1Entity.hashCode(), fred2Entity.hashCode());

  }
  @Test
  public void shouldNotHaveSameHashCodesForReportersWithDifferentSameValues(){
    gov.ca.cwds.rest.api.domain.cms.Reporter fred = new ReporterResourceBuilder().setEmployerName("Fred").build();
    Reporter fredEntity = new Reporter(fred, lastUpdatedId, lastUpdatedTime);
    gov.ca.cwds.rest.api.domain.cms.Reporter bill = new ReporterResourceBuilder().setEmployerName("Bill").build();
    Reporter billEntity = new Reporter(bill, lastUpdatedId, lastUpdatedTime);
    assertNotEquals("Expecting reporters to have different hash code", fredEntity.hashCode(), billEntity.hashCode());

  }
  private Reporter validReporter() throws JsonParseException, JsonMappingException, IOException {
    Reporter validReporter =
        MAPPER.readValue(fixture("fixtures/persistent/Reporter/valid/valid.json"), Reporter.class);
    return validReporter;
  }

}
