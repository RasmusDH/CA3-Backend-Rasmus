package facades;

import dtos.BeerDTO.BeerDTO;
import dtos.RenameMeDTO;
import entities.Beer.Beer;
import entities.Beer.BeerRepository;
import entities.User;
import entities.renameme.RenameMe;
import entities.renameme.RenameMeRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BeerFacade implements BeerRepository {

    private static BeerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BeerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BeerFacade getBeerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BeerFacade();
        }
        return instance;
    }

    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }

    @Override
    public List<BeerDTO> getUserBeers(String username) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Beer> beers = em.createQuery("SELECT b FROM Beer b JOIN b.userList u WHERE u.userName = :username", Beer.class)
                    .setParameter("username", username)
                    .getResultList();
            return BeerDTO.getBeerFromList(beers);
        } catch (Exception e) {
            throw new WebApplicationException("Cant find any beers for this user", 400);
            
        } finally {
            em.close();
        }
    }

    @Override
    public BeerDTO createBeer(BeerDTO beerDTO, String username) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Beer beer = new Beer(beerDTO);
        User user = em.find(User.class, username);
        try {
            em.getTransaction().begin();
            beer.addUser(user);
            em.persist(beer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        BeerDTO newBeerDTO = new BeerDTO(beer);
        return newBeerDTO;
    }

    @Override
    public BeerDTO getById(int id) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BeerDTO> getAll() throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
