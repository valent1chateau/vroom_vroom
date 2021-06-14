package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import traffic_simulation.environment.Graph;
import traffic_simulation.environment.Vehicle;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class priorityVehicleBody extends Vehicle {
  public void newPath(final Graph G) {
    int i = 0;
  }
  
  @SyntheticMember
  public priorityVehicleBody() {
    super();
  }
}
