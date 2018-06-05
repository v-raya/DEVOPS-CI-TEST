package gov.ca.cwds.rest.services.referentialintegrity;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import gov.ca.cwds.data.cms.DrmsDocumentDao;
import gov.ca.cwds.data.cms.LawEnforcementDao;
import gov.ca.cwds.data.cms.ReferralDao;
import gov.ca.cwds.data.persistence.cms.LawEnforcementEntity;
import gov.ca.cwds.fixture.CmsReporterResourceBuilder;
import gov.ca.cwds.fixture.DrmsDocumentResourceBuilder;
import gov.ca.cwds.fixture.LawEnforcementEntityBuilder;
import gov.ca.cwds.fixture.ReferralResourceBuilder;
import gov.ca.cwds.rest.api.domain.cms.DrmsDocument;
import gov.ca.cwds.rest.api.domain.cms.Referral;
import gov.ca.cwds.rest.api.domain.cms.Reporter;
import gov.ca.cwds.rest.validation.ReferentialIntegrityException;

/**
 * @author CWDS API Team
 *
 */
@SuppressWarnings("javadoc")
public class RIReporterTest {

  private ReferralDao referralDao;
  private LawEnforcementDao lawEnforcementDao;
  private DrmsDocumentDao drmsDocumentDao;

  @Before
  public void setup() {
    referralDao = mock(ReferralDao.class);
    lawEnforcementDao = mock(LawEnforcementDao.class);
    drmsDocumentDao = mock(DrmsDocumentDao.class);
  }

  /**
   * @throws Exception - exception
   */
  @Test
  public void type() throws Exception {
    assertThat(RIReporter.class, notNullValue());
  }

  /**
   * @throws Exception- exception
   */
  @Test
  public void instantiation() throws Exception {
    RIReporter riReporter = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    assertThat(riReporter, notNullValue());
  }

  /*
   * Test for test the referential Integrity Exception
   */
  @Test(expected = ReferentialIntegrityException.class)
  public void riCheckFailureWhenReferralIdNotFound() throws Exception {
    Reporter reporterDomain = new CmsReporterResourceBuilder().setReferralId("abpI86Te1V").build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    when(referralDao.find(any())).thenReturn(null);
    when(lawEnforcementDao.find(any())).thenReturn(null);
    when(drmsDocumentDao.find(any())).thenReturn(null);
    RIReporter target = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    target.apply(reporter);
  }

  @Test(expected = ReferentialIntegrityException.class)
  public void riCheckFailureWhenLawEnforcemntNotFound() throws Exception {
    Reporter reporterDomain = new CmsReporterResourceBuilder().setReferralId("AB0751Gthu")
        .setLawEnforcementId("lpourfGe7V").build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(null);
    when(drmsDocumentDao.find(any())).thenReturn(null);
    RIReporter target = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    target.apply(reporter);
  }

  @Test
  public void riCheckPassWhenFoundDrmsDocument() throws Exception {
    Reporter reporterDomain =
        new CmsReporterResourceBuilder().setDrmsMandatedRprtrFeedback("ABC1234lll").build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    DrmsDocument drmsDocumentDomain = new DrmsDocumentResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.DrmsDocument drmsDocument =
        new gov.ca.cwds.data.persistence.cms.DrmsDocument("ABC1234lll", drmsDocumentDomain, "0X5",
            new Date());

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(null);
    when(drmsDocumentDao.find(any())).thenReturn(drmsDocument);
    RIReporter riReporter = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    riReporter.apply(reporter);
  }

  @Test
  public void riCheckPassWhenFoundLawEnforcement() throws Exception {
    Reporter reporterDomain =
        new CmsReporterResourceBuilder().setLawEnforcementId("lpourfGe7V").build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    LawEnforcementEntity lawEnforcemnt = new LawEnforcementEntityBuilder().build();

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(lawEnforcemnt);
    when(drmsDocumentDao.find(any())).thenReturn(null);
    RIReporter riReporter = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    riReporter.apply(reporter);
  }

