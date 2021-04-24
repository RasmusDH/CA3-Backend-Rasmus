package rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dtos.Example.AnimeDTO;
import dtos.BeerDTO.BeerDTO;
import dtos.Example.CatDTO;
import dtos.Example.ChuckDTO;
import dtos.Example.CombinedDTO;
import dtos.Example.JokeDTO;
import dtos.Example.WeatherDTO;
import dtos.RenameMeDTO;
import entities.Beer.BeerRepository;
import entities.User;
import facades.BeerFacade;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import rest.provider.Provider;
import utils.EMF_Creator;
import utils.HttpUtil;

/**
 * @author lam@cphbusiness.dk
 */
@Path("beer")
public class BeerResource extends Provider {

    Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final BeerRepository beerRepo = BeerFacade.getBeerFacade(EMF);
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;
    

    @Override
    public Response getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed({"user", "admin"})
    public Response getAll() {
        String username = securityContext.getUserPrincipal().getName();
        List<BeerDTO> beerDTOs = beerRepo.getUserBeers(username);
        return Response.ok(GSON.toJson(beerDTOs)).build();
    }

    @Override
    @RolesAllowed({"user", "admin"})
    public Response create(String jsonBody) {
        String username = securityContext.getUserPrincipal().getName();
        BeerDTO beerDTO = GSON.fromJson(jsonBody, BeerDTO.class);
        BeerDTO madeBeerDTO = beerRepo.createBeer(beerDTO, username);
        return Response.ok(GSON.toJson(madeBeerDTO)).build();
    }

    @Override
    public Response update(int id, String jsonBody) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
