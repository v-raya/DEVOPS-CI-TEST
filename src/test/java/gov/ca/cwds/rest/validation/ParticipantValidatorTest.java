package gov.ca.cwds.rest.validation;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import gov.ca.cwds.fixture.ParticipantResourceBuilder;
import gov.ca.cwds.fixture.ScreeningToReferralResourceBuilder;
import gov.ca.cwds.rest.api.domain.Participant;
import gov.ca.cwds.rest.api.domain.Role;
import gov.ca.cwds.rest.api.domain.ScreeningToReferral;
import gov.ca.cwds.rest.messages.MessageBuilder;
import io.dropwizard.jackson.Jackson;

@SuppressWarnings("javadoc")
public class ParticipantValidatorTest {
  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
  private Validator validator;
  private MessageBuilder messageBuilder;


  @Before
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    messageBuilder = new MessageBuilder();

    MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
  }

  @Test
  public void testHasValidParticipantsSuccess() throws Exception {
    ScreeningToReferral toValidate =
        new ScreeningToReferralResourceBuilder().createScreeningToReferral();
    assertThat(ParticipantValidator.hasValidParticipants(toValidate), equalTo(true));
  }

  @Test
  public void testHasMultipleVictimsSuccess() throws Exception {
    ScreeningToReferral toValidate = MAPPER.readValue(
        fixture("fixtures/domain/ScreeningToReferral/valid/moreThanOneVictim.json"),
        ScreeningToReferral.class);
    assertThat(ParticipantValidator.hasValidParticipants(toValidate), equalTo(true));
  }

  @Test
  public void testHasMultipleReporterFail() throws Exception {
    ScreeningToReferral toValidate = MAPPER.readValue(
        fixture("fixtures/domain/ScreeningToReferral/invalid/moreThanOneReporter.json"),
        ScreeningToReferral.class);
    assertThat(ParticipantValidator.hasValidParticipants(toValidate), equalTo(false));
  }

  @Test
  public void testHasMultipleSelfReportedFail() throws Exception {
    ScreeningToReferral toValidate = MAPPER.readValue(
        fixture("fixtures/domain/ScreeningToReferral/invalid/moreThanOneSelfReported.json"),
        ScreeningToReferral.class);
    assertThat(ParticipantValidator.hasValidParticipants(toValidate), equalTo(false));
  }

  @Test
  public void testHasMultipleAllegationsSuccess() throws Exception {
    ScreeningToReferral toValidate = MAPPER.readValue(
        fixture("fixtures/domain/ScreeningToReferral/valid/multipleAllegations.json"),
        ScreeningToReferral.class);
    assertThat(ParticipantValidator.hasValidParticipants(toValidate), equalTo(true));
  }

  @Test
  public void testNoVictimFail() throws Exception {
    ScreeningToReferral toValidate =
        MAPPER.readValue(fixture("fixtures/domain/ScreeningToReferral/invalid/noVictim.json"),
            ScreeningToReferral.class);
    assertThat(ParticipantValidator.hasValidParticipants(toValidate), equalTo(false));
  }

  @Test
  public void testAllegationVictimPersonIdNotParticipantVictimFail() throws Exception {
    ScreeningToReferral toValidate =
        new ScreeningToReferralResourceBuilder().createScreeningToReferral();
    assertThat(ParticipantValidator.isVictimParticipant(toValidate, 9999), equalTo(false));
  }

  @Test
  public void testAllegationVictimPersonIdIsParticipantVictimSuccess() throws Exception {
    ScreeningToReferral toValidate =
        new ScreeningToReferralResourceBuilder().createScreeningToReferral();
    assertThat(ParticipantValidator.isVictimParticipant(toValidate, 5432), equalTo(true));
  }

  @Test
  public void testAllegationPerpetratorPersonIdNotParticipantPerpetratorFail() throws Exception {
    ScreeningToReferral toValidate =
        new ScreeningToReferralResourceBuilder().createScreeningToReferral();
    assertThat(ParticipantValidator.isPerpetratorParticipant(toValidate, 9999), equalTo(false));
  }

  @Test
  public void testAllegationPerpetratorPersonIdIsParticipantPerpetratorSuccess() throws Exception {
    ScreeningToReferral toValidate = MAPPER.readValue(fixture(
        "fixtures/domain/ScreeningToReferral/valid/allegationPerpetratorIsParticipantPerpetrator.json"),
        ScreeningToReferral.class);
    assertThat(ParticipantValidator.isPerpetratorParticipant(toValidate, 1234), equalTo(true));
  }

  @Test
  public void testRoleIsVictimSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsVictim("Victim"), equalTo(true));
  }

  @Test
  public void testRoleIsNotVictimSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsVictim("Perpetrator"), equalTo(false));
  }

  @Test
  public void testRoleIsNotVictimWhenNullSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsVictim(null), equalTo(false));
  }

  @Test
  public void testRoleIsPerpetratorSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsPerpetrator("Perpetrator"), equalTo(true));
  }

  @Test
  public void testRoleIsNotPerpetratorSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsPerpetrator("Victim"), equalTo(false));
  }

  @Test
  public void testRoleIsNotPerpetratorWhenNullSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsPerpetrator(null), equalTo(false));
  }

  @Test
  public void testRoleIsAnonymousReporterSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsAnonymousReporter("Anonymous Reporter"), equalTo(true));
  }

  @Test
  public void testRoleIsNotAnonymousReporterSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsAnonymousReporter("Perpetrator"), equalTo(false));
  }

  @Test
  public void testRoleIsNotAnonymousReporterWhenNullSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsAnonymousReporter(null), equalTo(false));
  }

  @Test
  public void testRoleIsReporterTypeWhenMandatedReporterSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsReporterType("Mandated Reporter"), equalTo(true));
  }

  @Test
  public void testRoleIsReporterTypeWhenNonMandatedReporterSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsReporterType("Non-mandated Reporter"), equalTo(true));
  }

  @Test
  public void testRoleIsNotReporterTypeSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsReporterType("Anonymous Reporter"), equalTo(false));
  }

  @Test
  public void testRoleIsNotReporterTypeWhenNullSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsReporterType(null), equalTo(false));
  }

  @Test
  public void testRoleIsMandatedReporterSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsMandatedReporter("Mandated Reporter"), equalTo(true));
  }

  @Test
  public void testRoleIsNotMandatedReporterSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsMandatedReporter("Perpetrator"), equalTo(false));
  }

  @Test
  public void testRoleIsNotMandatedReporterWhenNullSuccess() throws Exception {
    assertThat(ParticipantValidator.roleIsMandatedReporter(null), equalTo(false));
  }


  @Test
  public void roleIsSelfReporterShouldReportTrueWhenRoleIsSelfReporter() {
    assertTrue(ParticipantValidator.roleIsAnyReporter(Role.SELF_REPORTED_ROLE.getType()));
  }

  @Test
  public void roleIsSelfReporterShouldReportFalseWhenRoleIsNotSelfReporter() {
    assertFalse(ParticipantValidator.roleIsAnyReporter("Not a self reporter"));
  }

  @Test
  public void shouldReturnFalseIfParticipantsRoleContainNull() {

    Participant participants = new ParticipantResourceBuilder()
        .setRoles(new HashSet(Arrays.asList("SomeRole", null))).createParticipant();
    ScreeningToReferral referral = new ScreeningToReferralResourceBuilder()
        .setParticipants(new HashSet(Arrays.asList(participants))).createScreeningToReferral();

    assertThat(ParticipantValidator.anonymousReporter(referral), equalTo(false));
  }

  @Test
  public void roleIsAnyReporterShouldReportTrueWhenRoleMandatedReporter() {
    assertTrue(ParticipantValidator.roleIsAnyReporter(Role.MANDATED_REPORTER_ROLE.getType()));
  }

  @Test
  public void roleIsAnyReporterShouldReportTrueWhenRoleNonMandatedReporter() {
    assertTrue(ParticipantValidator.roleIsAnyReporter(Role.NON_MANDATED_REPORTER_ROLE.getType()));
  }

  @Test
  public void roleIsAnyReporterShouldReportTrueWhenRoleAnonymousReporter() {
    assertTrue(ParticipantValidator.roleIsAnyReporter(Role.ANONYMOUS_REPORTER_ROLE.getType()));
  }

  @Test
  public void roleIsAnyReporterShouldReportTrueWhenRoleSelfReporter() {
    assertTrue(ParticipantValidator.roleIsAnyReporter(Role.SELF_REPORTED_ROLE.getType()));
  }

  @Test
  public void roleIsAnyReporterShouldReportFalseWhenRoleNotAValidReporter() {
    assertFalse(ParticipantValidator.roleIsAnyReporter("Not A reporter"));
  }

  @Test
  public void hasValidRolesShouldBeValidWhenRolesAreNull() {
    Participant participant = new ParticipantResourceBuilder().setRoles(null).createParticipant();

    assertTrue(ParticipantValidator.hasValidRoles(participant));
  }

  @Test
  public void hasValidRolesShouldBeNotBeValidWhenRolesIncludeAnonymousAndSelfReporter() {
    Set roles = new HashSet();
    roles.add(Role.ANONYMOUS_REPORTER_ROLE.getType());
    roles.add(Role.SELF_REPORTED_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();

    assertFalse(ParticipantValidator.hasValidRoles(participant));
  }

  @Test
  public void hasValidRolesShouldBeNotBeValidWhenRolesIncludeAnonymousAndVictim() {
    Set roles = new HashSet();
    roles.add(Role.ANONYMOUS_REPORTER_ROLE.getType());
    roles.add(Role.VICTIM_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();

    assertFalse(ParticipantValidator.hasValidRoles(participant));
  }

  @Test
  public void hasValidRolesShouldBeNotBeValidWhenRolesIncludeAnonymousAndMandatedReporter() {
    Set roles = new HashSet();
    roles.add(Role.ANONYMOUS_REPORTER_ROLE.getType());
    roles.add(Role.MANDATED_REPORTER_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();

    assertFalse(ParticipantValidator.hasValidRoles(participant));
  }

  @Test
  public void hasValidRolesShouldBeNotBeValidWhenRolesIncludeVictimAndPerpetrator() {
    Set roles = new HashSet();
    roles.add(Role.VICTIM_ROLE.getType());
    roles.add(Role.PERPETRATOR_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();

    assertFalse(ParticipantValidator.hasValidRoles(participant));
  }

  @Test
  public void hasValidRolesShouldBeNotBeValidWhenRolesIncludeMandatedAndNonMandatedRole() {
    Set roles = new HashSet();
    roles.add(Role.MANDATED_REPORTER_ROLE.getType());
    roles.add(Role.NON_MANDATED_REPORTER_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();

    assertFalse(ParticipantValidator.hasValidRoles(participant));
  }

  @Test
  public void hasMandatedReporterRoleValidScenario() {
    Set<String> roles = new HashSet<String>();
    roles.add(Role.MANDATED_REPORTER_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();

    assertTrue(ParticipantValidator.hasMandatedReporterRole(participant));
  }

  @Test
  public void hasMandatedReporterRoleInValidScenario() {
    Set<String> roles = new HashSet<String>();
    roles.add(Role.VICTIM_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();

    assertFalse(ParticipantValidator.hasMandatedReporterRole(participant));
  }


  @Test
  public void hasMandatedReporterRoleSet() {
    Set<Participant> participants = new HashSet<Participant>();
    Set<String> roles = new HashSet<String>();
    roles.add(Role.MANDATED_REPORTER_ROLE.getType());
    Participant participant = new ParticipantResourceBuilder().setRoles(roles).createParticipant();
    participants.add(participant);

    assertTrue(ParticipantValidator.hasMandatedReporterRole(participants));
  }
}
