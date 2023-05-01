import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketImpl;
import java.net.Socket;
public class Server {



    private ServerSocket serverSocket;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer()
    {
        try
        {
            while(!serverSocket.isClosed())
            {
                //blocking method , when client connects a socket is returned
                Socket socket=serverSocket.accept();
                System.out.println("A new client has connected!");

                //each object will be responsible for communicating with a client
                // also implements runable

                ClientHandler clientHandler= new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }


        }
        catch(IOException e)

        {



        }


    }
    public  void closeServerSocket(){

    try
    {
    if(serverSocket != null)
        serverSocket.close();

    }
    catch(IOException e)
    {

        e.printStackTrace();

    }

    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket=new ServerSocket(1234);
        Server server= new Server(serverSocket);
        server.startServer();
    }

}
