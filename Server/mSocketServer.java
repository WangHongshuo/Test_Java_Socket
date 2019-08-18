import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class mSocketServer {
    public static void main(String[] arg) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Port: ");
        int port = sc.nextInt();
        sc.close();

        ServerSocket server = new ServerSocket(port);
        System.out.println("Waiting for connection...");
        Socket socket = server.accept();

        InputStream inputStream = socket.getInputStream();
        
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "UTF-8"));
            System.out.println("Get message from client: " + sb);
        }

        OutputStream outputStream = socket.getOutputStream();
        String message = "Hello, client!";
        outputStream.write(message.getBytes("UTF-8"));

        inputStream.close();
        outputStream.close();
        socket.close();
        server.close();
    }
}