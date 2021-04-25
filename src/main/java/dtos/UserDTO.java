/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import dtos.BeerDTO.BeerDTO;
import entities.Beer.Beer;
import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rasmus
 */
public class UserDTO {
    private String userName;
    private String hashPassword;
    private List<Role> roleList;
    private List<BeerDTO> beers;

    public UserDTO() {
    }

    public UserDTO(String userName, String hashPassword, List<Role> roleList, List<BeerDTO> beers) {
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.roleList = roleList;
        this.beers = beers;
    }

   public UserDTO(User user) {
        this.userName = user.getUserName();
        this.hashPassword = user.getUserPass();
        this.roleList = user.getRoleList();
        this.beers = BeerDTO.getBeerFromList(user.getBeers());
    }
    public List<String> getRolesAsStrings() {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roleList.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<BeerDTO> getBeers() {
        return beers;
    }

    public void setBeers(List<BeerDTO> beers) {
        this.beers = beers;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userName=" + userName + ", hashPassword=" + hashPassword + ", roleList=" + roleList + ", beers=" + beers + '}';
    }
    
    
}
