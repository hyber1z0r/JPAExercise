package program;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;

/**
 *
 * @author jakobgaardandersen
 */
public class App {

    public void testMethod() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestCRUDPU");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("Leon","Hansen","11111111");
        Person p2 = new Person("Kurt","Severinsen","22222222");
        Person p3 = new Person("Ivan","Lorentsen","33333333");
        Person p4 = new Person("Kenny","Eberhart","44444444");

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        
        new App().testMethod();

    }

}
