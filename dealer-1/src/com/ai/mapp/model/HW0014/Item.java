/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0014;

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
public class Item implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _goodId;

    private java.lang.String _itemValue;

    private java.lang.String _status;

    private java.lang.String _userCode;

    private java.lang.String _userName;


      //----------------/
     //- Constructors -/
    //----------------/

    public Item() {
        super();
    } //-- com.ai.mapp.model.HW0014.Item()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'goodId'.
     * 
     * @return the value of field 'goodId'.
    **/
    public java.lang.String getGoodId()
    {
        return this._goodId;
    } //-- java.lang.String getGoodId() 

    /**
     * Returns the value of field 'itemValue'.
     * 
     * @return the value of field 'itemValue'.
    **/
    public java.lang.String getItemValue()
    {
        return this._itemValue;
    } //-- java.lang.String getItemValue() 

    /**
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'status'.
    **/
    public java.lang.String getStatus()
    {
        return this._status;
    } //-- java.lang.String getStatus() 

    /**
     * Returns the value of field 'userCode'.
     * 
     * @return the value of field 'userCode'.
    **/
    public java.lang.String getUserCode()
    {
        return this._userCode;
    } //-- java.lang.String getUserCode() 

    /**
     * Returns the value of field 'userName'.
     * 
     * @return the value of field 'userName'.
    **/
    public java.lang.String getUserName()
    {
        return this._userName;
    } //-- java.lang.String getUserName() 

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
     * Sets the value of field 'goodId'.
     * 
     * @param goodId the value of field 'goodId'.
    **/
    public void setGoodId(java.lang.String goodId)
    {
        this._goodId = goodId;
    } //-- void setGoodId(java.lang.String) 

    /**
     * Sets the value of field 'itemValue'.
     * 
     * @param itemValue the value of field 'itemValue'.
    **/
    public void setItemValue(java.lang.String itemValue)
    {
        this._itemValue = itemValue;
    } //-- void setItemValue(java.lang.String) 

    /**
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
    **/
    public void setStatus(java.lang.String status)
    {
        this._status = status;
    } //-- void setStatus(java.lang.String) 

    /**
     * Sets the value of field 'userCode'.
     * 
     * @param userCode the value of field 'userCode'.
    **/
    public void setUserCode(java.lang.String userCode)
    {
        this._userCode = userCode;
    } //-- void setUserCode(java.lang.String) 

    /**
     * Sets the value of field 'userName'.
     * 
     * @param userName the value of field 'userName'.
    **/
    public void setUserName(java.lang.String userName)
    {
        this._userName = userName;
    } //-- void setUserName(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0014.Item unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0014.Item) Unmarshaller.unmarshal(com.ai.mapp.model.HW0014.Item.class, reader);
    } //-- com.ai.mapp.model.HW0014.Item unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
