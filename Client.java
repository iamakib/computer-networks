import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("localhost",5555);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        String msg;

        while(true){
            System.out.println("Enter msg:");
            msg = scanner.nextLine();

            if(msg.equalsIgnoreCase("exit")){
                break;
            }
            dos.writeUTF(msg);
            dos.flush();
        }
        scanner.close();
        socket.close();
        dos.close();

        System.out.println("Connection Closed");
    }
}