  @Test
  public void riCheckPassWhenLawEnforcemntNull() throws Exception {
    Reporter reporterDomain = new CmsReporterResourceBuilder().setLawEnforcementId(null).build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    DrmsDocument drmsDocumentDomain = new DrmsDocumentResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.DrmsDocument drmsDocument =
        new gov.ca.cwds.data.persistence.cms.DrmsDocument("ABC1234lll", drmsDocumentDomain, "0X5",
            new Date());

    LawEnforcementEntity lawEnforcemnt = new LawEnforcementEntityBuilder().build();

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(lawEnforcemnt);
    when(drmsDocumentDao.find(any())).thenReturn(drmsDocument);
    RIReporter riReporter = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    riReporter.apply(reporter);
  }

  @Test
  public void riCheckPassWhenLawEnforcemntEmpty() throws Exception {
    Reporter reporterDomain = new CmsReporterResourceBuilder().setLawEnforcementId("").build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    DrmsDocument drmsDocumentDomain = new DrmsDocumentResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.DrmsDocument drmsDocument =
        new gov.ca.cwds.data.persistence.cms.DrmsDocument("ABC1234lll", drmsDocumentDomain, "0X5",
            new Date());

    LawEnforcementEntity lawEnforcemnt = new LawEnforcementEntityBuilder().build();

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(lawEnforcemnt);
    when(drmsDocumentDao.find(any())).thenReturn(drmsDocument);
    RIReporter riReporter = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    riReporter.apply(reporter);
  }

  @Test
  public void riCheckPassWhenDrmsDocReporterNull() throws Exception {
    Reporter reporterDomain = new CmsReporterResourceBuilder().setLawEnforcementId("ABC1234lll")
        .setDrmsMandatedRprtrFeedback(null).build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    DrmsDocument drmsDocumentDomain = new DrmsDocumentResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.DrmsDocument drmsDocument =
        new gov.ca.cwds.data.persistence.cms.DrmsDocument("ABC1234lll", drmsDocumentDomain, "0X5",
            new Date());

    LawEnforcementEntity lawEnforcemnt = new LawEnforcementEntityBuilder().build();

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(lawEnforcemnt);
    when(drmsDocumentDao.find(any())).thenReturn(drmsDocument);
    RIReporter riReporter = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    riReporter.apply(reporter);
  }

  @Test
  public void riCheckPassWhenDrmsDocReporterEmpty() throws Exception {
    Reporter reporterDomain = new CmsReporterResourceBuilder().setLawEnforcementId("ABC1234lll")
        .setDrmsMandatedRprtrFeedback("").build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    DrmsDocument drmsDocumentDomain = new DrmsDocumentResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.DrmsDocument drmsDocument =
        new gov.ca.cwds.data.persistence.cms.DrmsDocument("ABC1234lll", drmsDocumentDomain, "0X5",
            new Date());

    LawEnforcementEntity lawEnforcemnt = new LawEnforcementEntityBuilder().build();

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(lawEnforcemnt);
    when(drmsDocumentDao.find(any())).thenReturn(drmsDocument);
    RIReporter riReporter = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    riReporter.apply(reporter);
  }

  @Test(expected = ReferentialIntegrityException.class)
  public void riCheckFailureWhenDrmsDocReporterNotFound() throws Exception {
    Reporter reporterDomain =
        new CmsReporterResourceBuilder().setDrmsMandatedRprtrFeedback("ABC1234lll").build();
    gov.ca.cwds.data.persistence.cms.Reporter reporter =
        new gov.ca.cwds.data.persistence.cms.Reporter(reporterDomain, "0X5", new Date());

    Referral referralDomain = new ReferralResourceBuilder().build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("AB0751Gthu", referralDomain, "0X5");

    when(referralDao.find(any())).thenReturn(referral);
    when(lawEnforcementDao.find(any())).thenReturn(null);
    when(drmsDocumentDao.find(any())).thenReturn(null);
    RIReporter target = new RIReporter(referralDao, lawEnforcementDao, drmsDocumentDao);
    target.apply(reporter);
  }

}
