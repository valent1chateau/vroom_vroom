package traffic_simulation.agent;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.11")
@SarlElementType(15)
@SuppressWarnings("all")
public class influence extends Event {
  public double acc;
  
  public UUID idt;
  
  public influence(final double a, final UUID id) {
    this.acc = a;
    this.idt = id;
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
    influence other = (influence) obj;
    if (Double.doubleToLongBits(other.acc) != Double.doubleToLongBits(this.acc))
      return false;
    if (!Objects.equals(this.idt, other.idt))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Double.hashCode(this.acc);
    result = prime * result + Objects.hashCode(this.idt);
    return result;
  }
  
  /**
   * Returns a String representation of the influence event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("acc", this.acc);
    builder.add("idt", this.idt);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -1558656705L;
}
