package ZKUtil;

import org.apache.zookeeper.*;

import java.util.List;

public class ZKUtil {
    private static String zkConnectStr = "127.0.0.1:2181";
    private static int sessionTimeOut = 50000;

    public static ZooKeeper getConnnet() throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper(zkConnectStr, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //
            }
        });
        return zooKeeper;
    }

    public static String createEphemNode(ZooKeeper zk,String serviceRootName,String childName,int port) throws Exception{
        return zk.create("/"+serviceRootName+"/"+childName+","+port,childName.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    public static List<String> getChildren(ZooKeeper zk, String serviceRootName) throws Exception{
        return zk.getChildren("/"+serviceRootName,false);
    }
}
