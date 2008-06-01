//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// Niko Schmuck
// http://sourceforge.net/projects/informa
// mailto:niko_schmuck@users.sourceforge.net
//
// This library is free software.
//
// You may redistribute it and/or modify it under the terms of the GNU
// Lesser General Public License as published by the Free Software Foundation.
//
// Version 2.1 of the license should be included with this distribution in
// the file LICENSE. If the license is not included with this distribution,
// you may find a copy at the FSF web site at 'www.gnu.org' or 'www.fsf.org',
// or you may write to the Free Software Foundation, 675 Mass Ave, Cambridge,
// MA 02139 USA.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied waranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//

// $Id: ItemGuidIF.java,v 1.3 2005/09/27 22:17:07 niko_schmuck Exp $

package de.nava.informa.core;

/**
 * This interface is implemented by objects representing 
 * the GUID of a news item.
 *
 * @author Michael Harhen
 */
public interface ItemGuidIF extends WithIdMIF {

  String getLocation();
  void setLocation(String location);

  /**
   * @return indicates whether the guid is a permalink.
   */
  boolean isPermaLink();
  void setPermaLink(boolean permaLink);

}
