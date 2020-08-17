package Client.start;

import java.lang.reflect.Proxy;
import Client.proxy.MyInvokerHandler;
import RPC_Server.HelloService;

public class ClientStart {
    public static void main(String[] args) throws Exception {
        MyInvokerHandler invokeHandler = new MyInvokerHandler(HelloService.class);
        HelloService helloService = (HelloService) Proxy.newProxyInstance(HelloService.class.getClassLoader(),new Class<?>[]{HelloService.class},invokeHandler);
        String result = helloService.sayHi("Martin") ;
        System.out.println("结果："+result);
    }

}
