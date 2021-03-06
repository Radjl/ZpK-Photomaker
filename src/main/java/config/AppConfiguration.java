package config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
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
    private int photoDelay = 1700;
    //Урл камеры
    private String camUrl = "http://10.100.100.132/Streaming/channels/1/picture?snapShotImageType=JPEG";
    //Урл реле
    private String controllerUrl = "http://admin:vkmodule@10.47.1.101/protect/status.xml";



    private String CheckOutPathToSave() {
        Date date = new Date();

        return System.getProperty("user.home")+"\\Desktop\\temp\\img"+date.getTime()+".jpeg";
    }

}
