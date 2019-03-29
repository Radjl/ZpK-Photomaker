package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;

@Configuration
public class SysPathConfig {

    private String uploadPathPhoto = System.getProperty("user.home")+"/Desktop/temp";

    public String getUploadPathPhoto() {
        return uploadPathPhoto;
    }

    public void setUploadPathPhoto(String uploadPathPhoto) {
        this.uploadPathPhoto = uploadPathPhoto;
    }
}
