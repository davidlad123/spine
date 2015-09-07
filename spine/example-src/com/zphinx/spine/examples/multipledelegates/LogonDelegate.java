/**
 * LogonDelegate.java
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

package com.zphinx.spine.examples.multipledelegates;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.AbstractBusinessDelegate;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.members.Member;
import com.zphinx.spine.message.DisplayError;
import com.zphinx.spine.message.DisplayMessage;
import com.zphinx.spine.vo.DataTransferAssembler;
import com.zphinx.spine.vo.ResultObject;

/**
 * LogonDelegate is an example delegate for multiple delegates
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class LogonDelegate extends AbstractBusinessDelegate {

    /**
     * The log instance for this object
     */
    private static final transient Logger log = Universal.getLogger(LogonDelegate.class.getName());

    /**
     * Public Constructor
     */
    public LogonDelegate() {
        log.debug("initialising..." + this.getClass().getSimpleName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.core.AbstractBusinessDelegate#run()
     */
    @Override
    protected ResultObject run() {
            log.debug("Running logon delegate...");
        ResultObject result = null;
        Locale locale = getUserLocale();
        
        try{
           result =  (ResultObject) this.processDAO();
           DisplayMessage message = new DisplayMessage("examples.success",new Object[] {result.getObj().getClass().getSimpleName()});
           result.getDisplayMessages().add(ResultObject.MESSAGES,message,locale);
                  
        }
        catch (SpineException e){
            result = new ResultObject();
            DisplayError error = new DisplayError(4,"examples.error",new Object[] {e.getMessage()});
            result.getDisplayMessages().add(ResultObject.ERRORS,error,locale );
            e.printStackTrace();
        }
    
        return result;

    }
    
    /* (non-Javadoc)
     * @see com.zphinx.spine.core.AbstractBusinessDelegate#postProcessBusinessRules(com.zphinx.spine.vo.ResultObject, com.zphinx.spine.vo.DataTransferAssembler)
     */
    protected ResultObject postProcessBusinessRules(ResultObject results1, DataTransferAssembler command1) throws SpineException {
       ResultObject res = results1;
       if(this.getCommand().getObj() != null) {
           Member member = (Member) results1.getObj();
           if((member != null) &&(!member.getMemberName().equalsIgnoreCase("Application"))) {
               res = super.postProcessBusinessRules(results1, command1);
           }
           else {
              
               DisplayError error = new DisplayError(4,"error.postrules",new Object[] {"LogonDelegate Post Process Rules Failed"});
               res.getDisplayMessages().add(ResultObject.ERRORS,error,this.getUserLocale() );
             
           }
       }
       else {
           DisplayError error = new DisplayError(4,"error.missing.datatransferobject",new Object[] {});
           res.getDisplayMessages().add(ResultObject.ERRORS,error,this.getUserLocale() );
         
       }
       return res;
    }

}
