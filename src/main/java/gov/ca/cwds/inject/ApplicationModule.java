package gov.ca.cwds.inject;

import com.google.inject.AbstractModule;

import gov.ca.cwds.auth.ScreeningAuthorizer;
import gov.ca.cwds.authorizer.ClientAbstractReadAuthorizer;
import gov.ca.cwds.rest.ApiApplication;
import gov.ca.cwds.rest.ApiConfiguration;
import gov.ca.cwds.rest.BaseApiApplication;
import gov.ca.cwds.security.module.SecurityModule;
import io.dropwizard.setup.Bootstrap;

/**
 * Bootstraps and configures the CWDS RESTful API application.
 * 
 * @author CWDS API Team
 * @see ApiApplication
 */
public class ApplicationModule extends AbstractModule {

  private Bootstrap<ApiConfiguration> bootstrap;

  private DataAccessModule dataAccessModule;

  /**
   * Constructor. {@link AbstractModule#AbstractModule()}
   * 
   * @param bootstrap API configuration
   */
  public ApplicationModule(Bootstrap<ApiConfiguration> bootstrap) {
    super();
    this.bootstrap = bootstrap;
  }

  /**
   * Configure and initialize API components, including services, resources, data access objects
   * (DAO), web service filters, and auditing.
   * 
   * {@inheritDoc}
   */
  @Override
  protected void configure() {
    dataAccessModule = new DataAccessModule(bootstrap);
    install(dataAccessModule);
    install(new DataAccessServicesModule());
    install(new ServicesModule());
    install(new MappingModule());
    install(new ResourcesModule());
    install(new FiltersModule());
    install(new AuditingModule());
    install(new TestModule());
    install(new HealthCheckModule());
    install(new SecurityModule(BaseApiApplication::getInjector)
        .addAuthorizer("client:read", ClientAbstractReadAuthorizer.class)
        .addAuthorizer("screening.read", ScreeningAuthorizer.class));
  }

  public DataAccessModule getDataAccessModule() {
    return dataAccessModule;
  }

}
