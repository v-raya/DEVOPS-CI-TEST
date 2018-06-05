package gov.ca.cwds.rest.services.cms;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.ca.cwds.data.cms.ReporterDao;
import gov.ca.cwds.fixture.ReporterResourceBuilder;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.cms.PostedReporter;
import gov.ca.cwds.rest.api.domain.cms.Reporter;
import gov.ca.cwds.rest.filters.TestingRequestExecutionContext;
import gov.ca.cwds.rest.services.ServiceException;
import gov.ca.cwds.rest.services.referentialintegrity.RIReporter;
import io.dropwizard.jackson.Jackson;

/**
 * @author CWDS API Team
 *
 */
public class ReporterServiceTest {
  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
  private ReporterService reporterService;
  private ReporterDao reporterDao;
  private RIReporter riReporter;

  @SuppressWarnings("javadoc")
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @SuppressWarnings("javadoc")
  @Before
  public void setup() throws Exception {
    new TestingRequestExecutionContext("0X5");
    reporterDao = mock(ReporterDao.class);
    riReporter = mock(RIReporter.class);
    reporterService = new ReporterService(reporterDao, riReporter);
  }

  @SuppressWarnings("javadoc")
  @Test
  public void findReturnsNullWhenNotFound() throws Exception {
    Response found = reporterService.find("ABC1234567");
    assertThat(found, is(nullValue()));
  }

  // delete test
  @SuppressWarnings("javadoc")
  @Test
  public void deleteDelegatesToCrudsService() {
    reporterService.delete("ABC2345678");
    verify(reporterDao, times(1)).delete("ABC2345678");
  }

  @SuppressWarnings("javadoc")
  @Test
  public void deleteReturnsNullWhenNotFount() throws Exception {
    Response found = reporterService.delete("ABC1234567");
    assertThat(found, is(nullValue()));
  }

  @SuppressWarnings("javadoc")
  @Test
  public void reporterServiceDeleteReturnsNotNull() throws Exception {
    String referralId = "AbiQCgu0Hj";
    Reporter expected = MAPPER
        .readValue(fixture("fixtures/domain/legacy/Reporter/valid.json"), Reporter.class);
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(expected, "0Hj", new Date());

    when(reporterDao.delete(referralId)).thenReturn(reporter);
    Reporter found = reporterService.delete(referralId);
    assertThat(found.getReferralId(), is(equalTo("AbiQCgu0Hj")));
  }

  // update test
  @SuppressWarnings("javadoc")
  @Test
  public void updateReturnsReporterResponseOnSuccess() throws Exception {
    Reporter expected = MAPPER
        .readValue(fixture("fixtures/domain/legacy/Reporter/valid.json"), Reporter.class);

    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(expected, "ABC", new Date());

    when(reporterDao.find("ABC1234567")).thenReturn(reporter);
    when(reporterDao.update(any())).thenReturn(reporter);

    Object retval = reporterService.update("ABC1234567", expected);
    assertThat(retval.getClass(), is(Reporter.class));
  }

  @SuppressWarnings("javadoc")
  @Test
  public void updateThrowsExceptionWhenReporterNotFound() throws Exception {

    try {
      Reporter reporterRequest = MAPPER
          .readValue(fixture("fixtures/domain/legacy/Reporter/valid.json"), Reporter.class);

      when(reporterDao.update(any())).thenThrow(EntityNotFoundException.class);

      reporterService.update("ZZZZZZZZZZ", reporterRequest);
      Assert.fail("Expected ServiceException was not thrown");
    } catch (Exception ex) {
      assertEquals(ex.getClass(), ServiceException.class);
    }
  }

  // create test
  @SuppressWarnings("javadoc")
  @Test
  public void reporterServiceCreateThrowsEntityExistsException() throws Exception {
    try {
      Reporter reporterRequest = MAPPER
          .readValue(fixture("fixtures/domain/legacy/Reporter/valid.json"), Reporter.class);

      when(reporterDao.create(any())).thenThrow(EntityExistsException.class);

      reporterService.create(reporterRequest);
    } catch (Exception e) {
      assertEquals(e.getClass(), ServiceException.class);
    }
  }

  @SuppressWarnings("javadoc")
  @Test
  public void createReturnsPostedReporterClass() throws Exception {
    Reporter reporterDomain = MAPPER
        .readValue(fixture("fixtures/domain/legacy/Reporter/valid.json"), Reporter.class);
    gov.ca.cwds.data.persistence.cms.Reporter toCreate =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "ABC", new Date());

    Reporter request = new Reporter(toCreate);

    when(reporterDao.create(any(gov.ca.cwds.data.persistence.cms.Reporter.class)))
        .thenReturn(toCreate);

    Response response = reporterService.create(request);

    assertThat(response.getClass(), is(PostedReporter.class));
  }

  @SuppressWarnings("javadoc")
  @Test
  public void createReturnsNonNull() throws Exception {
    Reporter reporterDomain = MAPPER
        .readValue(fixture("fixtures/domain/legacy/Reporter/valid.json"), Reporter.class);
    gov.ca.cwds.data.persistence.cms.Reporter toCreate =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "ABC", new Date());

    Reporter request = new Reporter(toCreate);

    when(reporterDao.create(any(gov.ca.cwds.data.persistence.cms.Reporter.class)))
        .thenReturn(toCreate);

    PostedReporter postedReporter = reporterService.create(request);

    assertThat(postedReporter, is(notNullValue()));
  }

  @SuppressWarnings("javadoc")
  @Test
  public void createReturnsCorrectPostedPerson() throws Exception {
    Reporter reporterDomain = MAPPER
        .readValue(fixture("fixtures/domain/legacy/Reporter/valid.json"), Reporter.class);
    gov.ca.cwds.data.persistence.cms.Reporter toCreate =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "ABC", new Date());

    Reporter request = new Reporter(toCreate);

    when(reporterDao.create(any(gov.ca.cwds.data.persistence.cms.Reporter.class)))
        .thenReturn(toCreate);

    PostedReporter expected = new PostedReporter(toCreate);

    PostedReporter returned = reporterService.create(request);

    assertThat(returned, is(expected));
  }

  @SuppressWarnings("javadoc")
  @Test
  public void failsWhenPostedReporterServiceEmpty() throws Exception {
    try {
      Reporter reporterDomain = new ReporterResourceBuilder().setReferralId("").build();
      gov.ca.cwds.data.persistence.cms.Reporter toCreate =
          new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "ABC", new Date());

      when(reporterDao.create(any(gov.ca.cwds.data.persistence.cms.Reporter.class)))
          .thenReturn(toCreate);

      PostedReporter expected = new PostedReporter(toCreate);
      Assert.fail("Expected ServiceException was not thrown");
    } catch (ServiceException e) {
      assertEquals("Referral ID cannot be empty for Reporter", e.getMessage());
    }

  }

  @SuppressWarnings("javadoc")
  @Test
  public void failsWhenPostedReporterServiceNull() throws Exception {
    try {
      Reporter reporterDomain = new ReporterResourceBuilder().setReferralId(null).build();
      gov.ca.cwds.data.persistence.cms.Reporter toCreate =
          new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "ABC", new Date());

      when(reporterDao.create(any(gov.ca.cwds.data.persistence.cms.Reporter.class)))
          .thenReturn(toCreate);
      PostedReporter expected = new PostedReporter(toCreate);
      Assert.fail("Expected ServiceException was not thrown");


    } catch (ServiceException e) {
      assertEquals("Reporter ID cannot be empty", e.getMessage());
    } catch (NullPointerException ex) {
      assertEquals(ex.getClass(), NullPointerException.class);
    }

  }
}
