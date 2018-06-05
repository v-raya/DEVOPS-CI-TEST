package gov.ca.cwds.rest.util;

import java.security.SecureRandom;

import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;

/**
 * A holder class for generating identifiers. A real implementation is in the works. See user story
 * #128886979.
 * 
 * @author CWDS API Team
 * @see CmsKeyIdGenerator
 */
public class IdGenerator {

  static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  static SecureRandom rnd = new SecureRandom();

  private IdGenerator() {
    // Default no-op
  }

  public static String randomString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++)
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    return sb.toString();
  }

}
