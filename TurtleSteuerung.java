import ch.aplu.turtle.Turtle;

public class TurtleSteuerung {

  private int posX;
  private int posY;
  private String muster;
  private Turtle turtle;
  private int winkel;
  private Thread turtleThread;
  private boolean abbruch= false;
  

  public TurtleSteuerung(Turtle turtle, int posX, int posY, int winkel, String muster) {
    this.turtle = turtle;
    this.muster = muster;
    this.posX = posX;
    this.posY = posY;
    this.winkel = winkel;
  }

  public void start() {
    turtleThread = new Thread() {
      public void run() {
        runTurtle();
      }
    };
    turtleThread.start();
  }

  public void cancel() {
    abbruch = true;
  }
  
  private void runTurtle() {
    bewegeTurtleAnKoordinaten(posX, posY);
    turtle.hideTurtle();
    
    switch (muster) {
      case TurtleGrafik.MUSTER_STERN:
        zeichneStern();
        break;
      case TurtleGrafik.MUSTER_PYRAMIDE:
        zeichnePyramide();
        break;
      case TurtleGrafik.MUSTER_SPIRALE:
        zeichneSpirale();
        break;
      case TurtleGrafik.MUSTER_ERWEITERTE_ZIRKELBLUME: 
        zeichneErweiterteZirkelblume();  
        break; 
      case TurtleGrafik.MUSTER_ZUFÄLLIGE_KREISE: 
        zeichneZufälligeKreise();
        break; 
      case TurtleGrafik.MUSTER_GESPIEGELTE_KREISE: 
        zeichneGespiegelteKreise();
        break; 
      case TurtleGrafik.MUSTER_WÜRFEL: 
        zeichneWürfel();
        break; 
      case TurtleGrafik.MUSTER_WEIßES_LOCH: 
        zeichneWeißesLoch();
        break; 
      case TurtleGrafik.MUSTER_GRÖßER_WERDENDER_STERN: 
        zeichneGrößerWerdenderStern();
        break; 
      case TurtleGrafik.MUSTER_ZWEI_FÜNFECKE: 
        zeichneZweiFünfecke();
        break; 
      case TurtleGrafik.MUSTER_SCHWARZES_LOCH: 
        zeichneSchwarzesLoch();
        break; 
      case TurtleGrafik.MUSTER_GROßE_SPIRALE:
        zeichneGroßeSpirale();
        break; 
      case TurtleGrafik.MUSTER_PYRAMIDE_ZWEI: 
        zeichnePyramideZwei();
        break; 
      case TurtleGrafik.MUSTER_DURCH_RADAR_ENTSTEHENDES_MUSTER:
        zeichneDurchRadarEntstehendesMuster();
        break; 
      case TurtleGrafik.MUSTER_ACHTECKE:
        zeichneAchtecke();
        break; 
      case TurtleGrafik.MUSTER_VERBUNDENER_POLYGONENKREIS:
        zeichneVerbundenderPolygonenkreis();
        break; 
      case TurtleGrafik.MUSTER_GEFÜLLTER_KREIS: 
        zeichneGefüllterKreis();
        break;
        
      default:
        bewegeTurtleAnKoordinaten(posX, posY);
    }
  } 
  
  private void zeichneStern() {   
    for (int i = 0; i <= 40; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(50000000);
      turtle.forward(200);
      turtle.left(150);
    }
  }
  
  private void zeichnePyramide() {
    for (int i = 0; i <= 80; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(50000000);
      turtle.forward(i);
      turtle.left(90);
    }    
  }
  
  private void zeichneSpirale() {
    for (int i = 0; i <= 800; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(90000000);
      turtle.forward(i);
      turtle.left(this.winkel);
    }
  }
  
  private void zeichneErweiterteZirkelblume() {
    for (int i = 0; i <= 6; i++) { 
      if (abbruch == true) {
        break;
      }
      turtle.leftCircle(100);
      turtle.speed(500000); 
      turtle.forward(100); 
      turtle.leftCircle(70);
      turtle.left(60); 
    }     
  }
  
  private void zeichneZufälligeKreise () {
    for (int i = 0; i <= 8000; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(999999999); 
      turtle.forward(10); 
      turtle.left(10);
      turtle.leftCircle(Zufall()); 
    }
  }
  
