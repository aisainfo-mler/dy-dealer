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
public class State implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _stateCode;

    private java.lang.String _stateName;

    private CityList _cityList;


      //----------------/
     //- Constructors -/
    //----------------/

    public State() {
        super();
    } //-- com.ai.mapp.model.HW0024.State()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cityList'.
     * 
     * @return the value of field 'cityList'.
    **/
    public CityList getCityList()
    {
        return this._cityList;
    } //-- CityList getCityList() 

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
     * Returns the value of field 'stateName'.
     * 
     * @return the value of field 'stateName'.
    **/
    public java.lang.String getStateName()
    {
        return this._stateName;
    } //-- java.lang.String getStateName() 

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
     * Sets the value of field 'cityList'.
     * 
     * @param cityList the value of field 'cityList'.
    **/
    public void setCityList(CityList cityList)
    {
        this._cityList = cityList;
    } //-- void setCityList(CityList) 

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
     * Sets the value of field 'stateName'.
     * 
     * @param stateName the value of field 'stateName'.
    **/
    public void setStateName(java.lang.String stateName)
    {
        this._stateName = stateName;
    } //-- void setStateName(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0024.State unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0024.State) Unmarshaller.unmarshal(com.ai.mapp.model.HW0024.State.class, reader);
    } //-- com.ai.mapp.model.HW0024.State unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
