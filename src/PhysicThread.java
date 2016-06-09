class PhysicThread  extends Thread{
    PhysicThread() {
        super();
        start();
    }

    public void run(){
        System.out.printf("q");
        while(Main.isRunning){
            for (IBody obj : Main.objectPool) {
                if (obj instanceof Updateable) {
                    ((Updateable) obj).Update();
                }

                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
