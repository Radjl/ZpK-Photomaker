package Photo;

import lombok.Getter;
import lombok.Setter;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;

/**
 * Created by sheludko on 31.10.2018.
 */


@Getter
@Setter
public class MyAuthenticator extends Authenticator {

    String username = "admin";
    String password = "vkmodule";



    protected PasswordAuthentication getPasswordAuthentication() {
        String promptString = getRequestingPrompt();
        System.out.println(promptString);
        String hostname = getRequestingHost();
        System.out.println(hostname);
        InetAddress ipaddr = getRequestingSite();
        System.out.println(ipaddr);
        int port = getRequestingPort();


        return new PasswordAuthentication(username, password.toCharArray());




    }
}
