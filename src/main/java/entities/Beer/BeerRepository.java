package entities.Beer;

import entities.renameme.*;
import dtos.BeerDTO.BeerDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface BeerRepository {

    public List<BeerDTO> getUserBeers(String username) throws WebApplicationException;
    
    public BeerDTO getById(int id) throws WebApplicationException;

    public List<BeerDTO> getAll() throws WebApplicationException;

    public BeerDTO createBeer(BeerDTO beerDTO, String username) throws WebApplicationException;

}
