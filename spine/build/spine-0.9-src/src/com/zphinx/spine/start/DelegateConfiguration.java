/**
 * DelegateConfiguration.java
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

package com.zphinx.spine.start;

import java.util.ArrayList;

import com.zphinx.spine.vo.DelegateBean;

/**
 * DelegateConfiguration maintains configuration properties related to data access for each individual alias that is retrieved from configuration
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 25-Mar-2005 12:38:55 <br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DelegateConfiguration {

    /**
     * The Array of DelegateBeans to contained within this Object
     */
    private DelegateBean[] dataBean = null;

    /**
     * The ArrayList which contains the DelegateBeans
     */
    private ArrayList delegateList = null;

    /**
     * The name of the processor to attach to this Configuration
     */
    private String processorClass = null;

    /**
     * Public Constructor - creates a new ArrayList to store our delegate beans
     */
    public DelegateConfiguration() {
        delegateList = new ArrayList();

    }

    /**
     * Gets the array of DelegateBeans
     * 
     * @return Returns the dataBean.
     */
    public DelegateBean[] getDataBean() {
        if((this.dataBean == null) || (this.dataBean.length == 0)){
            this.dataBean = processBean();
        }
        else if(delegateList != null){
            this.dataBean = null;
            this.dataBean = processBean();
        }
        return this.dataBean;
    }

    /**
     * Process the ArrayList and return an Array of DelegateBeans
     * 
     * @return The Array of Delegate Beans
     */
    private DelegateBean[] processBean() {
        delegateList.trimToSize();
        DelegateBean[] dBean = new DelegateBean[(delegateList.size())];
        for (int j = 0; j < delegateList.size(); j++){
            dBean[j] = (DelegateBean) delegateList.get(j);
        }
        this.delegateList = null;
        return dBean;

    }

    /**
     * Add a DelegateBean to the array of DelegateBeans
     * 
     * @param dBean The DelegateBean to add to the array of DelegateBeans
     */
    public void add(DelegateBean dBean) {
        if(this.delegateList == null){
            this.delegateList = new ArrayList();
            if(this.dataBean.length > 0){
                for (int k = 0; k < dataBean.length; k++){
                    delegateList.add(dataBean[k]);
                }
            }
        }
        this.delegateList.add(dBean);

    }

    /**
     * Sets the processorClass
     * 
     * @return the processorClass
     */
    public String getProcessorClass() {
        return processorClass;
    }

    /**
     * Gets the processorClass
     * 
     * @param processorClass the processorClass to set
     */
    public void setProcessorClass(String processorClass) {
        this.processorClass = processorClass;
    }
}