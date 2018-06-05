package gov.ca.cwds.rest.services.cms;

import javax.persistence.EntityExistsException;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import gov.ca.cwds.data.Dao;
import gov.ca.cwds.data.cms.DrmsDocumentDao;
import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;
import gov.ca.cwds.data.persistence.cms.DrmsDocument;
import gov.ca.cwds.rest.api.domain.cms.PostedDrmsDocument;
import gov.ca.cwds.rest.filters.RequestExecutionContext;
import gov.ca.cwds.rest.messages.MessageBuilder;
import gov.ca.cwds.rest.services.ServiceException;
import gov.ca.cwds.rest.services.TypedCrudsService;

/**
 * Business layer object to work on {@link DrmsDocument}
 * 
 * @author CWDS API Team
 */
public class DrmsDocumentService implements
    TypedCrudsService<String, gov.ca.cwds.rest.api.domain.cms.DrmsDocument, gov.ca.cwds.rest.api.domain.cms.DrmsDocument> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DrmsDocumentService.class);

  private DrmsDocumentDao drmsDocumentDao;

  /**
   * @param drmsDocumentDao {@link Dao} handling
   *        {@link gov.ca.cwds.data.persistence.cms.DrmsDocument} objects
   *        {@link gov.ca.cwds.data.persistence.cms.DrmsDocument} objects
   */
  @Inject
  public DrmsDocumentService(DrmsDocumentDao drmsDocumentDao) {
    super();
    this.drmsDocumentDao = drmsDocumentDao;
  }

  /**
   * {@inheritDoc}
   * 
   * @see gov.ca.cwds.rest.services.CrudsService#create(gov.ca.cwds.rest.api.Request)
   */
  @Override
  public PostedDrmsDocument create(gov.ca.cwds.rest.api.domain.cms.DrmsDocument request) {

    gov.ca.cwds.rest.api.domain.cms.DrmsDocument drmsDocument = request;

    try {
      DrmsDocument managed = new DrmsDocument(
          CmsKeyIdGenerator.getNextValue(RequestExecutionContext.instance().getStaffId()),
          drmsDocument, RequestExecutionContext.instance().getStaffId(),
          RequestExecutionContext.instance().getRequestStartTime());
      managed = drmsDocumentDao.create(managed);
      return new PostedDrmsDocument(managed);
    } catch (EntityExistsException e) {
      LOGGER.info("drmsDocument already exists : {}", drmsDocument);
      throw new ServiceException(e);
    }

  }

  @Override
  public gov.ca.cwds.rest.api.domain.cms.DrmsDocument delete(String primaryKey) {
    throw new NotImplementedException("delete not implement");
  }

  @Override
  public gov.ca.cwds.rest.api.domain.cms.DrmsDocument find(String primaryKey) {
    throw new NotImplementedException("find not implement");
  }

  @Override
  public gov.ca.cwds.rest.api.domain.cms.DrmsDocument update(String primaryKey,
      gov.ca.cwds.rest.api.domain.cms.DrmsDocument request) {
    throw new NotImplementedException("update not implement");
  }

  /**
   * <blockquote>
   *
   * <pre>
   * BUSINESS RULE: "R - 07577" - Create Dummy Docs for Referral
   *
   * When Referral is Posted, it creates three dummy document values in the drmsDocument and
   * assigned the identifer in the referrals(drmsAllegationDescriptionDoc, drmsErReferralDoc,
   * drmsInvestigationDoc).
   *
   * </pre>
   *
   * </blockquote>
   *
   * @param messageBuilder the messaging object to build error messages
   * @return The Id of the created DRMS Document
   * @throws ServiceException - serviceException
   */
  public String generateDrmsDocumentId(MessageBuilder messageBuilder) {
    PostedDrmsDocument postedDrmsDocument = null;
    try {
      gov.ca.cwds.rest.api.domain.cms.DrmsDocument drmsDocument =
          gov.ca.cwds.rest.api.domain.cms.DrmsDocument
              .createDefaults(RequestExecutionContext.instance().getStaffId());
      postedDrmsDocument = create(drmsDocument);
    } catch (ServiceException e) {
      String message = e.getMessage();
      messageBuilder.addMessageAndLog(message, e, LOGGER);
    }
    if (postedDrmsDocument == null) {
      throw new ServiceException("Unable to Create DRMS Documents");
    }
    return postedDrmsDocument.getId();
  }

}
