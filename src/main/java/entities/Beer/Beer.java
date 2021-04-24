/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.Beer;

import dtos.BeerDTO.BeerDTO;
import entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rasmus
 */
@Entity
@Table(name = "beers")
@NamedQuery(name = "Beer.deleteAllRows", query = "DELETE from Beer")
public class Beer implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String beerName;
    private String tagline;
    private String description;
    
    
    @ManyToMany(mappedBy = "beers")
    private List<User> userList;
    
    public Beer() {
        
    }
    
    
    
    public Beer(String beerName, String tagline, String description) {
        this.beerName = beerName;
        this.tagline = tagline;
        this.description = description;
        this.userList = new ArrayList<>();
    }
    
    public Beer(BeerDTO beerDTO) {
        this.beerName = beerDTO.getName();
        this.tagline = beerDTO.getTagline();
        this.description = beerDTO.getDescription();
        this.userList = new ArrayList<>();
    }
    
    public void addUser(User user) {
        if (user != null) {
            this.userList.add(user);
            user.addBeer(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Beer{" + "id=" + id + ", beerName=" + beerName + ", tagline=" + tagline + ", description=" + description + ", userList=" + userList + '}';
    }
    
    
    
}
