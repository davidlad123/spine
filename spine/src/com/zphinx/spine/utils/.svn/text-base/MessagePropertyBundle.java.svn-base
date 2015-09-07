/**
 * MessagePropertyBundle.java
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

package com.zphinx.spine.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.exceptions.SpineRuntimeException;

/**
 * MessagePropertyBundle adds the ability to store a ResourceBundle back to it's original location in its original format.ResourceBundles are normally read only objects of which data storage is not possible.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class MessagePropertyBundle extends PropertyResourceBundle {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(MessagePropertyBundle.class.getName());

    /**
     * Public Constructor
     * 
     * @param stream The input stream from which a bundle is retrieved
     * @throws IOException Throw an IOException if an error occurs
     */
    public MessagePropertyBundle(InputStream stream) throws IOException {
        super(stream);

    }

    /**
     * Saves the properties object
     * 
     * @param out The outputStream to use
     * @param props The properties object to store
     */
    public void store(OutputStream out, Properties props) {

        try{
            props.store(out, "Saved by message properties!!");
        }
        catch (IOException e){

            log.debug("Error from store: " + e.getMessage());
        }

    }

    /**
     * Gets the outputStream used to store the properties object
     * 
     * @param className The fullName of the properties file
     * @param locale The Locale of the properties file to load
     * @param props The properties object to use
     */
    public static void saveStream(String className, Locale locale, Properties props) throws SpineException {

        if(className == null){ throw new NullPointerException(); }

        // fast path the case where the bundle is cached
        String bundleName = className;
        /**
         * String localeSuffix = locale.toString(); if(localeSuffix.length() > 0){ bundleName += "_" + localeSuffix; } else if(locale.getVariant().length() > 0){ // This corrects some strange behavior in Locale where // new Locale("", "", "VARIANT").toString == "" bundleName += "___" + locale.getVariant(); }
         */
        File file = getFile(bundleName, locale);
        if(file != null){
            FileOutputStream os = null;

            try{
                os = new FileOutputStream(file);
            }
            catch (FileNotFoundException e){

                log.debug("Error in getStream: " + e.getMessage());
            }
            try{
                props.store(os, "Saved by message properties!!");
            }
            catch (IOException e){

                log.debug("Error from store: " + e.getMessage());
            }
            finally{
                try{
                    os.close();
                }
                catch (IOException e){

                    e.printStackTrace();
                }
            }
        }
        else{
            throw new MissingResourceException("Unable to locate writtable file", bundleName, bundleName);
        }

    }

    /**
     * Gets a File object representing this bundle
     * 
     * @param bundleName The name of the resource bundle
     * @return A file object representing this resource bundle
     * @throws SpineException Throw a SpineException if an error occurs
     */
    private static File getFile(String bundleName, Locale locale) throws SpineException {
        File file = null;
        ArrayList bundles = calculateBundleNames(bundleName, locale);
        // add the Locale default language file ...check this has not been added
        // ... only if the default Locale is same as chosen locale
        if(Locale.getDefault().getLanguage().equals(locale.getLanguage())){
            bundles.add(0, bundleName);
        }

        for (int i = bundles.size() - 1; i >= 0; i--){
            String s = (String) bundles.get(i);
            file = getPropertiesFile(s);
            if((file != null) && (file.exists() && file.canWrite())){
                break;
            }
        }
        return file;
    }

    /**
     * Gets a File object suitable for use in writing to the Properties file.
     * 
     * @param bundleName The name of the PropertiesresourceBundle to fetch
     * @return The file object created from the generated url
     */
    private static File getPropertiesFile(String bundleName) throws SpineRuntimeException {
        String resName = bundleName.replace('.', '/') + ".properties";
        BundlePriviledge bp = new BundlePriviledge(resName);
        URL url = (URL) java.security.AccessController.doPrivileged(bp);
        File file = null;
        if(url != null){
            file = new File(url.getFile());
        }
        return file;
    }

    /**
     * Calculate the bundles along the search path from the base bundle to the bundle specified by baseName and locale.
     * 
     * @param baseName the base bundle name
     * @param locale the locale
     */
    private static ArrayList calculateBundleNames(String baseName, Locale locale) {
        final ArrayList result = new ArrayList();
        final String language = locale.getLanguage();
        final int languageLength = language.length();
        final String country = locale.getCountry();
        final int countryLength = country.length();
        final String variant = locale.getVariant();
        final int variantLength = variant.length();

        if(languageLength + countryLength + variantLength == 0){
            // The locale is "", "", "".
            return result;
        }
        final StringBuffer temp = new StringBuffer(baseName);
        temp.append('_');
        temp.append(language);
        if(languageLength > 0){
            result.add(temp.toString());
        }

        if(countryLength + variantLength == 0){ return result; }
        temp.append('_');
        temp.append(country);
        if(countryLength > 0){
            result.add(temp.toString());
        }

        if(variantLength == 0){ return result; }
        temp.append('_');
        temp.append(variant);
        result.add(temp.toString());
        result.trimToSize();
        return result;
    }

    /**
     * BundlePriviledge is a minimal implementation of PrivilegedAction used to create a secure resource URL.
     * 
     * @author David Ladapo
     * @version $1.0
     *          <p>
     *          
     *          Copyright &copy;Zphinx Software Solutions
     *          </p>
     */
    protected static class BundlePriviledge implements java.security.PrivilegedAction {
        /**
         * The name of the bundle to use
         */
        private String bundleName = null;

        /**
         * Public Constructor
         * 
         * @param name The name of this Priviledge action
         */
        public BundlePriviledge(String name) {
            this.bundleName = name;
        }

        /**
         * Gets the Object returned by the class Loader
         * 
         * @return An object suitable for use
         */
        public Object run() {

            ClassLoader loader = this.getClass().getClassLoader();
            return loader.getResource(bundleName);

        }

        /**
         * Gets the Object returned by the class Loader
         * 
         * @return An object suitable for use
         */

        public Object run(String name) {
            this.bundleName = name;
            return run();

        }

    }

}
