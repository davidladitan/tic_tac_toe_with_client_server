import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Tic tac toe server.
 */
public class TicTacToeServer implements Constants {
    private ServerSocket serverSocket;
    private Socket aSocket;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private ExecutorService pool;
    private int numberOfConnections;
    private Player xPlayer;
    private Player oPlayer;
    private String xName = "";
    private String oName = "";


    /**
     * Instantiates a new Tic tac toe server.
     */
    public TicTacToeServer() {
        try {
            serverSocket = new ServerSocket(5_000, 1714);
            pool = Executors.newFixedThreadPool(8);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run server.
     */
    public void runServer() {

        try {
            while(true) {

                System.out.println("Server running...");
                aSocket = serverSocket.accept();
                System.out.println("Console at server side says: " + (numberOfConnections + 1) + "Connections accepted by server!");
                socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                socketOut = new PrintWriter(aSocket.getOutputStream(), true);
                numberOfConnections++;


                if (numberOfConnections % 2 == 1 ){
                    // socket is in first connection
                    socketOut.println("You are X player, what is the name of X player?");
                    xName = socketIn.readLine();
                    xPlayer = new HumanPlayer(xName, LETTER_X);
                    xPlayer.setSockets(socketIn, socketOut); // set xPlayer to have first client connection
                    socketOut.println(xName + ", we are waiting for your opponent to connect");
                }else if (numberOfConnections % 2 == 0){
                    //socket is in second connection
                    socketOut.println("You are O player, what is the name of O player?");
                    oName = socketIn.readLine();
                    socketOut.println("your opponent is: " + xName);
                    oPlayer = new HumanPlayer(oName, LETTER_O);
                    oPlayer.setSockets(socketIn, socketOut); // set oPlayer to have second client connection
                    xPlayer.getSocketOut().println("Your opponent is: " + oName);
                    Game game = new Game(xPlayer, oPlayer);
                    pool.execute(game);
                }

            }
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String [] args){
        TicTacToeServer tic = new TicTacToeServer();
        tic.runServer();
    }
}
