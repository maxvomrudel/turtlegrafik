import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ch.aplu.turtle.Turtle;
import ch.aplu.turtle.TurtlePane;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 22.11.2011
 * @author
 */

public class TurtleGrafik extends Applet {
  
  // -------------------------------------------------------------------------
  // Konstanten für Muster-Auwahl
  
  public static final String MUSTER_STERN = "Stern";  
  public static final String MUSTER_PYRAMIDE = "Pyramide";  
  public static final String MUSTER_SPIRALE = "Spirale";  
  public static final String MUSTER_ERWEITERTE_ZIRKELBLUME = "erweiterte Zirkelblume";
  public static final String MUSTER_ZUFÄLLIGE_KREISE = "zufällige Kreise";
  public static final String MUSTER_GESPIEGELTE_KREISE = "gespiegelte Kreise";
  public static final String MUSTER_WÜRFEL = "Würfel";
  public static final String MUSTER_WEIßES_LOCH = "weißes Loch";
  public static final String MUSTER_GRÖßER_WERDENDER_STERN = "größer werdender Stern";
  public static final String MUSTER_ZWEI_FÜNFECKE = "zwei Fünfecke";
  public static final String MUSTER_SCHWARZES_LOCH  = "schwarzes Loch ";
  public static final String MUSTER_GROßE_SPIRALE = "große Spirale";
  public static final String MUSTER_PYRAMIDE_ZWEI = "Pyramide zwei";
  public static final String MUSTER_DURCH_RADAR_ENTSTEHENDES_MUSTER = "durch Radar entstehendes Muster";
  public static final String MUSTER_ACHTECKE = "Achtecke";
  public static final String MUSTER_VERBUNDENER_POLYGONENKREIS = "verbudener Polygonenkreis";
  public static final String MUSTER_GEFÜLLTER_KREIS = "gefüllter Kreis";
  
  
  // -------------------------------------------------------------------------
  // Anfang Attribute
  
  private TurtlePane turtlePane1 = new TurtlePane();
  private Button buttonStart = new Button();
  private Label labelStartpunkt = new Label();
  private NumberField numberFieldX = new NumberField();
  private NumberField numberFieldY = new NumberField();
  private Button buttonClean = new Button();
  private Choice musterChoice = new Choice();
  private Label labelWinkel = new Label();
  private NumberField numberFieldWinkel = new NumberField();
  private Label labelMuster = new Label();
  private Button buttonStop = new Button();
  private TextArea log = new TextArea("", 1, 1, TextArea.SCROLLBARS_VERTICAL_ONLY);
  
  private Turtle turtle;
  private int posX;
  private int posY;
  private String muster;
  private int winkel;
  private Thread turtleThread;
  private boolean abbruch= false;
  // Ende Attribute
  
  // -------------------------------------------------------------------------
  // Methoden
  
