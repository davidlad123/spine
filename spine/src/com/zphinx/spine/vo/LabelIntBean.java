/**
 * LabelIntBean.java
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

import java.io.Serializable;

import com.zphinx.spine.Universal;

/**
 * LabelIntBean is a bean used to associate a label/name with an int value
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class LabelIntBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8318552401496000331L;

    /**
     * The int to be saved in this bean
     */
    private int intValue = 0;

    /**
     * The label of this bean
     */
    private String label = null;

    /**
     * Public Constructor
     */
    public LabelIntBean() {
        super();
    }

    /**
     * Constructor to set the label and the int value
     * 
     * @param label The name of the label
     * @param intValue The int to set in this bean
     */
    public LabelIntBean(String label, int intValue) {
        setLabel(label);
        setIntValue(intValue);

    }

    /**
     * Gets the name of the Label
     * 
     * @return Returns the label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the name of the label
     * 
     * @param label The label to set.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets the int in this object
     * 
     * @return Returns the intValue.
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Gets the int in this object
     * 
     * @param intValue The intValue to set.
     */
    public void setIntValue(int intValue) {
        if(intValue >= 0){
            this.intValue = intValue;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if(obj instanceof LabelIntBean){
            LabelIntBean lib = (LabelIntBean) obj;
            if((this.intValue == lib.intValue) && (this.label.equals(lib.getLabel()))){
                b = true;
            }

        }
        return b;

    }

    /**
     * Returns a description for this LabelIntBean
     */
    public String toString() {
        return "LabelIntBean[" + intValue + "][" + label + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.intValue + Universal.generateHashCode(this.label);
    }
}
