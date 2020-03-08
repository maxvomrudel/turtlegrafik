import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
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
  
  private TurtleSteuerung steuerung = null;
  private Turtle turtle;
  // Ende Attribute
  
  // -------------------------------------------------------------------------
  // Methoden
  
  public void init() {
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
    labelMuster.setText("Auswahl Turtle-Figur");
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
    
    turtle = new Turtle(turtlePane1);
  }
  // Anfang Methoden
  
  public void startTurtle(ActionEvent evt) {
    int posX = numberFieldX.getInt();
    int posY = numberFieldY.getInt();
    int winkel = numberFieldWinkel.getInt();
    String figur = musterChoice.getSelectedItem();
    turtle = new Turtle(turtlePane1);
    steuerung = new TurtleSteuerung(turtle, posX, posY, winkel, figur);    
    steuerung.start();
  }
  
  public void cleanTurtlePane(ActionEvent evt) {
    turtlePane1.clear();
  }
  
  public void stopTurtle(ActionEvent evt) {
    if (this.steuerung != null) {      
      steuerung.cancel();
    }
  }
  
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
  
  // Ende Methoden
  
}
