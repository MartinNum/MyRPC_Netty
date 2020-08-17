package Client;

import Bean.Response;
import Serialization.MarshallingCodeCFactory;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client {

    private ClientHandler clientHandler ;
    public Client(ClientHandler c){
        clientHandler = c;
    }

    public Response send() throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Response response = null;
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("localhost", 8000))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(io.netty.channel.socket.SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(clientHandler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
//            clientHandler.getCountDownLatch().await();
            response = clientHandler.getResponse();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventExecutors.shutdownGracefully().sync();
        }
        return response;
    }
}
