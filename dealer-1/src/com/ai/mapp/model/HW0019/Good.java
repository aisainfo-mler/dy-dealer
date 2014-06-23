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
public class Good implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _goodId;

    private java.lang.String _goodName;

    private java.lang.String _goodPrice;

    private java.lang.String _goodSalePrice;

    private java.lang.String _goodStatus;

    private java.lang.String _image;


      //----------------/
     //- Constructors -/
    //----------------/

    public Good() {
        super();
    } //-- com.ai.mapp.model.HW0019.Good()


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
     * Returns the value of field 'goodName'.
     * 
     * @return the value of field 'goodName'.
    **/
    public java.lang.String getGoodName()
    {
        return this._goodName;
    } //-- java.lang.String getGoodName() 

    /**
     * Returns the value of field 'goodPrice'.
     * 
     * @return the value of field 'goodPrice'.
    **/
    public java.lang.String getGoodPrice()
    {
        return this._goodPrice;
    } //-- java.lang.String getGoodPrice() 

    /**
     * Returns the value of field 'goodSalePrice'.
     * 
     * @return the value of field 'goodSalePrice'.
    **/
    public java.lang.String getGoodSalePrice()
    {
        return this._goodSalePrice;
    } //-- java.lang.String getGoodSalePrice() 

    /**
     * Returns the value of field 'goodStatus'.
     * 
     * @return the value of field 'goodStatus'.
    **/
    public java.lang.String getGoodStatus()
    {
        return this._goodStatus;
    } //-- java.lang.String getGoodStatus() 

    /**
     * Returns the value of field 'image'.
     * 
     * @return the value of field 'image'.
    **/
    public java.lang.String getImage()
    {
        return this._image;
    } //-- java.lang.String getImage() 

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
     * Sets the value of field 'goodName'.
     * 
     * @param goodName the value of field 'goodName'.
    **/
    public void setGoodName(java.lang.String goodName)
    {
        this._goodName = goodName;
    } //-- void setGoodName(java.lang.String) 

    /**
     * Sets the value of field 'goodPrice'.
     * 
     * @param goodPrice the value of field 'goodPrice'.
    **/
    public void setGoodPrice(java.lang.String goodPrice)
    {
        this._goodPrice = goodPrice;
    } //-- void setGoodPrice(java.lang.String) 

    /**
     * Sets the value of field 'goodSalePrice'.
     * 
     * @param goodSalePrice the value of field 'goodSalePrice'.
    **/
    public void setGoodSalePrice(java.lang.String goodSalePrice)
    {
        this._goodSalePrice = goodSalePrice;
    } //-- void setGoodSalePrice(java.lang.String) 

    /**
     * Sets the value of field 'goodStatus'.
     * 
     * @param goodStatus the value of field 'goodStatus'.
    **/
    public void setGoodStatus(java.lang.String goodStatus)
    {
        this._goodStatus = goodStatus;
    } //-- void setGoodStatus(java.lang.String) 

    /**
     * Sets the value of field 'image'.
     * 
     * @param image the value of field 'image'.
    **/
    public void setImage(java.lang.String image)
    {
        this._image = image;
    } //-- void setImage(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0019.Good unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0019.Good) Unmarshaller.unmarshal(com.ai.mapp.model.HW0019.Good.class, reader);
    } //-- com.ai.mapp.model.HW0019.Good unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
