package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SarlSpecification("0.11")
@SarlElementType(15)
@SuppressWarnings("all")
public class suicide extends Event {
  @SyntheticMember
  public suicide() {
    super();
  }
  
  @SyntheticMember
  public suicide(final Address arg0) {
    super(arg0);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 588368462L;
}
