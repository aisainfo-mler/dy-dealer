/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id: Item.java,v 1.3 2012/05/15 03:25:55 luyang2 Exp $
 */

package com.ai.mapp.model.HW0008;

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
 * @version $Revision: 1.3 $ $Date: 2012/05/15 03:25:55 $
**/
public class Item implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _svcNo;

    private java.lang.String _svcLevel;

    private java.lang.String _serviceType;


      //----------------/
     //- Constructors -/
    //----------------/

    public Item() {
        super();
    } //-- com.ai.mapp.model.Z10004.Item()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'serviceType'.
     * 
     * @return the value of field 'serviceType'.
    **/
    public java.lang.String getServiceType()
    {
        return this._serviceType;
    } //-- java.lang.String getServiceType() 

    /**
     * Returns the value of field 'svcLevel'.
     * 
     * @return the value of field 'svcLevel'.
    **/
    public java.lang.String getSvcLevel()
    {
        return this._svcLevel;
    } //-- java.lang.String getSvcLevel() 

    /**
     * Returns the value of field 'svcNo'.
     * 
     * @return the value of field 'svcNo'.
    **/
    public java.lang.String getSvcNo()
    {
        return this._svcNo;
    } //-- java.lang.String getSvcNo() 

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
     * Sets the value of field 'serviceType'.
     * 
     * @param serviceType the value of field 'serviceType'.
    **/
    public void setServiceType(java.lang.String serviceType)
    {
        this._serviceType = serviceType;
    } //-- void setServiceType(java.lang.String) 

    /**
     * Sets the value of field 'svcLevel'.
     * 
     * @param svcLevel the value of field 'svcLevel'.
    **/
    public void setSvcLevel(java.lang.String svcLevel)
    {
        this._svcLevel = svcLevel;
    } //-- void setSvcLevel(java.lang.String) 

    /**
     * Sets the value of field 'svcNo'.
     * 
     * @param svcNo the value of field 'svcNo'.
    **/
    public void setSvcNo(java.lang.String svcNo)
    {
        this._svcNo = svcNo;
    } //-- void setSvcNo(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0008.Item unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0008.Item) Unmarshaller.unmarshal(com.ai.mapp.model.HW0008.Item.class, reader);
    } //-- com.ai.mapp.model.Z10004.Item unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
