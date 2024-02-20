package process;

/**
 * @author Martin Ramonda
 */
public class DotsAnimation extends Thread{
    @Override
    public void run() {
        int count = 0;
        try {
            while(!this.isInterrupted()) {
                if(count<3){
                    System.out.print(".");
                    Thread.sleep(500);
                    count++;
                }
                else{
                    System.out.print("\b");
                    Thread.sleep(500);
                    count = 0;
                }           
            }
        } catch (InterruptedException e) {
        }         
    }
    
}
