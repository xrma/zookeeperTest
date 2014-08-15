/**
 * 
 */
package com.zookeeper.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

/** 
 * @Title: CommonUtil.java
 * @Author: xrma
 * @CreateTime: Aug 15, 2014 5:35:27 PM
 * @CodeReviewer: 
 * @ReviewTime: 
 * @Company: CFCA
 */

public class CommonUtil {
    
    public static ZooKeeper zookeeperConn() throws IOException{
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher(){

            @Override
            public void process(WatchedEvent event) {
                // TODO Auto-generated method stub
                System.out.println(event.getType() + "发生了。");
            }
            
        });
        
        return zooKeeper;
    }
    
    public static List<ACL> getACLList(){
        List<ACL> aclList = new ArrayList<ACL>();
        
        Id id1 = new Id("world", "anyone");
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
        
        aclList.add(acl1);
        return aclList;
    }

}
