

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import utils.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;


public class Connector {
    InetSocketAddress serverAddress;
    SocketChannel client;

    ByteArrayOutputStream b1 = new ByteArrayOutputStream(1024);
    ObjectOutputStream outputStream;
    ObjectInput input;

    ByteBuffer byteBuffer;
    byte[] buffer = new byte[1024];

    public Connector(int PORT) {
        try {
            serverAddress = new InetSocketAddress("localhost", PORT);
            client = SocketChannel.open(serverAddress);
            client.configureBlocking(false);
            outputStream = new ObjectOutputStream(b1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Object data) {
        try {
            b1 = new ByteArrayOutputStream(2048);
            outputStream = new ObjectOutputStream(b1);
            outputStream.writeObject(data);
            byteBuffer = ByteBuffer.wrap(b1.toByteArray());
            client.write(byteBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response receive() {
        try {
            buffer = new byte[32158];
            if(client.read(ByteBuffer.wrap(buffer)) == 0) return null;
            for(int i=0;i<120;i++){
                System.out.println(buffer[i]+' ');
            }
            input = new ObjectInputStream(new ByteArrayInputStream(buffer));
            System.out.println("Ждёт ответ от сервера");
            return (Response) input.readObject();

        }catch (ClosedByInterruptException ignored){
            return null;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}