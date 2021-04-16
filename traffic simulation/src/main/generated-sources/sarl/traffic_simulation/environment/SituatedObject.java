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
import java.io.Serializable;
import java.util.UUID;
import traffic_simulation.environment.ShapedObject;

/**
 * Object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(11)
@SuppressWarnings("all")
public interface SituatedObject extends ShapedObject, Cloneable, Comparable<SituatedObject> {
  /**
   * Clone the object.
   * 
   * @return the clone.
   */
  public abstract SituatedObject clone();
  
  /**
   * Replies the type of the object.
   * 
   * @return the type of the object.
   */
  public abstract Serializable getType();
  
  /**
   * Replies the identifier of the object.
   * 
   * @return the identifier of the object.
   */
  public abstract UUID getID();
  
  /**
   * Replies the name of the object.
   * 
   * The name is defined only for displaying purpose.
   * 
   * @return the name of the object.
   */
  public abstract String getName();
  
  /**
   * Replies the position of the object.
   * 
   * @return the x-coordinate of the position of this object.
   */
  public abstract float getX();
  
  /**
   * Replies the position of the object.
   * 
   * @return the y-coordinate of the position of this object.
   */
  public abstract float getY();
}
