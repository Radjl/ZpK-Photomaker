package models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.LinkedList;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class CarriageMassive {

    public boolean done;
    public int timer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime EndTime;
    private LinkedList<String> photos = new LinkedList<>();
    private int lastState = 0;
    private boolean photoDone = false;



}
