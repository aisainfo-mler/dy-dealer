/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0017;

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

    private java.lang.String _count;

    private java.lang.String _discount;

    private java.lang.String _saleFee;

    private java.lang.String _realFee;


      //----------------/
     //- Constructors -/
    //----------------/

    public Good() {
        super();
    } //-- com.ai.mapp.model.HW0017.Good()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'count'.
     * 
     * @return the value of field 'count'.
    **/
    public java.lang.String getCount()
    {
        return this._count;
    } //-- java.lang.String getCount() 

    /**
     * Returns the value of field 'discount'.
     * 
     * @return the value of field 'discount'.
    **/
    public java.lang.String getDiscount()
    {
        return this._discount;
    } //-- java.lang.String getDiscount() 

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
     * Returns the value of field 'realFee'.
     * 
     * @return the value of field 'realFee'.
    **/
    public java.lang.String getRealFee()
    {
        return this._realFee;
    } //-- java.lang.String getRealFee() 

    /**
     * Returns the value of field 'saleFee'.
     * 
     * @return the value of field 'saleFee'.
    **/
    public java.lang.String getSaleFee()
    {
        return this._saleFee;
    } //-- java.lang.String getSaleFee() 

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
     * Sets the value of field 'count'.
     * 
     * @param count the value of field 'count'.
    **/
    public void setCount(java.lang.String count)
    {
        this._count = count;
    } //-- void setCount(java.lang.String) 

    /**
     * Sets the value of field 'discount'.
     * 
     * @param discount the value of field 'discount'.
    **/
    public void setDiscount(java.lang.String discount)
    {
        this._discount = discount;
    } //-- void setDiscount(java.lang.String) 

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
     * Sets the value of field 'realFee'.
     * 
     * @param realFee the value of field 'realFee'.
    **/
    public void setRealFee(java.lang.String realFee)
    {
        this._realFee = realFee;
    } //-- void setRealFee(java.lang.String) 

    /**
     * Sets the value of field 'saleFee'.
     * 
     * @param saleFee the value of field 'saleFee'.
    **/
    public void setSaleFee(java.lang.String saleFee)
    {
        this._saleFee = saleFee;
    } //-- void setSaleFee(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0017.Good unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0017.Good) Unmarshaller.unmarshal(com.ai.mapp.model.HW0017.Good.class, reader);
    } //-- com.ai.mapp.model.HW0017.Good unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
