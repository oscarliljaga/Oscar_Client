import java.io.*;
import java.net.*;

public class NetworkingClient {
    public static void main(String[] args) {
        Socket client = null;

        //Default port number
        int portnumber = 50000;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        while (true) {
            try {
                String msg = "";

                //Create client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket created " + client);

                //Create output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                //Create input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                //Create BufferedReader for standard input
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter two numbers separated by [+], [-], [*] or [/] ('Bye' to exit): ");

                //Write data from standard input device to  client socket output stream
                msg = stdIn.readLine().trim();
                pw.println(msg);

                //Read data from input stream of client socket
                System.out.println("Message from server: " + br.readLine());

                pw.close();
                br.close();
                client.close();

                //Stop operation
                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }
            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}