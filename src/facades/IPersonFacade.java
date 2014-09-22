package facades;

import model.Person;

/**
 * @author Lars
 */
public interface IPersonFacade {
  public Person addPerson(String json);  
  public Person deletePerson(int id);  
  public String getPerson(int id);  
  public String getPersons();  
  public Person editPerson(String json);  
}
