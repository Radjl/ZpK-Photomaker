package models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;



@NoArgsConstructor
@Setter
@Getter
@Entity
public class Carriage {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quanity;
    private String password;
    private boolean active;
    private LocalDate localDate = LocalDate.now();
    private LocalTime localTime = LocalTime.now();
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String img;
    private LinkedList<String> photos = new LinkedList<>();





}
