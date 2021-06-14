package traffic_simulation.agent;

import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.agent.influence;
import traffic_simulation.environment.Perceptions;
import traffic_simulation.environment.Vehicle;
import traffic_simulation.environment.suicide;
import traffic_simulation.util.Tools;

@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class classicDriver extends Agent {
  private Tools tool = new Tools();
  
  private double So = 1.5;
  
  private double T = 1.5;
  
  private double b = 1.67;
  
  private boolean kill = false;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.setLoggingName("classicDriver");
  }
  
  private void $behaviorUnit$suicide$1(final suicide occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  private void $behaviorUnit$Perceptions$2(final Perceptions occurrence) {
    ArrayList<Vehicle> p = occurrence.p_value;
    double[] etat = occurrence.state;
    double dim_car = occurrence.dim;
    Point2D coord_car = occurrence.loc;
    boolean isRed = occurrence.isRedView;
    double distWithRedLight = occurrence.distwithLight;
    double dist_min = (-1.0);
    Vehicle carInFront = null;
    double a = 0;
    boolean _isEmpty = p.isEmpty();
    if ((_isEmpty != true)) {
      for (int i = 0; (i < p.size()); i++) {
        {
          double distBetweenCar = this.tool.distance_vehicle2(coord_car, p.get(i), dim_car);
          if ((i == 0)) {
            dist_min = distBetweenCar;
            carInFront = p.get(i);
          } else {
            if ((distBetweenCar < dist_min)) {
              dist_min = distBetweenCar;
              carInFront = p.get(i);
            }
          }
        }
      }
      if (isRed) {
        if ((distWithRedLight < dist_min)) {
          if (((distWithRedLight > 5) && (etat[1] == 0))) {
            a = this.tool.accelerationFree(etat[2], etat[1], etat[0]);
          } else {
            a = this.tool.accelerationInt(etat[2], etat[1], this.b, distWithRedLight, 1, etat[1], this.T);
          }
        } else {
          if (((dist_min > 5) && (etat[1] == 0))) {
            a = this.tool.accelerationFree(etat[2], etat[1], etat[0]);
          } else {
            a = this.tool.accelerationInt(etat[2], etat[1], this.b, dist_min, 1, etat[1], this.T);
          }
        }
      } else {
        if (((dist_min > 5) && (etat[1] == 0))) {
          a = this.tool.accelerationFree(etat[2], etat[1], etat[0]);
        } else {
          a = this.tool.accelerationInt(etat[2], etat[1], this.b, dist_min, 1, etat[1], this.T);
        }
      }
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      UUID _iD = this.getID();
      influence _influence = new influence(a, _iD);
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influence);
    } else {
      if (isRed) {
        if (((distWithRedLight > 5) && (etat[1] == 0))) {
          a = this.tool.accelerationFree(etat[2], etat[1], etat[0]);
        } else {
          a = this.tool.accelerationInt(etat[2], etat[1], this.b, distWithRedLight, 1, etat[1], this.T);
        }
      } else {
        a = this.tool.accelerationFree(etat[2], etat[1], etat[0]);
      }
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      UUID _iD_1 = this.getID();
      influence _influence_1 = new influence(a, _iD_1);
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_influence_1);
    }
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Perceptions(final Perceptions occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Perceptions$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$suicide(final suicide occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$suicide$1(occurrence));
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
    classicDriver other = (classicDriver) obj;
    if (Double.doubleToLongBits(other.So) != Double.doubleToLongBits(this.So))
      return false;
    if (Double.doubleToLongBits(other.T) != Double.doubleToLongBits(this.T))
      return false;
    if (Double.doubleToLongBits(other.b) != Double.doubleToLongBits(this.b))
      return false;
    if (other.kill != this.kill)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Double.hashCode(this.So);
    result = prime * result + Double.hashCode(this.T);
    result = prime * result + Double.hashCode(this.b);
    result = prime * result + Boolean.hashCode(this.kill);
    return result;
  }
  
  @SyntheticMember
  public classicDriver(final UUID arg0, final UUID arg1) {
    super(arg0, arg1);
  }
  
  @SyntheticMember
  @Deprecated
  @Inject
  public classicDriver(final BuiltinCapacitiesProvider arg0, final UUID arg1, final UUID arg2) {
    super(arg0, arg1, arg2);
  }
  
  @SyntheticMember
  @Inject
  public classicDriver(final UUID arg0, final UUID arg1, final DynamicSkillProvider arg2) {
    super(arg0, arg1, arg2);
  }
}
