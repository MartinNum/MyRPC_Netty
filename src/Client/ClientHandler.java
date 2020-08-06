package Client;

import Bean.Request;
import Bean.Response;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.CountDownLatch;
@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private Response response = new Response("初始值", true);
    private Request request;

    public ClientHandler(Request request){
        this.request = request;
    }

    public Response getResponse(){
        return response;
    }

    public CountDownLatch getCountDownLatch(){
        return countDownLatch;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client 与Server建立链接");
        ctx.writeAndFlush(request);
        System.out.println("Client 请求成功");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client 收到Server的结果"+msg);
        this.response = new Response(msg.toString(),true);
//        this.countDownLatch.countDown();
    }
}
