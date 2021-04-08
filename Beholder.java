import java.util.HashMap;
import java.util.ArrayList;

class Beholder{

  ArrayList<HashMap<String,SubSekvens>> beholder;
  String navn;
  public Beholder(String navn){
    beholder = new ArrayList<HashMap<String,SubSekvens>>();
    this.navn = navn;
  }

  public void leggTilHM(HashMap<String,SubSekvens> nyHM){
    beholder.add(nyHM);
  }

  public HashMap fjernHM(int indeks){
    return beholder.remove(indeks);
  }

  public HashMap<String,SubSekvens> hentHM(){
    return beholder.get(0);
  }

  public int hentSize(){
    return beholder.size();
  }

  public static HashMap<String,SubSekvens> leggSammen(HashMap<String,SubSekvens> hm1,
    HashMap<String,SubSekvens>  hm2){

      SubSekvens hentaSub;
      HashMap<String,SubSekvens> nyHM = new HashMap<String,SubSekvens> ();
      for(SubSekvens sub1:hm1.values()){
          hentaSub = hm2.remove(sub1.hentSubSekvens());
          if (hentaSub == null) {
              nyHM.put(sub1.hentSubSekvens(), sub1);
          }
          else {
              int ant = hentaSub.hentForekomster();
              sub1.leggTilForekomster(ant);
              nyHM.put(sub1.hentSubSekvens(), sub1);
          }
      }
      // Legger inn resten av subSeqHash2:
      for(SubSekvens sub2:hm2.values()) {
          nyHM.put(sub2.hentSubSekvens(),sub2);
      }
      return 	nyHM;
  }
  public String hentNavn(){
    return navn;
  }
}
