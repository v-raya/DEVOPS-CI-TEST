package gov.ca.cwds.inject;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;

import gov.ca.cwds.data.persistence.xa.WorkDB2UserInfo;

public class CaresDB2DatasourceConnectionProviderImpl extends DatasourceConnectionProviderImpl {

  private static final long serialVersionUID = 1L;

  @Override
  public Connection getConnection() throws SQLException {
    final Connection con = super.getConnection();
    new WorkDB2UserInfo().execute(con);
    return con;
  }

}
