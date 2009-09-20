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


// $Id: UnsupportedFormatException.java,v 1.6 2005/09/27 22:17:07 niko_schmuck Exp $

package de.nava.informa.core;

/**
 * An exception thrown when no appropiate parser is available for a
 * specific document instance.
 *
 * @author Niko Schmuck (niko@nava.de) 
 */
public class UnsupportedFormatException extends ParseException {

	private static final long serialVersionUID = -9203172795373959253L;

	public UnsupportedFormatException() {
    super();
  }

  public UnsupportedFormatException(String message) {
    super(message);
  }

  public UnsupportedFormatException(Throwable cause) {
    // Java 1.3 does not support this constructor
    super("UnsupportedFormatException: " + cause.getMessage());
  }

}
