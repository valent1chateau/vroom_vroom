package traffic_simulation.gui;

import io.sarl.bootstrap.SRE;
import io.sarl.bootstrap.SREBootstrap;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Environment;
import traffic_simulation.environment.EnvironmentAgent;
import traffic_simulation.environment.Window2;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Menu extends Application {
  private Button buttonPlay;
  
  private Button buttonExit;
  
  private Window2 win;
  
  private Environment env;
  
  private Label labelnbAgent;
  
  private Label labelDimFen;
  
  private Label labelAccel;
  
  private Label labelUnitAccel;
  
  private Label labelFeu;
  
  private Label labelUnitTemps;
  
  private TextField nbAgentText;
  
  private TextField nbHautFen;
  
  private TextField nbLargFen;
  
  private TextField nbAccel;
  
  private TextField nbTempsFeu;
  
  private int nbAgent;
  
  private int LongueurFenetre;
  
  private int HauteurFenetre;
  
  private double nbAcceleration;
  
  private int nbTempsFeuRouge;
  
  public Menu() {
    abstract class __Menu_0 implements EventHandler<ActionEvent> {
      public abstract void handle(final ActionEvent e);
    }
    
    abstract class __Menu_1 implements EventHandler<ActionEvent> {
      public abstract void handle(final ActionEvent e);
    }
    
    Button _button = new Button(" ▶ ");
    this.buttonPlay = _button;
    __Menu_0 ___Menu_0 = new __Menu_0() {
      public void handle(final ActionEvent e) {
        try {
          Menu.this.nbAgent = Integer.parseInt(Menu.this.nbAgentText.getText());
          Menu.this.LongueurFenetre = Integer.parseInt(Menu.this.nbLargFen.getText());
          Menu.this.HauteurFenetre = Integer.parseInt(Menu.this.nbHautFen.getText());
          Menu.this.nbAcceleration = Double.parseDouble(Menu.this.nbAccel.getText());
          Menu.this.nbTempsFeuRouge = Integer.parseInt(Menu.this.nbTempsFeu.getText());
          Environment _environment = new Environment(Menu.this.LongueurFenetre, Menu.this.HauteurFenetre, Menu.this.nbAgent, Menu.this.nbAcceleration, Menu.this.nbTempsFeuRouge);
          Menu.this.env = _environment;
          Window2 _window2 = new Window2(Menu.this.env, Menu.this.LongueurFenetre, Menu.this.HauteurFenetre);
          Menu.this.win = _window2;
          Menu.this.win.show();
          SREBootstrap boot = SRE.getBootstrap();
          boot.startAgent(EnvironmentAgent.class, Menu.this.env);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    this.buttonPlay.setOnAction(___Menu_0);
    this.buttonPlay.setStyle("-fx-font: 22 arial; -fx-base: #8AFC88; -fx-text-fill : white");
    Button _button_1 = new Button("✖");
    this.buttonExit = _button_1;
    __Menu_1 ___Menu_1 = new __Menu_1() {
      public void handle(final ActionEvent e) {
        Platform.exit();
      }
    };
    this.buttonExit.setOnAction(___Menu_1);
    this.buttonExit.setStyle("-fx-font: 22 arial; -fx-base: #C55252; -fx-text-fill : white");
    Label _label = new Label("Nombre d\'agent normaux:");
    this.labelnbAgent = _label;
    TextField _textField = new TextField();
    this.nbAgentText = _textField;
    this.nbAgentText.setText("30");
    this.nbAgentText.setPrefColumnCount(2);
    Label _label_1 = new Label("Dimension de la fenetre:");
    this.labelDimFen = _label_1;
    TextField _textField_1 = new TextField();
    this.nbHautFen = _textField_1;
    this.nbHautFen.setText("800");
    this.nbHautFen.setPrefColumnCount(3);
    TextField _textField_2 = new TextField();
    this.nbLargFen = _textField_2;
    this.nbLargFen.setText("1200");
    this.nbLargFen.setPrefColumnCount(3);
    Label _label_2 = new Label("Accelération du véhicule:");
    this.labelAccel = _label_2;
    TextField _textField_3 = new TextField();
    this.nbAccel = _textField_3;
    this.nbAccel.setText("4");
    this.nbAccel.setPrefColumnCount(3);
    Label _label_3 = new Label("m/s^-2");
    this.labelUnitAccel = _label_3;
    Label _label_4 = new Label("Temps feu rouge:");
    this.labelFeu = _label_4;
    TextField _textField_4 = new TextField();
    this.nbTempsFeu = _textField_4;
    this.nbTempsFeu.setText("10");
    this.nbTempsFeu.setPrefColumnCount(3);
    Label _label_5 = new Label("s");
    this.labelUnitTemps = _label_5;
  }
  
  public void start(final Stage primaryStage) throws Exception {
    GridPane root = null;
    Scene scene = null;
    GridPane _gridPane = new GridPane();
    root = _gridPane;
    root.setHgap(10);
    root.setVgap(10);
    Insets _insets = new Insets(10, 10, 10, 10);
    root.setPadding(_insets);
    root.add(this.labelnbAgent, 0, 0);
    root.add(this.nbAgentText, 1, 0);
    root.add(this.labelDimFen, 0, 1);
    root.add(this.nbLargFen, 1, 1);
    root.add(this.nbHautFen, 2, 1);
    root.add(this.labelAccel, 0, 2);
    root.add(this.nbAccel, 1, 2);
    root.add(this.labelUnitAccel, 2, 2);
    root.add(this.labelFeu, 0, 3);
    root.add(this.nbTempsFeu, 1, 3);
    root.add(this.labelUnitTemps, 2, 3);
    root.add(this.buttonPlay, 0, 4);
    root.add(this.buttonExit, 1, 4);
    primaryStage.setTitle("Menu - Traffic simulation");
    Scene _scene = new Scene(root, 400, 250);
    scene = _scene;
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Menu other = (Menu) obj;
    if (other.nbAgent != this.nbAgent)
      return false;
    if (other.LongueurFenetre != this.LongueurFenetre)
      return false;
    if (other.HauteurFenetre != this.HauteurFenetre)
      return false;
    if (Double.doubleToLongBits(other.nbAcceleration) != Double.doubleToLongBits(this.nbAcceleration))
      return false;
    if (other.nbTempsFeuRouge != this.nbTempsFeuRouge)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.nbAgent);
    result = prime * result + Integer.hashCode(this.LongueurFenetre);
    result = prime * result + Integer.hashCode(this.HauteurFenetre);
    result = prime * result + Double.hashCode(this.nbAcceleration);
    result = prime * result + Integer.hashCode(this.nbTempsFeuRouge);
    return result;
  }
}
