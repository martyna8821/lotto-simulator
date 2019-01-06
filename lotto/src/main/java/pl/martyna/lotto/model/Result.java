package pl.martyna.lotto.model;

import javax.persistence.*;

/**
 *  Represents results table's entities
 * @author Martyna Drabinska
 * @version 0.1
 */
@Entity
@Table(name = "results")
public class Result {

    /** ID value */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Result value*/
    @Column(name = "number", nullable = false)
    private int number;

    /** Draw entity of this result */
    @ManyToOne
    @JoinColumn(name = "draw_id")
    private Draw draw;

    /**
     * Returns result value
     * @return result value
     */
    public int getNumber(){
        return number;
    }

    /**
     * Constructor
     * @param number result value
     * @param draw draw entity of this result
     */
    public Result(Integer number, Draw draw){
        this.number = number;
        this.draw = draw;
    }

    /** Default constructor */
    public Result(){

    }

}
