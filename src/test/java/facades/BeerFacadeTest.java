package facades;

import dtos.BeerDTO.BeerDTO;
import dtos.RenameMeDTO;
import entities.Beer.Beer;
import entities.Beer.BeerRepository;
import entities.Role;
import entities.User;
import entities.renameme.RenameMeRepository;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import entities.renameme.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class BeerFacadeTest {

    private static EntityManagerFactory emf;
    private static BeerRepository facade;
    public static Beer beer1, beer2;
    User user;
    Role role;

    public BeerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = BeerFacade.getBeerFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        beer1 = new Beer("Carlsberg", "dansk øl", "Dansk produceret øl");
        beer2 = new Beer("Tuburg", "dansk øl", "Dansk produceret ølmærke");
        
        role = new Role("user");
        user = new User("user", "test");
        
        user.addRole(role);
        beer1.addUser(user);
        beer2.addUser(user);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Beer.deleteAllRows").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            
            em.persist(role);
            em.persist(user);
            
            em.persist(beer1);
            em.persist(beer2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    @Test
    void createBeer() {
        BeerDTO expectedResult = new BeerDTO("Test", "Test1", "Test2");
        BeerDTO actualResult = facade.createBeer(expectedResult, user.getUserName());
        assertNotNull(actualResult.getId());
        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getTagline(), actualResult.getTagline());
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
        
    }
    
}
