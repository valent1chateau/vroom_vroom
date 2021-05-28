package traffic_simulation.environment;

import com.sun.javafx.application.LauncherImpl;
import io.sarl.bootstrap.SRE;
import io.sarl.bootstrap.SREBootstrap;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Exceptions;
import traffic_simulation.environment.Agenttest;
import traffic_simulation.gui.Menu;

/**
 * @author jeome
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Main {
  public static void main(final String... args) {
    LauncherImpl.launchApplication(Menu.class, args);
  }
  
  public static void startexemple() {
    try {
      SREBootstrap boot = SRE.getBootstrap();
      boot.startAgent(Agenttest.class);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @SyntheticMember
  public Main() {
    super();
  }
}
