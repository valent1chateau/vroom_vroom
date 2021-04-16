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

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.concurrent.TimeUnit;

/**
 * Time manager for the Jaak environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(11)
@SuppressWarnings("all")
public interface Time extends Comparable<Time> {
  /**
   * Replies the current time in the given time unit.
   * 
   * @param unit is the time unit to use for replied value.
   * @return the current time.
   */
  @DefaultValueSource
  public abstract float getCurrentTime(@DefaultValue("traffic_simulation.time.Time#GETCURRENTTIME_0") final TimeUnit unit);
  
  /**
   * Default value for the parameter unit
   */
  @SyntheticMember
  @SarlSourceCode("null")
  public static final TimeUnit $DEFAULT_VALUE$GETCURRENTTIME_0 = null;
  
  /**
   * Replies the duration of the last simulation step in the given time unit.
   * 
   * @param unit is the time unit used to format the replied value.
   * @return the duration of the last simulation step.
   */
  @DefaultValueSource
  public abstract float getLastStepDuration(@DefaultValue("traffic_simulation.time.Time#GETLASTSTEPDURATION_0") final TimeUnit unit);
  
  /**
   * Default value for the parameter unit
   */
  @SyntheticMember
  @SarlSourceCode("null")
  public static final TimeUnit $DEFAULT_VALUE$GETLASTSTEPDURATION_0 = null;
  
  /**
   * Replies the instant amount which is corresponds to the
   * given amount, given per second.
   * 
   * @param amountPerSecond
   * @return amountPerSecond * getTimeStepDuration()
   */
  public abstract float perSecond(final float amountPerSecond);
  
  /**
   * Replies the current time in the given time unit.
   * 
   * @optionalparam unit is the time unit to use for replied value.
   * @return the current time.
   */
  @DefaultValueUse("java.util.concurrent.TimeUnit")
  @SyntheticMember
  public default float getCurrentTime() {
    return getCurrentTime($DEFAULT_VALUE$GETCURRENTTIME_0);
  }
  
  /**
   * Replies the duration of the last simulation step in the given time unit.
   * 
   * @optionalparam unit is the time unit used to format the replied value.
   * @return the duration of the last simulation step.
   */
  @DefaultValueUse("java.util.concurrent.TimeUnit")
  @SyntheticMember
  public default float getLastStepDuration() {
    return getLastStepDuration($DEFAULT_VALUE$GETLASTSTEPDURATION_0);
  }
}
