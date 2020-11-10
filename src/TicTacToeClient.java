import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The type Tic tac toe client.
 */
public class TicTacToeClient {

    private Socket aSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private BufferedReader stdIn;

    /**
     * Instantiates a new Tic tac toe client.
     *
     * @param serverName the server name
     * @param portNumber the port number
     */
    public TicTacToeClient(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            // keyboard input stream
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter(aSocket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Communicate.
     */
    public void communicate(){
        String line ="";
        String response = "";
        setUpInteraction();
        while (!(response.contains("GAME IS OVER") )){
            try {
                response = socketIn.readLine();
                // prints board
                if (!response.contains("GAME IS OVER"))
                    System.out.println(response);

                // collects row and column input
                if (response.contains("what row") || response.contains("what column")){
                    line = stdIn.readLine();
                    socketOut.println(line);
                }

                // prints game over
                if (response.contains("GAME IS OVER"))
                    System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * contains the number of socket inputs and socket output lines to set up the game for each player
     */
    public void setUpInteraction(){
        try {
            String response = "";
            String line ="";
            response = socketIn.readLine();
            System.out.println(response);
            line = stdIn.readLine();
            socketOut.println(line);
            response = socketIn.readLine();
            System.out.println(response);
            response = socketIn.readLine();
            System.out.println(response);
            response = socketIn.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String [] args){
        TicTacToeClient client = new TicTacToeClient("localhost", 5000);
        client.communicate();
    }
}
