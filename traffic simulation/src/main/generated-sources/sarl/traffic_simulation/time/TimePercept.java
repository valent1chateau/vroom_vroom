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
package traffic_simulation.time;

import com.google.common.base.Objects;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.time.AbstractTime;
import traffic_simulation.time.Time;

/**
 * A perception of the time.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class TimePercept extends AbstractTime implements Serializable, Cloneable {
  private final float currentTime;
  
  private final float stepDuration;
  
  /**
   * @param currentTime
   * @param stepDuration
   */
  public TimePercept(final float currentTime, final float stepDuration) {
    this.currentTime = currentTime;
    this.stepDuration = stepDuration;
  }
  
  @DefaultValueSource
  public float getCurrentTime(@DefaultValue("traffic_simulation.time.TimePercept#GETCURRENTTIME_0") final TimeUnit unit) {
    return AbstractTime.secondsToUnits(this.currentTime, unit);
  }
  
  /**
   * Default value for the parameter unit
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private static final TimeUnit $DEFAULT_VALUE$GETCURRENTTIME_0 = null;
  
  @DefaultValueSource
  public float getLastStepDuration(@DefaultValue("traffic_simulation.time.TimePercept#GETLASTSTEPDURATION_0") final TimeUnit unit) {
    return AbstractTime.secondsToUnits(this.stepDuration, unit);
  }
  
  /**
   * Default value for the parameter unit
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private static final TimeUnit $DEFAULT_VALUE$GETLASTSTEPDURATION_0 = null;
  
  @Override
  @Pure
  public TimePercept clone() {
    try {
      Object _clone = super.clone();
      return ((TimePercept) _clone);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    boolean _xifexpression = false;
    if ((obj instanceof Time)) {
      float _currentTime = ((Time)obj).getCurrentTime();
      _xifexpression = (this.currentTime == _currentTime);
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return Objects.hashCode(Float.valueOf(this.currentTime));
  }
  
  @Override
  @Pure
  public String toString() {
    return Float.toString(this.currentTime);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 904984924L;
}
