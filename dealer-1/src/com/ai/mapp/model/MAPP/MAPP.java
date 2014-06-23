/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id: MAPP.java,v 1.2 2012/07/19 06:06:04 luyang2 Exp $
 */

package com.ai.mapp.model.MAPP;

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
 * @version $Revision: 1.2 $ $Date: 2012/07/19 06:06:04 $
**/
public class MAPP implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _bizCode;

    private java.lang.String _sessionId;

    private java.lang.String _mapType;

    private java.lang.String _XValue;

    private java.lang.String _YValue;

    private java.lang.String _IMEI;

    private java.lang.String _svcContent;


      //----------------/
     //- Constructors -/
    //----------------/

    public MAPP() {
        super();
    } //-- com.ai.mapp.model.MAPP.MAPP()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bizCode'.
     * 
     * @return the value of field 'bizCode'.
    **/
    public java.lang.String getBizCode()
    {
        return this._bizCode;
    } //-- java.lang.String getBizCode() 

    /**
     * Returns the value of field 'IMEI'.
     * 
     * @return the value of field 'IMEI'.
    **/
    public java.lang.String getIMEI()
    {
        return this._IMEI;
    } //-- java.lang.String getIMEI() 

    /**
     * Returns the value of field 'mapType'.
     * 
     * @return the value of field 'mapType'.
    **/
    public java.lang.String getMapType()
    {
        return this._mapType;
    } //-- java.lang.String getMapType() 

    /**
     * Returns the value of field 'sessionId'.
     * 
     * @return the value of field 'sessionId'.
    **/
    public java.lang.String getSessionId()
    {
        return this._sessionId;
    } //-- java.lang.String getSessionId() 

    /**
     * Returns the value of field 'svcContent'.
     * 
     * @return the value of field 'svcContent'.
    **/
    public java.lang.String getSvcContent()
    {
        return this._svcContent;
    } //-- java.lang.String getSvcContent() 

    /**
     * Returns the value of field 'XValue'.
     * 
     * @return the value of field 'XValue'.
    **/
    public java.lang.String getXValue()
    {
        return this._XValue;
    } //-- java.lang.String getXValue() 

    /**
     * Returns the value of field 'YValue'.
     * 
     * @return the value of field 'YValue'.
    **/
    public java.lang.String getYValue()
    {
        return this._YValue;
    } //-- java.lang.String getYValue() 

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
     * Sets the value of field 'bizCode'.
     * 
     * @param bizCode the value of field 'bizCode'.
    **/
    public void setBizCode(java.lang.String bizCode)
    {
        this._bizCode = bizCode;
    } //-- void setBizCode(java.lang.String) 

    /**
     * Sets the value of field 'IMEI'.
     * 
     * @param IMEI the value of field 'IMEI'.
    **/
    public void setIMEI(java.lang.String IMEI)
    {
        this._IMEI = IMEI;
    } //-- void setIMEI(java.lang.String) 

    /**
     * Sets the value of field 'mapType'.
     * 
     * @param mapType the value of field 'mapType'.
    **/
    public void setMapType(java.lang.String mapType)
    {
        this._mapType = mapType;
    } //-- void setMapType(java.lang.String) 

    /**
     * Sets the value of field 'sessionId'.
     * 
     * @param sessionId the value of field 'sessionId'.
    **/
    public void setSessionId(java.lang.String sessionId)
    {
        this._sessionId = sessionId;
    } //-- void setSessionId(java.lang.String) 

    /**
     * Sets the value of field 'svcContent'.
     * 
     * @param svcContent the value of field 'svcContent'.
    **/
    public void setSvcContent(java.lang.String svcContent)
    {
        this._svcContent = svcContent;
    } //-- void setSvcContent(java.lang.String) 

    /**
     * Sets the value of field 'XValue'.
     * 
     * @param XValue the value of field 'XValue'.
    **/
    public void setXValue(java.lang.String XValue)
    {
        this._XValue = XValue;
    } //-- void setXValue(java.lang.String) 

    /**
     * Sets the value of field 'YValue'.
     * 
     * @param YValue the value of field 'YValue'.
    **/
    public void setYValue(java.lang.String YValue)
    {
        this._YValue = YValue;
    } //-- void setYValue(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.MAPP.MAPP unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.MAPP.MAPP) Unmarshaller.unmarshal(com.ai.mapp.model.MAPP.MAPP.class, reader);
    } //-- com.ai.mapp.model.MAPP.MAPP unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
