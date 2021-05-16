package traffic_simulation.environment;

import com.sun.javafx.application.LauncherImpl;
import io.sarl.bootstrap.SRE;
import io.sarl.bootstrap.SREBootstrap;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Exceptions;
import traffic_simulation.environment.Agenttest;
import traffic_simulation.environment.Node;
import traffic_simulation.environment.window;

/**
 * @author jeome
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Main {
  public static void main(final String... args) {
    Node n = new Node(1, Double.valueOf(10.0), Double.valueOf(20.0));
    Node n2 = new Node(2, Double.valueOf(12.0), Double.valueOf(22.0));
    Node n3 = new Node(4, Double.valueOf(1.0), Double.valueOf(2.0));
    n3 = n;
    n.getNeighboors().add(n2);
    Node _get = n.getNeighboors().get(0);
    _get.setId(3);
    n.setId(23);
    LauncherImpl.launchApplication(window.class, args);
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
