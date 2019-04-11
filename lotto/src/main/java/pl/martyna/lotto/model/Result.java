package pl.martyna.lotto.model;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "draw_id")
    private Draw draw;

    public int getNumber(){
        return number;
    }

    public Result(Integer number, Draw draw){
        this.number = number;
        this.draw = draw;
    }

}
