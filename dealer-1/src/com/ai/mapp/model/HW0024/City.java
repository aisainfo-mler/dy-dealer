/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0024;

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
public class City implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _cityCode;

    private java.lang.String _cityName;

    private java.lang.String _stateCode;


      //----------------/
     //- Constructors -/
    //----------------/

    public City() {
        super();
    } //-- com.ai.mapp.model.HW0024.City()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cityCode'.
     * 
     * @return the value of field 'cityCode'.
    **/
    public java.lang.String getCityCode()
    {
        return this._cityCode;
    } //-- java.lang.String getCityCode() 

    /**
     * Returns the value of field 'cityName'.
     * 
     * @return the value of field 'cityName'.
    **/
    public java.lang.String getCityName()
    {
        return this._cityName;
    } //-- java.lang.String getCityName() 

    /**
     * Returns the value of field 'stateCode'.
     * 
     * @return the value of field 'stateCode'.
    **/
    public java.lang.String getStateCode()
    {
        return this._stateCode;
    } //-- java.lang.String getStateCode() 

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
     * Sets the value of field 'cityCode'.
     * 
     * @param cityCode the value of field 'cityCode'.
    **/
    public void setCityCode(java.lang.String cityCode)
    {
        this._cityCode = cityCode;
    } //-- void setCityCode(java.lang.String) 

    /**
     * Sets the value of field 'cityName'.
     * 
     * @param cityName the value of field 'cityName'.
    **/
    public void setCityName(java.lang.String cityName)
    {
        this._cityName = cityName;
    } //-- void setCityName(java.lang.String) 

    /**
     * Sets the value of field 'stateCode'.
     * 
     * @param stateCode the value of field 'stateCode'.
    **/
    public void setStateCode(java.lang.String stateCode)
    {
        this._stateCode = stateCode;
    } //-- void setStateCode(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0024.City unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0024.City) Unmarshaller.unmarshal(com.ai.mapp.model.HW0024.City.class, reader);
    } //-- com.ai.mapp.model.HW0024.City unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
