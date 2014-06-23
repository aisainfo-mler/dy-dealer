/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0003;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.*;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * 
 * 
 * @version $Revision$ $Date$
**/
public class Image implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _url;

    private java.lang.String _orgiUrl;


      //----------------/
     //- Constructors -/
    //----------------/

    public Image() {
        super();
    } //-- com.ai.mapp.model.HW0003.Image()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'orgiUrl'.
     * 
     * @return the value of field 'orgiUrl'.
    **/
    public java.lang.String getOrgiUrl()
    {
        return this._orgiUrl;
    } //-- java.lang.String getOrgiUrl() 

    /**
     * Returns the value of field 'url'.
     * 
     * @return the value of field 'url'.
    **/
    public java.lang.String getUrl()
    {
        return this._url;
    } //-- java.lang.String getUrl() 

    /**
    **/
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * 
     * 
     * @param out
    **/
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * 
     * 
     * @param handler
    **/
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'orgiUrl'.
     * 
     * @param orgiUrl the value of field 'orgiUrl'.
    **/
    public void setOrgiUrl(java.lang.String orgiUrl)
    {
        this._orgiUrl = orgiUrl;
    } //-- void setOrgiUrl(java.lang.String) 

    /**
     * Sets the value of field 'url'.
     * 
     * @param url the value of field 'url'.
    **/
    public void setUrl(java.lang.String url)
    {
        this._url = url;
    } //-- void setUrl(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0003.Image unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0003.Image) Unmarshaller.unmarshal(com.ai.mapp.model.HW0003.Image.class, reader);
    } //-- com.ai.mapp.model.HW0003.Image unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
