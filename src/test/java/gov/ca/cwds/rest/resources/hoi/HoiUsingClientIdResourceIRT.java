package gov.ca.cwds.rest.resources.hoi;

import static gov.ca.cwds.rest.core.Api.RESOURCE_CLIENT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import gov.ca.cwds.rest.api.domain.hoi.HOIScreening;
import gov.ca.cwds.rest.api.domain.hoi.InvolvementHistory;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class HoiUsingClientIdResourceIRT extends HOIBaseTest {

  @Test
  public void testGet() throws Exception {
    final InvolvementHistory expectedInvolvementHistory = getExpectedInvolvementHistory();
    final List<HOIScreening> expectedHOIScreenings = getExpectedHOIScreenings(
        expectedInvolvementHistory);

    final String actualJson = doGet();
    final InvolvementHistory actualInvolvementHistory = objectMapper
        .readValue(actualJson.getBytes(), InvolvementHistory.class);

    assertNull(actualInvolvementHistory.getId());
    assertEquals(expectedInvolvementHistory.getCases(), actualInvolvementHistory.getCases());
    assertEquals(expectedInvolvementHistory.getReferrals(),
        actualInvolvementHistory.getReferrals());
    assertEquals(expectedHOIScreenings, actualInvolvementHistory.getScreenings());

    assertHOICasesAreSorted(new String[]{"Co8uaDi0DW", "IdQImWo0DW"},
        actualInvolvementHistory.getCases());
    assertHOIReferralsAreSorted(new String[]{"MYsSPHW0DW", "9OQhOAE0DW"},
        actualInvolvementHistory.getReferrals());
    assertHOIScreeningsAreSorted(new String[]{"750", "885", "862", "714"},
        actualInvolvementHistory.getScreenings());
  }

  private String doGet() throws Exception {
    WebTarget target = clientTestRule.target(RESOURCE_CLIENT + "/history_of_involvements");
    Response response = target.queryParam("clientIds", "5DK5THO0DW", "SFpVhtC0DW").request()
        .accept(MediaType.APPLICATION_JSON).get();
    return IOUtils.toString((InputStream) response.getEntity(), StandardCharsets.UTF_8);
  }
}
