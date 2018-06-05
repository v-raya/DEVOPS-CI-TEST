package gov.ca.cwds.data.persistence.cms;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * {@link CmsPersistentObject} representing a Referral Assignment.
 * 
 * <p>
 * This entity bean wraps the Assignment table's "folded key" FK (virtual FK) on columns ESTBLISH_CD
 * and ESTBLSH_ID to either to Referral or Case, as dictated by ESTBLISH_CD.
 * </p>
 * 
 * @author CWDS API Team
 */
@Entity(name = "ReferralAssignment")
@DiscriminatorValue(value = "REFERRAL")
@PrimaryKeyJoinColumn(name = "IDENTIFIER")
@SuppressWarnings("serial")
public class ReferralAssignment extends BaseAssignment {

  public static final String FOLDED_KEY_CODE = "R";

  /**
   * Declare foreign key from Assignment to Referral.
   */
  @OneToOne
  @JoinColumn(name = "IDENTIFIER", referencedColumnName = "ESTBLSH_ID")
  private Referral referral;

  /**
   * Default constructor.
   */
  public ReferralAssignment() {
    super();
    setEstablishedForCode(FOLDED_KEY_CODE);
  }

  /**
   * Construct from all fields.
   * 
   * @param countySpecificCode - county code of case load with this assignment
   * @param endDate - end date of assignment
   * @param endTime - end time of assignment
   * @param referralId - referral Id
   * @param fkCaseLoad - foreign key to the case load
   * @param fkOutOfStateContactParty - foreign ky to the out of state contact party
   * @param responsibilityDescription - description
   * @param secondaryAssignmentRoleType - primary or secondary
   * @param startDate - start date of assignment
   * @param startTime - end date of assignment
   * @param typeOfAssignmentCode - primary, secondary, or read only
   * @param weightingNumber - weighting within case load
   */
  public ReferralAssignment(String countySpecificCode, Date endDate, Date endTime,
      String referralId, String fkCaseLoad, String fkOutOfStateContactParty,
      String responsibilityDescription, Short secondaryAssignmentRoleType, Date startDate,
      Date startTime, String typeOfAssignmentCode, BigDecimal weightingNumber) {
    super(countySpecificCode, endDate, endTime, FOLDED_KEY_CODE, referralId, fkCaseLoad,
        fkOutOfStateContactParty, responsibilityDescription, secondaryAssignmentRoleType, startDate,
        startTime, typeOfAssignmentCode, weightingNumber);
  }

  public Referral getReferral() {
    return referral;
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, false);
  }

}
