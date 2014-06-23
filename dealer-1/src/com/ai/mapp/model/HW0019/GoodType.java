/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0019;

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
public class GoodType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _typeValue;

    private GoodList _goodList;


      //----------------/
     //- Constructors -/
    //----------------/

    public GoodType() {
        super();
    } //-- com.ai.mapp.model.HW0019.GoodType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'goodList'.
     * 
     * @return the value of field 'goodList'.
    **/
    public GoodList getGoodList()
    {
        return this._goodList;
    } //-- GoodList getGoodList() 

    /**
     * Returns the value of field 'typeValue'.
     * 
     * @return the value of field 'typeValue'.
    **/
    public java.lang.String getTypeValue()
    {
        return this._typeValue;
    } //-- java.lang.String getTypeValue() 

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
     * Sets the value of field 'goodList'.
     * 
     * @param goodList the value of field 'goodList'.
    **/
    public void setGoodList(GoodList goodList)
    {
        this._goodList = goodList;
    } //-- void setGoodList(GoodList) 

    /**
     * Sets the value of field 'typeValue'.
     * 
     * @param typeValue the value of field 'typeValue'.
    **/
    public void setTypeValue(java.lang.String typeValue)
    {
        this._typeValue = typeValue;
    } //-- void setTypeValue(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0019.GoodType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0019.GoodType) Unmarshaller.unmarshal(com.ai.mapp.model.HW0019.GoodType.class, reader);
    } //-- com.ai.mapp.model.HW0019.GoodType unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
