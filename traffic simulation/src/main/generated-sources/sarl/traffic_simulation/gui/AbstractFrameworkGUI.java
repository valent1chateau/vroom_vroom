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
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import traffic_simulation.gui.FrameworkGUI;

/**
 * Abstract implementation of a GUI for the agent framework.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class AbstractFrameworkGUI extends JFrame implements FrameworkGUI {
  @SyntheticMember
  public AbstractFrameworkGUI() {
    super();
  }
  
  @SyntheticMember
  public AbstractFrameworkGUI(final GraphicsConfiguration arg0) {
    super(arg0);
  }
  
  @SyntheticMember
  public AbstractFrameworkGUI(final String arg0) {
    super(arg0);
  }
  
  @SyntheticMember
  public AbstractFrameworkGUI(final String arg0, final GraphicsConfiguration arg1) {
    super(arg0, arg1);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 1L;
}
