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

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.concurrent.TimeUnit;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.time.Time;

/**
 * Abstract implementation of a time.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class AbstractTime implements Time {
  @Pure
  public static float secondsToUnits(final float t, final TimeUnit unit) {
    float _switchResult = (float) 0;
    if (unit != null) {
      switch (unit) {
        case DAYS:
          _switchResult = (t / 86400f);
          break;
        case HOURS:
          _switchResult = (t / 3600f);
          break;
        case MINUTES:
          _switchResult = (t / 60f);
          break;
        case MILLISECONDS:
          _switchResult = (t * 1000f);
          break;
        case MICROSECONDS:
          _switchResult = (t * 1000000f);
          break;
        case NANOSECONDS:
          _switchResult = (t * 1000000000f);
          break;
        default:
          _switchResult = t;
          break;
      }
    } else {
      _switchResult = t;
    }
    return _switchResult;
  }
  
  @Pure
  public static float unitsToSeconds(final float t, final TimeUnit unit) {
    float _switchResult = (float) 0;
    if (unit != null) {
      switch (unit) {
        case DAYS:
          _switchResult = (t * 86400f);
          break;
        case HOURS:
          _switchResult = (t * 3600f);
          break;
        case MINUTES:
          _switchResult = (t * 60f);
          break;
        case MILLISECONDS:
          _switchResult = (t / 1000f);
          break;
        case MICROSECONDS:
          _switchResult = (t / 1000000f);
          break;
        case NANOSECONDS:
          _switchResult = (t / 1000000000f);
          break;
        default:
          _switchResult = t;
          break;
      }
    } else {
      _switchResult = t;
    }
    return _switchResult;
  }
  
  @Override
  public float perSecond(final float amountPerSecond) {
    float _lastStepDuration = this.getLastStepDuration();
    return (amountPerSecond * _lastStepDuration);
  }
  
  @Override
  @Pure
  public int compareTo(final Time o) {
    float _currentTime = 0f;
    if (o!=null) {
      _currentTime=o.getCurrentTime();
    }
    float t = _currentTime;
    float _currentTime_1 = this.getCurrentTime();
    return Float.compare(_currentTime_1, t);
  }
  
  @SyntheticMember
  public AbstractTime() {
    super();
  }
}
