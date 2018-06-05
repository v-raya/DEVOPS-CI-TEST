package gov.ca.cwds.data.persistence.cms;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.ca.cwds.data.persistence.junit.template.PersistentTestTemplate;
import gov.ca.cwds.data.std.ApiLanguageAware;
import gov.ca.cwds.fixture.ClientResourceBuilder;
import gov.ca.cwds.rest.api.domain.DomainChef;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * @author CWDS API Team
 */
public class ClientTest implements PersistentTestTemplate {

  private String id = "1234567ABC";
  private String lastUpdatedId = "0X5";
  private Date lastUpdatedTime = new Date();
  private static final ObjectMapper MAPPER = SystemCodeTestHarness.MAPPER;

  /*
   * Constructor test
   */
  @Override
  public void testEmptyConstructor() throws Exception {
    assertThat(Client.class.newInstance(), is(notNullValue()));
  }

  @Test
  public void nullConstructorFieldsShouldBeStoredAsStrings() throws Exception {
    Client vc = validClient();
    String aNullValue = null;
    Client pers = new Client(vc.getAdjudicatedDelinquentIndicator(), vc.getAdoptionStatusCode(),
        vc.getAlienRegistrationNumber(), vc.getBirthCity(), vc.getBirthCountryCodeType(),
        vc.getBirthDate(), vc.getBirthFacilityName(), vc.getBirthStateCodeType(),
        vc.getBirthplaceVerifiedIndicator(), vc.getChildClientIndicatorVar(),
        vc.getClientIndexNumber(), vc.getCommentDescription(), vc.getCommonFirstName(), aNullValue,
        vc.getCommonLastName(), vc.getConfidentialityActionDate(),
        vc.getConfidentialityInEffectIndicator(), vc.getCreationDate(),
        vc.getCurrCaChildrenServIndicator(), vc.getCurrentlyOtherDescription(),
        vc.getCurrentlyRegionalCenterIndicator(), vc.getDeathDate(),
        vc.getDeathDateVerifiedIndicator(), vc.getDeathPlace(), vc.getDeathReasonText(),
        vc.getDriverLicenseNumber(), vc.getDriverLicenseStateCodeType(), vc.getEmailAddress(),
        vc.getEstimatedDobCode(), vc.getEthUnableToDetReasonCode(),
        vc.getFatherParentalRightTermDate(), vc.getGenderCode(), vc.getGenderIdentityType(),
        vc.getGiNotListedDescription(), vc.getGenderExpressionType(), vc.getHealthSummaryText(),
        vc.getHispUnableToDetReasonCode(), vc.getHispanicOriginCode(), vc.getId(),
        vc.getImmigrationCountryCodeType(), vc.getImmigrationStatusType(),
        vc.getIncapacitatedParentCode(), vc.getIndividualHealthCarePlanIndicator(),
        vc.getLimitationOnScpHealthIndicator(), vc.getLiterateCode(),
        vc.getMaritalCohabitatnHstryIndicatorVar(), vc.getMaritalStatusType(),
        vc.getMilitaryStatusCode(), vc.getMotherParentalRightTermDate(),
        vc.getNamePrefixDescription(), vc.getNameType(), vc.getOutstandingWarrantIndicator(),
        vc.getPrevCaChildrenServIndicator(), vc.getPrevOtherDescription(),
        vc.getPrevRegionalCenterIndicator(), vc.getPrimaryEthnicityType(),
        vc.getPrimaryLanguageType(), vc.getReligionType(), vc.getSecondaryLanguageType(),
        vc.getSensitiveHlthInfoOnFileIndicator(), vc.getSensitivityIndicator(),
        vc.getSexualOrientationType(), vc.getSoUnableToDetermineCode(),
        vc.getSoNotListedDescrption(), vc.getSoc158PlacementCode(),
        vc.getSoc158SealedClientIndicator(), vc.getSocialSecurityNumChangedCode(),
        vc.getSocialSecurityNumber(), aNullValue, vc.getTribalAncestryClientIndicatorVar(),
        vc.getTribalMembrshpVerifctnIndicatorVar(), vc.getUnemployedParentCode(),
        vc.getZippyCreatedIndicator(), null);
    assertThat(pers.getCommonMiddleName(), is(equalTo("")));
    assertThat(pers.getSuffixTitleDescription(), is(equalTo("")));
  }

