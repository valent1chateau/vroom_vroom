package traffic_simulation.gui;

import io.sarl.bootstrap.SRE;
import io.sarl.bootstrap.SREBootstrap;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Environment;
import traffic_simulation.environment.EnvironmentAgent;
import traffic_simulation.environment.Window2;

/**
 * @author jeome
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Menu extends Application {
  private Button button;
  
  private Window2 win;
  
  private Environment env;
  
  public Menu() {
    abstract class __Menu_0 implements EventHandler<ActionEvent> {
      public abstract void handle(final ActionEvent e);
    }
    
    Environment _environment = new Environment();
    this.env = _environment;
    Button _button = new Button("Play");
    this.button = _button;
    __Menu_0 ___Menu_0 = new __Menu_0() {
      public void handle(final ActionEvent e) {
        try {
          Window2 _window2 = new Window2(Menu.this.env);
          Menu.this.win = _window2;
          Menu.this.win.show();
          SREBootstrap boot = SRE.getBootstrap();
          boot.startAgent(EnvironmentAgent.class, Menu.this.env);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    this.button.setOnAction(___Menu_0);
  }
  
  public void start(final Stage primaryStage) throws Exception {
    Scene scene = new Scene(this.button, 200, 100);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}
