package traffic_simulation.environment;

import com.sun.javafx.application.LauncherImpl;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import traffic_simulation.gui.Menu;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Main {
  public static void main(final String... args) {
    LauncherImpl.launchApplication(Menu.class, args);
  }
  
  @SyntheticMember
  public Main() {
    super();
  }
}
