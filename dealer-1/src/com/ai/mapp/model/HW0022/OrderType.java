/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0022;

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
public class OrderType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _text;

    private java.lang.String _value;

    private java.lang.String _income;

    private java.lang.String _productType;


      //----------------/
     //- Constructors -/
    //----------------/

    public OrderType() {
        super();
    } //-- com.ai.mapp.model.HW0022.OrderType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'income'.
     * 
     * @return the value of field 'income'.
    **/
    public java.lang.String getIncome()
    {
        return this._income;
    } //-- java.lang.String getIncome() 

    /**
     * Returns the value of field 'productType'.
     * 
     * @return the value of field 'productType'.
    **/
    public java.lang.String getProductType()
    {
        return this._productType;
    } //-- java.lang.String getProductType() 

    /**
     * Returns the value of field 'text'.
     * 
     * @return the value of field 'text'.
    **/
    public java.lang.String getText()
    {
        return this._text;
    } //-- java.lang.String getText() 

    /**
     * Returns the value of field 'value'.
     * 
     * @return the value of field 'value'.
    **/
    public java.lang.String getValue()
    {
        return this._value;
    } //-- java.lang.String getValue() 

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
     * Sets the value of field 'income'.
     * 
     * @param income the value of field 'income'.
    **/
    public void setIncome(java.lang.String income)
    {
        this._income = income;
    } //-- void setIncome(java.lang.String) 

    /**
     * Sets the value of field 'productType'.
     * 
     * @param productType the value of field 'productType'.
    **/
    public void setProductType(java.lang.String productType)
    {
        this._productType = productType;
    } //-- void setProductType(java.lang.String) 

    /**
     * Sets the value of field 'text'.
     * 
     * @param text the value of field 'text'.
    **/
    public void setText(java.lang.String text)
    {
        this._text = text;
    } //-- void setText(java.lang.String) 

    /**
     * Sets the value of field 'value'.
     * 
     * @param value the value of field 'value'.
    **/
    public void setValue(java.lang.String value)
    {
        this._value = value;
    } //-- void setValue(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0022.OrderType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0022.OrderType) Unmarshaller.unmarshal(com.ai.mapp.model.HW0022.OrderType.class, reader);
    } //-- com.ai.mapp.model.HW0022.OrderType unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
