package facades;

import com.google.gson.Gson;
import exceptions.NotFoundException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import model.Person;
import org.omg.CORBA.SystemException;

/**
 *
 * @author jakobgaardandersen
 */
public class PersonFacadeDB implements IPersonFacade {

    private static PersonFacadeDB instance;

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private final Gson gson;

    private PersonFacadeDB() {
        this.emf = Persistence.createEntityManagerFactory("RestCRUDPU");
        this.em = emf.createEntityManager();
        this.gson = new Gson();
    }

    public static PersonFacadeDB getFacade() {
        if (instance == null) {
            instance = new PersonFacadeDB();
        }
        return instance;
    }

    /*
     From JSON to Person to database. If Transaction fails returns null, else returns new Person obj
     */
    @Override
    public Person addPerson(String json) {
        Person p = gson.fromJson(json, Person.class);
        em.getTransaction().begin();
        try {
            em.persist(p); // persist should put to database and automatically give an id
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            p = null;
        }
        return p;
    }

    /*
     deletes person from database from ID, throws NotFoundEx if person does not exist
     returns null if transaction failed. (also calls rollback)
     */
    @Override
    public Person deletePerson(int id) throws NotFoundException {
        Person p = em.find(Person.class, id);
        if (p == null) {
            throw new NotFoundException("No person exists for the given id");
        }
        em.getTransaction().begin();
        try {
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            p = null;
        }
        return p;
    }

    /*
     returns persons from id as JSON or throws NotFoundEx 
     */
    @Override
    public String getPerson(int id) throws NotFoundException {
        Person p = em.find(Person.class, id);
        if (p == null) {
            throw new NotFoundException("No person exists for the given id");
        }
        return gson.toJson(p);
    }

    /*
     returns all persons as JSON
     */
    @Override
    public String getPersons() {
        Collection<Person> resultList = em.createNamedQuery("Person.findAll").getResultList();
        return gson.toJson(resultList);
    }

    @Override
    public Person editPerson(String json) throws NotFoundException {
        Person newValue = gson.fromJson(json, Person.class);
        System.out.println("CHANGED/NEW VALUE: " + newValue.getfName());
        Person oldValue = em.find(Person.class, newValue.getId());
        System.out.println("THE ORIGINAL VALUE THAT SHOULD BE RETURNED: " + oldValue.getfName());
        if (oldValue == null) {
            throw new NotFoundException("No person exists for the given id");
        }
        em.getTransaction().begin();
        try {
            em.merge(newValue);
            System.out.println("After merge (the original: " + oldValue.getfName());
            System.out.println("After merge (new val: " + newValue.getfName());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            oldValue = null;
            em.getTransaction().rollback();
        }
        return oldValue;
    }

    public void clearTablesForTesting() {
        try {
            em.getTransaction().begin();

            Query q3 = em.createNativeQuery("DELETE FROM PERSON");

            q3.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
