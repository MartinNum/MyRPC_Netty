package Proxy;

import Bean.Request;
import Bean.Response;
import Client.Client;
import Client.ClientHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokerHandler implements InvocationHandler {
    private Class obj;

    public MyInvokerHandler(Class obj){
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ClientHandler clientHandler = getClientHandler(method, args);
        Client client = new Client(clientHandler);
        Response response = client.send();
        return response.getResult();
    }

    public ClientHandler getClientHandler(Method method, Object[] args){
        Request request = new Request();
        request.setClassName(obj.getName());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParametersVal(args);
        return new ClientHandler(request);
    }
}
