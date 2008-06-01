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

// $Id: FeedManagerException.java,v 1.6 2006/12/04 23:43:27 italobb Exp $

package de.nava.informa.utils;

/**
 * Exception class, which is used if an invalid feed is tried to be loaded.
 *
 * @author Sam Newman
 * @see FeedManager
 */
public class FeedManagerException extends Exception {

  private static final long serialVersionUID = -1982751404099834335L;

  public FeedManagerException(Exception e) {
    super(e.getMessage());
    initCause(e);
  }

}
