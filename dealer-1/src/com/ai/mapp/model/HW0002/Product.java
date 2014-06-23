/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0002;

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
public class Product implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _term;

    private java.lang.String _productId;

    private java.lang.String _actType;

    private java.lang.String _productDesc;

    private java.lang.String _price;

    private java.lang.String _preStore;

    private java.lang.String _preStoreDesc;

    private java.lang.String _backMonth;

    private java.lang.String _resFee;

    private java.lang.String _allFee;

    private java.lang.String _productFee;

    private java.lang.String _phoneId;

    private java.lang.String _contractId;

    private java.lang.String _recommend;


      //----------------/
     //- Constructors -/
    //----------------/

    public Product() {
        super();
    } //-- com.ai.mapp.model.HW0002.Product()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'actType'.
     * 
     * @return the value of field 'actType'.
    **/
    public java.lang.String getActType()
    {
        return this._actType;
    } //-- java.lang.String getActType() 

    /**
     * Returns the value of field 'allFee'.
     * 
     * @return the value of field 'allFee'.
    **/
    public java.lang.String getAllFee()
    {
        return this._allFee;
    } //-- java.lang.String getAllFee() 

    /**
     * Returns the value of field 'backMonth'.
     * 
     * @return the value of field 'backMonth'.
    **/
    public java.lang.String getBackMonth()
    {
        return this._backMonth;
    } //-- java.lang.String getBackMonth() 

    /**
     * Returns the value of field 'contractId'.
     * 
     * @return the value of field 'contractId'.
    **/
    public java.lang.String getContractId()
    {
        return this._contractId;
    } //-- java.lang.String getContractId() 

    /**
     * Returns the value of field 'phoneId'.
     * 
     * @return the value of field 'phoneId'.
    **/
    public java.lang.String getPhoneId()
    {
        return this._phoneId;
    } //-- java.lang.String getPhoneId() 

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
     * Returns the value of field 'preStoreDesc'.
     * 
     * @return the value of field 'preStoreDesc'.
    **/
    public java.lang.String getPreStoreDesc()
    {
        return this._preStoreDesc;
    } //-- java.lang.String getPreStoreDesc() 

    /**
     * Returns the value of field 'price'.
     * 
     * @return the value of field 'price'.
    **/
    public java.lang.String getPrice()
    {
        return this._price;
    } //-- java.lang.String getPrice() 

    /**
     * Returns the value of field 'productDesc'.
     * 
     * @return the value of field 'productDesc'.
    **/
    public java.lang.String getProductDesc()
    {
        return this._productDesc;
    } //-- java.lang.String getProductDesc() 

    /**
     * Returns the value of field 'productFee'.
     * 
     * @return the value of field 'productFee'.
    **/
    public java.lang.String getProductFee()
    {
        return this._productFee;
    } //-- java.lang.String getProductFee() 

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
     * Returns the value of field 'recommend'.
     * 
     * @return the value of field 'recommend'.
    **/
    public java.lang.String getRecommend()
    {
        return this._recommend;
    } //-- java.lang.String getRecommend() 

    /**
     * Returns the value of field 'resFee'.
     * 
     * @return the value of field 'resFee'.
    **/
    public java.lang.String getResFee()
    {
        return this._resFee;
    } //-- java.lang.String getResFee() 

    /**
     * Returns the value of field 'term'.
     * 
     * @return the value of field 'term'.
    **/
    public java.lang.String getTerm()
    {
        return this._term;
    } //-- java.lang.String getTerm() 

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
     * Sets the value of field 'actType'.
     * 
     * @param actType the value of field 'actType'.
    **/
    public void setActType(java.lang.String actType)
    {
        this._actType = actType;
    } //-- void setActType(java.lang.String) 

    /**
     * Sets the value of field 'allFee'.
     * 
     * @param allFee the value of field 'allFee'.
    **/
    public void setAllFee(java.lang.String allFee)
    {
        this._allFee = allFee;
    } //-- void setAllFee(java.lang.String) 

    /**
     * Sets the value of field 'backMonth'.
     * 
     * @param backMonth the value of field 'backMonth'.
    **/
    public void setBackMonth(java.lang.String backMonth)
    {
        this._backMonth = backMonth;
    } //-- void setBackMonth(java.lang.String) 

    /**
     * Sets the value of field 'contractId'.
     * 
     * @param contractId the value of field 'contractId'.
    **/
    public void setContractId(java.lang.String contractId)
    {
        this._contractId = contractId;
    } //-- void setContractId(java.lang.String) 

    /**
     * Sets the value of field 'phoneId'.
     * 
     * @param phoneId the value of field 'phoneId'.
    **/
    public void setPhoneId(java.lang.String phoneId)
    {
        this._phoneId = phoneId;
    } //-- void setPhoneId(java.lang.String) 

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
     * Sets the value of field 'preStoreDesc'.
     * 
     * @param preStoreDesc the value of field 'preStoreDesc'.
    **/
    public void setPreStoreDesc(java.lang.String preStoreDesc)
    {
        this._preStoreDesc = preStoreDesc;
    } //-- void setPreStoreDesc(java.lang.String) 

    /**
     * Sets the value of field 'price'.
     * 
     * @param price the value of field 'price'.
    **/
    public void setPrice(java.lang.String price)
    {
        this._price = price;
    } //-- void setPrice(java.lang.String) 

    /**
     * Sets the value of field 'productDesc'.
     * 
     * @param productDesc the value of field 'productDesc'.
    **/
    public void setProductDesc(java.lang.String productDesc)
    {
        this._productDesc = productDesc;
    } //-- void setProductDesc(java.lang.String) 

    /**
     * Sets the value of field 'productFee'.
     * 
     * @param productFee the value of field 'productFee'.
    **/
    public void setProductFee(java.lang.String productFee)
    {
        this._productFee = productFee;
    } //-- void setProductFee(java.lang.String) 

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
     * Sets the value of field 'recommend'.
     * 
     * @param recommend the value of field 'recommend'.
    **/
    public void setRecommend(java.lang.String recommend)
    {
        this._recommend = recommend;
    } //-- void setRecommend(java.lang.String) 

    /**
     * Sets the value of field 'resFee'.
     * 
     * @param resFee the value of field 'resFee'.
    **/
    public void setResFee(java.lang.String resFee)
    {
        this._resFee = resFee;
    } //-- void setResFee(java.lang.String) 

    /**
     * Sets the value of field 'term'.
     * 
     * @param term the value of field 'term'.
    **/
    public void setTerm(java.lang.String term)
    {
        this._term = term;
    } //-- void setTerm(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0002.Product unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0002.Product) Unmarshaller.unmarshal(com.ai.mapp.model.HW0002.Product.class, reader);
    } //-- com.ai.mapp.model.HW0002.Product unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
