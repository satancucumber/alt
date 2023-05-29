package LETI.alt.models;

import jakarta.persistence.*;

@Entity
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
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="plot_id")
    private Plot plot;

//    public Plot getPlot() {
//        return plot;
//    }

    public void setPlot(Plot plot) {
        this.plot = plot;
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
