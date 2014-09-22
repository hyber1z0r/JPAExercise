package facades;

import com.google.gson.Gson;
import exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonFacadeTest {

  PersonFacade facade;
  Gson gson = new Gson();

  public PersonFacadeTest() {
  }

  @Before
  public void x() {
    facade = new PersonFacade();
  }

  @Test
  public void testAddPerson() throws NotFoundException {
    Person p = new Person("aaa", "bbb", "ccc");
    String personAsJson = gson.toJson(p);
    Person person = facade.addPerson(personAsJson);
    String actual = facade.getPerson(person.getId());
    assertEquals(personAsJson, actual);
  }

  @Test
  public void testDeletePerson() {
    System.out.println("deletePerson");
    int id = 0;
    PersonFacade instance = new PersonFacade();
    Person expResult = null;
    Person result = instance.deletePerson(id);
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testGetPerson() throws Exception {
    Person p = new Person("aaa", "bbb", "ccc");
    String personAsJson = gson.toJson(p);
    Person person = facade.addPerson(personAsJson);
    String actual = facade.getPerson(person.getId());
    assertEquals(personAsJson, actual);
  }
  
  @Test(expected = NotFoundException.class)
    public void testGetNonExistingPerson() throws Exception {
    facade.getPerson(5);
    
  }

  @Test
  public void testGetPersons() {
    Person p = new Person("aaa", "bbb", "ccc");
    Person person1 = facade.addPerson(gson.toJson(p));
    Person p2 = new Person("bbb", "bbb", "bbb");
    Person person2 = facade.addPerson(gson.toJson(p2));
    
    Map<Integer,Person> test = new HashMap();
    test.put(person1.getId(),person1);
    test.put(person2.getId(),person2);
    String expected = gson.toJson(test.values());
    
    String result = facade.getPersons();
    assertEquals(expected,result);

  }

  @Test
  public void testEditPerson() {
    System.out.println("editPerson");
    String json = "";
    PersonFacade instance = new PersonFacade();
    Person expResult = null;
    Person result = instance.editPerson(json);
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

}
