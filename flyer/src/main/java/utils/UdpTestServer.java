package utils;

import java.io.IOException;
import java.text.ParseException;

public class UdpTestServer {

    private String serverCommand;
    private Process serverProc;

    public UdpTestServer(String port) {
        try {
            Integer.parseInt(port);
            this.serverCommand = "python ./udp_server/udp_server.py " + port;
        }
        catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid port for UDP server " + port + " " + e.getMessage());
        }
    }

    public void runServer(){
        try {
            this.serverProc = Runtime.getRuntime().exec(this.serverCommand);
        }
        catch (IOException e) {
            System.out.println("UDP server error: " + e.getMessage());
        }
    }

    public void killServer(){
        serverProc.destroy();
    }
}
