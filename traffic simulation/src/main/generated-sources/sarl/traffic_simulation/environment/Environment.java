package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.UUID;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.Node;
import traffic_simulation.environment.classicDriverBody;
import traffic_simulation.environment.priorityVehicleBody;
import traffic_simulation.environment.trafficLight;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Environment {
  private Timer timer = new Timer();
  
  @Accessors
  private TreeMap<UUID, classicDriverBody> bodyList;
  
  @Accessors
  private priorityVehicleBody priorityVehicle;
  
  @Accessors
  private Map map;
  
  @Accessors
  private ArrayList<trafficLight> trafficlight_lst;
  
  @Accessors
  private int nombreTotalAgentClassic;
  
  @Accessors
  private int tempsFeu;
  
  @Accessors
  private double nbAccl;
  
  public Environment(final int height, final int width, final int nbAgent, final double nbAcc, final int tpsFeu) {
    abstract class __Environment_0 extends TimerTask {
      public abstract void run();
    }
    
    TreeMap<UUID, classicDriverBody> _treeMap = new TreeMap<UUID, classicDriverBody>();
    this.bodyList = _treeMap;
    priorityVehicleBody _priorityVehicleBody = new priorityVehicleBody();
    this.priorityVehicle = _priorityVehicleBody;
    Map _map = new Map(height, width);
    this.map = _map;
    ArrayList<trafficLight> _arrayList = new ArrayList<trafficLight>();
    this.trafficlight_lst = _arrayList;
    this.nbAccl = nbAcc;
    this.tempsFeu = tpsFeu;
    this.addTrafficlight(3, "23", true);
    this.addTrafficlight(5, "45", true);
    this.addTrafficlight(13, "1213", true);
    this.addTrafficlight(15, "1415", true);
    this.addTrafficlight(9, "89", false);
    this.addTrafficlight(19, "1819", false);
    this.nombreTotalAgentClassic = nbAgent;
    __Environment_0 ___Environment_0 = new __Environment_0() {
      public void run() {
        for (int i = 0; (i < Environment.this.trafficlight_lst.size()); i++) {
          boolean _isGreen = Environment.this.trafficlight_lst.get(i).isGreen();
          if (_isGreen) {
            Environment.this.trafficlight_lst.get(i).turnRed();
          } else {
            Environment.this.trafficlight_lst.get(i).turnGreen();
          }
        }
      }
    };
    this.timer.scheduleAtFixedRate(___Environment_0, (this.tempsFeu * 1000), (this.tempsFeu * 1000));
  }
  
  public void Update() {
    Collection<classicDriverBody> a = this.bodyList.values();
    Set<java.util.Map.Entry<UUID, classicDriverBody>> _entrySet = this.bodyList.entrySet();
    for (final java.util.Map.Entry<UUID, classicDriverBody> entry : _entrySet) {
      {
        this.bodyList.get(entry.getKey()).accelerate(0.5);
        double _speed = this.bodyList.get(entry.getKey()).getSpeed();
        if ((_speed == 0)) {
        }
      }
    }
  }
  
  public boolean addTrafficlight(final int idNode, final String keyEdge, final boolean state) {
    boolean _xblockexpression = false;
    {
      Node n = this.map.getG().getListNodes().get(idNode);
      Edge e = this.map.getG().getDict_Edges().get(keyEdge);
      double _x = n.getCoord().getX();
      double _y = n.getCoord().getY();
      trafficLight Tf = new trafficLight(_x, _y, state);
      e.setHaveLight(true);
      e.setLight(Tf);
      _xblockexpression = this.trafficlight_lst.add(Tf);
    }
    return _xblockexpression;
  }
  
  public int stop() {
    int _xblockexpression = (int) 0;
    {
      this.timer.cancel();
      _xblockexpression = this.timer.purge();
    }
    return _xblockexpression;
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
    Environment other = (Environment) obj;
    if (other.nombreTotalAgentClassic != this.nombreTotalAgentClassic)
      return false;
    if (other.tempsFeu != this.tempsFeu)
      return false;
    if (Double.doubleToLongBits(other.nbAccl) != Double.doubleToLongBits(this.nbAccl))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.nombreTotalAgentClassic);
    result = prime * result + Integer.hashCode(this.tempsFeu);
    result = prime * result + Double.hashCode(this.nbAccl);
    return result;
  }
  
  @Pure
  public TreeMap<UUID, classicDriverBody> getBodyList() {
    return this.bodyList;
  }
  
  public void setBodyList(final TreeMap<UUID, classicDriverBody> bodyList) {
    this.bodyList = bodyList;
  }
  
  @Pure
  public priorityVehicleBody getPriorityVehicle() {
    return this.priorityVehicle;
  }
  
  public void setPriorityVehicle(final priorityVehicleBody priorityVehicle) {
    this.priorityVehicle = priorityVehicle;
  }
  
  @Pure
  public Map getMap() {
    return this.map;
  }
  
  public void setMap(final Map map) {
    this.map = map;
  }
  
  @Pure
  public ArrayList<trafficLight> getTrafficlight_lst() {
    return this.trafficlight_lst;
  }
  
  public void setTrafficlight_lst(final ArrayList<trafficLight> trafficlight_lst) {
    this.trafficlight_lst = trafficlight_lst;
  }
  
  @Pure
  public int getNombreTotalAgentClassic() {
    return this.nombreTotalAgentClassic;
  }
  
  public void setNombreTotalAgentClassic(final int nombreTotalAgentClassic) {
    this.nombreTotalAgentClassic = nombreTotalAgentClassic;
  }
  
  @Pure
  public int getTempsFeu() {
    return this.tempsFeu;
  }
  
  public void setTempsFeu(final int tempsFeu) {
    this.tempsFeu = tempsFeu;
  }
  
  @Pure
  public double getNbAccl() {
    return this.nbAccl;
  }
  
  public void setNbAccl(final double nbAccl) {
    this.nbAccl = nbAccl;
  }
}
