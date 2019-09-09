package utils;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

public class UdpTestServer {

    private String serverCommand;

    public UdpTestServer(String port) {
        this.serverCommand = "python ./udp_server/udp_server.py " + port;
    }

    public void runServer() throws IOException {
        Runtime.getRuntime().exec(this.serverCommand);
    }
}
