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
public class Include implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _price;

    private java.lang.String _validDate;

    private java.lang.String _inValidDate;

    private java.lang.String _validMonth;

    private java.lang.String _name;

    private java.lang.String _lowCost;

    private java.lang.String _voiceLen;

    private java.lang.String _flowSize;

    private java.lang.String _sms;

    private java.lang.String _freeCall;


      //----------------/
     //- Constructors -/
    //----------------/

    public Include() {
        super();
    } //-- com.ai.mapp.model.HW0004.Include()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'flowSize'.
     * 
     * @return the value of field 'flowSize'.
    **/
    public java.lang.String getFlowSize()
    {
        return this._flowSize;
    } //-- java.lang.String getFlowSize() 

    /**
     * Returns the value of field 'freeCall'.
     * 
     * @return the value of field 'freeCall'.
    **/
    public java.lang.String getFreeCall()
    {
        return this._freeCall;
    } //-- java.lang.String getFreeCall() 

    /**
     * Returns the value of field 'inValidDate'.
     * 
     * @return the value of field 'inValidDate'.
    **/
    public java.lang.String getInValidDate()
    {
        return this._inValidDate;
    } //-- java.lang.String getInValidDate() 

    /**
     * Returns the value of field 'lowCost'.
     * 
     * @return the value of field 'lowCost'.
    **/
    public java.lang.String getLowCost()
    {
        return this._lowCost;
    } //-- java.lang.String getLowCost() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'name'.
    **/
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'price'.
     * 
     * @return the value of field 'price'.
    **/
    public java.lang.String getPrice()
    {
        return this._price;
    } //-- java.lang.String getPrice() 

    /**
     * Returns the value of field 'sms'.
     * 
     * @return the value of field 'sms'.
    **/
    public java.lang.String getSms()
    {
        return this._sms;
    } //-- java.lang.String getSms() 

    /**
     * Returns the value of field 'validDate'.
     * 
     * @return the value of field 'validDate'.
    **/
    public java.lang.String getValidDate()
    {
        return this._validDate;
    } //-- java.lang.String getValidDate() 

    /**
     * Returns the value of field 'validMonth'.
     * 
     * @return the value of field 'validMonth'.
    **/
    public java.lang.String getValidMonth()
    {
        return this._validMonth;
    } //-- java.lang.String getValidMonth() 

    /**
     * Returns the value of field 'voiceLen'.
     * 
     * @return the value of field 'voiceLen'.
    **/
    public java.lang.String getVoiceLen()
    {
        return this._voiceLen;
    } //-- java.lang.String getVoiceLen() 

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
     * Sets the value of field 'flowSize'.
     * 
     * @param flowSize the value of field 'flowSize'.
    **/
    public void setFlowSize(java.lang.String flowSize)
    {
        this._flowSize = flowSize;
    } //-- void setFlowSize(java.lang.String) 

    /**
     * Sets the value of field 'freeCall'.
     * 
     * @param freeCall the value of field 'freeCall'.
    **/
    public void setFreeCall(java.lang.String freeCall)
    {
        this._freeCall = freeCall;
    } //-- void setFreeCall(java.lang.String) 

    /**
     * Sets the value of field 'inValidDate'.
     * 
     * @param inValidDate the value of field 'inValidDate'.
    **/
    public void setInValidDate(java.lang.String inValidDate)
    {
        this._inValidDate = inValidDate;
    } //-- void setInValidDate(java.lang.String) 

    /**
     * Sets the value of field 'lowCost'.
     * 
     * @param lowCost the value of field 'lowCost'.
    **/
    public void setLowCost(java.lang.String lowCost)
    {
        this._lowCost = lowCost;
    } //-- void setLowCost(java.lang.String) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
    **/
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'price'.
     * 
     * @param price the value of field 'price'.
    **/
    public void setPrice(java.lang.String price)
    {
        this._price = price;
    } //-- void setPrice(java.lang.String) 

    /**
     * Sets the value of field 'sms'.
     * 
     * @param sms the value of field 'sms'.
    **/
    public void setSms(java.lang.String sms)
    {
        this._sms = sms;
    } //-- void setSms(java.lang.String) 

    /**
     * Sets the value of field 'validDate'.
     * 
     * @param validDate the value of field 'validDate'.
    **/
    public void setValidDate(java.lang.String validDate)
    {
        this._validDate = validDate;
    } //-- void setValidDate(java.lang.String) 

    /**
     * Sets the value of field 'validMonth'.
     * 
     * @param validMonth the value of field 'validMonth'.
    **/
    public void setValidMonth(java.lang.String validMonth)
    {
        this._validMonth = validMonth;
    } //-- void setValidMonth(java.lang.String) 

    /**
     * Sets the value of field 'voiceLen'.
     * 
     * @param voiceLen the value of field 'voiceLen'.
    **/
    public void setVoiceLen(java.lang.String voiceLen)
    {
        this._voiceLen = voiceLen;
    } //-- void setVoiceLen(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0004.Include unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0004.Include) Unmarshaller.unmarshal(com.ai.mapp.model.HW0004.Include.class, reader);
    } //-- com.ai.mapp.model.HW0004.Include unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
