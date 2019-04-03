package config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@Configuration
public class AppConfiguration {

    //Путь сохранения для настроек спринга
    private String uploadPathPhoto = System.getProperty("user.home")+"/Desktop/temp";

    //Путь сохранения для класса конектор
    private String uploadPathConnector = CheckOutPathToSave();

    //Задержка перед фото
    private int photoDelay = 2000;




    private String CheckOutPathToSave() {
        Date date = new Date();

        return System.getProperty("user.home")+"\\Desktop\\temp\\img"+date.getTime()+".jpeg";
    }

}
