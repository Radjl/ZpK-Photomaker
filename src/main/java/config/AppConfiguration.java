package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import java.util.Date;

@Configuration
public class AppConfiguration {

    //Путь сохранения для настроек спринга
    private String uploadPathPhoto = System.getProperty("user.home")+"/Desktop/temp";

    //Путь сохранения для класса конектор
    private String uploadPathConnector = CheckOutPathToSave();

    //Задержка перед фото
    private int photoDelay = 2000;


    public String getUploadPathConnector() {
        return uploadPathConnector;
    }

    public String getUploadPathPhoto() {
        return uploadPathPhoto;
    }

    public void setUploadPathPhoto(String uploadPathPhoto) {
        this.uploadPathPhoto = uploadPathPhoto;
    }

    public int getPhotoDelay() {
        return photoDelay;
    }

    public void setPhotoDelay(int photoDelay) {
        this.photoDelay = photoDelay;
    }

    private String CheckOutPathToSave() {
        Date date = new Date();

        return System.getProperty("user.home")+"\\Desktop\\temp\\img"+date.getTime()+".jpeg";
    }

}
