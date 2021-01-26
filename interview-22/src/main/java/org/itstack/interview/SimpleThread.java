package org.itstack.interview;

//SimpleThread.java
class SimpleThread extends Thread {
	private boolean runningFlag;

	private String argument;

	Object lock = new Object();

	public boolean isRunning() {
		return runningFlag;
	}

	public synchronized void setRunning(boolean flag) {
		runningFlag = flag;
		if (flag)
			synchronized (lock) {
				this.notify();
			}
	}

	//add by liling
	public synchronized boolean getRunning() {
		return this.runningFlag;
	}

	public String getArgument() {
		return this.argument;
	}

	public void setArgument(String string) {
		argument = string;
	}

	public SimpleThread(int threadNumber) {
		runningFlag = false;
		System.out.println("thread " + threadNumber + "started.");
	}

	public synchronized void run() {
		try {
			while (true) {
				if (!getRunning()) {

					this.wait();

				} else {
					
					//probe here
					//this is used to verify "notify" method
					IAfterAdvice afterAdvice = new AfterAdvice(Storage.getB(),
							Probe.getB());
					afterAdvice.outCallProbe();

					System.out.println(getArgument() + " processing...");
					sleep(2000);
					System.out.println("Done,Thread is sleeping...");
					//work finished, thread waiting...
					setRunning(false);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupt");
		}
	}//end of run()
}//end of class SimpleThread
