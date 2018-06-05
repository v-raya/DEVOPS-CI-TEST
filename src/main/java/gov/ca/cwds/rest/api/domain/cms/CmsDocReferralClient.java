package gov.ca.cwds.rest.api.domain.cms;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.DomainChef;
import gov.ca.cwds.rest.api.domain.DomainObject;
import gov.ca.cwds.rest.api.domain.ReportingDomain;
import gov.ca.cwds.rest.core.Api;
import gov.ca.cwds.rest.services.cms.CmsDocumentService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@link DomainObject} represents a CMS Document.
 * 
 * @author CWDS API Team
 */
@ApiModel
@InjectLinks({@InjectLink(value = "/{resource}/{id}", rel = "self", style = Style.ABSOLUTE,
    bindings = {@Binding(name = "id", value = "${instance.id}"),
        @Binding(name = "resource", value = Api.RESOURCE_CMS_DOCUMENT)})})
public class CmsDocReferralClient extends ReportingDomain
    implements Request, Response, Serializable {

  private static final long serialVersionUID = -9133158600820834189L;

  public static final class CmsDocReferralClientDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @JsonProperty("referral_id")
    private String referlId;

    @NotEmpty
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("common_first_name")
    private String commonFirstName;

    @JsonProperty("common_middle_name")
    private String commonMiddleName;

    @JsonProperty("common_last_name")
    private String commonLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "birth_date")
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd",
        example = "2000-01-01")
    private String birthDate;

    @JsonProperty("other_name")
    private String otherName;

    @JsonProperty("name_type")
    private String nameType;

    @JsonProperty("address")
    private String address;

    @JsonProperty("address_type")
    private String addressType;

    /**
     * Default Constructor
     */
    public CmsDocReferralClientDetail() {
      // required to construct CmsDocReferralClient from List of persistence layer referral/client
      // document records
    }

    @JsonCreator
    public CmsDocReferralClientDetail(@JsonProperty("referral_id") String referlId,
        @JsonProperty("client_id") String clientId,
        @JsonProperty("common_first_name") String commonFirstName,
        @JsonProperty("common_middle_name") String commonMiddleName,
        @JsonProperty("common_last_name") String commonLastName,
        @JsonProperty("birth_date") String birthDate, @JsonProperty("other_name") String otherName,
        @JsonProperty("name_type") String nameType, @JsonProperty("address") String address,
        @JsonProperty("address_type") String addressType) {
      this.referlId = referlId;
      this.clientId = clientId;
      this.commonFirstName = commonFirstName;
      this.commonMiddleName = commonMiddleName;
      this.commonLastName = commonLastName;
      this.birthDate = birthDate;
      this.otherName = otherName;
      this.nameType = nameType;
      this.address = address;
      this.addressType = addressType;
    }

    public String getReferlId() {
      return referlId;
    }

    public void setReferlId(String referlId) {
      this.referlId = referlId;
    }

    public String getClientId() {
      return clientId;
    }

    public void setClientId(String clientId) {
      this.clientId = clientId;
    }

    public String getCommonFirstName() {
      return commonFirstName;
    }

    public void setCommonFirstName(String commonFirstName) {
      this.commonFirstName = commonFirstName;
    }

    public String getCommonMiddleName() {
      return commonMiddleName;
    }

    public void setCommonMiddleName(String commonMiddleName) {
      this.commonMiddleName = commonMiddleName;
    }

    public String getCommonLastName() {
      return commonLastName;
    }

    public void setCommonLastName(String commonLastName) {
      this.commonLastName = commonLastName;
    }

    /**
     * 
     * @return birthDate
     */
    public String getBirthDate() {
      return birthDate;
    }

    /**
     * @param birthDate birth date to set
     */
    public void setBirthDate(String birthDate) {
      this.birthDate = birthDate;
    }

    /**
     * @return the otherName
     */
    public String getOtherName() {
      return otherName;
    }

    /**
     * @param otherName the otherName to set
     */
    public void setOtherName(String otherName) {
      this.otherName = otherName;
    }

    /**
     * @return the nameType
     */
    public String getNameType() {
      return nameType;
    }

    /**
     * @param nameType the nameType to set
     */
    public void setNameType(String nameType) {
      this.nameType = nameType;
    }

    /**
     * @return the address
     */
    public String getAddress() {
      return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
      this.address = address;
    }

    /**
     * @return the addressType
     */
    public String getAddressType() {
      return addressType;
    }

    /**
     * @param addressType the addressType to set
     */
    public void setAddressType(String addressType) {
      this.addressType = addressType;
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

  public static final class CmsDocReferralClientDocument implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("_name")
    private String name;

    @JsonProperty("_content")
    private String content;

    /**
     * Default, no-op constructor.
     */
    public CmsDocReferralClientDocument() {
      // Default, no-op.
    }

    @JsonCreator
    public CmsDocReferralClientDocument(@JsonProperty("_name") String name,
        @JsonProperty("_content") String content) {
      this.name = name;
      this.content = content;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return HashCodeBuilder.reflectionHashCode(this, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof CmsDocReferralClientDocument)) {
        return false;
      }
      CmsDocReferralClientDocument other = (CmsDocReferralClientDocument) obj;

      if (name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!name.equals(other.name)) {
        return false;
      }

      if (content == null) {
        if (other.content != null) {
          return false;
        }
      } else if (!content.equals(other.content)) {
        return false;
      }

      return true;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }

  @NotEmpty
  @Size(min = 30, max = 30)
  @ApiModelProperty(required = true, readOnly = false, value = "30 char document handle",
      example = "0131351421120020*JONESMF 00004")
  private String id;

  @NotNull
  @ApiModelProperty(required = true, readOnly = false, example = "MJS000.DOC")
  @JsonProperty("doc_name")
  private String docName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @JsonProperty("doc_added_date")
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
  @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd",
      example = "2000-01-01")
  private String docAddedDate;

  @NotNull
  @ApiModelProperty(required = true, readOnly = false, example = "base64-encoded binary document")
  @JsonProperty("cms_document")
  private CmsDocReferralClientDocument cmsDocument = new CmsDocReferralClientDocument();

  /**
   * Ordered set of document referral/client details.
   */
  private Set<CmsDocReferralClientDetail> details = new LinkedHashSet<>();

  @JsonCreator
  public CmsDocReferralClient(@JsonProperty("id") String docHandle,
      @JsonProperty("doc_name") String docName, @JsonProperty("doc_added_date") String docAddedDate,
      @JsonProperty("cms_document") CmsDocReferralClientDocument cmsDocument,
      @JsonProperty("details") Set<CmsDocReferralClientDetail> details) {
    super();

    this.id = docHandle;
    this.docName = docName;
    this.docAddedDate = docAddedDate;

    if (cmsDocument != null) {
      this.cmsDocument = cmsDocument;
    }

    if (details != null) {
      this.details = details;
    }
  }

  /**
   * Construct from List of persistence layer referral/client document records.
   * 
   * <p>
   * NOTE: The document service decompresses content, not this domain class. See
   * {@link CmsDocumentService}.
   * </p>
   * 
   * @param docs persistence layer referral/client doc entries
   */
  public CmsDocReferralClient(List<gov.ca.cwds.data.persistence.cms.CmsDocReferralClient> docs) {
    super();

    for (gov.ca.cwds.data.persistence.cms.CmsDocReferralClient entry : docs) {
      CmsDocReferralClientDetail detail = new CmsDocReferralClientDetail();
      detail.setReferlId(entry.getReferlId());
      detail.setClientId(entry.getClientId());
      detail.setCommonFirstName(entry.getCommonFirstName());
      detail.setCommonLastName(entry.getCommonLastName());
      detail.setCommonMiddleName(entry.getCommonMiddleName());
      detail.setAddress(entry.getAddress());
      detail.setAddressType(entry.getAddressType());
      detail.setNameType(entry.getNameType());
      detail.setOtherName(entry.getOtherName());
      details.add(detail);

      this.setDocName(entry.getDocName());
      this.setId(entry.getDocHandle());
      this.setDocAddedDate(DomainChef.cookDate(entry.getDocAddedDate()));

      // NOTE: The document service decompresses content, not this domain class.
      this.cmsDocument.setContent("6833c22e050ac434e10042e190d870007c0001801f");
      this.cmsDocument.setName(entry.getDocName());
    }
  }

  /**
   * The 30 char document handle.
   * 
   * @return doc handle
   */
  public String getId() {
    return id;
  }

  /**
   * The 30 char document handle.
   * 
   * @param id doc handle
   */
  public final void setId(String id) {
    this.id = id;
  }

  /**
   * Name of uncompressed document, such as HELLO.DOC.
   * 
   * @return document name
   */
  public String getDocName() {
    return docName;
  }

  /**
   * Name of uncompressed document, such as HELLO.DOC.
   * 
   * @param docName document name
   */
  public final void setDocName(String docName) {
    this.docName = docName;
  }

  /**
   * When the document was first added.
   * 
   * @return When the document was first added
   */
  public String getDocAddedDate() {
    return docAddedDate;
  }

  /**
   * Set when the document was first added.
   * 
   * @param docAddedDate When the document was first added
   */
  public final void setDocAddedDate(String docAddedDate) {
    this.docAddedDate = docAddedDate;
  }

  public Set<CmsDocReferralClientDetail> getDetails() {
    return details;
  }

  public void setDetails(Set<CmsDocReferralClientDetail> details) {
    this.details = details;
  }

  /**
   * Fetch underlying document
   * 
   * @return the document linked to referral/client
   */
  public CmsDocReferralClientDocument getCmsDocument() {
    return cmsDocument;
  }

  /**
   * Set the underlying document
   * 
   * @param cmsDocument the document linked to referral/client
   */
  public void setCmsDocument(CmsDocReferralClientDocument cmsDocument) {
    this.cmsDocument = cmsDocument;
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
