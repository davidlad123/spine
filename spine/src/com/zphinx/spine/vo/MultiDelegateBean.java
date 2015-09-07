/**
 * MultiDelegateBean.java
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

import java.util.ArrayList;

/**
 * MultiDelegateBean is used to encapsulate the data derived from a processor node which contains a dispatchdelegate node in the spine configuration xml file. The predefined data is wrapped within this object and can be retrieved by calling most of the getter methods in this delegate.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultiDelegateBean extends DelegateBean {

    /**
     * The Array of ManagedBeans
     */
    private ManagedDaoBean[] managedBean = null;

    /**
     * The Arraylist of ManagedBeans
     */
    private ArrayList managedBeanList = null;

    /**
     * The full class name of the builder set in configuration
     */
    private String builderName;

    /**
     * Default Constructor for creating a MultiDelegateBean
     */
    public MultiDelegateBean() {
        super();
    }

    /**
     * Constructor to set the name of the delegate class
     * 
     * @param subClass The name of the delegate class;
     */
    public MultiDelegateBean(String subClass) {
        this.setSubClass(subClass);
    }

    /**
     * Constructor which accepts a ManagedDaoBean and the name of the delegate class
     * 
     * @param mobBean A ManagedDaoBean instance to use to initialize this object
     * @param subClass The name of the delegate class;
     */
    public MultiDelegateBean(ManagedDaoBean mobBean, String subClass) {
        super(subClass, mobBean);
        setDaoBean(mobBean);

    }

    /**
     * Returns the array of ManagedDAOBeans
     * 
     * @return Returns the managedBean.
     */
    public ManagedDaoBean[] getManagedBean() {
        int size = managedBeanList.size();
        if(size > 0){
            if((managedBean == null) || ((managedBean != null) && (managedBean.length != size))){
                managedBean = loadManagedBean(size);
            }

        }

        return managedBean;
    }

    /**
     * Creates a new managedBean array from the contents of the ArrayList
     * 
     * @param size The size of the ArrayList which contains the managedBeans
     */
    private ManagedDaoBean[] loadManagedBean(int size) {
        ManagedDaoBean[] managedBeanArray = new ManagedDaoBean[size];
        for (int i = 0; i < size; i++){
            managedBeanArray[i] = (ManagedDaoBean) managedBeanList.get(i);

        }
        return managedBeanArray;
    }

    /**
     * Adds a ManagedDaoBean to this DispatchDelegate
     * 
     * @param daoBean The data access bean instance
     */
    public void setDaoBean(LabelIntBean daoBean) {
        ManagedDaoBean mBean = (ManagedDaoBean) daoBean;
        if(managedBeanList == null){
            managedBeanList = new ArrayList();
            if((managedBean != null) && (managedBean.length > 0)){
                for (int i = 0; i < managedBean.length; i++){
                    managedBeanList.add(managedBean[i]);
                }

            }
            managedBeanList.add(mBean);

        }
        else{
            managedBeanList.add(mBean);
        }
        managedBeanList.trimToSize();
    }

    /**
     * Gets the full class name of the buider in use by this bean
     * 
     * @return the full class name of the buider in use by this bean
     */
    public String getBuilderName() {

        return builderName;
    }

    /**
     * Sets the ManagerBuilder instance name
     * 
     * @param builderName The builderName to set.
     */
    public void setBuilderName(String builderName) {
        this.builderName = builderName;
    }
}