  @Override
  @Test
  public void testPersistentConstructor() throws Exception {
    Client vc = validClient();
    Client pers = new Client(vc.getAdjudicatedDelinquentIndicator(), vc.getAdoptionStatusCode(),
        vc.getAlienRegistrationNumber(), vc.getBirthCity(), vc.getBirthCountryCodeType(),
        vc.getBirthDate(), vc.getBirthFacilityName(), vc.getBirthStateCodeType(),
        vc.getBirthplaceVerifiedIndicator(), vc.getChildClientIndicatorVar(),
        vc.getClientIndexNumber(), vc.getCommentDescription(), vc.getCommonFirstName(),
        vc.getCommonMiddleName(), vc.getCommonLastName(), vc.getConfidentialityActionDate(),
        vc.getConfidentialityInEffectIndicator(), vc.getCreationDate(),
        vc.getCurrCaChildrenServIndicator(), vc.getCurrentlyOtherDescription(),
        vc.getCurrentlyRegionalCenterIndicator(), vc.getDeathDate(),
        vc.getDeathDateVerifiedIndicator(), vc.getDeathPlace(), vc.getDeathReasonText(),
        vc.getDriverLicenseNumber(), vc.getDriverLicenseStateCodeType(), vc.getEmailAddress(),
        vc.getEstimatedDobCode(), vc.getEthUnableToDetReasonCode(),
        vc.getFatherParentalRightTermDate(), vc.getGenderCode(), vc.getGenderIdentityType(),
        vc.getGiNotListedDescription(), vc.getGenderExpressionType(), vc.getHealthSummaryText(),
        vc.getHispUnableToDetReasonCode(), vc.getHispanicOriginCode(), vc.getId(),
        vc.getImmigrationCountryCodeType(), vc.getImmigrationStatusType(),
        vc.getIncapacitatedParentCode(), vc.getIndividualHealthCarePlanIndicator(),
        vc.getLimitationOnScpHealthIndicator(), vc.getLiterateCode(),
        vc.getMaritalCohabitatnHstryIndicatorVar(), vc.getMaritalStatusType(),
        vc.getMilitaryStatusCode(), vc.getMotherParentalRightTermDate(),
        vc.getNamePrefixDescription(), vc.getNameType(), vc.getOutstandingWarrantIndicator(),
        vc.getPrevCaChildrenServIndicator(), vc.getPrevOtherDescription(),
        vc.getPrevRegionalCenterIndicator(), vc.getPrimaryEthnicityType(),
        vc.getPrimaryLanguageType(), vc.getReligionType(), vc.getSecondaryLanguageType(),
        vc.getSensitiveHlthInfoOnFileIndicator(), vc.getSensitivityIndicator(),
        vc.getSexualOrientationType(), vc.getSoUnableToDetermineCode(),
        vc.getSoNotListedDescrption(), vc.getSoc158PlacementCode(),
        vc.getSoc158SealedClientIndicator(), vc.getSocialSecurityNumChangedCode(),
        vc.getSocialSecurityNumber(), vc.getSuffixTitleDescription(),
        vc.getTribalAncestryClientIndicatorVar(), vc.getTribalMembrshpVerifctnIndicatorVar(),
        vc.getUnemployedParentCode(), vc.getZippyCreatedIndicator(), null);
    assertThat(pers.getAdjudicatedDelinquentIndicator(),
        is(equalTo(vc.getAdjudicatedDelinquentIndicator())));
    assertThat(pers.getAdoptionStatusCode(), is(equalTo(vc.getAdoptionStatusCode())));
    assertThat(pers.getAlienRegistrationNumber(), is(equalTo(vc.getAlienRegistrationNumber())));
    assertThat(pers.getBirthCity(), is(equalTo(vc.getBirthCity())));
    assertThat(pers.getBirthCountryCodeType(), is(equalTo(vc.getBirthCountryCodeType())));
    assertThat(pers.getBirthDate(), is(equalTo(vc.getBirthDate())));
    assertThat(pers.getBirthFacilityName(), is(equalTo(vc.getBirthFacilityName())));
    assertThat(pers.getBirthStateCodeType(), is(equalTo(vc.getBirthStateCodeType())));
    assertThat(pers.getBirthplaceVerifiedIndicator(),
        is(equalTo(vc.getBirthplaceVerifiedIndicator())));
    assertThat(pers.getChildClientIndicatorVar(), is(equalTo(vc.getChildClientIndicatorVar())));
    assertThat(pers.getClientIndexNumber(), is(equalTo(vc.getClientIndexNumber())));
    assertThat(pers.getCommentDescription(), is(equalTo(vc.getCommentDescription())));
    assertThat(pers.getCommonFirstName(), is(equalTo(vc.getCommonFirstName())));
    assertThat(pers.getCommonLastName(), is(equalTo(vc.getCommonLastName())));
    assertThat(pers.getCommonMiddleName(), is(equalTo(vc.getCommonMiddleName())));
    assertThat(pers.getConfidentialityActionDate(), is(equalTo(vc.getConfidentialityActionDate())));
    assertThat(pers.getConfidentialityInEffectIndicator(),
        is(equalTo(vc.getConfidentialityInEffectIndicator())));
    assertThat(pers.getCurrCaChildrenServIndicator(),
        is(equalTo(vc.getCurrCaChildrenServIndicator())));
    assertThat(pers.getCurrentlyOtherDescription(), is(equalTo(vc.getCurrentlyOtherDescription())));
    assertThat(pers.getCurrentlyRegionalCenterIndicator(),
        is(equalTo(vc.getCurrentlyRegionalCenterIndicator())));
    assertThat(pers.getDeathDate(), is(equalTo(vc.getDeathDate())));
    assertThat(pers.getDeathDateVerifiedIndicator(),
        is(equalTo(vc.getDeathDateVerifiedIndicator())));
    assertThat(pers.getDeathPlace(), is(equalTo(vc.getDeathPlace())));
    assertThat(pers.getDeathReasonText(), is(equalTo(vc.getDeathReasonText())));
    assertThat(pers.getDriverLicenseNumber(), is(equalTo(vc.getDriverLicenseNumber())));
    assertThat(pers.getDriverLicenseStateCodeType(),
        is(equalTo(vc.getDriverLicenseStateCodeType())));
    assertThat(pers.getEmailAddress(), is(equalTo(vc.getEmailAddress())));
    assertThat(pers.getEstimatedDobCode(), is(equalTo(vc.getEstimatedDobCode())));
    assertThat(pers.getEthUnableToDetReasonCode(), is(equalTo(vc.getEthUnableToDetReasonCode())));
    assertThat(pers.getFatherParentalRightTermDate(),
        is(equalTo(vc.getFatherParentalRightTermDate())));
    assertThat(pers.getGenderIdentityType(), is(equalTo(vc.getGenderIdentityType())));
    assertThat(pers.getGiNotListedDescription(), is(equalTo(vc.getGiNotListedDescription())));
    assertThat(pers.getGenderExpressionType(), is(equalTo(vc.getGenderExpressionType())));
    assertThat(pers.getHealthSummaryText(), is(equalTo(vc.getHealthSummaryText())));
    assertThat(pers.getHispUnableToDetReasonCode(), is(equalTo(vc.getHispUnableToDetReasonCode())));
    assertThat(pers.getHispanicOriginCode(), is(equalTo(vc.getHispanicOriginCode())));
    assertThat(pers.getId(), is(equalTo(vc.getId())));
    assertThat(pers.getImmigrationCountryCodeType(),
        is(equalTo(vc.getImmigrationCountryCodeType())));
    assertThat(pers.getImmigrationStatusType(), is(equalTo(vc.getImmigrationStatusType())));
    assertThat(pers.getIncapacitatedParentCode(), is(equalTo(vc.getIncapacitatedParentCode())));
    assertThat(pers.getLimitationOnScpHealthIndicator(),
        is(equalTo(vc.getLimitationOnScpHealthIndicator())));
    assertThat(pers.getLiterateCode(), is(equalTo(vc.getLiterateCode())));
    assertThat(pers.getMaritalCohabitatnHstryIndicatorVar(),
        is(equalTo(vc.getMaritalCohabitatnHstryIndicatorVar())));
    assertThat(pers.getMaritalStatusType(), is(equalTo(vc.getMaritalStatusType())));
    assertThat(pers.getMilitaryStatusCode(), is(equalTo(vc.getMilitaryStatusCode())));
    assertThat(pers.getMotherParentalRightTermDate(),
        is(equalTo(vc.getMotherParentalRightTermDate())));
    assertThat(pers.getNamePrefixDescription(), is(equalTo(vc.getNamePrefixDescription())));
    assertThat(pers.getNameType(), is(equalTo(vc.getNameType())));
    assertThat(pers.getOutstandingWarrantIndicator(),
        is(equalTo(vc.getOutstandingWarrantIndicator())));
    assertThat(pers.getPrevCaChildrenServIndicator(),
        is(equalTo(vc.getPrevCaChildrenServIndicator())));
    assertThat(pers.getPrevOtherDescription(), is(equalTo(vc.getPrevOtherDescription())));
    assertThat(pers.getPrevRegionalCenterIndicator(),
        is(equalTo(vc.getPrevRegionalCenterIndicator())));
    assertThat(pers.getPrimaryEthnicityType(), is(equalTo(vc.getPrimaryEthnicityType())));
    assertThat(pers.getReligionType(), is(equalTo(vc.getReligionType())));
    assertThat(pers.getSecondaryLanguageType(), is(equalTo(vc.getSecondaryLanguageType())));
    assertThat(pers.getSensitiveHlthInfoOnFileIndicator(),
        is(equalTo(vc.getSensitiveHlthInfoOnFileIndicator())));
    assertThat(pers.getSensitivityIndicator(), is(equalTo(vc.getSensitivityIndicator())));
    assertThat(pers.getSexualOrientationType(), is(equalTo(vc.getSexualOrientationType())));
    assertThat(pers.getSoUnableToDetermineCode(), is(equalTo(vc.getSoUnableToDetermineCode())));
    assertThat(pers.getSoNotListedDescrption(), is(equalTo(vc.getSoNotListedDescrption())));
    assertThat(pers.getSoc158PlacementCode(), is(equalTo(vc.getSoc158PlacementCode())));
    assertThat(pers.getSoc158SealedClientIndicator(),
        is(equalTo(vc.getSoc158SealedClientIndicator())));
    assertThat(pers.getSocialSecurityNumChangedCode(),
        is(equalTo(vc.getSocialSecurityNumChangedCode())));
    assertThat(pers.getSocialSecurityNumber(), is(equalTo(vc.getSocialSecurityNumber())));
    assertThat(pers.getSuffixTitleDescription(), is(equalTo(vc.getSuffixTitleDescription())));
    assertThat(pers.getTribalAncestryClientIndicatorVar(),
        is(equalTo(vc.getTribalAncestryClientIndicatorVar())));
    assertThat(pers.getTribalMembrshpVerifctnIndicatorVar(),
        is(equalTo(vc.getTribalMembrshpVerifctnIndicatorVar())));
    assertThat(pers.getUnemployedParentCode(), is(equalTo(vc.getUnemployedParentCode())));
    assertThat(pers.getZippyCreatedIndicator(), is(equalTo(vc.getZippyCreatedIndicator())));
  }

