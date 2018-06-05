package gov.ca.cwds.rest.business.rules;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import gov.ca.cwds.data.cms.CountyOwnershipDao;
import gov.ca.cwds.data.cms.ReferralClientDao;
import gov.ca.cwds.data.cms.ReferralDao;
import gov.ca.cwds.data.persistence.cms.Client;
import gov.ca.cwds.data.persistence.cms.ClientAddress;
import gov.ca.cwds.data.persistence.cms.CountyOwnership;
import gov.ca.cwds.data.persistence.cms.ReferralClient.PrimaryKey;
import gov.ca.cwds.data.rules.TriggerTableException;
import gov.ca.cwds.fixture.AssignmentResourceBuilder;
import gov.ca.cwds.fixture.ClientAddressResourceBuilder;
import gov.ca.cwds.fixture.ClientResourceBuilder;
import gov.ca.cwds.fixture.ReferralClientResourceBuilder;
import gov.ca.cwds.fixture.ReferralResourceBuilder;
import gov.ca.cwds.rest.api.domain.cms.Assignment;
import gov.ca.cwds.rest.api.domain.cms.Referral;
import gov.ca.cwds.rest.api.domain.cms.ReferralClient;

/**
 * @author CWDS API Team
 *
 */
public class NonLACountyTriggersTest {

  private CountyOwnershipDao countyOwnershipDao;
  private ReferralDao referralDao;
  private ReferralClientDao referralClientDao;
  private NonLACountyTriggers nonLaCountyTriggers;

  private static CountyOwnership countyOwnership;
  private Date lastUpdatedTime = new Date();

  @SuppressWarnings("javadoc")
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * @throws Exception general test error
   */
  @Before
  public void setup() throws Exception {
    countyOwnershipDao = mock(CountyOwnershipDao.class);
    referralDao = mock(ReferralDao.class);
    referralClientDao = mock(ReferralClientDao.class);
    nonLaCountyTriggers =
        new NonLACountyTriggers(countyOwnershipDao, referralDao, referralClientDao);
    countyOwnership = null;
  }

  /**
   * Test for checking the referral Client CountyOnwership created or updated through the PUT method
   * 
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testChecksReferralClientCreatedCountyOwnership() throws Exception {
    ReferralClient referralClientDomain =
        new ReferralClientResourceBuilder().setCountySpecificCode("54").buildReferralClient();

    gov.ca.cwds.data.persistence.cms.ReferralClient toCreate =
        new gov.ca.cwds.data.persistence.cms.ReferralClient(referralClientDomain, "ABC",
            new Date());

    when(countyOwnershipDao.find(any(String.class))).thenReturn(null);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralClientCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getEntityCode(), is(equalTo("C")));
  }

  /**
   * Test for checking the referral Client CountyOnwership create throws exception
   * 
   * @throws Exception - TriggerTableException
   */
  @Test(expected = TriggerTableException.class)
  public void testChecksReferralClientCreatedCountyOwnershipThrowsTriggerTableException()
      throws Exception {
    ReferralClient referralClientDomain =
        new ReferralClientResourceBuilder().setCountySpecificCode("100").buildReferralClient();

    gov.ca.cwds.data.persistence.cms.ReferralClient toCreate =
        new gov.ca.cwds.data.persistence.cms.ReferralClient(referralClientDomain, "ABC",
            new Date());

    when(countyOwnershipDao.find(any(String.class))).thenReturn(null);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralClientCoutyOwnership(toCreate);
  }

