/*
 * Probe.java
 *
 * Created on 2006��8��21��, ����3:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.itstack.interview;
import java.util.*;

/**
 * this class is used to store probe result of user info for 3 demos
 * @author Li Ling
 */
public class Probe {
    
    public static Vector<UserInfo> A=new Vector<UserInfo>();
    public static Vector<UserInfo> B=new Vector<UserInfo>();
    public static Vector<UserInfo> C=new Vector<UserInfo>();

    public static UserInfo getAA() {
        System.out.println("getAA A.size:"+A.size());
        //System.out.println("getAA A.time:"+((UserInfo)A.get(0)).inCallTime);
        if(A.size()!=0)
        return (UserInfo)A.get(0);
        
        return null;
    }
    public static Vector<UserInfo> getA() {
        return A;
    }
    public static void setA(Vector<UserInfo> aA) {
        A = aA;
    }

    public static Vector<UserInfo> getB() {
        return B;
    }

    public static void setB(Vector<UserInfo> aB) {
        B = aB;
    }

    public static Vector<UserInfo> getC() {
        return C;
    }

    public static void setC(Vector<UserInfo> aC) {
        C = aC;
    }
    
}
