class PhysicThread  extends Thread{
    PhysicThread() {
        super();
        start();
    }

    public void run(){
        while(Main.isRunning){
            for (Block obj : Block.Blocks) {
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
