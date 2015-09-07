/**
 * ManagedDaoBean.java
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

import com.zphinx.spine.Universal;

/**
 * ManagedDaoBean is an extension of the label int bean which adds the class name of the object and its position within a configuration defined by the term <code>pageIndex</code>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 25-Aug-2005 15:31:38<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ManagedDaoBean extends LabelIntBean {

    /**
     * 
     */
    private static final long serialVersionUID = 3082343276903977272L;

    /**
     * An int representing the position of this ManagedDaoBean within the configuration pages
     */
    private int pageIndex = 0;

    /**
     * The name of the object to be managed
     */
    private String objectClass = null;

    /**
     * Default Constructor
     */
    public ManagedDaoBean() {
        super();
    }

    /**
     * Construtor which accepts parameters for all the properties of this object
     * 
     * @param className The name of the DAO object
     * @param proxyIndex The index of the type of proxy used to initialize the this object e.g DatabaseProxy
     * @param objectClass The name of the object been managed
     * @param page An int representing the position of this ManagedDaoBean
     */
    public ManagedDaoBean(String className, int proxyIndex, String objectClass, int page) {
        super(className, proxyIndex);
        this.objectClass = objectClass;
        this.pageIndex = page;
    }

    /**
     * Returns the name of the object been managed
     * 
     * @return Returns the objectClass.
     */
    public String getObjectClass() {
        return objectClass;
    }

    /**
     * Sets the name of the object been managed
     * 
     * @param objectClass The objectClass to set.
     */
    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * Gets the pageIndex/position of this object
     * 
     * @return Returns the pageIndex.
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * Sets the pageIndex/position of this object
     * 
     * @param page The pageIndex to set.
     */
    public void setPageIndex(int page) {
        this.pageIndex = page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if(obj instanceof ManagedDaoBean){
            ManagedDaoBean lib = (ManagedDaoBean) obj;
            if((this.getIntValue() == lib.getIntValue()) && (this.getLabel().equals(lib.getLabel())) && (this.objectClass.equals(lib.getObjectClass())) && (this.pageIndex == lib.getPageIndex())){
                b = true;
            }

        }
        return b;

    }

    /**
     * Returns a description for this LabelIntBean
     */
    public String toString() {
        return "ManagedDaoBean[ proxyIndex: " + this.getIntValue() + "][ DAO: " + this.getLabel() + "][ pageIndex: " + pageIndex + "][ objectClass: " + this.objectClass + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.getIntValue() + this.pageIndex + Universal.generateHashCode(this.getLabel()) + Universal.generateHashCode(this.objectClass);
    }

}