  public void init() {
    // Anfang Komponenten    
    Panel panel = new Panel(null);
    panel.setBounds(0, 0, 1039, 965);
    panel.setBackground(new Color(0xC0C0C0));
    add(panel);
    
    musterChoice.setBounds(16, 32, 249, 30);
    musterChoice.add(MUSTER_STERN);
    musterChoice.add(MUSTER_PYRAMIDE);
    musterChoice.add(MUSTER_SPIRALE);    
    musterChoice.add(MUSTER_ERWEITERTE_ZIRKELBLUME);
    musterChoice.add(MUSTER_ZUFÄLLIGE_KREISE);
    musterChoice.add(MUSTER_GESPIEGELTE_KREISE);
    musterChoice.add(MUSTER_WÜRFEL);
    musterChoice.add(MUSTER_WEIßES_LOCH);
    musterChoice.add(MUSTER_GRÖßER_WERDENDER_STERN);
    musterChoice.add(MUSTER_ZWEI_FÜNFECKE);
    musterChoice.add(MUSTER_SCHWARZES_LOCH);
    musterChoice.add(MUSTER_GROßE_SPIRALE);
    musterChoice.add(MUSTER_PYRAMIDE_ZWEI);
    musterChoice.add(MUSTER_DURCH_RADAR_ENTSTEHENDES_MUSTER);
    musterChoice.add(MUSTER_ACHTECKE);
    musterChoice.add(MUSTER_VERBUNDENER_POLYGONENKREIS);
    musterChoice.add(MUSTER_GEFÜLLTER_KREIS);
    
    musterChoice.addItemListener(new ItemListener() { 
      public void itemStateChanged(ItemEvent evt) { 
        musterAusgewaehlt(evt);
      }
    });
    
    panel.add(musterChoice);
    
    labelMuster.setBounds(18, 12, 126, 20);
    labelMuster.setText("Auswahl Turtle-Muster");
    panel.add(labelMuster);    
    
    labelStartpunkt.setBounds(289, 11, 110, 20);
    labelStartpunkt.setText("Startpunkt x / y");
    panel.add(labelStartpunkt);    
    
    numberFieldX.setBounds(288, 32, 48, 24);
    numberFieldX.setText("0");
    panel.add(numberFieldX);
    
    numberFieldY.setBounds(344, 32, 48, 24);
    numberFieldY.setText("0");
    panel.add(numberFieldY);
    
    labelWinkel.setBounds(416, 12, 48, 20);
    labelWinkel.setText("Winkel");
    labelWinkel.setVisible(false);
    
    panel.add(labelWinkel);
    
    numberFieldWinkel.setBounds(416, 32, 48, 24);
    numberFieldWinkel.setText("30");
    numberFieldWinkel.setVisible(false);      
    
    panel.add(numberFieldWinkel);
    
    buttonStart.setBounds(16, 72, 75, 30);
    buttonStart.setLabel("Start");
    buttonStart.setBackground(Color.GREEN);
    buttonStart.setFont(new Font("Arial", Font.BOLD, 12));
    buttonStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        startTurtle(evt);
      }
    });    
    panel.add(buttonStart);
    
    buttonStop.setBounds(111, 72, 75, 30);
    buttonStop.setLabel("Stop");
    buttonStop.setBackground(new Color(0xFFC800));
    buttonStop.setFont(new Font("Dialog", Font.BOLD, 12));
    buttonStop.setEnabled(false);
    buttonStop.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        stopTurtle(evt);
      }
    });
    panel.add(buttonStop);
    
    buttonClean.setBounds(192, 72, 75, 30);
    buttonClean.setLabel("Clean");
    buttonClean.setBackground(new Color(0xCCE5FF));
    buttonClean.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        cleanTurtlePane(evt);
      }
    });
    panel.add(buttonClean);
    
    turtlePane1.setBounds(16, 120, 448, 400);
    panel.add(turtlePane1);

    log.setBounds(18, 527, 448, 92);
    log.setEditable(false);
    panel.add(log);
    // Ende Komponenten

    turtle = new Turtle(turtlePane1);
  }
  // Anfang Methoden
  
  public void musterAusgewaehlt(ItemEvent evt) {
    String muster = (String)evt.getItem();    
    if (muster.equals(MUSTER_SPIRALE) || muster.equals(MUSTER_WEIßES_LOCH)) {
      labelWinkel.setVisible(true);
      numberFieldWinkel.setVisible(true);
    }
    else {
      labelWinkel.setVisible(false);
      numberFieldWinkel.setVisible(false);      
    }
  } 
  
  public void startTurtle(ActionEvent evt) {
    posX = numberFieldX.getInt();
    posY = numberFieldY.getInt();
    winkel = numberFieldWinkel.getInt();
    muster = musterChoice.getSelectedItem();
    turtle = new Turtle(turtlePane1);
    abbruch = false;
    logMessage("start turtle: x=" + posX + ", y=" + posY + ", muster=" + muster);

    turtleThread = new Thread() {
      public void run() {
        runTurtle();
      }
    };
    turtleThread.start();
  }
  
  public void cleanTurtlePane(ActionEvent evt) {
    turtlePane1.clear();
  }
  
  public void stopTurtle(ActionEvent evt) {
    abbruch = true;
  }
  
  private void runTurtle() {
    logMessage("Zeichnen gestartet");
    bewegeTurtleAnKoordinaten(posX, posY);
    turtle.hideTurtle();
    buttonStart.setEnabled(false);
    buttonStop.setEnabled(true);

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
    
    buttonStart.setEnabled(true);
    buttonStop.setEnabled(false);
    logMessage("Zeichnen beendet");
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
  // Ende Methoden
  
  private void logMessage(String message) {
    String txt = log.getText();
    log.setText(txt + message + "\n"); 
  }
}