  @Override
  @Test
  public void testConstructorUsingDomain() throws Exception {
    gov.ca.cwds.rest.api.domain.cms.Client vc = validDomainClient();
    Client pers = new Client(id, vc, lastUpdatedId, lastUpdatedTime);
    assertThat(pers.getId(), is(equalTo(id)));
    assertThat(pers.getLastUpdatedId(), is(equalTo(lastUpdatedId)));
    assertThat((pers.getAdjudicatedDelinquentIndicator()),
        is(equalTo(DomainChef.cookBoolean(vc.getAdjudicatedDelinquentIndicator()))));
    assertThat(pers.getAdoptionStatusCode(), is(equalTo(vc.getAdoptionStatusCode())));
    assertThat(pers.getAlienRegistrationNumber(), is(equalTo(vc.getAlienRegistrationNumber())));
    assertThat(pers.getBirthCity(), is(equalTo(vc.getBirthCity())));
    assertThat(pers.getBirthCountryCodeType(), is(equalTo(vc.getBirthCountryCodeType())));
    assertThat(pers.getBirthDate(), is(equalTo(DomainChef.uncookDateString(vc.getBirthDate()))));
    assertThat(pers.getBirthFacilityName(), is(equalTo(vc.getBirthFacilityName())));
    assertThat(pers.getBirthStateCodeType(), is(equalTo(vc.getBirthStateCodeType())));
    assertThat(pers.getBirthplaceVerifiedIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getBirthplaceVerifiedIndicator()))));
    assertThat(pers.getChildClientIndicatorVar(),
        is(equalTo(DomainChef.cookBoolean(vc.getChildClientIndicatorVar()))));
    assertThat(pers.getClientIndexNumber(), is(equalTo(vc.getClientIndexNumber())));
    assertThat(pers.getCommentDescription(), is(equalTo(vc.getCommentDescription())));
    assertThat(pers.getCommonFirstName(), is(equalTo(vc.getCommonFirstName())));
    assertThat(pers.getCommonLastName(), is(equalTo(vc.getCommonLastName())));
    assertThat(pers.getCommonMiddleName(), is(equalTo(vc.getCommonMiddleName())));
    assertThat(pers.getConfidentialityActionDate(),
        is(equalTo(DomainChef.uncookDateString(vc.getConfidentialityActionDate()))));
    assertThat(pers.getConfidentialityInEffectIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getConfidentialityInEffectIndicator()))));
    assertThat(pers.getCurrCaChildrenServIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getCurrCaChildrenServIndicator()))));
    assertThat(pers.getCurrentlyOtherDescription(), is(equalTo(vc.getCurrentlyOtherDescription())));
    assertThat(pers.getCurrentlyRegionalCenterIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getCurrentlyRegionalCenterIndicator()))));
    assertThat(pers.getDeathDate(), is(equalTo(DomainChef.uncookDateString(vc.getDeathDate()))));
    assertThat(pers.getDeathDateVerifiedIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getDeathDateVerifiedIndicator()))));
    assertThat(pers.getDeathPlace(), is(equalTo(vc.getDeathPlace())));
    assertThat(pers.getDeathReasonText(), is(equalTo(vc.getDeathReasonText())));
    assertThat(pers.getDriverLicenseNumber(), is(equalTo(vc.getDriverLicenseNumber())));
    assertThat(pers.getDriverLicenseStateCodeType(),
        is(equalTo(vc.getDriverLicenseStateCodeType())));
    assertThat(pers.getEmailAddress(), is(equalTo(vc.getEmailAddress())));
    assertThat(pers.getEstimatedDobCode(), is(equalTo(vc.getEstimatedDobCode())));
    assertThat(pers.getEthUnableToDetReasonCode(), is(equalTo(vc.getEthUnableToDetReasonCode())));
    assertThat(pers.getFatherParentalRightTermDate(),
        is(equalTo(DomainChef.uncookDateString(vc.getFatherParentalRightTermDate()))));
    assertThat(pers.getGenderCode(), is(equalTo(vc.getGenderCode())));
    assertThat(pers.getHealthSummaryText(), is(equalTo(vc.getHealthSummaryText())));
    assertThat(pers.getHispUnableToDetReasonCode(), is(equalTo(vc.getHispUnableToDetReasonCode())));
    assertThat(pers.getHispanicOriginCode(), is(equalTo(vc.getHispanicOriginCode())));
    assertThat(pers.getImmigrationCountryCodeType(),
        is(equalTo(vc.getImmigrationCountryCodeType())));
    assertThat(pers.getImmigrationStatusType(), is(equalTo(vc.getImmigrationStatusType())));
    assertThat(pers.getIncapacitatedParentCode(), is(equalTo(vc.getIncapacitatedParentCode())));
    assertThat(pers.getLimitationOnScpHealthIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getLimitationOnScpHealthIndicator()))));
    assertThat(pers.getLiterateCode(), is(equalTo(vc.getLiterateCode())));
    assertThat(pers.getMaritalCohabitatnHstryIndicatorVar(),
        is(equalTo(DomainChef.cookBoolean(vc.getMaritalCohabitatnHstryIndicatorVar()))));
    assertThat(pers.getMaritalStatusType(), is(equalTo(vc.getMaritalStatusType())));
    assertThat(pers.getMilitaryStatusCode(), is(equalTo(vc.getMilitaryStatusCode())));
    assertThat(pers.getMotherParentalRightTermDate(),
        is(equalTo(DomainChef.uncookDateString(vc.getMotherParentalRightTermDate()))));
    assertThat(pers.getNamePrefixDescription(), is(equalTo(vc.getNamePrefixDescription())));
    assertThat(pers.getNameType(), is(equalTo(vc.getNameType())));
    assertThat(pers.getOutstandingWarrantIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getOutstandingWarrantIndicator()))));
    assertThat(pers.getPrevCaChildrenServIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getPrevCaChildrenServIndicator()))));
    assertThat(pers.getPrevOtherDescription(), is(equalTo(vc.getPrevOtherDescription())));
    assertThat(pers.getPrevRegionalCenterIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getPrevRegionalCenterIndicator()))));
    assertThat(pers.getPrimaryEthnicityType(), is(equalTo(vc.getPrimaryEthnicityType())));
    assertThat(pers.getReligionType(), is(equalTo(vc.getReligionType())));
    assertThat(pers.getSecondaryLanguageType(), is(equalTo(vc.getSecondaryLanguage())));
    assertThat(pers.getSensitiveHlthInfoOnFileIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getSensitiveHlthInfoOnFileIndicator()))));
    assertThat(pers.getSensitivityIndicator(), is(equalTo(vc.getSensitivityIndicator())));
    assertThat(pers.getSoc158PlacementCode(), is(equalTo(vc.getSoc158PlacementCode())));
    assertThat(pers.getSoc158SealedClientIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getSoc158SealedClientIndicator()))));
    assertThat(pers.getSocialSecurityNumChangedCode(),
        is(equalTo(vc.getSocialSecurityNumChangedCode())));
    assertThat(pers.getSocialSecurityNumber(), is(equalTo(vc.getSocialSecurityNumber())));
    assertThat(pers.getSuffixTitleDescription(), is(equalTo(vc.getSuffixTitleDescription())));
    assertThat(pers.getTribalAncestryClientIndicatorVar(),
        is(equalTo(DomainChef.cookBoolean(vc.getTribalAncestryClientIndicatorVar()))));
    assertThat(pers.getTribalMembrshpVerifctnIndicatorVar(),
        is(equalTo(DomainChef.cookBoolean(vc.getTribalMembrshpVerifctnIndicatorVar()))));
    assertThat(pers.getUnemployedParentCode(), is(equalTo(vc.getUnemployedParentCode())));
    assertThat(pers.getZippyCreatedIndicator(),
        is(equalTo(DomainChef.cookBoolean(vc.getZippyCreatedIndicator()))));
  }

  @Test
  public void testSerializeJson() throws Exception {
    Client vc = validClient();
    Client pers = new Client(vc.getAdjudicatedDelinquentIndicator(), vc.getAdoptionStatusCode(),
        vc.getAlienRegistrationNumber(), vc.getBirthCity(), vc.getBirthCountryCodeType(),
        vc.getBirthDate(), vc.getBirthFacilityName(), vc.getBirthStateCodeType(),
        vc.getBirthplaceVerifiedIndicator(), vc.getChildClientIndicatorVar(),
        vc.getClientIndexNumber(), vc.getCommentDescription(), vc.getCommonFirstName(),
        vc.getCommonMiddleName(), vc.getCommonLastName(), vc.getConfidentialityActionDate(),
        vc.getConfidentialityInEffectIndicator(), vc.getCreationDate(),
        vc.getCurrCaChildrenServIndicator(), vc.getCurrentlyOtherDescription(),
        vc.getCurrentlyRegionalCenterIndicator(), vc.getDeathDate(),
        vc.getDeathDateVerifiedIndicator(), vc.getDeathPlace(), vc.getDeathReasonText(),
        vc.getDriverLicenseNumber(), vc.getDriverLicenseStateCodeType(), vc.getEmailAddress(),
        vc.getEstimatedDobCode(), vc.getEthUnableToDetReasonCode(),
        vc.getFatherParentalRightTermDate(), vc.getGenderCode(), vc.getGenderIdentityType(),
        vc.getGiNotListedDescription(), vc.getGenderExpressionType(), vc.getHealthSummaryText(),
        vc.getHispUnableToDetReasonCode(), vc.getHispanicOriginCode(), vc.getId(),
        vc.getImmigrationCountryCodeType(), vc.getImmigrationStatusType(),
        vc.getIncapacitatedParentCode(), vc.getIndividualHealthCarePlanIndicator(),
        vc.getLimitationOnScpHealthIndicator(), vc.getLiterateCode(),
        vc.getMaritalCohabitatnHstryIndicatorVar(), vc.getMaritalStatusType(),
        vc.getMilitaryStatusCode(), vc.getMotherParentalRightTermDate(),
        vc.getNamePrefixDescription(), vc.getNameType(), vc.getOutstandingWarrantIndicator(),
        vc.getPrevCaChildrenServIndicator(), vc.getPrevOtherDescription(),
        vc.getPrevRegionalCenterIndicator(), vc.getPrimaryEthnicityType(),
        vc.getPrimaryLanguageType(), vc.getReligionType(), vc.getSecondaryLanguageType(),
        vc.getSensitiveHlthInfoOnFileIndicator(), vc.getSensitivityIndicator(),
        vc.getSexualOrientationType(), vc.getSoUnableToDetermineCode(),
        vc.getSoNotListedDescrption(), vc.getSoc158PlacementCode(),
        vc.getSoc158SealedClientIndicator(), vc.getSocialSecurityNumChangedCode(),
        vc.getSocialSecurityNumber(), vc.getSuffixTitleDescription(),
        vc.getTribalAncestryClientIndicatorVar(), vc.getTribalMembrshpVerifctnIndicatorVar(),
        vc.getUnemployedParentCode(), vc.getZippyCreatedIndicator(), null);
    final String expected = MAPPER.writeValueAsString((MAPPER.readValue(
        fixture("fixtures/persistent/Client/valid/validWithSysCodes.json"), Client.class)));
    assertThat(MAPPER.writeValueAsString(pers)).isEqualTo(expected);
  }

  private Client validClient() throws JsonParseException, JsonMappingException, IOException {
    final Client validClient =
        MAPPER.readValue(fixture("fixtures/persistent/Client/valid/valid.json"), Client.class);
    return validClient;
  }

  private gov.ca.cwds.rest.api.domain.cms.Client validDomainClient()
      throws JsonParseException, JsonMappingException, IOException {
    gov.ca.cwds.rest.api.domain.cms.Client validDomainClient = new ClientResourceBuilder().build();
    return validDomainClient;
  }

  @Test
  public void testSerializeAndDeserialize() throws Exception {
    final Client tgt = validBean();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (PrintStream ps = new PrintStream(baos)) {
      MAPPER.writerWithDefaultPrettyPrinter().writeValue(ps, tgt);
    } finally {
    }

    final String json = baos.toString(java.nio.charset.StandardCharsets.UTF_8.name());
    // Deserialize from JSON just written.
    final Client actual = MAPPER.readValue(json, Client.class);
    // Does it match exactly?
    assertThat(actual, is(equalTo(validBean())));
  }

  @Test
  public void type() throws Exception {
    assertThat(Client.class, notNullValue());
  }

  @Test
  public void instantiation() throws Exception {
    final Client target = validBean();
    assertThat(target, notNullValue());
  }

  @Test
  public void getPrimaryKey_Args$() throws Exception {
    final Client target = validBean();
    final String actual = target.getPrimaryKey();
    final String expected = "B3ucP1K07n";
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getMiddleName_Args$() throws Exception {
    final Client target = validBean();
    String actual = target.getMiddleName();
    final String expected = "";
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getFirstName_Args$() throws Exception {
    final Client target = validBean();
    String actual = target.getFirstName();
    final String expected = "Chicken";
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getLastName_Args$() throws Exception {
    final Client target = validBean();
    String actual = target.getLastName();
    final String expected = "Little";
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getSsn_Args$() throws Exception {
    final Client target = validBean();
    String actual = target.getSsn();
    final String expected = "";
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getNameSuffix_Args$() throws Exception {
    final Client target = validBean();
    final String actual = target.getNameSuffix();
    final String expected = "";
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getLanguages_Args$() throws Exception {
    final Client target = validBean();
    final ApiLanguageAware[] actual = target.getLanguages();
    assertTrue(actual.length > 0);
  }

  @Test
  public void testConstructorWithNullSuffixTitleDescription() throws Exception {
    gov.ca.cwds.rest.api.domain.cms.Client vc = new ClientResourceBuilder().setSuffixTitleDescription(null).build();
    Client pers = new Client(id, vc, lastUpdatedId, lastUpdatedTime);
    assertThat(pers.getSuffixTitleDescription(), is(equalTo("")));
  }

  @Test
  public void testToString() throws Exception {
    gov.ca.cwds.rest.api.domain.cms.Client vc = validDomainClient();
    Client pers = new Client(id, vc, lastUpdatedId, lastUpdatedTime);
    assertThat(pers.toString(), is(not(equalTo(""))));
  }

  private Client validBean() throws JsonParseException, JsonMappingException, IOException {
    return MAPPER.readValue(fixture("fixtures/persistence/legacy/Client/valid.json"), Client.class);
  }

  @Test
  public void testEqualsHashCodeWorks() throws Exception {
    EqualsVerifier.forClass(gov.ca.cwds.data.persistence.cms.ClientRelationship.class)
        .suppress(Warning.STRICT_INHERITANCE).withRedefinedSuperclass().verify();
  }

}
