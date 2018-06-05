package gov.ca.cwds.data.persistence.cms;

import static gov.ca.cwds.rest.util.FerbDateUtils.freshDate;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.rest.api.domain.DomainChef;

/**
 * {@link PersistentObject} represents a record in TSCNTRLT.
 * 
 * @author CWDS API Team
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TSCNTRLT")
public class CmsDocument extends CmsPersistentObject {

  @Id
  @Column(name = "DOC_HANDLE", length = 30)
  private String id;

  @Type(type = "short")
  @Column(name = "DOC_SEGS")
  private Short segmentCount;

  @Type(type = "long")
  @Column(name = "DOC_LEN")
  private Long docLength;

  @Column(name = "DOC_AUTH")
  private String docAuth;

  @Column(name = "DOC_SERV")
  private String docServ;

  @Type(type = "date")
  @Column(name = "DOC_DATE")
  private Date docDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DomainChef.TIME_FORMAT)
  @Type(type = "time")
  @Column(name = "DOC_TIME")
  private Date docTime;

  @Column(name = "DOC_NAME")
  private String docName;

  @Column(name = "CMPRS_PRG")
  private String compressionMethod;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, mappedBy = "docHandle")
  @OrderBy("DOC_HANDLE, DOC_SEGSEQ")
  private Set<CmsDocumentBlobSegment> blobSegments = new LinkedHashSet<>();

  /**
   * Default constructor
   * 
   * Required for Hibernate.
   */
  public CmsDocument() {
    super();
  }

  /**
   * Convenience constructor.
   * 
   * @param id unique document handle
   * @param segmentCount number of blob segments
   * @param docLength total bytes in all blob segments
   * @param docAuth document "auth"
   * @param docServ document "server"
   * @param docDate creation date
   * @param docTime creation time
   * @param docName document name (e.g., "word.doc")
   * @param compressionMethod type of compression, 01 (LZW) or 02 (PK)
   */
  public CmsDocument(String id, Short segmentCount, Long docLength, String docAuth, String docServ,
      Date docDate, Date docTime, String docName, String compressionMethod) {
    super();
    this.id = id;
    this.docAuth = docAuth;
    this.docServ = docServ;
    this.docName = docName;
    this.segmentCount = segmentCount;
    this.docDate = freshDate(docDate);
    this.docTime = freshDate(docTime);
    this.docLength = docLength;
    this.compressionMethod = compressionMethod;
  }

  /**
   * Copy constructor.
   * 
   * @param copy deep copy from this
   */
  public CmsDocument(CmsDocument copy) {
    super(copy.getLastUpdatedId(), copy.getLastUpdatedTime());
    this.id = copy.id;
    this.docAuth = copy.docAuth;
    this.docServ = copy.docServ;
    this.docName = copy.docName;
    this.segmentCount = copy.segmentCount;
    this.docDate = freshDate(copy.docDate);
    this.docTime = freshDate(copy.docTime);
    this.docLength = copy.docLength;
    this.compressionMethod = copy.compressionMethod;
    this.blobSegments = new LinkedHashSet<>(copy.blobSegments);
  }

  /**
   * Domain copy constructor. Build a persistence document from a domain document.
   * 
   * <p>
   * This constructor doesn't populate blob segments directly from a base64-encoded, decompressed
   * document because that translation requires compression, which doesn't belong here.
   * </p>
   * 
   * @param cmsDocument domain (JSON) document object
   */
  public CmsDocument(gov.ca.cwds.rest.api.domain.cms.CmsDocument cmsDocument) {
    super();
    this.id = cmsDocument.getId();
    this.docAuth = cmsDocument.getDocAuth();
    this.docServ = cmsDocument.getDocServ();
    this.docName = cmsDocument.getDocName();
    this.segmentCount = cmsDocument.getSegmentCount();
    this.docDate = DomainChef.uncookDateString(cmsDocument.getDocDate());
    this.docLength = cmsDocument.getDocLength();
    this.docTime = DomainChef.uncookTimeString(cmsDocument.getDocTime());
    this.compressionMethod = cmsDocument.getCompressionMethod();
  }

  /**
   * {@inheritDoc}
   * 
   * @see gov.ca.cwds.data.persistence.PersistentObject#getPrimaryKey()
   */
  @Override
  public Serializable getPrimaryKey() {
    return getId();
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @return segmentCount
   */
  public Short getSegmentCount() {
    return segmentCount;
  }

  /**
   * @param segmentCount segment count
   */
  public void setSegmentCount(Short segmentCount) {
    this.segmentCount = segmentCount;
  }

  /**
   * @return docLength
   */
  public Long getDocLength() {
    return docLength;
  }

  /**
   * @param docLength document length (uncompressed)
   */
  public void setDocLength(Long docLength) {
    this.docLength = docLength;
  }

  /**
   * @return docAuth doc "auth" stamp
   */
  public String getDocAuth() {
    return docAuth;
  }

  /**
   * @param docAuth doc "auth" stamp
   */
  public void setDocAuth(String docAuth) {
    this.docAuth = docAuth;
  }

  /**
   * @return docServ
   */
  public String getDocServ() {
    return docServ;
  }

  /**
   * @param docServ internal document server
   */
  public void setDocServ(String docServ) {
    this.docServ = docServ;
  }

  /**
   * @return docDate document date
   */
  public Date getDocDate() {
    return freshDate(docDate);
  }

  /**
   * @param docDate document date
   */
  public void setDocDate(Date docDate) {
    this.docDate = freshDate(docDate);
  }

  /**
   * @return docTime
   */
  public Date getDocTime() {
    return freshDate(docTime);
  }

  /**
   * @param docTime time of document creation
   */
  public void setDocTime(Date docTime) {
    this.docTime = freshDate(docTime);
  }

  /**
   * @return docName document name, such as "child.doc"
   */
  public String getDocName() {
    return docName;
  }

  /**
   * @param docName document name, such as "child.doc"
   */
  public void setDocName(String docName) {
    this.docName = docName;
  }

  /**
   * @return compressionMethod
   */
  public String getCompressionMethod() {
    return compressionMethod;
  }

  /**
   * @param compressionMethod compression method, PK or LZW
   */
  public void setCompressionMethod(String compressionMethod) {
    this.compressionMethod = compressionMethod;
  }

  /**
   * @param id char(10) identifier
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return blobSegments Set of binary, compressed segments
   */
  public Set<CmsDocumentBlobSegment> getBlobSegments() {
    return blobSegments;
  }

  /**
   * @param blobSegment add a binary, compressed BLOB segment
   */
  public void addBlobSegment(CmsDocumentBlobSegment blobSegment) {
    this.blobSegments.add(blobSegment);
  }

  /**
   * @param blobSegments Set of binary, compressed segments
   */
  public void setBlobSegments(Set<CmsDocumentBlobSegment> blobSegments) {
    this.blobSegments = blobSegments;
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
