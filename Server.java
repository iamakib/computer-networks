import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket= new ServerSocket(5555);
        System.out.println("Server started. waiting for client");

        Socket socket = serverSocket.accept();
        System.out.println("Connected with client");

        DataInputStream dis = new DataInputStream(socket.getInputStream());

        while (true){
            try{
                String msg = dis.readUTF();
                System.out.println("Message:" + msg);
            } catch (Exception e) {
                System.out.println("Client Disconnected");
                break;
            }
        }

        socket.close();
        dis.close();
        serverSocket.close();

    }
}
