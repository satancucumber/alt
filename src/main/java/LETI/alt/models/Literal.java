package LETI.alt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter

@Table(name = "literal", schema = "detective")
public class Literal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "suspect")
    private Boolean suspect;
    @ManyToMany
    @JoinTable (name="formula_literal", schema = "detective",
            joinColumns=@JoinColumn (name="literal_id"),
            inverseJoinColumns=@JoinColumn(name="formula_id"))
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private List<Formula> formulas;
    @JsonIgnore
    private Boolean nerative;

    public Literal() {
    }

    public Literal(Long id, String name, String description, Boolean suspect, List<Formula> formulas, Boolean nerative) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.suspect = suspect;
        this.formulas = formulas;
        this.nerative = nerative;
    }

    public Boolean getNerative() {
        return nerative;
    }

    public void setNerative(Boolean nerative) {
        this.nerative = nerative;
    }

    public List<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }


    public Boolean getSuspect() {
        return suspect;
    }

    public void setSuspect(Boolean suspect) {
        this.suspect = suspect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}