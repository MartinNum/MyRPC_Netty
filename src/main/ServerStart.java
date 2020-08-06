package main;

import RPC_Server.HelloService;
import RPC_Server.HelloServiceImp;
import Server.Server;
import Server.ServerHandler;

public class ServerStart {
    public static void main(String[] args) throws Exception {
        int port = 8000;
        if(args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e) {
                //采用默认值
            }
        }
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.registry(HelloService.class.getName(), HelloServiceImp.class);
        Server server = new Server(serverHandler);
        server.bind(port);
    }
}
