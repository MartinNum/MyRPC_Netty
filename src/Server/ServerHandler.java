package Server;

import Bean.Request;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private HashMap<String, Class> registryCenter = new HashMap<String, Class>();

    public void registry(String name, Class imp){
        registryCenter.put(name, imp);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到客户端请求");
        Object result = HandlerRequest((Request) msg);
        ctx.write(result);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public Object HandlerRequest(Request request) throws Exception {
        Class imp = registryCenter.get(request.getClassName());
        Method method = imp.getMethod(request.getMethodName(), request.getTypeParameters());
        Object result = method.invoke(imp.newInstance(), request.getParametersVal());
        return result;
    }
}
