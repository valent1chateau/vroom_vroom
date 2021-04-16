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
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Environment;
import traffic_simulation.environment.SituatedObject;

/**
 * State of the world model.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class WorldModelState {
  private final List<SituatedObject> objects;
  
  /**
   * @param environment
   */
  public WorldModelState(final Environment environment) {
    ArrayList<SituatedObject> _arrayList = new ArrayList<SituatedObject>();
    this.objects = _arrayList;
    Iterable<? extends SituatedObject> _allObjects = environment.getAllObjects();
    for (final SituatedObject o : _allObjects) {
      SituatedObject _clone = o.clone();
      this.objects.add(_clone);
    }
  }
  
  /**
   * Replies the objects in the environment.
   * 
   * @return the objects in the environment.
   */
  @Pure
  public Iterable<SituatedObject> getObjects() {
    return this.objects;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}
