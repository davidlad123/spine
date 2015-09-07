/**
 * DelegateBean.java
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

package com.zphinx.spine.vo;

/**
 * DelegateBean contains all the properties needed by a View Processor/Business Delegate to instantiate itself from the spine configuration file.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 25-Mar-2005 14:17:35<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DelegateBean {

    /**
     * The String which represents the name of this Delegate.
     */
    private String subClass = null;

    /**
     * The LabelIntBean this Delegate uses
     */
    private LabelIntBean daoBean = null;

    /**
     * Public Constructor
     */
    public DelegateBean() {
        super();

    }

    /**
     * Constructor which accepts a string and a LabelIntBean as parameters
     * 
     * @param subClass The String which represents the name of this Delegate
     * @param daoBean The LabelIntBean this Delegate uses
     */
    public DelegateBean(String subClass, LabelIntBean daoBean) {
        super();
        this.subClass = subClass;
        this.daoBean = daoBean;
    }

    /**
     * Gets the LabelIntBean associated with this object which comprises of the dao className and the proxyIndex obtained from configuration.
     * 
     * @return Returns the daoBean.
     */
    public LabelIntBean getDaoBean() {
        return daoBean;
    }

    /**
     * Gets the LabelIntBean associated with this object which comprises of the dao className and the proxyIndex obtained from configuration.
     * 
     * @param dao The daoBean to set.
     */
    public void setDaoBean(LabelIntBean dao) {
        this.daoBean = dao;
    }

    /**
     * Gets the name of the Delegate which this objects properties represent
     * 
     * @return Returns the subClass.
     */
    public String getSubClass() {
        return subClass;
    }

    /**
     * Sets the name of the Delegate which this objects properties represent
     * 
     * @param subClass The subClass to set.
     */
    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }
}
