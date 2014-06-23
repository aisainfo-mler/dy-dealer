/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0004;

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
public class Present implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _videoLen;

    private java.lang.String _addedService;

    private java.lang.String _m;

    private java.lang.String _t;


      //----------------/
     //- Constructors -/
    //----------------/

    public Present() {
        super();
    } //-- com.ai.mapp.model.HW0004.Present()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'addedService'.
     * 
     * @return the value of field 'addedService'.
    **/
    public java.lang.String getAddedService()
    {
        return this._addedService;
    } //-- java.lang.String getAddedService() 

    /**
     * Returns the value of field 'm'.
     * 
     * @return the value of field 'm'.
    **/
    public java.lang.String getM()
    {
        return this._m;
    } //-- java.lang.String getM() 

    /**
     * Returns the value of field 't'.
     * 
     * @return the value of field 't'.
    **/
    public java.lang.String getT()
    {
        return this._t;
    } //-- java.lang.String getT() 

    /**
     * Returns the value of field 'videoLen'.
     * 
     * @return the value of field 'videoLen'.
    **/
    public java.lang.String getVideoLen()
    {
        return this._videoLen;
    } //-- java.lang.String getVideoLen() 

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
     * Sets the value of field 'addedService'.
     * 
     * @param addedService the value of field 'addedService'.
    **/
    public void setAddedService(java.lang.String addedService)
    {
        this._addedService = addedService;
    } //-- void setAddedService(java.lang.String) 

    /**
     * Sets the value of field 'm'.
     * 
     * @param m the value of field 'm'.
    **/
    public void setM(java.lang.String m)
    {
        this._m = m;
    } //-- void setM(java.lang.String) 

    /**
     * Sets the value of field 't'.
     * 
     * @param t the value of field 't'.
    **/
    public void setT(java.lang.String t)
    {
        this._t = t;
    } //-- void setT(java.lang.String) 

    /**
     * Sets the value of field 'videoLen'.
     * 
     * @param videoLen the value of field 'videoLen'.
    **/
    public void setVideoLen(java.lang.String videoLen)
    {
        this._videoLen = videoLen;
    } //-- void setVideoLen(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0004.Present unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0004.Present) Unmarshaller.unmarshal(com.ai.mapp.model.HW0004.Present.class, reader);
    } //-- com.ai.mapp.model.HW0004.Present unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
