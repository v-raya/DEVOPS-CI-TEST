package gov.ca.cwds.data.cms;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.hamcrest.junit.ExpectedException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;
import gov.ca.cwds.data.persistence.cms.ReferralAssignment;
import gov.ca.cwds.rest.api.domain.DomainChef;

@SuppressWarnings("javadoc")
public class ReferralAssignmentDaoIT {

  private static SessionFactory sessionFactory;
  private static ReferralAssignmentDao dao;
  private Session session;

  private String countySpecificCode = "20";
  private String endDate = "2018-06-01";
  private String endTime = "12:01:00";
  private String establishedForId = "C1wCWQ8X8G";
  private String caseLoadId = "2345678ABC";
  private String outOfStatePartyContactId = "";
  private String responsiblityDescription = "Referral Assignment responsibility description";
  private Short secondaryReferralAssignmentRoleType = 0;
  private String startDate = "2017-06-20";
  private String startTime = "16:41:49";
  private String typeOfReferralAssignmentCode = "P";
  private BigDecimal weightingNumber = new BigDecimal("0.0");
  private String staffId = "0X5";

  /**
   * id matches src/main/resources/db.cms/ci-seeds.sql
   */
  private String id = "SlCAr46088";

  /**
   * 
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * 
   */
  @BeforeClass
  public static void beforeClass() {
    sessionFactory = new Configuration().configure().buildSessionFactory();
    dao = new ReferralAssignmentDao(sessionFactory);
  }

  /**
   * 
   */
  @AfterClass
  public static void afterClass() {
    sessionFactory.close();
  }

  @Before
  public void setup() {
    session = sessionFactory.getCurrentSession();
    session.beginTransaction();
  }

  @After
  public void teardown() {
    session.getTransaction().rollback();
  }

  @Test
  public void testFind() throws Exception {
    ReferralAssignment found = dao.find(id);
    assertThat(found.getId(), is(equalTo(id)));
  }

  @Test
  public void testFindEntityNotFoundException() throws Exception {
    ReferralAssignment found = dao.find("xxxxxyzuk3");
    assertThat(found, is(nullValue()));
  }

  @Test
  public void testCreate() throws Exception {
    ReferralAssignment pa = new ReferralAssignment(countySpecificCode,
        DomainChef.uncookDateString(endDate), DomainChef.uncookTimeString(endTime),
        establishedForId, caseLoadId, outOfStatePartyContactId, responsiblityDescription,
        secondaryReferralAssignmentRoleType, DomainChef.uncookDateString(startDate),
        DomainChef.uncookTimeString(startTime), typeOfReferralAssignmentCode, weightingNumber);

    pa.setId(CmsKeyIdGenerator.getNextValue(staffId));

    ReferralAssignment create = dao.create(pa);
    assertThat(pa, is(create));
  }

  @Test(expected = EntityExistsException.class)
  public void testCreateExistingEntityException() throws Exception {
    ReferralAssignment pa = new ReferralAssignment(countySpecificCode,
        DomainChef.uncookDateString(endDate), DomainChef.uncookTimeString(endTime),
        establishedForId, caseLoadId, outOfStatePartyContactId, responsiblityDescription,
        secondaryReferralAssignmentRoleType, DomainChef.uncookDateString(startDate),
        DomainChef.uncookTimeString(startTime), typeOfReferralAssignmentCode, weightingNumber);

    pa.setId(id);
    dao.create(pa);

  }

  /**
   * Delete JUnit test
   * 
   * @throws Exception test general
   */
  @Test
  public void testDelete() throws Exception {
    ReferralAssignment deleted = dao.delete(id);
    assertThat(deleted.getId(), is(id));
  }

  @Test
  public void testDeleteEntityNotFoundException() throws Exception {
    ReferralAssignment deleted = dao.delete("Gxqddjyqwd");
    assertThat(deleted, is(nullValue()));
  }

  /**
   * Update JUnit test
   * 
   * @throws Exception test general
   */
  @Test
  public void testUpdate() throws Exception {
    ReferralAssignment pa = new ReferralAssignment(countySpecificCode,
        DomainChef.uncookDateString(endDate), DomainChef.uncookTimeString(endTime),
        establishedForId, caseLoadId, outOfStatePartyContactId, responsiblityDescription,
        secondaryReferralAssignmentRoleType, DomainChef.uncookDateString(startDate),
        DomainChef.uncookTimeString(startTime), typeOfReferralAssignmentCode, weightingNumber);

    pa.setId(id);

    ReferralAssignment update = dao.update(pa);
    assertThat(pa, is(update));
  }

  @Test(expected = EntityNotFoundException.class)
  public void testUpdateEntityNotFoundException() throws Exception {
    ReferralAssignment pa = new ReferralAssignment(countySpecificCode,
        DomainChef.uncookDateString(endDate), DomainChef.uncookTimeString(endTime),
        establishedForId, caseLoadId, outOfStatePartyContactId, responsiblityDescription,
        secondaryReferralAssignmentRoleType, DomainChef.uncookDateString(startDate),
        DomainChef.uncookTimeString(startTime), typeOfReferralAssignmentCode, weightingNumber);

    pa.setId("hjkbacecF");

    dao.update(pa);
  }

}
