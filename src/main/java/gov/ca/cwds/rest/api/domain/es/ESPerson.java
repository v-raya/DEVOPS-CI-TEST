package gov.ca.cwds.rest.api.domain.es;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.ca.cwds.ObjectMapperUtils;
import gov.ca.cwds.data.persistence.cms.OtherClientName;
import gov.ca.cwds.data.persistence.cms.Reporter;
import gov.ca.cwds.rest.api.domain.Address;
import gov.ca.cwds.rest.api.domain.Ethnicity;
import gov.ca.cwds.rest.api.domain.Language;
import gov.ca.cwds.rest.api.domain.Person;
import gov.ca.cwds.rest.api.domain.PhoneNumber;
import gov.ca.cwds.rest.api.domain.Race;
import gov.ca.cwds.rest.services.ServiceException;

/**
 * Generic "person" class for ElasticSearch results, which decorates domain-level {@link Person}
 * with a String id and optional nested person specialty document, such as {@link OtherClientName}
 * or {@link Reporter}.
 * 
 * @author CWDS API Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"})
public class ESPerson extends Person {

  // =========================
  // PRIVATE STATIC:
  // =========================

  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = LoggerFactory.getLogger(ESPerson.class);

  /**
   * {@link ObjectMapper}, used to unmarshall JSON Strings from member {@link #sourceJson} into
   * instances of {@link #sourceType}.
   * 
   * <p>
   * This mapper is thread-safe and reusable across multiple threads, yet any configuration made to
   * it, such as ignoring unknown JSON properties, applies to ALL target class types.
   * </p>
   */
  private static final ObjectMapper MAPPER = ObjectMapperUtils.createObjectMapper();

  // =========================
  // PUBLIC STATIC:
  // =========================

  /**
   * ElasticSearch field names for document type people.person.
   * 
   * @author CWDS API Team
   */
  public enum ESColumn {

    /**
     * ElasticSearch identifier
     */
    ID("id", String.class, ""),

    /**
     * first name
     */
    FIRST_NAME("first_name", String.class, ""),

    /**
     * middle name
     */
    MIDDLE_NAME("middle_name", String.class, ""),

    /**
     * last name
     */
    LAST_NAME("last_name", String.class, ""),

    /**
     * name suffix
     */
    NAME_SUFFIX("name_suffix", String.class, ""),

    /**
     * gender code (M,F,U)
     */
    GENDER("gender", String.class, "U"),

    /**
     * birth date
     */
    BIRTH_DATE("date_of_birth", String.class, null),

    /**
     * Social Security Number
     */
    SSN("ssn", String.class, null),

    /**
     * CWDS API class type, usually a domain class.
     */
    TYPE("type", String.class, null),

    /**
     * Nested JSON from {@link #TYPE} class.
     */
    SOURCE("source", String.class, null);

    /**
     * ElasticSearch column name.
     */
    final String col;

    /**
     * Value's data type as a Java Class.
     */
    final Class<? extends Serializable> klazz;

    /**
     * Default value, if no value is provided.
     */
    final Object defaultVal;

    /**
     * Enum constructor populates immutable members.
     * 
     * @param col ES column name
     * @param klazz Java Class of value
     * @param defaultVal default value. Must be assignable from {@link #klazz}.
     */
    ESColumn(String col, Class<? extends Serializable> klazz, Object defaultVal) {
      this.col = col;
      this.klazz = klazz;
      this.defaultVal = defaultVal;
    }

    /**
     * @return ElasticSearch column name
     */
    public final String getCol() {
      return col;
    }

    /**
     * @return target data type. Must be assignable from the ElasticSearch result data type.
     */
    public final Class<? extends Serializable> getKlazz() {
      return klazz;
    }

    /**
     * @return default value for target field that lacking a corresponding value in the
     *         ElasticSearch result Map
     */
    public final Object getDefaultVal() {
      return defaultVal;
    }
  }

  // ================
  // MEMBERS:
  // ================

  /**
   * The identifier is String in legacy (CMS, mainframe DB2) but Long in new style (NS, PostGreSQL).
   * Therefore, the generic id here is String to accommodate all possibilities without resorting to
   * generics, untyped Object, or collections with heterogeneous types. For now,
   * 
   * <p>
   * Java lacks a "union" construct (mutually exclusive child structures), and polymorphic return
   * types (though, technically, the JVM could...), and typed templates (generics aren't the same).
   * </p>
   */
  @JsonProperty("id")
  private String id;

  /**
   * Original, fully-qualified, persistence-level source class, such
   * "gov.ca.cwds.rest.api.persistence.cms.OtherClientName".
   */
  @JsonProperty("type")
  private String sourceType;

  /**
   * Raw, nested, child document (typically inherited from {@link Person} in JSON from object
   * {@link #sourceType} and stored in ES document.
   * 
   * <p>
   * Note that JSON marshalling intentionally ignores this member, since it represents the JSON to
   * create a child Object and not the Object itself.
   * </p>
   */
  @JsonProperty("source")
  @JsonIgnore
  private String sourceJson;

  /**
   * Nested document Object, constructed by unmarshalling {@link #sourceJson} into an instance of
   * Class type {@link #sourceType}.
   */
  @JsonProperty("source_object")
  private transient Object sourceObj;

  /**
   * Overload constructor.
   * 
   * @param id unique identifier
   * @param firstName The first name
   * @param middleName The middle name
   * @param lastName The last name
   * @param nameSuffix name suffix
   * @param gender The gender
   * @param birthDate The date of birth
   * @param ssn Social Security Number
   * @param address The address, if any
   * @param phoneNumber The phoneNumber, if any
   * @param language The language, if any
   * @param race The race, if any
   * @param ethnicity The Ethnicity, if any
   */
  public ESPerson(String id, String firstName, String middleName, String lastName,
      String nameSuffix, String gender, String birthDate, String ssn, Set<Address> address,
      Set<PhoneNumber> phoneNumber, Set<Language> language, Set<Race> race,
      Set<Ethnicity> ethnicity) {
    super(trim(firstName), trim(middleName), trim(lastName), trim(nameSuffix), trim(gender),
        trim(birthDate), trim(ssn), address, phoneNumber, language, race, ethnicity);
    this.id = id;
  }

  /**
   * Overload constructor, used to accommodate nested document members {@link #sourceType} and
   * {@link #sourceJson}.
   * 
   * @param id identifier
   * @param firstName first name
   * @param middleName middle name
   * @param lastName last name
   * @param nameSuffix name suffix
   * @param gender gender code
   * @param birthDate birth date
   * @param ssn SSN without dashes
   * @param sourceType fully-qualified, persistence-level source class
   * @param sourceJson raw, nested child document as JSON
   * @param address address, if any
   * @param phoneNumber PhoneNumber, if any
   * @param language languages, if any
   * @param race race, if any
   * @param ethnicity ethnicity, if any
   */
  public ESPerson(String id, String firstName, String middleName, String lastName,
      String nameSuffix, String gender, String birthDate, String ssn, String sourceType,
      String sourceJson, Set<Address> address, Set<PhoneNumber> phoneNumber, Set<Language> language,
      Set<Race> race, Set<Ethnicity> ethnicity) {
    super(trim(firstName), trim(middleName), trim(lastName), trim(nameSuffix), trim(gender),
        trim(birthDate), trim(ssn), address, phoneNumber, language, race, ethnicity);
    this.id = id;
    this.sourceType = sourceType;
    this.sourceJson = sourceJson;
  }

  /**
   * Construct from a persistence-level, new style {@link gov.ca.cwds.data.persistence.ns.Person}.
   * 
   * @param person database NS person object
   */
  public ESPerson(gov.ca.cwds.data.persistence.ns.Person person) {
    super(person);
    this.id = String.valueOf(person.getId());
  }

  /**
   * Produce an ESPerson domain from native ElasticSearch {@link SearchHit}. Parse JSON results and
   * populate associated fields.
   * 
   * <p>
   * <strong>Classloader Note:</strong> When running in an application server, the root classloader
   * may not know of our domain/persistence class, and so we look it up using the current thread's
   * classloader, like so:
   * </p>
   * 
   * <blockquote>
   * {@code Class.forName("some.nested.class", false, Thread.currentThread().getContextClassLoader())}
   * </blockquote>
   * 
   * @deprecated no longer in use, superseded by People Summary index
   * @param hit search result
   * @return populated domain-level ES object
   * @see #pullCol(Map, ESColumn)
   */
  @Deprecated
  public static ESPerson makeESPerson(SearchHit hit) {
    final Map<String, Object> m = hit.getSource();
    ESPerson ret = new ESPerson(ESPerson.<String>pullCol(m, ESColumn.ID),
        ESPerson.<String>pullCol(m, ESColumn.FIRST_NAME),
        ESPerson.<String>pullCol(m, ESColumn.MIDDLE_NAME),
        ESPerson.<String>pullCol(m, ESColumn.LAST_NAME),
        ESPerson.<String>pullCol(m, ESColumn.NAME_SUFFIX),
        ESPerson.<String>pullCol(m, ESColumn.GENDER),
        ESPerson.<String>pullCol(m, ESColumn.BIRTH_DATE), ESPerson.<String>pullCol(m, ESColumn.SSN),
        ESPerson.<String>pullCol(m, ESColumn.TYPE), ESPerson.<String>pullCol(m, ESColumn.SOURCE),
        null, null, null, null, null);

    if (!StringUtils.isBlank(ret.getSourceType()) && !StringUtils.isBlank(ret.getSourceJson())) {
      try {
        // STORY #137216799:
        // Tech debt: reverse compatibility with existing ElasticSearch documents.
        if (ret.getSourceType().startsWith("gov.ca.cwds.rest.api.")) {
          LOGGER.warn("LEGACY CLASS IN ELASTICSEARCH! class={}, id={}", ret.getSourceType(),
              ret.getId());
        }

        if (!StringUtils.isBlank(ret.getSourceJson())) {
          // Remove excess whitespace. Don't store excess whitespace in ElasticSearch!
          final String json = ret.getSourceJson().replaceAll("\\s+\",", "\",");

          // Dynamically instantiate the domain class specified by "type" and load from JSON.
          // Note: When running in an application server, the app server's root classloader may not
          // know of our domain/persistence class, but the current thread's classloader should.

          // STORY #137216799: again.
          final Object obj = MAPPER.readValue(json,
              Class.forName(
                  ret.getSourceType().replaceAll("gov\\.ca\\.cwds\\.rest\\.api\\.",
                      "gov\\.ca\\.cwds\\.data\\."),
                  false, Thread.currentThread().getContextClassLoader()));

          ret.sourceObj = obj;
        }

      } catch (ClassNotFoundException ce) {
        throw new ServiceException("ElasticSearch Person error: Failed to instantiate class "
            + ret.getSourceType() + ", ES person id=" + ret.getId(), ce);
      } catch (Exception e) {
        throw new ServiceException(
            "ElasticSearch Person error: " + e.getMessage() + ", ES person id=" + ret.getId(), e);
      }
    }

    return ret;
  }

  // =========================
  // PROTECTED STATIC:
  // =========================

  /**
   * Extract field's value from an ElasticSearch result {@link Map} (key: field name), using the
   * field's ES column name and data type.
   * 
   * @param <T> expected data type
   * @param m ES result map
   * @param f field to extract
   * @return field value as specified type T
   */
  @SuppressWarnings("unchecked")
  protected static <T extends Serializable> T pullCol(final Map<String, Object> m, ESColumn f) {
    return (T) f.klazz.cast(m.getOrDefault(f.col, f.defaultVal));
  }

  /**
   * Trim excess whitespace from ElasticSearch results. Non-null input of all whitespace returns
   * empty String, null input returns null.
   * 
   * @param s String to trim
   * @return String trimmed of outer whitespace or null if input is null
   */
  protected static String trim(String s) {
    return s != null ? s.trim() : null;
  }

  /**
   * See comments on {@link #id}.
   * 
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * See comments on {@link #sourceType}.
   * 
   * @return the fully-qualified source persistence class
   */
  public String getSourceType() {
    return sourceType;
  }

  /**
   * See comments on {@link #sourceJson}. JSON streaming intentionally ignores this field.
   * 
   * @return the raw JSON of nested person document, if any
   * @see #sourceType
   */
  @JsonIgnore
  public String getSourceJson() {
    return sourceJson;
  }

  /**
   * See comments on {@link #sourceObj}.
   * 
   * @return an instance of the Object represented by {@link #sourceType} and {@link #sourceJson}.
   * @see #sourceType
   */
  public Object getSourceObj() {
    return sourceObj;
  }

}