  private void zeichneGespiegelteKreise () {
    for (int i = 0; i <= 4; i++) {
      if (abbruch == true) {
        break;
      }
      Spiegel(); 
    } 
  }
  
  private void zeichneWürfel () {
    turtle.speed (9999);
    dreidimensional();
    turtle.right(135);
    turtle.forward(100);
    dreidimensional();
    turtle.left(135);
    turtle.forward(100);
    turtle.right(90); 
    turtle.forward(100);
    turtle.left(135);
    turtle.forward(50); 
    turtle.left(45);
    turtle.forward(100);
    turtle.left(90);
    turtle.forward(100);
    turtle.left(90);
    turtle.forward(100);
  }
  
  private void zeichneWeißesLoch () { 
    for (int i = 0; i <= 1000000000; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(9999999);
      turtle.forward(2);
      turtle.left(this.winkel);
      turtle.forward(1000);
      turtle.back(1000);
    } 
  }
  
  private void zeichneGrößerWerdenderStern () {
    for (int i = 0; i <= 800; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(99999999);
      turtle.forward(i);
      turtle.left(200); 
    }
  }
  
  private void zeichneZweiFünfecke () {
    for (int i = 0; i <= 800; i++)  {      
      if (abbruch == true) {
        break;
      }
      turtle.speed(99999999);
      turtle.forward(i);
      turtle.left(280); 
    }
  } 
  
  private void zeichneSchwarzesLoch () {
    for (int i = 0; i <= 800; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(999999); 
      turtle.forward(i);
      turtle.left(179); 
    }
  }
  
  private void zeichneGroßeSpirale () {
    for (int i = 0; i <= 800; i++) { 
      if (abbruch == true) {
        break;
      }
      turtle.speed(999999); 
      turtle.forward(i);
      turtle.left(91);
    }
  }
  
  private void zeichnePyramideZwei () {
    for (int i = 0; i <= 800; i++) {
      if (abbruch == true) {
        break;
      }
      turtle.speed(999999);  
      turtle.forward(i); 
      turtle.left(120); 
    }
  }
  
  private void zeichneDurchRadarEntstehendesMuster () {
    for (int i = 0; i <= 800; i++) { 
      if (abbruch == true) {
        break;
      }
      turtle.speed(99999999);
      turtle.forward(1000); 
      turtle.back(1000); 
      turtle.left(1); 
    } 
  }
  
  private void zeichneAchtecke () {
    for (int i = 0; i <= 800; i++) { 
      if (abbruch == true) {
        break;
      }
      Achteck();
      turtle.left(5); 
    }   
  }
  
  private void zeichneVerbundenderPolygonenkreis () {
    for (int i = 0; i <= 40; i++) { 
      if (abbruch == true) {
        break;
      }
      turtle.left(90); 
      turtle.forward(100);
      turtle.left(120); 
      turtle.forward(100); 
      turtle.left(120); 
      turtle.forward(100);
      turtle.left(1); 
    } // end of for
    
  }
  
  private void zeichneGefüllterKreis () {
    for (int i = 0; i <= 800; i++) { 
      if (abbruch == true) {
        break;
      }
      turtle.left(90);
      turtle.forward(100); 
      turtle.left(120); 
      turtle.forward(100); 
      turtle.left(120);
      turtle.forward(100); 
      turtle.left(1); 
    } 
  }
  
  private void bewegeTurtleAnKoordinaten(int x, int y) {
    turtle.setPos(x, y);
  }

  private int Zufall() {
    int e;
    e = (int) (Math.random() * 100 + 1);
    return e;
  }

  private void Spiegel() {
    for (int j = 0; j <= 100; j++) {
      turtle.leftCircle(j);
      turtle.left(180);
      
    }
  }

  private void dreidimensional() {
    turtle.right(90);
    turtle.forward(100);
    turtle.left(45);
    turtle.forward(50);
    turtle.left(135);
    turtle.forward(100);
    turtle.left(45);
    turtle.forward(50);
  }

  private void Achteck() {
    for (int j = 0; j <= 8; j++) {
      turtle.forward(100);
      turtle.left(45);
    } // end of for
  }

}
