package gov.ca.cwds.data.persistence.ns;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import gov.ca.cwds.data.ns.NsPersistentObject;

/**
 * {@link NsPersistentObject} representing an Race
 * 
 * @author CWDS API Team
 */
@NamedQuery(name = "gov.ca.cwds.rest.api.persistence.ns.Race.findAll", query = "FROM Race")
@NamedQuery(name = "gov.ca.cwds.rest.api.persistence.ns.Race.findAllUpdatedAfter",
    query = "FROM Race WHERE lastUpdatedTime > :after")
@SuppressWarnings("serial")
@Entity
@Table(name = "race")
public class Race extends NsPersistentObject {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_race")
  @SequenceGenerator(name = "seq_race", sequenceName = "seq_race", allocationSize = 50)
  @Column(name = "race_id")
  private Long id;

  @Column(name = "race_type_id")
  private String raceType;

  @Column(name = "subrace_type_id")
  private String subRaceType;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "personRaceId.race")
  private Set<PersonRace> personRace = new HashSet<>();

  @SuppressWarnings("javadoc")
  public Race() {
    super();
  }

  /**
   * @param id - unique id
   * @param race - race
   * @param subrace - sub race
   */
  public Race(Long id, String race, String subrace) {
    super();
    this.id = id;
    this.raceType = race;
    this.subRaceType = subrace;
  }

  @SuppressWarnings("javadoc")
  public Race(gov.ca.cwds.rest.api.domain.Race domain, String lastUpdatedId, String createUserId) {
    super(lastUpdatedId, createUserId);
    this.raceType = domain.getRaceType();
    this.subRaceType = domain.getSubRaceType();
  }

  /**
   * {@inheritDoc}
   * 
   * @see gov.ca.cwds.data.persistence.PersistentObject#getPrimaryKey()
   */
  @Override
  public Long getPrimaryKey() {
    return getId();
  }



  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @return the race
   */
  public String getRaceType() {
    return raceType;
  }

  @SuppressWarnings("javadoc")
  public String getSubRaceType() {
    return subRaceType;
  }

  /**
   * @return the personRace
   */
  public Set<PersonRace> getPersonRace() {
    return personRace;
  }

}
