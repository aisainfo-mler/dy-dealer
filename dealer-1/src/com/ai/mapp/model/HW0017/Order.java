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
public class Order implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _orderCode;

    private java.lang.String _orderType;

    private java.lang.String _saleFee;

    private java.lang.String _realFee;

    private java.lang.String _discount;

    private java.lang.String _createTime;

    private java.lang.String _payTime;

    private java.lang.String _orderStatus;

    private GoodList _goodList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Order() {
        super();
    } //-- com.ai.mapp.model.HW0017.Order()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'createTime'.
     * 
     * @return the value of field 'createTime'.
    **/
    public java.lang.String getCreateTime()
    {
        return this._createTime;
    } //-- java.lang.String getCreateTime() 

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
     * Returns the value of field 'goodList'.
     * 
     * @return the value of field 'goodList'.
    **/
    public GoodList getGoodList()
    {
        return this._goodList;
    } //-- GoodList getGoodList() 

    /**
     * Returns the value of field 'orderCode'.
     * 
     * @return the value of field 'orderCode'.
    **/
    public java.lang.String getOrderCode()
    {
        return this._orderCode;
    } //-- java.lang.String getOrderCode() 

    /**
     * Returns the value of field 'orderStatus'.
     * 
     * @return the value of field 'orderStatus'.
    **/
    public java.lang.String getOrderStatus()
    {
        return this._orderStatus;
    } //-- java.lang.String getOrderStatus() 

    /**
     * Returns the value of field 'orderType'.
     * 
     * @return the value of field 'orderType'.
    **/
    public java.lang.String getOrderType()
    {
        return this._orderType;
    } //-- java.lang.String getOrderType() 

    /**
     * Returns the value of field 'payTime'.
     * 
     * @return the value of field 'payTime'.
    **/
    public java.lang.String getPayTime()
    {
        return this._payTime;
    } //-- java.lang.String getPayTime() 

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
     * Sets the value of field 'createTime'.
     * 
     * @param createTime the value of field 'createTime'.
    **/
    public void setCreateTime(java.lang.String createTime)
    {
        this._createTime = createTime;
    } //-- void setCreateTime(java.lang.String) 

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
     * Sets the value of field 'goodList'.
     * 
     * @param goodList the value of field 'goodList'.
    **/
    public void setGoodList(GoodList goodList)
    {
        this._goodList = goodList;
    } //-- void setGoodList(GoodList) 

    /**
     * Sets the value of field 'orderCode'.
     * 
     * @param orderCode the value of field 'orderCode'.
    **/
    public void setOrderCode(java.lang.String orderCode)
    {
        this._orderCode = orderCode;
    } //-- void setOrderCode(java.lang.String) 

    /**
     * Sets the value of field 'orderStatus'.
     * 
     * @param orderStatus the value of field 'orderStatus'.
    **/
    public void setOrderStatus(java.lang.String orderStatus)
    {
        this._orderStatus = orderStatus;
    } //-- void setOrderStatus(java.lang.String) 

    /**
     * Sets the value of field 'orderType'.
     * 
     * @param orderType the value of field 'orderType'.
    **/
    public void setOrderType(java.lang.String orderType)
    {
        this._orderType = orderType;
    } //-- void setOrderType(java.lang.String) 

    /**
     * Sets the value of field 'payTime'.
     * 
     * @param payTime the value of field 'payTime'.
    **/
    public void setPayTime(java.lang.String payTime)
    {
        this._payTime = payTime;
    } //-- void setPayTime(java.lang.String) 

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
    public static com.ai.mapp.model.HW0017.Order unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0017.Order) Unmarshaller.unmarshal(com.ai.mapp.model.HW0017.Order.class, reader);
    } //-- com.ai.mapp.model.HW0017.Order unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
