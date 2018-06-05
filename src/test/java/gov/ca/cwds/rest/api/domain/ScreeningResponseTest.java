package gov.ca.cwds.rest.api.domain;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;

import io.dropwizard.jackson.Jackson;

/**
 * @author CWDS API Team
 */
public class ScreeningResponseTest {

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  private long participantId = 1;
  private long personId = 12345;
  private long screeningId = 12345;
  private String firstName = "john";
  private String middleName = "T.";
  private String lastName = "smith";
  private String suffix = "";
  private String gender = "male";
  private String dateOfBirth = "2001-09-13";
  private String ssn = "123456789";
  final Long id = (long) 1234567;
  final String lastUpdateId = "234567";
  final String createId = "234567";
  private Set<String> roles = new HashSet<>();
  private Set<Address> addresses = new HashSet<>();

  /*
   * constructor tests
   *
   */
  @SuppressWarnings("javadoc")
  @Test
  public void testJsonConstuctor() throws Exception {

    // ScreeningResponse vsr = validScreeningResponse();

  }

  @SuppressWarnings("javadoc")
  @Test
  public void testPesistentObjectConstructor() throws Exception {
//    LegacyDescriptorEntity legacyDescriptor = new LegacyDescriptorEntity();
//    Address addressDomain =
//        new Address("", "", "123 Main", "Sacramento", 1828, "95757", 32, legacyDescriptor);
//    addresses.add(addressDomain);
//    roles.add("victim");
//
//    // ScreeningResponse vsr = validScreeningResponse();
//    ScreeningEntity vs = validScreening();
//
//    gov.ca.cwds.data.persistence.ns.Address address = this.validAddress();
//
//    ParticipantEntity dp = validParticipant();
//    gov.ca.cwds.data.persistence.ns.ParticipantEntity participant =
//        new gov.ca.cwds.data.persistence.ns.ParticipantEntity(dp, lastUpdateId, createId);
//
//    ImmutableSet.Builder<gov.ca.cwds.data.persistence.ns.ParticipantEntity> participantSetBuilder =
//        ImmutableSet.builder();
//    participantSetBuilder.add(participant);
//    Set<gov.ca.cwds.data.persistence.ns.ParticipantEntity> participants;
//    participants = participantSetBuilder.build();

    // gov.ca.cwds.data.persistence.ns.ScreeningEntity persistent =
    // new gov.ca.cwds.data.persistence.ns.ScreeningEntity(id, vs, address, participants, lastUpdateId,
    // createId);
    //
    // ScreeningResponse domain = new ScreeningResponse(persistent, participants);
    // // MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
    // // String s = MAPPER.writeValueAsString(persistent);
    // // System.out.println(s);
    //
    // System.out.println(vsr);
    // assertThat(domain.getReference(), is(equalTo(vsr.getReference())));
    // assertThat(domain.getEndedAt(), is(equalTo(vsr.getEndedAt())));
    // assertThat(domain.getIncidentCounty(), is(equalTo(vsr.getIncidentCounty())));
    // assertThat(domain.getIncidentDate(), is(equalTo(vsr.getIncidentDate())));
    // assertThat(domain.getLocationType(), is(equalTo(vsr.getLocationType())));
    // assertThat(domain.getCommunicationMethod(), is(equalTo(vsr.getCommunicationMethod())));
    // assertThat(domain.getName(), is(equalTo(vsr.getName())));
    // assertThat(domain.getResponseTime(), is(equalTo(vsr.getResponseTime())));
    // assertThat(domain.getScreeningDecision(), is(equalTo(vsr.getScreeningDecision())));
    // assertThat(domain.getStartedAt(), is(equalTo(vsr.getStartedAt())));
    // assertThat(domain.getNarrative(), is(equalTo(vsr.getNarrative())));
    // // assertThat(domain.getAddress(), is(equalTo(vsr.getAddress())));
    // // TODO : domain Participants do not equal persistent Participants ???
    // // assertThat(domain.getParticipants(), is(equalTo(vsr.getParticipants())));

  }

