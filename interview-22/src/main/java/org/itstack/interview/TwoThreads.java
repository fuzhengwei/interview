package org.itstack.interview;

public class TwoThreads implements Runnable {

	int i = 1;

	public TwoThreads() {
	}

	public void someBusiness() {
		//business logic
		//create 2 threads, jump between each other
		TestThread testthread1 = new TestThread(this, "1");
		TestThread testthread2 = new TestThread(this, "2");
		testthread2.start();
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
		}
		testthread1.start();

	}

	/**
	 * -agentpath:E:\itstack\git\github.com\itstack-jvmti-thread\cmake-build-debug\libitstack_jvmti_thread.dll
	 * @param args
	 */
	public static void main(String[] args) {
		new TwoThreads().someBusiness();
	}

	public void run() {
		TestThread t = (TestThread) Thread.currentThread();

		//probe here, this is used to verify the "start" method
		IAfterAdvice afterAdvice = new AfterAdvice(Storage.getA(), Probe.getA());
		afterAdvice.outCallProbe();

		try {
			if ((!t.getName().equalsIgnoreCase("1#main"))
					&& (!t.getName().equalsIgnoreCase("1"))) {
				synchronized (this) {
					//System.out.println(Thread.currentThread().getName());
					wait();
				}
			}

			while (true) {
				int ts = t.increaseTime();
				//System.out.println("@time in thread "+ t.getName()+ ":"+ ts);
				if (t.getTime() % 10 == 0) {
					synchronized (this) {
						//System.out.println("***************Times:"+(i++)+"******************");
						notifyAll();
						if (t.getTime() == 10)
							break;
						wait();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class TestThread extends Thread {
	private int time = 0;

	public TestThread(Runnable r, String name) {
		super(r, name);
	}

	public int getTime() {
		return time;
	}

	public int increaseTime() {
		return ++time;
	}
}
