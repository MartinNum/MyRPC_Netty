package RPC_Server;

public class HelloServiceImp implements HelloService {
    public String sayHi(String name) {
        return "Hi " + name;
    }
}
