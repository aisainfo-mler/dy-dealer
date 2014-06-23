/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0010;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import org.exolab.castor.xml.*;
import org.xml.sax.ContentHandler;

/**
 * 
 * 
 * @version $Revision$ $Date$
**/
public class Request implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _mdn;

    private java.lang.String _SIM;

    private java.lang.String _IMSI;

    private java.lang.String _cardType;

    private java.lang.String _cardData;

    private java.lang.String _productId;

    private java.lang.String _model;

    private java.lang.String _IMEI;

    private java.lang.String _name;

    private java.lang.String _email;

    private java.lang.String _contactPhone;

    private java.lang.String _fee;

    private java.lang.String _IDCardType;

    private java.lang.String _IDCardNo;

    private java.lang.String _payMethodId;

    private java.lang.String _voucherNo;

    private java.lang.String _serviceType;

    private java.lang.String _firstMonthType;

    private java.lang.String _developerId;

    private java.lang.String _clientName;

    private java.lang.String _productName;

    private java.lang.String _feeLog;

    private java.lang.String _bankType;

    private java.lang.String _terms;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0010.Request()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bankType'.
     * 
     * @return the value of field 'bankType'.
    **/
    public java.lang.String getBankType()
    {
        return this._bankType;
    } //-- java.lang.String getBankType() 

    /**
     * Returns the value of field 'cardData'.
     * 
     * @return the value of field 'cardData'.
    **/
    public java.lang.String getCardData()
    {
        return this._cardData;
    } //-- java.lang.String getCardData() 

    /**
     * Returns the value of field 'cardType'.
     * 
     * @return the value of field 'cardType'.
    **/
    public java.lang.String getCardType()
    {
        return this._cardType;
    } //-- java.lang.String getCardType() 

    /**
     * Returns the value of field 'clientName'.
     * 
     * @return the value of field 'clientName'.
    **/
    public java.lang.String getClientName()
    {
        return this._clientName;
    } //-- java.lang.String getClientName() 

    /**
     * Returns the value of field 'contactPhone'.
     * 
     * @return the value of field 'contactPhone'.
    **/
    public java.lang.String getContactPhone()
    {
        return this._contactPhone;
    } //-- java.lang.String getContactPhone() 

    /**
     * Returns the value of field 'developerId'.
     * 
     * @return the value of field 'developerId'.
    **/
    public java.lang.String getDeveloperId()
    {
        return this._developerId;
    } //-- java.lang.String getDeveloperId() 

    /**
     * Returns the value of field 'email'.
     * 
     * @return the value of field 'email'.
    **/
    public java.lang.String getEmail()
    {
        return this._email;
    } //-- java.lang.String getEmail() 

    /**
     * Returns the value of field 'fee'.
     * 
     * @return the value of field 'fee'.
    **/
    public java.lang.String getFee()
    {
        return this._fee;
    } //-- java.lang.String getFee() 

    /**
     * Returns the value of field 'feeLog'.
     * 
     * @return the value of field 'feeLog'.
    **/
    public java.lang.String getFeeLog()
    {
        return this._feeLog;
    } //-- java.lang.String getFeeLog() 

    /**
     * Returns the value of field 'firstMonthType'.
     * 
     * @return the value of field 'firstMonthType'.
    **/
    public java.lang.String getFirstMonthType()
    {
        return this._firstMonthType;
    } //-- java.lang.String getFirstMonthType() 

    /**
     * Returns the value of field 'IDCardNo'.
     * 
     * @return the value of field 'IDCardNo'.
    **/
    public java.lang.String getIDCardNo()
    {
        return this._IDCardNo;
    } //-- java.lang.String getIDCardNo() 

    /**
     * Returns the value of field 'IDCardType'.
     * 
     * @return the value of field 'IDCardType'.
    **/
    public java.lang.String getIDCardType()
    {
        return this._IDCardType;
    } //-- java.lang.String getIDCardType() 

    /**
     * Returns the value of field 'IMEI'.
     * 
     * @return the value of field 'IMEI'.
    **/
    public java.lang.String getIMEI()
    {
        return this._IMEI;
    } //-- java.lang.String getIMEI() 

    /**
     * Returns the value of field 'IMSI'.
     * 
     * @return the value of field 'IMSI'.
    **/
    public java.lang.String getIMSI()
    {
        return this._IMSI;
    } //-- java.lang.String getIMSI() 

    /**
     * Returns the value of field 'mdn'.
     * 
     * @return the value of field 'mdn'.
    **/
    public java.lang.String getMdn()
    {
        return this._mdn;
    } //-- java.lang.String getMdn() 

    /**
     * Returns the value of field 'model'.
     * 
     * @return the value of field 'model'.
    **/
    public java.lang.String getModel()
    {
        return this._model;
    } //-- java.lang.String getModel() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'name'.
    **/
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'payMethodId'.
     * 
     * @return the value of field 'payMethodId'.
    **/
    public java.lang.String getPayMethodId()
    {
        return this._payMethodId;
    } //-- java.lang.String getPayMethodId() 

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
     * Returns the value of field 'productName'.
     * 
     * @return the value of field 'productName'.
    **/
    public java.lang.String getProductName()
    {
        return this._productName;
    } //-- java.lang.String getProductName() 

    /**
     * Returns the value of field 'SIM'.
     * 
     * @return the value of field 'SIM'.
    **/
    public java.lang.String getSIM()
    {
        return this._SIM;
    } //-- java.lang.String getSIM() 

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
     * Returns the value of field 'terms'.
     * 
     * @return the value of field 'terms'.
    **/
    public java.lang.String getTerms()
    {
        return this._terms;
    } //-- java.lang.String getTerms() 

    /**
     * Returns the value of field 'voucherNo'.
     * 
     * @return the value of field 'voucherNo'.
    **/
    public java.lang.String getVoucherNo()
    {
        return this._voucherNo;
    } //-- java.lang.String getVoucherNo() 

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
     * Sets the value of field 'bankType'.
     * 
     * @param bankType the value of field 'bankType'.
    **/
    public void setBankType(java.lang.String bankType)
    {
        this._bankType = bankType;
    } //-- void setBankType(java.lang.String) 

    /**
     * Sets the value of field 'cardData'.
     * 
     * @param cardData the value of field 'cardData'.
    **/
    public void setCardData(java.lang.String cardData)
    {
        this._cardData = cardData;
    } //-- void setCardData(java.lang.String) 

    /**
     * Sets the value of field 'cardType'.
     * 
     * @param cardType the value of field 'cardType'.
    **/
    public void setCardType(java.lang.String cardType)
    {
        this._cardType = cardType;
    } //-- void setCardType(java.lang.String) 

    /**
     * Sets the value of field 'clientName'.
     * 
     * @param clientName the value of field 'clientName'.
    **/
    public void setClientName(java.lang.String clientName)
    {
        this._clientName = clientName;
    } //-- void setClientName(java.lang.String) 

    /**
     * Sets the value of field 'contactPhone'.
     * 
     * @param contactPhone the value of field 'contactPhone'.
    **/
    public void setContactPhone(java.lang.String contactPhone)
    {
        this._contactPhone = contactPhone;
    } //-- void setContactPhone(java.lang.String) 

    /**
     * Sets the value of field 'developerId'.
     * 
     * @param developerId the value of field 'developerId'.
    **/
    public void setDeveloperId(java.lang.String developerId)
    {
        this._developerId = developerId;
    } //-- void setDeveloperId(java.lang.String) 

    /**
     * Sets the value of field 'email'.
     * 
     * @param email the value of field 'email'.
    **/
    public void setEmail(java.lang.String email)
    {
        this._email = email;
    } //-- void setEmail(java.lang.String) 

    /**
     * Sets the value of field 'fee'.
     * 
     * @param fee the value of field 'fee'.
    **/
    public void setFee(java.lang.String fee)
    {
        this._fee = fee;
    } //-- void setFee(java.lang.String) 

    /**
     * Sets the value of field 'feeLog'.
     * 
     * @param feeLog the value of field 'feeLog'.
    **/
    public void setFeeLog(java.lang.String feeLog)
    {
        this._feeLog = feeLog;
    } //-- void setFeeLog(java.lang.String) 

    /**
     * Sets the value of field 'firstMonthType'.
     * 
     * @param firstMonthType the value of field 'firstMonthType'.
    **/
    public void setFirstMonthType(java.lang.String firstMonthType)
    {
        this._firstMonthType = firstMonthType;
    } //-- void setFirstMonthType(java.lang.String) 

    /**
     * Sets the value of field 'IDCardNo'.
     * 
     * @param IDCardNo the value of field 'IDCardNo'.
    **/
    public void setIDCardNo(java.lang.String IDCardNo)
    {
        this._IDCardNo = IDCardNo;
    } //-- void setIDCardNo(java.lang.String) 

    /**
     * Sets the value of field 'IDCardType'.
     * 
     * @param IDCardType the value of field 'IDCardType'.
    **/
    public void setIDCardType(java.lang.String IDCardType)
    {
        this._IDCardType = IDCardType;
    } //-- void setIDCardType(java.lang.String) 

    /**
     * Sets the value of field 'IMEI'.
     * 
     * @param IMEI the value of field 'IMEI'.
    **/
    public void setIMEI(java.lang.String IMEI)
    {
        this._IMEI = IMEI;
    } //-- void setIMEI(java.lang.String) 

    /**
     * Sets the value of field 'IMSI'.
     * 
     * @param IMSI the value of field 'IMSI'.
    **/
    public void setIMSI(java.lang.String IMSI)
    {
        this._IMSI = IMSI;
    } //-- void setIMSI(java.lang.String) 

    /**
     * Sets the value of field 'mdn'.
     * 
     * @param mdn the value of field 'mdn'.
    **/
    public void setMdn(java.lang.String mdn)
    {
        this._mdn = mdn;
    } //-- void setMdn(java.lang.String) 

    /**
     * Sets the value of field 'model'.
     * 
     * @param model the value of field 'model'.
    **/
    public void setModel(java.lang.String model)
    {
        this._model = model;
    } //-- void setModel(java.lang.String) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
    **/
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'payMethodId'.
     * 
     * @param payMethodId the value of field 'payMethodId'.
    **/
    public void setPayMethodId(java.lang.String payMethodId)
    {
        this._payMethodId = payMethodId;
    } //-- void setPayMethodId(java.lang.String) 

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
     * Sets the value of field 'productName'.
     * 
     * @param productName the value of field 'productName'.
    **/
    public void setProductName(java.lang.String productName)
    {
        this._productName = productName;
    } //-- void setProductName(java.lang.String) 

    /**
     * Sets the value of field 'SIM'.
     * 
     * @param SIM the value of field 'SIM'.
    **/
    public void setSIM(java.lang.String SIM)
    {
        this._SIM = SIM;
    } //-- void setSIM(java.lang.String) 

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
     * Sets the value of field 'terms'.
     * 
     * @param terms the value of field 'terms'.
    **/
    public void setTerms(java.lang.String terms)
    {
        this._terms = terms;
    } //-- void setTerms(java.lang.String) 

    /**
     * Sets the value of field 'voucherNo'.
     * 
     * @param voucherNo the value of field 'voucherNo'.
    **/
    public void setVoucherNo(java.lang.String voucherNo)
    {
        this._voucherNo = voucherNo;
    } //-- void setVoucherNo(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0010.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0010.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0010.Request.class, reader);
    } //-- com.ai.mapp.model.HW0010.Request unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 
    public String toXMLString(){
		try{
	    	StringWriter out = new StringWriter();
	    	this.marshal(out);
			return out.toString(); 
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}		
    }
}
