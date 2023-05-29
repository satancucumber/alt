package LETI.alt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "evidence", schema = "detective")
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "left_pos")
    private Integer left;
    @Column(name = "right_pos")
    private Integer right;
    @Column(name = "top_pos")
    private Integer top;
    @Column(name = "bottom_pos")
    private Integer bottom;

//    @ManyToOne (optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="formula_id")
//    private Formula formula;
//    @ManyToOne (optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="plot_id")
//    private Plot plot;

//    public Formula getFormula() {
//        return this.formula;
//    }
//
//    public void setFormula(Formula formula) {
//        this.formula = formula;
//    }

//    public Plot getPlot() {
//        return this.plot;
//    }
//
//    public void setPlot(Plot plot) {
//        this.plot = plot;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getBottom() {
        return bottom;
    }

    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }

}
