package ZookeeperUtil;

import Client.routing.RouteUnit;
import Client.routing.RoutingInformation;
import org.apache.zookeeper.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZKUtil {
    private static String zookeeperAddress = "127.0.0.1:2181";
    private static int timeOut = 50000;
    ZooKeeper zooKeeper;
    RoutingInformation routingTool = new RoutingInformation();

    public void getConnnet() throws Exception{
        zooKeeper = new ZooKeeper(zookeeperAddress, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //
            }
        });
    }

    // 创建临时节点
    public String createEphemNode(String serviceRootName,String childName,int port) throws Exception{
        String child = childName + "," +  port;
        return zooKeeper.create("/"+serviceRootName, child.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    // 创建权重临时节点
    public String createWeightsEphemNode(String serviceRootName,String childName,int port, int weights) throws Exception{
        String child = childName + "," +  port + "," + weights;
        return zooKeeper.create("/"+serviceRootName, child.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    // 获取serviceRootName服务的所有路由信息
    public List<String> getChildren(String serviceRootName, RoutingInformation routingInformation) throws Exception{
        return zooKeeper.getChildren("/" + serviceRootName, false);
    }
}
