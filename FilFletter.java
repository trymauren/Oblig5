import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FilFletter implements Runnable{
  Monitor monitor;
  FilFletter(Monitor monitor){
    this.monitor = monitor;
  }
  public void run(){
    monitor.flettFrisk();
    monitor.flettSyk();
  }
}
