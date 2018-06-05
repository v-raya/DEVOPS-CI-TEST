package gov.ca.cwds.rest.validation;

import java.util.HashSet;
import java.util.Set;

import gov.ca.cwds.rest.api.domain.Participant;
import gov.ca.cwds.rest.api.domain.Role;
import gov.ca.cwds.rest.api.domain.ScreeningToReferral;
import gov.ca.cwds.rest.services.ServiceException;

/**
 * @author CWDS API Team
 */
public class ParticipantValidator {


  /**
   * CWS/CMS Referral must have on reporter
   */
  public static final int ALLOWED_NUMBER_OF_REPORTERS = 1;

  /**
   * CWS/CMS Referral must have at least one victim
   */
  public static final int MINIMUM_NUMBER_OF_VICTIMS = 1;

  @SuppressWarnings("javadoc")
  public static final Boolean INVALID_PARTICIPANTS = Boolean.FALSE;

  @SuppressWarnings("javadoc")
  public static final Boolean VALID_PARTICIPANTS = Boolean.TRUE;

  /**
   * default constructor
   */
  private ParticipantValidator() {
    // Default, no-op.
  }

  /**
   * @param scr - ScreeningToReferral object
   * @return Boolean - has valid participants
   * @throws ServiceException - throw and errors
   */
  public static Boolean hasValidParticipants(ScreeningToReferral scr) {
    int reporterCount = 0;
    int victimCount = 0;

    Set<Participant> participants = scr.getParticipants();
    if (participants != null) {
      for (Participant participant : participants) {
        if (isReporterType(participant)) {
          reporterCount++;
        }
        if (hasVictimRole(participant)) {
          victimCount++;
        }
      }
    }
    // R - 00851 Reporter Creation
    // R - 00836 Self Rep Ind Limit
    // only one reporter is allowed on a referral
    if (reporterCount != ALLOWED_NUMBER_OF_REPORTERS) {
      return INVALID_PARTICIPANTS;
    }
    // R - 00851 Reporter Creation
    // one victim is required for a referral
    if (victimCount < MINIMUM_NUMBER_OF_VICTIMS) {
      return INVALID_PARTICIPANTS;
    }

    return VALID_PARTICIPANTS;
  }

  /**
   * @param str - ScreeningToReferral object
   * @return Boolean - is an anonymous reporter
   * @throws ServiceException - throw any exception
   */
  // is there an anonymous reporter participant on this screening to referral?
  public static Boolean anonymousReporter(ScreeningToReferral str) {
    Set<Participant> participants;
    participants = str.getParticipants();
    if (participants != null) {
      for (Participant incomingParticipant : participants) {
        Set<String> roles = new HashSet<>(incomingParticipant.getRoles());
        if (roles.contains(Role.ANONYMOUS_REPORTER_ROLE.getType())) {
          return Boolean.TRUE;
        }
      }
    }

    return Boolean.FALSE;
  }