  /**
   * Test for checking the referral Client CountyOnwership updated
   * 
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testForReferralClientUpdatedCountyOwnership() throws Exception {
    ReferralClient referralClientDomain =
        new ReferralClientResourceBuilder().setCountySpecificCode("55").buildReferralClient();

    gov.ca.cwds.data.persistence.cms.ReferralClient toCreate =
        new gov.ca.cwds.data.persistence.cms.ReferralClient(referralClientDomain, "ABC",
            new Date());

    when(countyOwnershipDao.find(any(String.class))).thenReturn(new CountyOwnership());
    when(countyOwnershipDao.update(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralClientCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getCounty55Flag(), is(equalTo("Y")));
  }

  /**
   * Test for checking the Client CountyOnwership created
   * 
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testForClientCreatedCountyOwnership() throws Exception {
    gov.ca.cwds.rest.api.domain.cms.Client ClientDomain = new ClientResourceBuilder().build();

    Client toCreate = new Client("ABC1234567", ClientDomain, "ABC", new Date());
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createClientCountyTrigger(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getEntityCode(), is(equalTo("C")));
  }

  /**
   * Test for ClientAddress CountyOwnership created
   * 
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testForClientAddressCreatedCountyOwnership() throws Exception {

    gov.ca.cwds.rest.api.domain.cms.ClientAddress clientAddressDomain =
        new ClientAddressResourceBuilder().buildClientAddress();
    ClientAddress toCreate =
        new ClientAddress("ABC1234567", clientAddressDomain, "ABC", new Date());

    ReferralClient referralClientDomain =
        new ReferralClientResourceBuilder().setCountySpecificCode("55").buildReferralClient();
    gov.ca.cwds.data.persistence.cms.ReferralClient referralClient =
        new gov.ca.cwds.data.persistence.cms.ReferralClient(referralClientDomain, "ABC",
            new Date());

    when(referralClientDao.find(any(PrimaryKey.class))).thenReturn(referralClient);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateClientAddressCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getEntityCode(), is(equalTo("A")));
    assertThat(countyOwnership.getCounty55Flag(), is(equalTo("Y")));
  }


  /**
   * Test for clientAddress CountyOwnership creation throws TriggerTableException
   * 
   * @throws Exception - TriggerTableException
   */
  @Test(expected = TriggerTableException.class)
  public void testForClientAddressCreatedCountyOwnershipThrowsTriggerTableException()
      throws Exception {

    gov.ca.cwds.rest.api.domain.cms.ClientAddress clientAddressDomain =
        new ClientAddressResourceBuilder().buildClientAddress();
    ClientAddress toCreate =
        new ClientAddress("ABC1234567", clientAddressDomain, "ABC", new Date());

    ReferralClient referralClientDomain =
        new ReferralClientResourceBuilder().setCountySpecificCode("100").buildReferralClient();
    gov.ca.cwds.data.persistence.cms.ReferralClient referralClient =
        new gov.ca.cwds.data.persistence.cms.ReferralClient(referralClientDomain, "ABC",
            new Date());

    when(referralClientDao.find(any(PrimaryKey.class))).thenReturn(referralClient);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateClientAddressCoutyOwnership(toCreate);
  }

