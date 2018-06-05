package gov.ca.cwds.rest.business;

/**
 * A common interface for executing legacy business rules
 *
 * Validators perform some sort of Business Rule action and does not return a value.
 */
public interface RuleAction {

  /**
   * 
   */
  public void execute();
}
