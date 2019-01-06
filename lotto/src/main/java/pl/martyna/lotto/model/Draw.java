package pl.martyna.lotto.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents draw_history table's entities
 * @author Martyna Drabinska
 * @version 0.1
 */
@Entity(name = "draw")
@Table(name = "draw_history")
public class Draw {

    /** ID value */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Date of drawing */
    @Column(name = "result_date")
    private final LocalDateTime resultDate;

    /** Results of drawing */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "draw")
    private Set<Result> results = new HashSet<>(0);

    /**
     * Returns results
     * @return results
     */
    public Set<Result> getResults(){
        return results;
    }

    /**
     * Sets results attribute
     * @param results set of results
     */
    public void setResults( Set<Result> results){
        this.results = results;
    }

    /** Default constructor */
    public Draw(){
        resultDate = LocalDateTime.now();
    }
}
