package traffic_simulation.environment;

import com.google.common.base.Objects;
import io.sarl.core.AgentSpawned;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.core.Schedules;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.util.SerializableProxy;
import java.awt.geom.Point2D;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.agent.classicDriver;
import traffic_simulation.agent.influence;
import traffic_simulation.environment.Environment;
import traffic_simulation.environment.Perceptions;
import traffic_simulation.environment.Vehicle;
import traffic_simulation.environment.classicDriverBody;
import traffic_simulation.environment.suicide;

@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class EnvironmentAgent extends Agent {
  private Environment environment;
  
  private AtomicInteger spawnedReceived = new AtomicInteger(0);
  
  private int nbrAgentOnMap = 0;
  
  private AtomicInteger countAgentInfluence = new AtomicInteger(0);
  
  private int countAgentSpawned = 0;
  
  private int nbTotalVehiculeClassic;
  
  private double accBody;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    this.environment = ((Environment) _get);
    this.nbTotalVehiculeClassic = this.environment.getNombreTotalAgentClassic();
    this.accBody = this.environment.getNbAccl();
    this.actionsOnInfluence();
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    this.environment.stop();
  }
  
  private void $behaviorUnit$AgentSpawned$2(final AgentSpawned occurrence) {
    int v = this.spawnedReceived.incrementAndGet();
    if ((v == this.countAgentSpawned)) {
      this.countAgentSpawned = 0;
      AtomicInteger _atomicInteger = new AtomicInteger(0);
      this.spawnedReceived = _atomicInteger;
      this.startLoop();
    }
  }
  
  private void $behaviorUnit$influence$3(final influence occurrence) {
    double ac = occurrence.acc;
    UUID id = occurrence.idt;
    classicDriverBody _get = this.environment.getBodyList().get(id);
    _get.setAcc(ac);
    int _incrementAndGet = this.countAgentInfluence.incrementAndGet();
    if ((_incrementAndGet == this.nbrAgentOnMap)) {
      this.countAgentInfluence.set(0);
      this.actionsOnInfluence();
    }
  }
  
  protected void startLoop() {
    TreeMap<UUID, classicDriverBody> bodies = this.environment.getBodyList();
    this.nbrAgentOnMap = this.environment.getBodyList().size();
    boolean _isEmpty = bodies.isEmpty();
    if ((_isEmpty == false)) {
      Set<Map.Entry<UUID, classicDriverBody>> _entrySet = bodies.entrySet();
      for (final Map.Entry<UUID, classicDriverBody> entry : _entrySet) {
        {
          bodies.get(entry.getKey()).calculatePerceptions();
          ArrayList<Vehicle> p = bodies.get(entry.getKey()).getPerception().getRes();
          double vmax = bodies.get(entry.getKey()).getMaxSpeed();
          double v = bodies.get(entry.getKey()).getSpeed();
          double amax = bodies.get(entry.getKey()).getAccMax();
          Point2D coo = bodies.get(entry.getKey()).getCoord();
          double dims = bodies.get(entry.getKey()).getDim();
          boolean redLight = bodies.get(entry.getKey()).getPerception().getLightIsView();
          double dstLight = bodies.get(entry.getKey()).getPerception().getDistWithLight();
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
          Perceptions _perceptions = new Perceptions(p, vmax, v, amax, coo, dims, redLight, dstLight);
          class $SerializableClosureProxy implements Scope<Address> {
            
            private final UUID $_key;
            
            public $SerializableClosureProxy(final UUID $_key) {
              this.$_key = $_key;
            }
            
            @Override
            public boolean matches(final Address it) {
              UUID _uUID = it.getUUID();
              return Objects.equal(_uUID, $_key);
            }
          }
          final Scope<Address> _function = new Scope<Address>() {
            @Override
            public boolean matches(final Address it) {
              UUID _uUID = it.getUUID();
              UUID _key = entry.getKey();
              return Objects.equal(_uUID, _key);
            }
            private Object writeReplace() throws ObjectStreamException {
              return new SerializableProxy($SerializableClosureProxy.class, entry.getKey());
            }
          };
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_perceptions, _function);
        }
      }
    }
  }
  
  protected void actionsOnInfluence() {
    try {
      ArrayList<UUID> listKey = new ArrayList<UUID>();
      boolean _isEmpty = this.environment.getBodyList().isEmpty();
      if ((_isEmpty != true)) {
        this.environment.Update();
        Set<Map.Entry<UUID, classicDriverBody>> _entrySet = this.environment.getBodyList().entrySet();
        for (final Map.Entry<UUID, classicDriverBody> entry : _entrySet) {
          double _position = this.environment.getBodyList().get(entry.getKey()).getPosition();
          double _distanceMax = this.environment.getBodyList().get(entry.getKey()).getDistanceMax();
          if ((_position >= _distanceMax)) {
            listKey.add(entry.getKey());
          }
        }
        for (int k = 0; (k < listKey.size()); k++) {
          {
            this.environment.getBodyList().remove(listKey.get(k));
            UUID key = listKey.get(k);
            DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
            suicide _suicide = new suicide();
            class $SerializableClosureProxy implements Scope<Address> {
              
              private final UUID key;
              
              public $SerializableClosureProxy(final UUID key) {
                this.key = key;
              }
              
              @Override
              public boolean matches(final Address it) {
                UUID _uUID = it.getUUID();
                return Objects.equal(_uUID, key);
              }
            }
            final Scope<Address> _function = new Scope<Address>() {
              @Override
              public boolean matches(final Address it) {
                UUID _uUID = it.getUUID();
                return Objects.equal(_uUID, key);
              }
              private Object writeReplace() throws ObjectStreamException {
                return new SerializableProxy($SerializableClosureProxy.class, key);
              }
            };
            _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_suicide, _function);
          }
        }
      }
      Random rand = new Random();
      int _nextInt = rand.nextInt(4);
      int nbAgent = (1 + _nextInt);
      for (int i = 1; (i <= nbAgent); i++) {
        {
          traffic_simulation.environment.Map _map = this.environment.getMap();
          classicDriverBody bodyAgent = new classicDriverBody(_map, this.accBody);
          boolean _canSpawn = bodyAgent.canSpawn();
          if ((_canSpawn == true)) {
            if ((this.nbTotalVehiculeClassic != 0)) {
              bodyAgent.initialzeEdgeBodies();
              this.environment.getBodyList().put(bodyAgent.getID(), bodyAgent);
              Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
              DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
              _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(classicDriver.class, bodyAgent.getID(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext());
              int _countAgentSpawned = this.countAgentSpawned;
              this.countAgentSpawned = (_countAgentSpawned + 1);
              int _nbTotalVehiculeClassic = this.nbTotalVehiculeClassic;
              this.nbTotalVehiculeClassic = (_nbTotalVehiculeClassic - 1);
            }
          }
        }
      }
      if ((this.countAgentSpawned == 0)) {
        Thread.sleep(20);
        if (((this.nbTotalVehiculeClassic == 0) && this.environment.getBodyList().isEmpty())) {
          Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
          _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
        } else {
          this.startLoop();
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
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
  
  @Extension
  @ImportedCapacityFeature(Schedules.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_SCHEDULES;
  
  @SyntheticMember
  @Pure
  private Schedules $CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class);
    }
    return $castSkill(Schedules.class, this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
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
  private void $guardEvaluator$AgentSpawned(final AgentSpawned occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentSpawned$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$influence(final influence occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$influence$3(occurrence));
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
    EnvironmentAgent other = (EnvironmentAgent) obj;
    if (other.nbrAgentOnMap != this.nbrAgentOnMap)
      return false;
    if (other.countAgentSpawned != this.countAgentSpawned)
      return false;
    if (other.nbTotalVehiculeClassic != this.nbTotalVehiculeClassic)
      return false;
    if (Double.doubleToLongBits(other.accBody) != Double.doubleToLongBits(this.accBody))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.nbrAgentOnMap);
    result = prime * result + Integer.hashCode(this.countAgentSpawned);
    result = prime * result + Integer.hashCode(this.nbTotalVehiculeClassic);
    result = prime * result + Double.hashCode(this.accBody);
    return result;
  }
  
  @SyntheticMember
  public EnvironmentAgent(final UUID arg0, final UUID arg1) {
    super(arg0, arg1);
  }
  
  @SyntheticMember
  @Deprecated
  @Inject
  public EnvironmentAgent(final BuiltinCapacitiesProvider arg0, final UUID arg1, final UUID arg2) {
    super(arg0, arg1, arg2);
  }
  
  @SyntheticMember
  @Inject
  public EnvironmentAgent(final UUID arg0, final UUID arg1, final DynamicSkillProvider arg2) {
    super(arg0, arg1, arg2);
  }
}
