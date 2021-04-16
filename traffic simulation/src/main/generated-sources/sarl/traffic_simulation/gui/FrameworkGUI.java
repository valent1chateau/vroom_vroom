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
package traffic_simulation.gui;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import java.awt.Graphics2D;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import traffic_simulation.environment.EnvironmentListener;
import traffic_simulation.environment.WorldModelState;

/**
 * GUI for the simulation framework.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(11)
@SuppressWarnings("all")
public interface FrameworkGUI extends EnvironmentListener {
  /**
   * Change the visibility of the GUI.
   * 
   * @param visible
   */
  public abstract void setVisible(final boolean visible);
  
  /**
   * Release all the resources own by the UI.
   */
  public abstract void dispose();
  
  /**
   * Change the message in the dedicated box.
   * 
   * @param message - the message.
   */
  public abstract void setMessage(final String message);
  
  /**
   * Replies the message in the dedicated box.
   * 
   * @return the message (could be <code>null</code>).
   */
  public abstract String getMessage();
  
  /**
   * Replies the width of the world.
   * 
   * @return the width.
   */
  public abstract float getWorldWidth();
  
  /**
   * Replies the height of the world.
   * 
   * @return the height.
   */
  public abstract float getWorldHeight();
  
  /**
   * Replies the last environment state.
   * 
   * @return the last environment state.
   */
  public abstract WorldModelState getLastWorldState();
  
  /**
   * Convert the size in the MAS into the equivalent size on the screen.
   * 
   * @param size the size
   * @return the size on the screen.
   */
  public abstract float mas2screen(final float size);
  
  /**
   * Paint the world
   */
  public abstract void paintWorld(final Graphics2D g);
  
  /**
   * Paint the coordinate system axes.
   */
  public abstract void paintAxes(final Graphics2D g);
  
  /**
   * Change the handler for termination queried with the UI.
   * 
   * @param handler the handler
   */
  public abstract void setTerminationHandler(final Procedure0 handler);
  
  /**
   * Replies the handler for termination queried with the UI.
   * 
   * @return the handler
   */
  public abstract Procedure0 getTerminationHandler();
}
