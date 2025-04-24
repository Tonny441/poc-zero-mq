
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class AppAmi {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
            socket.connect("tcp://localhost:5555");

            String message = "qui est la";
            System.out.println("envoi: " + message);
            socket.send(message.getBytes(ZMQ.CHARSET), 0);

            byte[] reply = socket.recv(0);
            String response = new String(reply, ZMQ.CHARSET);
            System.out.println("reponse recue: " + response);
        }
    }
}
