

import utils.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;


public class Connector {
    InetSocketAddress serverAddress;
    SocketChannel client;

    ByteArrayOutputStream b1 = new ByteArrayOutputStream(1024);
    ObjectOutputStream outputStream;
    ObjectInput input;
    boolean isFull;
    int lastByte;

    ByteBuffer byteBuffer;
    byte[] buffer = new byte[1024];

    public Connector(int PORT) {
        try {
            serverAddress = new InetSocketAddress("localhost", PORT);
            client = SocketChannel.open(serverAddress);
            client.configureBlocking(false);
            lastByte = 0;
            outputStream = new ObjectOutputStream(b1);
            isFull = true;
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
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a) {
            sb.append(String.format("%02x ", b));
        }
        return sb.toString();
    }
    public Response receive() {
        try {
            if(isFull){
                buffer = new byte[2048];
            }
            lastByte = client.read(ByteBuffer.wrap(buffer, lastByte,1024));
            if(lastByte == 0)return null;
            System.out.println(byteArrayToHex(buffer));
            input = new ObjectInputStream(new ByteArrayInputStream(buffer));
            System.out.println("Ждёт ответ от сервера");
            isFull=true;
            return (Response) input.readObject();

        }
        catch (StreamCorruptedException e){
            isFull=false;
            return null;
        }
        catch (ClosedByInterruptException ignored){
            return null;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}