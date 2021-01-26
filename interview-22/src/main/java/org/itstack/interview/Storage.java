/*
 * Storage.java
 *
 * Created on 2006��8��21��, ����1:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.itstack.interview;
import java.util.*;
/**
 *this class is used to store user info for 3 demos
 * @author LiLing
 */
public class Storage {
    
    /** Creates a new instance of Storage */
    private static int index=1000000;
    private static Vector<UserInfo> A=new Vector<UserInfo>();
    private static Vector<UserInfo> B=new Vector<UserInfo>();
    private static Vector<UserInfo> C=new Vector<UserInfo>();
    public Storage() {
        
    }

   /* public static UserInfo getA() {
        return (UserInfo)A.get(0);
    }*/

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

    public static synchronized int getIndex() {
        index++;
        return index;
    }

 
}
