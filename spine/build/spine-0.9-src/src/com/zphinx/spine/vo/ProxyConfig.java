/**
 * ProxyConfig.java
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
import java.util.List;

/**
 * ProxyConfig is the Object representation of the configured Proxy objects within the Spine framework.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Nov 30, 2007 12:43:11 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ProxyConfig {

    /**
     * The list to hold the configured proxies
     */
    private List proxyList = null;

    /**
     * Public Constructor
     */
    public ProxyConfig() {
        this.proxyList = new ArrayList();
    }

    /**
     * @return the proxyList
     */
    public List getProxyList() {
        return proxyList;
    }

    /**
     * @param proxyList the proxyList to set
     */
    public void setProxyList(List proxyList) {
        this.proxyList = proxyList;
    }

    /**
     * Adds a LabenIntBean to the list contained in this object
     * 
     * @param lib The LabelIntBean to add
     */
    public void addProxy(LabelIntBean lib) {
        if(!this.proxyList.contains(lib)){
            this.proxyList.add(lib);
        }
    }

}
