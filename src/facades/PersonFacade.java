package facades;

import com.google.gson.Gson;
import exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import model.Person;

/**
 * @author Lars Mortensen
 */
public class PersonFacade implements IPersonFacade {
  Map<Integer,Person> persons = new HashMap();
  private int nextId;
  private final Gson gson = new Gson();

  public PersonFacade() {
  }
  
  public PersonFacade(boolean test) {
    addPerson(gson.toJson(new Person("AAA","BBB","CCC")));
    addPerson(gson.toJson(new Person("BBB","BBB","BBBB")));
   
  }
  
  

  @Override
  public Person addPerson(String json) {
    Person p = gson.fromJson(json, Person.class);
    System.out.println(p);
    p.setId(nextId);
    persons.put(nextId, p);
    nextId++;
    return p; 
  }

  @Override
  public Person deletePerson(int id) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getPerson(int id) throws NotFoundException {
    Person p = persons.get(id);
    if(p==null){
      throw new NotFoundException("No person exists for the given id");
    }
    return gson.toJson(p);
  }

  @Override
  public String getPersons() {
    if(persons.isEmpty()){
      return null;
    }
    return gson.toJson(persons.values());
  }

  @Override
  public Person editPerson(String json) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
