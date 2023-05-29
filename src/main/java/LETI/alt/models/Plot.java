package LETI.alt.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "plot", schema = "detective")
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
//    @Column(name = "img")
//    private String img;
    @Column(name = "text")
    private List<String> text;
    @OneToMany (mappedBy="plot", fetch=FetchType.EAGER)
    private List<Literal> literals;
    @OneToMany (mappedBy="plot", fetch=FetchType.EAGER)
    private List<Formula> formulas;
//    @OneToMany (mappedBy="plot", fetch=FetchType.EAGER)
//    private List<Evidence> evidences;

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

//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<Literal> getLiterals() {
        return literals;
    }

    public void setLiterals(List<Literal> literals) {
        this.literals = literals;
    }

    public List<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }

//    public List<Evidence> getEvidences() {
//        return evidences;
//    }
//
//    public void setEvidences(List<Evidence> evidences) {
//        this.evidences = evidences;
//    }
}
