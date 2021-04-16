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
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import javax.swing.JDialog;

/**
 * Dialog box for selecting the type of a behavior.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class BehaviorTypeSelector extends JDialog {
  @SyntheticMember
  public BehaviorTypeSelector() {
    super();
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Dialog owner) {
    super(owner);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Frame owner) {
    super(owner);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Window owner) {
    super(owner);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Dialog owner, final boolean modal) {
    super(owner, modal);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Dialog owner, final String title) {
    super(owner, title);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Frame owner, final boolean modal) {
    super(owner, modal);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Frame owner, final String title) {
    super(owner, title);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Window owner, final Dialog.ModalityType modalityType) {
    super(owner, modalityType);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Window owner, final String title) {
    super(owner, title);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Dialog owner, final String title, final boolean modal) {
    super(owner, title, modal);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Frame owner, final String title, final boolean modal) {
    super(owner, title, modal);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Window owner, final String title, final Dialog.ModalityType modalityType) {
    super(owner, title, modalityType);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Dialog owner, final String title, final boolean modal, final GraphicsConfiguration gc) {
    super(owner, title, modal, gc);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Frame owner, final String title, final boolean modal, final GraphicsConfiguration gc) {
    super(owner, title, modal, gc);
  }
  
  @SyntheticMember
  public BehaviorTypeSelector(final Window owner, final String title, final Dialog.ModalityType modalityType, final GraphicsConfiguration gc) {
    super(owner, title, modalityType, gc);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 1L;
}
