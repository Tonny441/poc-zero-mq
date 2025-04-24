

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class AppVoyageur {
    public static void main(String[] args) throws Exception {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");

            System.out.println("att messages...");

            while (!Thread.currentThread().isInterrupted()) {
                byte[] request = socket.recv(0);
                String message = new String(request, ZMQ.CHARSET);
                System.out.println("recu: " + message);

                if (message.equals("qui est la")) {
             
                    String response = "Présent";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);
                    System.out.println("reponse envoyer: " + response);
                } 
            }
        }
    }
}
