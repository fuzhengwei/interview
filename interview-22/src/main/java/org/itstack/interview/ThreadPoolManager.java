package org.itstack.interview;
import java.util.*;

class ThreadPoolManager {
	private int maxThread;

	public Vector vector;

	public void setMaxThread(int threadCount) {
		maxThread = threadCount;
	}
	
	//create a thread pool that contains "threadCount" threads
	public ThreadPoolManager(int threadCount) {
		setMaxThread(threadCount);
		System.out.println("Starting thread pool...");
		vector = new Vector();
		//modify by lis
		for (int i = 1; i <= threadCount; i++) {
			SimpleThread thread = new SimpleThread(i);
			//add by liling
			//thread.setDaemon(true);
			vector.addElement(thread);
			thread.start();
		}
	}
	// Polling every thread to find a idle thread
	//then set its state Running to notify work
	public void process(String argument) {
		int i;
		for (i = 0; i < vector.size(); i++) {
			SimpleThread currentThread = (SimpleThread) vector.elementAt(i);
			if (!currentThread.isRunning()) {
				System.out.println("Thread " + (i + 1) + " is processing:"
						+ argument);
				currentThread.setArgument(argument);
				currentThread.setRunning(true);
				return;
			}
		}
		if (i == vector.size()) {
			System.out.println("pool is full, try in another time.");
		}
	}
}// end of class ThreadPoolManager

