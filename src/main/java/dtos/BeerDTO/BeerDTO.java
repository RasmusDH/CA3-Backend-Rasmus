package dtos.BeerDTO;

import entities.Beer.Beer;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BeerDTO {
    private Integer id;
    private String name;
    private String tagline;
    private String description;

    public static List<BeerDTO> getBeerFromList(List<Beer> beers) {
        return beers.stream()
                .map(beer -> new BeerDTO(beer))
                .collect(Collectors.toList());
    }

    public BeerDTO() {
    }

    public BeerDTO(String name, String tagline, String description) {
        this.name = name;
        this.tagline = tagline;
        this.description = description;
    }
    
    public BeerDTO(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getBeerName();
        this.tagline = beer.getTagline();
        this.description = beer.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeerDTO)) {
            return false;
        }
        BeerDTO that = (BeerDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getTagline(), that.getTagline())
            && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTagline(), getDescription());
    }
    
    
    
}
