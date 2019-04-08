package Photo;

import config.AppConfiguration;
import lombok.NoArgsConstructor;
import models.Carriage;
import models.CarriageMassive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import repository.CarriageMassiveRepo;
import repository.CarriageRepo;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


@NoArgsConstructor
@Service
public class Connector2 {



    @Autowired
    private CarriageRepo carriageRepo;

    @Autowired
    CarriageMassiveRepo carriageMassiveRepo;

    @Autowired
    AppConfiguration appConfiguration;



    public void Start(CarriageMassive carriageMassive, Carriage carriage) throws Exception {


        String off = "up";
        String on = "dn";

        //Проверка на сохранение обьекта
        CheckForSaveCarriageMassive(carriageMassive);

        //Создание Данных для авторизации
        MyAuthenticator myAuthenticator = new MyAuthenticator();
        Authenticator.setDefault(myAuthenticator);

        //Обработка
        ProcessConnectAlgorythm(carriageMassive, off, on, myAuthenticator,carriage);


    }

    private void ProcessConnectAlgorythm(CarriageMassive carriageMassive, String off, String on, MyAuthenticator myAuthenticator, Carriage carriage) throws IOException {
        try {

               // File file = new File("C:\\Users\\sheludko\\Desktop\\test.xml");
               // URL url = file.toURL();
              URLConnection con = new URL(appConfiguration.getControllerUrl()).openConnection();
              //  URLConnection con = url.openConnection();




            Document doc = parseXML(con.getInputStream());

            NodeList descNodes = doc.getElementsByTagName("btn0");

            for (int i = 0; i < descNodes.getLength(); i++) {
                System.out.println("Состояние луча: - " + descNodes.item(i).getTextContent());




                if (descNodes.item(i).getTextContent().equalsIgnoreCase(on)) {


                    carriageMassive.setLastState(1);
                    System.out.println("Луч оборвался , Состояние установлено - 1");




                    if (carriageMassive.getLastState() == 1 && !carriageMassive.isPhotoDone()){
                        System.out.println("Проверка на состояние и сделанное фото ");



                        Thread.sleep(appConfiguration.getPhotoDelay());
                        System.out.println("Поток выспался - "+ appConfiguration.getPhotoDelay());



                        myAuthenticator.setUsername("admin");
                        myAuthenticator.setPassword("h31FF9c4");




                        URLConnection conCam = new URL(appConfiguration.getCamUrl()).openConnection();
                        System.out.println("Получаем соединение с камерой");


                        BufferedImage bufferedImage = ImageIO.read(conCam.getInputStream());
                        System.out.println("Буферизируем поток данных с камеры");


                        Date date = new Date();
                        String path =  System.getProperty("user.home")+"\\Desktop\\temp\\img"+date.getTime()+".jpeg";

                        File f = new File(path);
                        System.out.println("Создаем файл с именем пути для сохранения");


                            String dbImagePathResult = f.getName();
                        System.out.println("Получаем имя файла: " + dbImagePathResult);



                            ImageIO.write(bufferedImage,"JPEG",f);
                        System.out.println("Записываем фото на диск");


                            carriageMassive.getPhotos().add(dbImagePathResult);

                             carriage.getPhotos().add(dbImagePathResult);
                             carriageRepo.save(carriage);
                        System.out.println("Добавляем фото в масив обьекта подач");


                            carriageMassive.timer = 0;
                        System.out.println("Сбрасываем таймер, таймер после сброса = " + carriageMassive.timer);

                            carriageMassive.setPhotoDone(true);
                        System.out.println("Выставляем флаг что фото готово");





                    }




                }else if (descNodes.item(i).getTextContent().equalsIgnoreCase(off)){
                    System.out.println("Луч не прерывался , состояние - 0 , Фото не сделано");
                    carriageMassive.setLastState(0);
                    carriageMassive.setPhotoDone(false);
                    carriageMassive.timer ++;
                    System.out.println("Таймер увеличен " + "текущий счетчик таймера - " + carriageMassive.timer);
                    System.out.println("");

                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            URLConnection con = new URL(appConfiguration.getControllerUrl()).openConnection();
            URLConnection conCam = new URL(appConfiguration.getCamUrl()).openConnection();
        }
    }

    private void CheckForSaveCarriageMassive(CarriageMassive carriageMassive) {
        if (carriageMassive.timer > 100 && carriageMassive.getPhotos().size() > 7){
            carriageMassive.done = true;
            carriageMassiveRepo.save(carriageMassive);
            carriageRepo.deleteAll();
            appConfiguration.setPhotoDelay(1700);
            System.out.println("Новый обьект записан в базу и сохранён");
        }


        if (carriageMassive.timer > 100){
            carriageRepo.deleteAll();
        }


        if (carriageMassive.timer > 1000 && carriageMassive.getPhotos().size() < 7){
            carriageMassive.done = true;
            carriageRepo.deleteAll();
            appConfiguration.setPhotoDelay(1700);
            System.out.println("обьект не записан , выход из цикла , пересоздание обьекта с нуля");
        }




    }

    private Document parseXML(InputStream stream) throws Exception {

    DocumentBuilderFactory objDocumentBuilderFactory = null;
    DocumentBuilder objDocumentBuilder = null;
    Document doc = null;
    try {
        objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

        doc = objDocumentBuilder.parse(stream);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
        stream.close();
    return doc;


}

}











