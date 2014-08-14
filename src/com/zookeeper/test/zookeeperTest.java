package com.zookeeper.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

public class zookeeperTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zookeeper = new ZooKeeper("0.0.0.0:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                // TODO Auto-generated method stub
                System.out.println("已经触发了" + watchedEvent.getType() + "事件");
            }
        });

        // 创建一个目录节点
        zookeeper.create("/zookeeper3", "zookeeperTest".getBytes(), aclList(), CreateMode.PERSISTENT);
        System.out.println(new String(zookeeper.getData("/", false, null)));
        zookeeper.close();
    }

    private static List<ACL> aclList() {
        List<ACL> aclList = new ArrayList<ACL>();
        Id id1 = new Id("world", "anyone");
        ACL acl1 = new ACL(ZooDefs.Perms.READ, id1);
        aclList.add(acl1);
        return aclList;
    }
}
