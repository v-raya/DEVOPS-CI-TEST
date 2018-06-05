package gov.ca.cwds.inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import gov.ca.cwds.data.es.ElasticsearchDao;
import gov.ca.cwds.data.persistence.ns.papertrail.PaperTrailInterceptor;
import gov.ca.cwds.rest.ApiConfiguration;
import gov.ca.cwds.rest.ElasticsearchConfiguration;
import gov.ca.cwds.rest.TriggerTablesConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;

public class DataAccessModuleTest {

  public static class TestDataAccessModule extends AbstractModule {

    private DataAccessModule dataAccessModule;

    @Override
    protected void configure() {
      install(dataAccessModule);
    }

    public DataAccessModule getDataAccessModule() {
      return dataAccessModule;
    }

    public void setDataAccessModule(DataAccessModule dataAccessModule) {
      this.dataAccessModule = dataAccessModule;
    }
  }

  Bootstrap<ApiConfiguration> bootstrap;
  ApiConfiguration apiConfiguration;
  DataAccessModule target;

  @Before
  public void setup() throws Exception {
    bootstrap = mock(Bootstrap.class);
    apiConfiguration = mock(ApiConfiguration.class);
    target = new DataAccessModule(bootstrap);
  }

  @Test
  public void type() throws Exception {
    assertThat(DataAccessModule.class, notNullValue());
  }

  @Test
  public void instantiation() throws Exception {
    assertThat(target, notNullValue());
  }

  @Test
  public void configure_A$() throws Exception {
    final TestDataAccessModule module = new TestDataAccessModule();
    module.setDataAccessModule(target);

    final Injector injector = Guice.createInjector(module);
  }

  @Test
  public void cmsSessionFactory_A$() throws Exception {
    SessionFactory actual = target.cmsSessionFactory();
    SessionFactory expected = null;
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void nsSessionFactory_A$() throws Exception {
    SessionFactory actual = target.nsSessionFactory();
    SessionFactory expected = null;
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void rsSessionFactory_A$() throws Exception {
    SessionFactory actual = target.rsSessionFactory();
    SessionFactory expected = null;
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void cmsHibernateBundle_A$() throws Exception {
    HibernateBundle<ApiConfiguration> actual = target.cmsHibernateBundle();
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void nsHibernateBundle_A$() throws Exception {
    HibernateBundle<ApiConfiguration> actual = target.nsHibernateBundle();
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void rsHibernateBundle_A$() throws Exception {
    HibernateBundle<ApiConfiguration> actual = target.rsHibernateBundle();
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void getXaCmsHibernateBundle_A$() throws Exception {
    HibernateBundle<ApiConfiguration> actual = target.getXaCmsHibernateBundle();
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void xaNsSessionFactory_A$() throws Exception {
    SessionFactory actual = target.xaNsSessionFactory();
    SessionFactory expected = null;
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void xaCmsSessionFactory_A$() throws Exception {
    SessionFactory actual = target.xaCmsSessionFactory();
    SessionFactory expected = null;
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getXaNsHibernateBundle_A$() throws Exception {
    HibernateBundle<ApiConfiguration> actual = target.getXaNsHibernateBundle();
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void elasticSearchConfigs_A$ApiConfiguration() throws Exception {
    Map<String, ElasticsearchConfiguration> actual = target.elasticSearchConfigs(apiConfiguration);
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void triggerTablesConfiguration_A$ApiConfiguration() throws Exception {
    TriggerTablesConfiguration actual = target.triggerTablesConfiguration(apiConfiguration);
    TriggerTablesConfiguration expected = null;
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void provideElasticSearchDaos_A$ApiConfiguration() throws Exception {
    Map<String, ElasticsearchDao> actual = target.provideElasticSearchDaos(apiConfiguration);
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void provideEelasticSearchDaoScreenings_A$Map() throws Exception {
    Map<String, ElasticsearchDao> esDaos = new HashMap<String, ElasticsearchDao>();
    ElasticsearchDao actual = target.provideElasticSearchDaoScreenings(esDaos);
    ElasticsearchDao expected = null;
    assertThat(actual, is(expected));
  }

  @Test
  public void provideElasticsearchClients_A$ApiConfiguration() throws Exception {
    Map<String, Client> actual = target.provideElasticsearchClients(apiConfiguration);
    assertThat(actual, is(notNullValue()));
  }

  @Test
  public void getPaperTrailInterceptor_A$() throws Exception {
    PaperTrailInterceptor actual = target.getPaperTrailInterceptor();
    assertThat(actual, is(notNullValue()));
  }

}
