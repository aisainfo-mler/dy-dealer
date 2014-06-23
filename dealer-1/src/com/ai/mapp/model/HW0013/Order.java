/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0013;

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

    private java.lang.String _svn;

    private java.lang.String _sim;

    private java.lang.String _preStore;

    private java.lang.String _totalFee;

    private java.lang.String _createTime;

    private java.lang.String _simFee;

    private java.lang.String _balance;

    private java.lang.String _payMode;

    private java.lang.String _saleFee;

    private java.lang.String _discountFee;

    private java.lang.String _realFee;

    private java.lang.String _payStatus;

    private java.lang.String _orderStatus;

    private java.lang.String _productId;

    private java.lang.String _productType;

    private java.lang.String _pin;

    private java.lang.String _packageFee;

    private java.lang.String _packageName;

    private java.lang.String _numberFee;

    private java.lang.String _SIMFee;


      //----------------/
     //- Constructors -/
    //----------------/

    public Order() {
        super();
    } //-- com.ai.mapp.model.HW0013.Order()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'balance'.
     * 
     * @return the value of field 'balance'.
    **/
    public java.lang.String getBalance()
    {
        return this._balance;
    } //-- java.lang.String getBalance() 

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
     * Returns the value of field 'discountFee'.
     * 
     * @return the value of field 'discountFee'.
    **/
    public java.lang.String getDiscountFee()
    {
        return this._discountFee;
    } //-- java.lang.String getDiscountFee() 

    /**
     * Returns the value of field 'numberFee'.
     * 
     * @return the value of field 'numberFee'.
    **/
    public java.lang.String getNumberFee()
    {
        return this._numberFee;
    } //-- java.lang.String getNumberFee() 

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
     * Returns the value of field 'packageFee'.
     * 
     * @return the value of field 'packageFee'.
    **/
    public java.lang.String getPackageFee()
    {
        return this._packageFee;
    } //-- java.lang.String getPackageFee() 

    /**
     * Returns the value of field 'packageName'.
     * 
     * @return the value of field 'packageName'.
    **/
    public java.lang.String getPackageName()
    {
        return this._packageName;
    } //-- java.lang.String getPackageName() 

    /**
     * Returns the value of field 'payMode'.
     * 
     * @return the value of field 'payMode'.
    **/
    public java.lang.String getPayMode()
    {
        return this._payMode;
    } //-- java.lang.String getPayMode() 

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
     * Returns the value of field 'pin'.
     * 
     * @return the value of field 'pin'.
    **/
    public java.lang.String getPin()
    {
        return this._pin;
    } //-- java.lang.String getPin() 

    /**
     * Returns the value of field 'preStore'.
     * 
     * @return the value of field 'preStore'.
    **/
    public java.lang.String getPreStore()
    {
        return this._preStore;
    } //-- java.lang.String getPreStore() 

    /**
     * Returns the value of field 'productId'.
     * 
     * @return the value of field 'productId'.
    **/
    public java.lang.String getProductId()
    {
        return this._productId;
    } //-- java.lang.String getProductId() 

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
     * Returns the value of field 'realFee'.
     * 
     * @return the value of field 'realFee'.
    **/
    public java.lang.String getRealFee()
    {
        return this._realFee;
    } //-- java.lang.String getRealFee() 

    /**
     * Returns the value of field 'SIMFee'.
     * 
     * @return the value of field 'SIMFee'.
    **/
    public java.lang.String getSIMFee()
    {
        return this._SIMFee;
    } //-- java.lang.String getSIMFee() 

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
     * Returns the value of field 'sim'.
     * 
     * @return the value of field 'sim'.
    **/
    public java.lang.String getSim()
    {
        return this._sim;
    } //-- java.lang.String getSim() 

    /**
     * Returns the value of field 'simFee'.
     * 
     * @return the value of field 'simFee'.
    **/
    public java.lang.String getSimFee()
    {
        return this._simFee;
    } //-- java.lang.String getSimFee() 

    /**
     * Returns the value of field 'svn'.
     * 
     * @return the value of field 'svn'.
    **/
    public java.lang.String getSvn()
    {
        return this._svn;
    } //-- java.lang.String getSvn() 

    /**
     * Returns the value of field 'totalFee'.
     * 
     * @return the value of field 'totalFee'.
    **/
    public java.lang.String getTotalFee()
    {
        return this._totalFee;
    } //-- java.lang.String getTotalFee() 

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
     * Sets the value of field 'balance'.
     * 
     * @param balance the value of field 'balance'.
    **/
    public void setBalance(java.lang.String balance)
    {
        this._balance = balance;
    } //-- void setBalance(java.lang.String) 

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
     * Sets the value of field 'discountFee'.
     * 
     * @param discountFee the value of field 'discountFee'.
    **/
    public void setDiscountFee(java.lang.String discountFee)
    {
        this._discountFee = discountFee;
    } //-- void setDiscountFee(java.lang.String) 

    /**
     * Sets the value of field 'numberFee'.
     * 
     * @param numberFee the value of field 'numberFee'.
    **/
    public void setNumberFee(java.lang.String numberFee)
    {
        this._numberFee = numberFee;
    } //-- void setNumberFee(java.lang.String) 

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
     * Sets the value of field 'packageFee'.
     * 
     * @param packageFee the value of field 'packageFee'.
    **/
    public void setPackageFee(java.lang.String packageFee)
    {
        this._packageFee = packageFee;
    } //-- void setPackageFee(java.lang.String) 

    /**
     * Sets the value of field 'packageName'.
     * 
     * @param packageName the value of field 'packageName'.
    **/
    public void setPackageName(java.lang.String packageName)
    {
        this._packageName = packageName;
    } //-- void setPackageName(java.lang.String) 

    /**
     * Sets the value of field 'payMode'.
     * 
     * @param payMode the value of field 'payMode'.
    **/
    public void setPayMode(java.lang.String payMode)
    {
        this._payMode = payMode;
    } //-- void setPayMode(java.lang.String) 

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
     * Sets the value of field 'pin'.
     * 
     * @param pin the value of field 'pin'.
    **/
    public void setPin(java.lang.String pin)
    {
        this._pin = pin;
    } //-- void setPin(java.lang.String) 

    /**
     * Sets the value of field 'preStore'.
     * 
     * @param preStore the value of field 'preStore'.
    **/
    public void setPreStore(java.lang.String preStore)
    {
        this._preStore = preStore;
    } //-- void setPreStore(java.lang.String) 

    /**
     * Sets the value of field 'productId'.
     * 
     * @param productId the value of field 'productId'.
    **/
    public void setProductId(java.lang.String productId)
    {
        this._productId = productId;
    } //-- void setProductId(java.lang.String) 

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
     * Sets the value of field 'realFee'.
     * 
     * @param realFee the value of field 'realFee'.
    **/
    public void setRealFee(java.lang.String realFee)
    {
        this._realFee = realFee;
    } //-- void setRealFee(java.lang.String) 

    /**
     * Sets the value of field 'SIMFee'.
     * 
     * @param SIMFee the value of field 'SIMFee'.
    **/
    public void setSIMFee(java.lang.String SIMFee)
    {
        this._SIMFee = SIMFee;
    } //-- void setSIMFee(java.lang.String) 

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
     * Sets the value of field 'sim'.
     * 
     * @param sim the value of field 'sim'.
    **/
    public void setSim(java.lang.String sim)
    {
        this._sim = sim;
    } //-- void setSim(java.lang.String) 

    /**
     * Sets the value of field 'simFee'.
     * 
     * @param simFee the value of field 'simFee'.
    **/
    public void setSimFee(java.lang.String simFee)
    {
        this._simFee = simFee;
    } //-- void setSimFee(java.lang.String) 

    /**
     * Sets the value of field 'svn'.
     * 
     * @param svn the value of field 'svn'.
    **/
    public void setSvn(java.lang.String svn)
    {
        this._svn = svn;
    } //-- void setSvn(java.lang.String) 

    /**
     * Sets the value of field 'totalFee'.
     * 
     * @param totalFee the value of field 'totalFee'.
    **/
    public void setTotalFee(java.lang.String totalFee)
    {
        this._totalFee = totalFee;
    } //-- void setTotalFee(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0013.Order unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0013.Order) Unmarshaller.unmarshal(com.ai.mapp.model.HW0013.Order.class, reader);
    } //-- com.ai.mapp.model.HW0013.Order unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
