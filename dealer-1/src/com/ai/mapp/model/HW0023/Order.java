/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0023;

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

    private java.lang.String _income;

    private java.lang.String _comment;

    private java.lang.String _commissionCode;

    private java.lang.String _commissionId;

    private java.lang.String _payStatus;

    private java.lang.String _payTime;

    private java.lang.String _createTime;

    private java.lang.String _chargeType;


      //----------------/
     //- Constructors -/
    //----------------/

    public Order() {
        super();
    } //-- com.ai.mapp.model.HW0023.Order()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'chargeType'.
     * 
     * @return the value of field 'chargeType'.
    **/
    public java.lang.String getChargeType()
    {
        return this._chargeType;
    } //-- java.lang.String getChargeType() 

    /**
     * Returns the value of field 'comment'.
     * 
     * @return the value of field 'comment'.
    **/
    public java.lang.String getComment()
    {
        return this._comment;
    } //-- java.lang.String getComment() 

    /**
     * Returns the value of field 'commissionCode'.
     * 
     * @return the value of field 'commissionCode'.
    **/
    public java.lang.String getCommissionCode()
    {
        return this._commissionCode;
    } //-- java.lang.String getCommissionCode() 

    /**
     * Returns the value of field 'commissionId'.
     * 
     * @return the value of field 'commissionId'.
    **/
    public java.lang.String getCommissionId()
    {
        return this._commissionId;
    } //-- java.lang.String getCommissionId() 

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
     * Returns the value of field 'income'.
     * 
     * @return the value of field 'income'.
    **/
    public java.lang.String getIncome()
    {
        return this._income;
    } //-- java.lang.String getIncome() 

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
     * Returns the value of field 'orderType'.
     * 
     * @return the value of field 'orderType'.
    **/
    public java.lang.String getOrderType()
    {
        return this._orderType;
    } //-- java.lang.String getOrderType() 

    /**
     * Returns the value of field 'payStatus'.
     * 
     * @return the value of field 'payStatus'.
    **/
    public java.lang.String getPayStatus()
    {
        return this._payStatus;
    } //-- java.lang.String getPayStatus() 

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
     * Sets the value of field 'chargeType'.
     * 
     * @param chargeType the value of field 'chargeType'.
    **/
    public void setChargeType(java.lang.String chargeType)
    {
        this._chargeType = chargeType;
    } //-- void setChargeType(java.lang.String) 

    /**
     * Sets the value of field 'comment'.
     * 
     * @param comment the value of field 'comment'.
    **/
    public void setComment(java.lang.String comment)
    {
        this._comment = comment;
    } //-- void setComment(java.lang.String) 

    /**
     * Sets the value of field 'commissionCode'.
     * 
     * @param commissionCode the value of field 'commissionCode'.
    **/
    public void setCommissionCode(java.lang.String commissionCode)
    {
        this._commissionCode = commissionCode;
    } //-- void setCommissionCode(java.lang.String) 

    /**
     * Sets the value of field 'commissionId'.
     * 
     * @param commissionId the value of field 'commissionId'.
    **/
    public void setCommissionId(java.lang.String commissionId)
    {
        this._commissionId = commissionId;
    } //-- void setCommissionId(java.lang.String) 

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
     * Sets the value of field 'income'.
     * 
     * @param income the value of field 'income'.
    **/
    public void setIncome(java.lang.String income)
    {
        this._income = income;
    } //-- void setIncome(java.lang.String) 

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
     * Sets the value of field 'orderType'.
     * 
     * @param orderType the value of field 'orderType'.
    **/
    public void setOrderType(java.lang.String orderType)
    {
        this._orderType = orderType;
    } //-- void setOrderType(java.lang.String) 

    /**
     * Sets the value of field 'payStatus'.
     * 
     * @param payStatus the value of field 'payStatus'.
    **/
    public void setPayStatus(java.lang.String payStatus)
    {
        this._payStatus = payStatus;
    } //-- void setPayStatus(java.lang.String) 

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
    public static com.ai.mapp.model.HW0023.Order unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0023.Order) Unmarshaller.unmarshal(com.ai.mapp.model.HW0023.Order.class, reader);
    } //-- com.ai.mapp.model.HW0023.Order unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
