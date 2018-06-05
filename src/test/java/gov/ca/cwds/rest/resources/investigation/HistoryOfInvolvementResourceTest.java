package gov.ca.cwds.rest.resources.investigation;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.MediaType;

import org.hamcrest.junit.ExpectedException;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import gov.ca.cwds.data.cms.TestSystemCodeCache;
import gov.ca.cwds.rest.api.domain.hoi.InvolvementHistory;
import gov.ca.cwds.rest.resources.ServiceBackedResourceDelegate;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import gov.ca.cwds.rest.resources.cms.JerseyGuiceRule;
import io.dropwizard.testing.junit.ResourceTestRule;

/****
 * NOTE:The CWDS API Team has taken the pattern of delegating Resource functions to
 * 
 * {@link ServiceBackedResourceDelegate}. As such the tests in here reflect that assumption.
 *
 * @author CWDS API Team
 */
@SuppressWarnings("javadoc")
public class HistoryOfInvolvementResourceTest {

  private static final String ROOT_RESOURCE = "/investigations/";

  @ClassRule
  public static JerseyGuiceRule rule = new JerseyGuiceRule();

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @SuppressWarnings("unchecked")
  private final static TypedResourceDelegate<String, InvolvementHistory> typedResourceDelegate =
      mock(TypedResourceDelegate.class);

  @ClassRule
  public final static ResourceTestRule inMemoryResource = ResourceTestRule.builder()
      .addResource(new HistoryOfInvolvementResource(typedResourceDelegate)).build();
  /*
   * Load system code cache
   */
  TestSystemCodeCache testSystemCodeCache = new TestSystemCodeCache();

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findDelegatesToResourceDelegate() throws Exception {

    inMemoryResource.client().target(ROOT_RESOURCE + "1/history_of_involvements").request()
        .accept(MediaType.APPLICATION_JSON).get().getStatus();
    verify(typedResourceDelegate, atLeastOnce()).get("1");
  }

}