  /**
   * Test for updating the countyOwnership for the exisiting Address Id
   * 
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testForClientAddressUpdatedCountyOwnership() throws Exception {

    gov.ca.cwds.rest.api.domain.cms.ClientAddress clientAddressDomain =
        new ClientAddressResourceBuilder().buildClientAddress();
    ClientAddress toCreate =
        new ClientAddress("ABC1234567", clientAddressDomain, "ABC", new Date());

    ReferralClient referralClientDomain =
        new ReferralClientResourceBuilder().setCountySpecificCode("54").buildReferralClient();
    gov.ca.cwds.data.persistence.cms.ReferralClient referralClient =
        new gov.ca.cwds.data.persistence.cms.ReferralClient(referralClientDomain, "ABC",
            new Date());

    when(referralClientDao.find(any(PrimaryKey.class))).thenReturn(referralClient);

    when(countyOwnershipDao.find(any(String.class))).thenReturn(new CountyOwnership());
    when(countyOwnershipDao.update(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });

    nonLaCountyTriggers.createAndUpdateClientAddressCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getCounty54Flag(), is(equalTo("Y")));
  }


  /**
   * Test for Referral CountyOwnership created
   * 
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testForReferralCreatedCountyOwnership() throws Exception {

    Assignment assignmentDomain = new AssignmentResourceBuilder().buildAssignment();
    gov.ca.cwds.data.persistence.cms.Assignment toCreate =
        new gov.ca.cwds.data.persistence.cms.Assignment("ABC1234567", assignmentDomain, "ABC",
            lastUpdatedTime);

    Referral referralClientDomain =
        new ReferralResourceBuilder().setCountySpecificCode("55").build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("ABC1234567", referralClientDomain, "0X5");

    when(referralDao.find(any(String.class))).thenReturn(referral);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getEntityCode(), is(equalTo("R")));
    assertThat(countyOwnership.getCounty55Flag(), is(equalTo("Y")));
  }

  /**
   * Referral countyOwnership is not created when AssignmentCode is set to S
   * 
   * @throws Exception test general
   */
  @Test
  public void testForAssignmentCodeIsS() throws Exception {

    Assignment assignmentDomain =
        new AssignmentResourceBuilder().setTypeOfAssignmentCode("S").buildAssignment();
    gov.ca.cwds.data.persistence.cms.Assignment toCreate =
        new gov.ca.cwds.data.persistence.cms.Assignment("ABC1234567", assignmentDomain, "ABC",
            lastUpdatedTime);

    Referral referralClientDomain =
        new ReferralResourceBuilder().setCountySpecificCode("62").build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("ABC1234567", referralClientDomain, "0X5");

    when(referralDao.find(any(String.class))).thenReturn(referral);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(nullValue()));
  }

  /**
   * Referral countyOwnership is not created when assignment established is set to C
   * 
   * @throws Exception test general
   */
  @Test
  public void testForEstablishedCodeIsC() throws Exception {

    Assignment assignmentDomain =
        new AssignmentResourceBuilder().setEstablishedForCode("C").buildAssignment();
    gov.ca.cwds.data.persistence.cms.Assignment toCreate =
        new gov.ca.cwds.data.persistence.cms.Assignment("ABC1234567", assignmentDomain, "ABC",
            lastUpdatedTime);

    Referral referralClientDomain =
        new ReferralResourceBuilder().setCountySpecificCode("62").build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("ABC1234567", referralClientDomain, "0X5");

    when(referralDao.find(any(String.class))).thenReturn(referral);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(nullValue()));
  }

  /**
   * @throws Exception - exception
   */
  @Test
  public void testForReferralClientCreatedCountyOwnershipForDefault99() throws Exception {

    ReferralClient referralClientDomain =
        new ReferralClientResourceBuilder().setCountySpecificCode("99").buildReferralClient();

    gov.ca.cwds.data.persistence.cms.ReferralClient toCreate =
        new gov.ca.cwds.data.persistence.cms.ReferralClient(referralClientDomain, "ABC",
            new Date());

    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralClientCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getEntityCode(), is(equalTo("C")));
  }

  /**
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testForReferralCreatedCountyOwnershipForDefault99() throws Exception {

    Assignment assignmentDomain = new AssignmentResourceBuilder().buildAssignment();
    gov.ca.cwds.data.persistence.cms.Assignment toCreate =
        new gov.ca.cwds.data.persistence.cms.Assignment("ABC1234567", assignmentDomain, "ABC",
            lastUpdatedTime);

    Referral referralClientDomain =
        new ReferralResourceBuilder().setCountySpecificCode("99").build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("ABC1234567", referralClientDomain, "0X5");

    when(referralDao.find(any(String.class))).thenReturn(referral);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getEntityCode(), is(equalTo("R")));
  }

  /**
   * @throws Exception - TriggerTableException
   */
  @Test(expected = TriggerTableException.class)
  public void testForReferralCreatedCountyOwnershipThrowsTriggerTableException() throws Exception {

    Assignment assignmentDomain = new AssignmentResourceBuilder().buildAssignment();
    gov.ca.cwds.data.persistence.cms.Assignment toCreate =
        new gov.ca.cwds.data.persistence.cms.Assignment("ABC1234567", assignmentDomain, "ABC",
            lastUpdatedTime);

    Referral referralClientDomain =
        new ReferralResourceBuilder().setCountySpecificCode("100").build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("ABC1234567", referralClientDomain, "0X5");

    when(referralDao.find(any(String.class))).thenReturn(referral);
    when(countyOwnershipDao.create(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralCoutyOwnership(toCreate);
  }

  /**
   * Test for updating the countyOwnership for the exisiting Referral Id
   * 
   * @throws Exception - TriggerTableException
   */
  @Test
  public void testForReferralUpdatedCountyOwnership() throws Exception {

    Assignment assignmentDomain = new AssignmentResourceBuilder().buildAssignment();
    gov.ca.cwds.data.persistence.cms.Assignment toCreate =
        new gov.ca.cwds.data.persistence.cms.Assignment("ABC1234567", assignmentDomain, "ABC",
            lastUpdatedTime);

    Referral referralClientDomain =
        new ReferralResourceBuilder().setCountySpecificCode("54").build();
    gov.ca.cwds.data.persistence.cms.Referral referral =
        new gov.ca.cwds.data.persistence.cms.Referral("ABC1234567", referralClientDomain, "0X5");

    when(referralDao.find(any(String.class))).thenReturn(referral);
    when(countyOwnershipDao.find(any(String.class))).thenReturn(new CountyOwnership());
    when(countyOwnershipDao.update(any(CountyOwnership.class)))
        .thenAnswer(new Answer<CountyOwnership>() {

          @Override
          public CountyOwnership answer(InvocationOnMock invocation) throws Throwable {

            CountyOwnership report = (CountyOwnership) invocation.getArguments()[0];
            countyOwnership = report;
            return report;
          }
        });
    nonLaCountyTriggers.createAndUpdateReferralCoutyOwnership(toCreate);
    assertThat(countyOwnership, is(notNullValue()));
    assertThat(countyOwnership.getCounty54Flag(), is(equalTo("Y")));
  }

}
