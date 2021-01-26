package org.itstack.interview;

public class 丽春院 {

    public static void main(String[] args) {
        老鸨 鸨子 = new 老鸨();

        清倌 miss = new 清倌(鸨子);
        客官 guest = new 客官(鸨子);

        Thread t_miss = new Thread(miss);
        Thread t_guest = new Thread(guest);

        t_miss.start();
        t_guest.start();
    }

}

class 清倌 implements Runnable {

    老鸨 鸨子;

    public 清倌(老鸨 鸨子) {
        this.鸨子 = 鸨子;
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if (i == 1) {
                try {
                    鸨子.在岗清倌("苍田野子", "500 日元");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    鸨子.在岗清倌("花田岗子", "800 日元");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i = (i + 1) % 2;
        }
    }

}

class 客官 implements Runnable {

    老鸨 鸨子;

    public 客官(老鸨 鸨子) {
        this.鸨子 = 鸨子;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                鸨子.喝茶吟诗聊风月();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class 老鸨 {

    private String 清倌 = null;
    private String price = null;
    private boolean 工作状态 = true;

    public synchronized void 在岗清倌(String 清倌, String price) throws InterruptedException {
        if (!工作状态)
            wait();//等待
        this.清倌 = 清倌;
        this.price = price;
        工作状态 = false;
        notify();//叫醒
    }

    public synchronized void 喝茶吟诗聊风月() throws InterruptedException {
        if (工作状态)
            wait();//等待
        System.out.println("聊风月：" + 清倌);
        System.out.println("茶水费：" + price);
        System.out.println("  " + "  " + "  " + "  " + "  " + "  " + "  " + "  " + "  " + "  " + 清倌 + "工作完事" + "准备 ... ...");
        System.out.println("****************************************");
        工作状态 = true;
        notify();//叫醒
    }

}
