import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;


public class Connector{
    private String host;
    private int port;
    private int reconnectionTimeout;
    private int reconnectionAttempts;
    private int maxReconnectionAttempts;
    private Socket socket;
    private ObjectOutputStream serverWriter;

    public Connector(String host, int port, int reconnectionAttempts, int reconnectionTimeout) {
        this.host = host;
        this.port = port;
        this.reconnectionAttempts = reconnectionAttempts;
        this.reconnectionTimeout = reconnectionTimeout;
    }

    public boolean connect() throws IOException {
        socket = new Socket(host,port);
        ObjectOutputStream objectOutputStream= (ObjectOutputStream) socket.getOutputStream();
        return true;
    }

}