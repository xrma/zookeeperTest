/**
 * 
 */
package com.zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

import com.zookeeper.common.CommonUtil;

/** 
 * @Title: LeaderElection.java
 * @Author: xrma
 * @CreateTime: Aug 15, 2014 5:33:25 PM
 * @CodeReviewer: 
 * @ReviewTime: 
 * @Company: CFCA
 */

public class LeaderElection {
    public static String leaderPath = "/leader";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        byte[] leader = null;
        
        try {
            leader = CommonUtil.zookeeperConn().getData(leaderPath, true, null);
            
            if(leader != null){
                System.out.println("已经有leader了！");
            }else{
                CommonUtil.zookeeperConn().create(leaderPath, "leader".getBytes(), CommonUtil.getACLList(), CreateMode.EPHEMERAL);
            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    

}
