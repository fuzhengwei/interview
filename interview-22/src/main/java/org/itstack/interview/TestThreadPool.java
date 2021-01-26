package org.itstack.interview;
//TestThreadPool.java
import java.io.*;

// this is Demo B, thread pool
public class TestThreadPool {
	
	static private ThreadPoolManager manager = new ThreadPoolManager(3);
	
	public static void main(String[] args) {
        someBusiness();
    }
    public static void someBusiness() {
        try {
        	//fetch a thread from thread pool to do some business
            manager.process(" start ");

        } catch (Exception e) {
        }
    }
}