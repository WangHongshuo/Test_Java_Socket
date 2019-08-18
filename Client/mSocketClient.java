import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class mSocketClient {
    public static void main(String[] arg) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Addr: ");
        String addr;
        addr = sc.nextLine();
        String[] strArr = addr.split(":");
        String IP = strArr[0];
        int port =  Integer.valueOf(strArr[1]).intValue();

        Socket socket = new Socket(IP, port);
        int len;
        byte[] bytes = new byte[1024];
        OutputStream outputStream = socket.getOutputStream();
        
        String message = "Hello, Server!";
        outputStream.write(message.getBytes("UTF-8"));
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "UTF-8"));
            System.out.println("Get message from Server: " + sb);
        }

        sc.close();
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}