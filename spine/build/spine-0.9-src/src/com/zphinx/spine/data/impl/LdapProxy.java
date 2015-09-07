/**
 * LdapProxy.java
 * Copyright (C) 2008  Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING WITH ZPHINX SOFTWARE SOLUTIONS 
 * AND/OR OTHER PARTIES WHO PROVIDE THIS SOFTWARE "AS IS" WITHOUT WARRANTY
 * OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
 * IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
 * ALL NECESSARY SERVICING, REPAIR OR CORRECTION.
 *
 * IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
 * WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS
 * THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY
 * GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
 * USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
 * DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD
 * PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),
 * EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGES.
 *
 * For further information, please go to http://spine.zphinx.co.uk/

 * 
 **/

package com.zphinx.spine.data.impl;

import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.data.AbstractDataProxy;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.vo.DAOInput;

/**
 * LdapProxy acts as a proxy for Ldap access.It will handle all calls for instantiating a suitable DataAccessObject and calling it's run method
 * 
 * @author David Ladapo
 * @version 1.0 TODO Incomplete Implementation
 *          <p>
 *          Created 14-Jan-2005 02:44:22<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class LdapProxy extends AbstractDataProxy {

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.AbstractDataProxy#close(com.zphinx.spine.data.DataAccessObject)
     */
    public void close(DataAccessObject dao) {
        if(dao != null){
            dao.close();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.AbstractDataProxy#open(java.lang.Object, java.lang.String, int)
     */

    protected DataAccessObject open(Object obj1, String s, DAOInput daoCons) throws SpineApplicationException {

        return null;
    }

}
