package pl.martyna.lotto.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "draw")
@Table(name = "draw_history")
public class Draw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result_date")
    private final LocalDateTime resultDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "draw")
    private Set<Result> results = new HashSet<>(0);

    public Set<Result> getResults(){
        return results;
    }

    public void setResults( Set<Result> results){
        this.results = results;
    }

    public Draw(){
        resultDate = LocalDateTime.now();
    }
}
