
public class MyThread extends Thread {

    private String task;
    private long time;
    private boolean flag;

    MyThread(String s) {
        super(s);
        task = "";
        time = 0;
        flag = true;
    }

    void setFlag() {
        this.flag = false;
    }

    boolean getFlag() {
        return flag;
    }


    void setTask(String temp) {
        this.task = temp;
    }

    void setTime(long temp) {
        this.time = temp;
    }

    private String getTask() {
        return task;
    }

    private long getTime() {
        return time;
    }

    @Override
    public void run() {
        flag = true;
        try {
            while (true) {
                if (!flag) {
                    flag = false;
                    System.out.println(this.getTask() + " " + this.getTime() + " " + this.getName());
                    sleep(this.getTime());
                    flag = true;
                } else {
                    sleep(100);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
