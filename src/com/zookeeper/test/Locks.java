package com.zookeeper.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.zookeeper.common.CommonUtil;

public class Locks {
    public static Integer mutex;

    /**
     * @param args
     * 共享锁，需要创建EPHEMERAL_SEQUENTIAL的目录节点
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            ZooKeeper zookeeper = CommonUtil.zookeeperConn();
            String myZnode = "/root/1";
            getLock(zookeeper, myZnode);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static void getLock(ZooKeeper zookeeper, String myZnode) throws KeeperException, InterruptedException{
        List<String> nodeList = zookeeper.getChildren("/root", true);
        String[] nodes = nodeList.toArray(new String[nodeList.size()]);
        
        Arrays.sort(nodes);
        
        if(myZnode.equals("/root/" + nodes[0])){
            System.out.println("do something");
        }else{
            waitForLock(zookeeper, nodes[0]);
        }
    }
    
    private static void waitForLock(ZooKeeper zookeeper, String lower) throws KeeperException, InterruptedException{
        Stat stat = zookeeper.exists("/root/" + lower , true);
        if(stat != null){
            getLock(zookeeper, "/root/1");
        }else{
            System.out.println("wait");
            mutex.wait();
        }
    }

}
