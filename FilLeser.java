import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

class FilLeser implements Runnable{
  static Monitor lesInnMonitor;
  String filnavnOgTilstand;
  int subLengde;

  FilLeser(Monitor lesInnMonitor, String filnavnOgTilstand, int subLengde){
    this.lesInnMonitor = lesInnMonitor;
    this.filnavnOgTilstand = filnavnOgTilstand;
    this.subLengde = subLengde;
  }

  public void run(){
    String linje;
    String subStreng;

    try {
        String[] filInfo = filnavnOgTilstand.split(",");
        String filnavn = filInfo[0];
        String tilstand = filInfo[1];
        File fil = new File(filnavn);
        Scanner leser = new Scanner(fil);

        HashMap<String,SubSekvens>  subHM = new HashMap <> ();
        System.out.println(" Virussjekker leser fil   " + filnavn );

        while(leser.hasNextLine()) {
            linje = leser.nextLine();
            linje = linje.trim();
            for (int ind = 0; ind + subLengde <= linje.length(); ind ++) {
                subStreng = linje.substring(ind,ind+subLengde);
                subHM.putIfAbsent(subStreng,new SubSekvens(subStreng));
            }
        }
        leser.close();
      lesInnMonitor.leggTil(subHM,tilstand);

    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  public static Monitor hentMonitor(){
    return lesInnMonitor;
  }
}
