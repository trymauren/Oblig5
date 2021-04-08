import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
import java.util.HashMap;

class Monitor{

  public static Beholder beholderFrisk = new Beholder("friske");
  public static Beholder beholderSyk = new Beholder("syke");

  private Lock laas = new ReentrantLock();

  public void leggTil(HashMap<String,SubSekvens> hashmap, String type){
    laas.lock();
    try{
      if(type.equals("True")){
        beholderFrisk.leggTilHM(hashmap);
        System.out.println(" Lagt til person i FRISK beholder");
      }
      else if(type.equals("False")){
        beholderSyk.leggTilHM(hashmap);
        System.out.println(" Lagt til person i SYK beholder");
      }
      else{
        System.out.println(" FEIL: Hverken true eller false!");
      }
    }
    finally{
    laas.unlock();
    }
  }

  public Beholder flettFrisk(){
    laas.lock();
    while(beholderFrisk.hentSize() > 1){
      HashMap<String,SubSekvens> res =
          beholderFrisk.leggSammen(beholderFrisk.hentHM(), beholderFrisk.hentHM());
      beholderFrisk.leggTilHM(res);
    }
    laas.unlock();
    return beholderFrisk;
  }

  public Beholder flettSyk(){
    laas.lock();
    while(beholderSyk.hentSize() > 1){
      HashMap<String,SubSekvens> res =
          beholderSyk.leggSammen(beholderSyk.hentHM(), beholderSyk.hentHM());
      beholderSyk.leggTilHM(res);
    }
    laas.unlock();
    return beholderSyk;

  }

  public Beholder hentFBeholder(){
    return beholderFrisk;
  }
  public Beholder hentSBeholder(){
    return beholderSyk;
  }
}
