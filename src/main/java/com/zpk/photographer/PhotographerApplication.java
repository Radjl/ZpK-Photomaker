package com.zpk.photographer;

import Photo.Connector2;
import models.CarriageMassive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication(scanBasePackages = {"controllers","controllers","models","services","repository","config","Photo"})
@EnableJpaRepositories("repository")
@EntityScan("models")
public class PhotographerApplication implements CommandLineRunner {



    @Autowired
    Connector2 connector2;


    @Override
    public void run(String... args) throws Exception {

            while (true){

                CarriageMassive carriageMassive = new CarriageMassive();
                System.out.println("Done");




             while (carriageMassive.done!=true) {
                  connector2.Start(carriageMassive);
                  Thread.sleep(300);
              }

            }

    }

    public static void main(String[] args) {
        SpringApplication.run(PhotographerApplication.class, args);



    }



}
