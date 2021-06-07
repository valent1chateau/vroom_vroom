/**
 * $Id$
 * 
 * Copyright (c) 2011-17 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.classicDriverBody;
import traffic_simulation.environment.priorityVehicleBody;

/**
 * Situated environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Environment {
  private int agentsToSpawn;
  
  @Accessors
  private TreeMap<UUID, classicDriverBody> bodyList;
  
  @Accessors
  private priorityVehicleBody priorityVehicle;
  
  @Accessors
  private Map map;
  
  public Environment() {
    TreeMap<UUID, classicDriverBody> _treeMap = new TreeMap<UUID, classicDriverBody>();
    this.bodyList = _treeMap;
    priorityVehicleBody _priorityVehicleBody = new priorityVehicleBody();
    this.priorityVehicle = _priorityVehicleBody;
    Map _map = new Map(1600, 900);
    this.map = _map;
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
  
  public void initEnvironment(final int agents) {
    this.agentsToSpawn = agents;
    int i = 0;
    classicDriverBody currentV = null;
    while ((i < (this.agentsToSpawn - 1))) {
      {
        classicDriverBody _classicDriverBody = new classicDriverBody(this.map);
        currentV = _classicDriverBody;
        this.bodyList.put(currentV.getID(), currentV);
        i++;
      }
    }
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
    if (other.agentsToSpawn != this.agentsToSpawn)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.agentsToSpawn);
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
}
