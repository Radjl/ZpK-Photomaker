package Photo;

import config.AppConfiguration;
import lombok.NoArgsConstructor;
import models.Carriage;
import models.CarriageMassive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import repository.CarriageMassiveRepo;
import repository.CarriageRepo;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.URL;
import java.net.URLConnection;


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
            URLConnection con = new URL("http://admin:vkmodule@10.47.1.101/protect/status.xml").openConnection();




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
                        System.out.println("Поток выспался - 2.5 секунд");



                        myAuthenticator.setUsername("admin");
                        myAuthenticator.setPassword("h31FF9c4");






                        URLConnection conCam = new URL("http://10.100.100.132/Streaming/channels/1/picture?snapShotImageType=JPEG").openConnection();
                        System.out.println("Получаем соединение с камерой");


                        BufferedImage bufferedImage = ImageIO.read(conCam.getInputStream());
                        System.out.println("Буферизируем поток данных с камеры");


                        File f = new File(appConfiguration.getUploadPathConnector());
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
            URLConnection con = new URL("http://admin:vkmodule@10.47.1.101/protect/status.xml").openConnection();
            URLConnection conCam = new URL("http://10.100.100.28/Streaming/channels/1/picture?snapShotImageType=JPEG").openConnection();
        }
    }

    private void CheckForSaveCarriageMassive(CarriageMassive carriageMassive) {
        if (carriageMassive.timer > 100 && carriageMassive.getPhotos().size() > 7){
            carriageMassive.done = true;
            carriageMassiveRepo.save(carriageMassive);
           // carriageRepo.deleteAll();
            System.out.println("Новый обьект записан в базу и сохранён");

        }

        if (carriageMassive.timer > 1000 && carriageMassive.getPhotos().size() < 7){
            carriageMassive.done = true;
          //  carriageRepo.deleteAll();
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