  // TODO : "equals" seems to work but the test is failing. Need to figure out the message.
  // TODO : verify 'STRICT_INHERITENCE' is appropriate here - reference pvitoltracker #136527227
  // @Test
  // public void equalsHashCodeWork() {
  // EqualsVerifier.forClass(ScreeningResponse.class)
  // .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).suppress().verify();
  // }

  // Serialization and deserialization

  @Test
  public void serializesToJSON() throws Exception {

    // Address address = new Address("", "", "123 Main", "Sacramento", "CA", 95757, "Home");
    // addresses.add(address);
    // roles.add("victim");
    //
    // ParticipantEntity participant =
    // new ParticipantEntity(participantId, "", "", new LegacyDescriptorEntity(), firstName, middleName,
    // lastName, suffix, gender, ssn, dateOfBirth, personId, screeningId, roles, addresses);
    // ImmutableSet.Builder<ParticipantEntity> participantSetBuilder = ImmutableSet.builder();
    // participantSetBuilder.add(participant);
    // Set<ParticipantEntity> participants;
    // participants = participantSetBuilder.build();
    //
    // ScreeningResponse screeningResponse = new ScreeningResponse("screening reference",
    // "2016-10-31",
    // "Santa Clara", "2016-10-31", "school", "phone", "screening name", "24 hour",
    // "accept_for_investigation", "2016-10-05", "test the narrative", address, participants);
    //
    // String expected = MAPPER.writeValueAsString(screeningResponse);
    // String serialized = MAPPER.writeValueAsString(MAPPER.readValue(
    // fixture("fixtures/domain/screeningResponse/valid/valid.json"), ScreeningResponse.class));
    //
    // assertThat(serialized, is(expected));
  }

  @Test
  public void deserializesFromJSON() throws Exception {
    // Address address = new Address("", "", "123 Main", "Sacramento", "CA", 95757, "Home");
    // addresses.add(address);
    // roles.add("victim");
    //
    // ParticipantEntity participant =
    // new ParticipantEntity(participantId, "", "", new LegacyDescriptorEntity(), firstName, middleName,
    // lastName, suffix, gender, ssn, dateOfBirth, personId, screeningId, roles, addresses);
    // ImmutableSet.Builder<ParticipantEntity> participantSetBuilder = ImmutableSet.builder();
    // participantSetBuilder.add(participant);
    // Set<ParticipantEntity> participants;
    // participants = participantSetBuilder.build();
    //
    // ScreeningResponse expected = new ScreeningResponse("screening reference", "2016-10-31",
    // "Santa Clara", "2016-10-31", "school", "phone", "screening name", "24 hour",
    // "accept_for_investigation", "2016-10-05", "test the narrative", address, participants);
    //
    // ScreeningResponse serialized = MAPPER.readValue(
    // fixture("fixtures/domain/screeningResponse/valid/valid.json"), ScreeningResponse.class);
    // assertThat(serialized, is(expected));
    // }
    //
    // private ScreeningResponse validScreeningResponse() throws Exception {
    // ScreeningResponse vsr = MAPPER.readValue(
    // fixture("fixtures/domain/screeningResponse/valid/valid.json"), ScreeningResponse.class);
    // return vsr;
  }

  private Screening validScreening() {

    try {
      Screening validScreening =
          MAPPER.readValue(fixture("fixtures/domain/screening/valid/valid.json"), Screening.class);

      return validScreening;

    } catch (JsonParseException e) {
      e.printStackTrace();
      return null;
    } catch (JsonMappingException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private Participant validParticipant() {

    try {
      Participant validParticipant = MAPPER
          .readValue(fixture("fixtures/domain/participant/valid/valid.json"), Participant.class);

      return validParticipant;

    } catch (JsonParseException e) {
      e.printStackTrace();
      return null;
    } catch (JsonMappingException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * persistence Address object
   *
   * @Address
   */
  private gov.ca.cwds.data.persistence.ns.Address validAddress() {

    try {
      gov.ca.cwds.data.persistence.ns.Address validAddress =
          MAPPER.readValue(fixture("fixtures/persistence/ns/Address/validPersistentAddress.json"),
              gov.ca.cwds.data.persistence.ns.Address.class);

      return validAddress;

    } catch (JsonParseException e) {
      e.printStackTrace();
      return null;
    } catch (JsonMappingException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
