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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.ref.WeakReference;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.gui.FrameworkGUI;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class WindowClosingHandler extends WindowAdapter implements ActionListener {
  private final WeakReference<FrameworkGUI> parentUI;
  
  public WindowClosingHandler(final FrameworkGUI parent) {
    WeakReference<FrameworkGUI> _weakReference = new WeakReference<FrameworkGUI>(parent);
    this.parentUI = _weakReference;
  }
  
  @Override
  public void windowClosing(final WindowEvent e) {
    WeakReference<FrameworkGUI> _parentUI = this.parentUI;
    FrameworkGUI _get = null;
    if (_parentUI!=null) {
      _get=_parentUI.get();
    }
    Procedure0 _terminationHandler = _get.getTerminationHandler();
    if (_terminationHandler!=null) {
      _terminationHandler.apply();
    }
  }
  
  @Override
  public void actionPerformed(final ActionEvent e) {
    WeakReference<FrameworkGUI> _parentUI = this.parentUI;
    FrameworkGUI _get = null;
    if (_parentUI!=null) {
      _get=_parentUI.get();
    }
    Procedure0 _terminationHandler = _get.getTerminationHandler();
    if (_terminationHandler!=null) {
      _terminationHandler.apply();
    }
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
