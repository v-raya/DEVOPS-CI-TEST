package gov.ca.cwds.data.persistence.cms;

import static gov.ca.cwds.rest.util.FerbDateUtils.freshDate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import gov.ca.cwds.rest.api.domain.DomainChef;

/**
 * Class representing an AllegationPrepetratorHistory.
 * 
 * @author CWDS API Team
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ALPRHIST")
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllegationPerpetratorHistory extends CmsPersistentObject {
  protected static final String DATE_FORMAT = "yyyy-MM-dd";

  @Id
  @Column(name = "IDENTIFIER", length = CMS_ID_LEN)
  private String id;

  @NotEmpty
  @Size(min = 2, max = 2)
  @Column(name = "CNTY_SPFCD")
  private String countySpecificCode;

  @Size(max = CMS_ID_LEN)
  @Column(name = "FKCLIENT_T", length = CMS_ID_LEN)
  private String perpetratorClientId;

  @NotEmpty
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @Column(name = "FKALLGTN_T", length = CMS_ID_LEN)
  private String allegationId;

  @NotNull
  @Type(type = "date")
  @Column(name = "PR_UPD_DT")
  private Date perpetratorUpdateDate;

  @ManyToOne(optional = true)
  @JoinColumn(name = "FKCLIENT_T", nullable = true, updatable = false, insertable = false)
  private Client client;

  @ManyToOne(optional = false)
  @JoinColumn(name = "FKALLGTN_T", nullable = false, updatable = false, insertable = false)
  private Allegation allegation;

  /**
   * Default constructor
   * 
   * Required for Hibernate
   */
  public AllegationPerpetratorHistory() {
    super();
  }

  /**
   * Constructor
   * 
   * @param id primary key
   * @param countySpecificCode county Specific Code
   * @param perpetratorClientId perpetrator Client Id
   * @param allegationId allegation Id
   * @param perpetratorUpdateDate perpetrator Update Date
   */
  public AllegationPerpetratorHistory(String id, String countySpecificCode,
      String perpetratorClientId, String allegationId, Date perpetratorUpdateDate) {
    super();
    this.id = id;
    this.countySpecificCode = countySpecificCode;
    this.perpetratorClientId = perpetratorClientId;
    this.allegationId = allegationId;
    this.perpetratorUpdateDate = freshDate(perpetratorUpdateDate);
  }

  /**
   * Constructor using domain
   * 
   * @param id The id
   * @param persistedAllegationPerpetratorHistory The domain object to construct this object from
   * @param lastUpdatedId the id of the last user to update this object
   * @param lastUpdateTime the time of last update this object
   */
  public AllegationPerpetratorHistory(String id,
      gov.ca.cwds.rest.api.domain.cms.AllegationPerpetratorHistory persistedAllegationPerpetratorHistory,
      String lastUpdatedId, Date lastUpdateTime) {
    super(lastUpdatedId, lastUpdateTime);
    this.id = id;
    this.countySpecificCode = persistedAllegationPerpetratorHistory.getCountySpecificCode();
    this.perpetratorClientId = persistedAllegationPerpetratorHistory.getPerpetratorClientId();
    this.allegationId = persistedAllegationPerpetratorHistory.getAllegationId();
    this.perpetratorUpdateDate = DomainChef
        .uncookDateString(persistedAllegationPerpetratorHistory.getPerpetratorUpdateDate());
  }

  /**
   * {@inheritDoc}
   * 
   * @see gov.ca.cwds.data.persistence.PersistentObject#getPrimaryKey()
   */
  @Override
  public String getPrimaryKey() {
    return getId();
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @return the countySpecificCode
   */
  public String getCountySpecificCode() {
    return countySpecificCode;
  }

  /**
   * @return the perpetratorClientId
   */
  public String getPerpetratorClientId() {
    return perpetratorClientId;
  }

  /**
   * @return the allegationId
   */
  public String getAllegationId() {
    return allegationId;
  }

  /**
   * @return the prepetratorUpdateDate
   */
  public Date getPerpetratorUpdateDate() {
    return freshDate(perpetratorUpdateDate);
  }

  public Client getClient() {
    return client;
  }

  public Allegation getAllegation() {
    return allegation;
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
