import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class TestProgram{
  private static int subSekvensLengde = 3;

  public static void main(String [] args){
    Monitor lesInnMonitor = new Monitor();
    String filnavn = args[0];
    int antTraader = 0;
    
    try{
      //Leser linjene i metadata, og lager en ny tråd som leser fil per linje i metadata
      BufferedReader br = new BufferedReader(new FileReader(filnavn));
      String linje;
      br.readLine();
      Runnable runnable;
      Thread thread;
      //Kjører så lenge fila har linjer
      while((linje = br.readLine()) != null){
        runnable = new FilLeser(lesInnMonitor,linje,subSekvensLengde);
        thread = new Thread(runnable);
        thread.start();
      }

    System.out.println(lesInnMonitor.hentFBeholder().hentSize());
    //Fletting:
    Beholder fBeholder = lesInnMonitor.hentFBeholder();
    antTraader = fBeholder.hentSize() - 1;
      for(int i = 0; i < antTraader; i++){
        Runnable runnable2 = new FilFletter(lesInnMonitor);
        Thread thread2 = new Thread(runnable2);
        thread2.start();
      }
    Beholder sBeholder = lesInnMonitor.hentSBeholder();
    antTraader = sBeholder.hentSize() - 1;
      for(int i = 0; i < antTraader; i++){
        Runnable runnable3 = new FilFletter(lesInnMonitor);
        Thread thread3 = new Thread(runnable3);
        thread3.start();
      }
    }

    catch(IOException e){
      e.printStackTrace();
    }
    // System.out.println(lesInnMonitor.hentFBeholder().hentSize());
    // System.out.println(lesInnMonitor.hentSBeholder().hentSize());
  }
}
