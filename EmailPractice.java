import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Base64;

public class EmailPractice {
    public static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String[] args) throws  Exception{

        String from = "akibzaman6577@gmail.com";
        String to = "kdakib13@gmail.com";
        String cc = "akibzaman1426@gmail.com";
        String bcc = "s2210976128@ru.ac.bd";

        String user = "akibzaman6577@gmail.com";
        String pass = "";

        String username = new String(Base64.getEncoder().encode(user.getBytes()));
        String password = new String(Base64.getEncoder().encode(pass.getBytes()));

        SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com",465);
        dos = new DataOutputStream(s.getOutputStream());
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        System.out.println("SERVER: "+br.readLine() );

        send("EHLO smtp.gmail.com\r\n");

        String line;
        while((line = br.readLine())!=null){
            System.out.println("SERVER: "+ line);
            if(line.startsWith("250 ")){
                break;
            }
        }

        send("AUTH LOGIN\r\n");
        System.out.println("SERVER: "+ br.readLine());

        send(username + "\r\n");
        System.out.println("SERVER: "+ br.readLine());

        send(password + "\r\n");
        System.out.println("SERVER: "+ br.readLine());

        send("MAIL FROM:<"+from+">\r\n");
        System.out.println("SERVER: "+ br.readLine());

        send("RCPT TO:<"+to+">\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("RCPT TO:<"+cc+">\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("RCPT TO:<"+bcc+">\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("DATA\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("FROM: "+ from +  "\r\n");
        send("TO: " +to+"\r\n");
        send("CC: " +cc+"\r\n");
        send("Subject: Email test  \r\n");
        send("\r\n");
        send("THIS IS A TEST EMAIL WITH CC and BCC. THANK YOU\r\n");
        send(".\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("QUIT\r\n");
        System.out.println("SERVER: " + br.readLine());



    }
    private static void send(String s) throws Exception{
        dos.writeBytes(s);
        Thread.sleep(1000);

        System.out.println("CLIENT: "+s);
    }
}