  /**
   * @param participant - Participant
   * @return - Boolean true if Participant has reporter type role
   * @throws ServiceException - throw all Exceptions
   */
  public static Boolean isReporterType(Participant participant) {
    Set<String> roles = participant.getRoles();
    if (roles != null) {
      if (roles.contains(Role.ANONYMOUS_REPORTER_ROLE.getType())) {
        return Boolean.TRUE;
      }
      if (roles.contains(Role.MANDATED_REPORTER_ROLE.getType())) {
        return Boolean.TRUE;
      }
      if (roles.contains(Role.NON_MANDATED_REPORTER_ROLE.getType())) {
        return Boolean.TRUE;
      }
      if (roles.contains(Role.SELF_REPORTED_ROLE.getType())) {
        return Boolean.TRUE;
      }
      if (roles.contains(Role.VICTIM_ROLE.getType())
          && roles.contains(Role.NON_MANDATED_REPORTER_ROLE.getType())) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  /**
   * @param participant - Participant
   * @return - Boolean true if Participant has perpetrator role
   */
  public static Boolean isPerpetrator(Participant participant) {
    Set<String> roles = participant.getRoles();
    if (roles != null && roles.contains(Role.PERPETRATOR_ROLE.getType())) {

      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * @param participant - Participant
   * @return Boolean - true if victim role found in participant
   * @throws ServiceException - throw any exception
   */
  public static Boolean hasVictimRole(Participant participant) {
    final Set<String> roles = participant.getRoles();
    return roles != null && roles.contains(Role.VICTIM_ROLE.getType());
  }

  /**
   * @param participant - Participant
   * @return Boolean - true if mandated reporter role found in participant
   * @throws ServiceException on arbitrary error
   */
  public static Boolean hasMandatedReporterRole(Participant participant) {
    final Set<String> roles = participant.getRoles();
    return roles != null && roles.contains(Role.MANDATED_REPORTER_ROLE.getType());
  }

  /**
   * @param participants - Participants
   * @return Boolean - true if mandated reporter role found in any of the participant
   * @throws ServiceException on arbitrary error
   */
  public static Boolean hasMandatedReporterRole(Set<Participant> participants) {
    boolean mandatedRepoter = false;
    for (Participant p : participants) {
      mandatedRepoter = hasMandatedReporterRole(p);
      if (mandatedRepoter) {
        break;
      }
    }
    return mandatedRepoter;
  }

  /**
   * Check for incompatible roles for this participant.
   * 
   * @param participant - Participant object
   * @return Boolean - has valid roles
   * @throws ServiceException - throw any exception
   */
  public static Boolean hasValidRoles(Participant participant) {
    final Set<String> roles = participant.getRoles();
    if (roles == null) {
      return Boolean.TRUE;
    }

    // R - 00831
    if (roles.contains(Role.ANONYMOUS_REPORTER_ROLE.getType())
        && roles.contains(Role.SELF_REPORTED_ROLE.getType())) {
      return Boolean.FALSE;
    }
    if (roles.contains(Role.ANONYMOUS_REPORTER_ROLE.getType())
        && roles.contains(Role.VICTIM_ROLE.getType())) {
      return Boolean.FALSE;
    }
    if (roles.contains(Role.ANONYMOUS_REPORTER_ROLE.getType())
        && (roles.contains(Role.MANDATED_REPORTER_ROLE.getType())
            || roles.contains(Role.NON_MANDATED_REPORTER_ROLE.getType()))) {
      return Boolean.FALSE;
    }
    if (roles.contains(Role.VICTIM_ROLE.getType())
        && roles.contains(Role.PERPETRATOR_ROLE.getType())) {
      return Boolean.FALSE;
    }
    if (roles.contains(Role.MANDATED_REPORTER_ROLE.getType())
        && roles.contains(Role.NON_MANDATED_REPORTER_ROLE.getType())) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  // check for self-reported participant
  /**
   * @param participant - Participant object
   * @return Boolean - is a self reported participant
   * @throws ServiceException - throw any exception
   */
  public static Boolean selfReported(Participant participant) {
    Set<String> roles = participant.getRoles();
    if (roles != null) {
      if (roles.contains(Role.VICTIM_ROLE.getType())
          && roles.contains(Role.NON_MANDATED_REPORTER_ROLE.getType())) {
        return Boolean.TRUE;
      }
      if (roles.contains(Role.SELF_REPORTED_ROLE.getType())) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  /**
   * @param str - ScreeningToReferral object
   * @param victimPersonId - Person Id of victim
   * @return - True if participant has a role of Victim
   * @throws ServiceException - throw any exception
   */
  public static Boolean isVictimParticipant(ScreeningToReferral str, long victimPersonId) {
    if (str.getParticipants() != null) {
      final Set<Participant> participants = str.getParticipants();
      for (Participant participant : participants) {
        if (participant.getId() == victimPersonId && hasVictimRole(participant)) {
          return Boolean.TRUE;
        }
      }
    }

    return Boolean.FALSE;
  }

  /**
   * @param str - ScreeningToReferral object
   * @param perpetratorPersonId - Person Id of perpetrator
   * @return - True if participant has a role of Perpetrator
   */
  public static Boolean isPerpetratorParticipant(ScreeningToReferral str,
      long perpetratorPersonId) {
    if (str.getParticipants() != null) {
      Set<Participant> participants = str.getParticipants();
      for (Participant participant : participants) {
        if (participant.getId() == perpetratorPersonId && (isPerpetrator(participant))) {
          return Boolean.TRUE;
        }
      }
    }
    return Boolean.FALSE;
  }

  /**
   * @param role - String role
   * @return - Boolean true if perpetrator
   */
  public static Boolean roleIsPerpetrator(String role) {
    return role != null && role.equalsIgnoreCase(Role.PERPETRATOR_ROLE.getType());
  }

  /**
   * @param role - String role
   * @return - Boolean true if Victim
   */
  public static Boolean roleIsVictim(String role) {
    return role != null && role.equalsIgnoreCase(Role.VICTIM_ROLE.getType());
  }

  /**
   * @param role - String role
   * @return - Boolean true if any reporter type
   *
   *         Returns true if role is any reporter role
   */
  public static Boolean roleIsAnyReporter(String role) {
    return roleIsReporterType(role) || roleIsAnonymousReporter(role) || roleIsSelfReporter(role);
  }

  /**
   * @param role - String role
   * @return - Boolean true if mandated or non mandated
   *
   *         Do Not include Anonymous Reporter (special case of reporter)
   */
  public static Boolean roleIsReporterType(String role) {
    return role != null && (role.equalsIgnoreCase(Role.MANDATED_REPORTER_ROLE.getType())
        || role.equalsIgnoreCase(Role.NON_MANDATED_REPORTER_ROLE.getType()));
  }

  /**
   * @param role - String role
   * @return - Boolean true if anonymous reporter
   */
  public static Boolean roleIsAnonymousReporter(String role) {
    return role != null && role.equalsIgnoreCase(Role.ANONYMOUS_REPORTER_ROLE.getType());
  }

  /**
   * @param role - String role
   * @return - Boolean true if Self Reporter reporter
   */
  public static Boolean roleIsSelfReporter(String role) {
    return role != null && role.equalsIgnoreCase(Role.SELF_REPORTED_ROLE.getType());
  }

  /**
   * @param role - String role
   * @return - Boolean true if mandated reporter
   */
  public static Boolean roleIsMandatedReporter(String role) {
    return role != null && role.equalsIgnoreCase(Role.MANDATED_REPORTER_ROLE.getType());
  }

}
