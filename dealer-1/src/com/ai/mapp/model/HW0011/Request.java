/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id: Request.java,v 1.1 2012/03/16 09:42:39 luyang2 Exp $
 */

package com.ai.mapp.model.HW0011;

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
 * @version $Revision: 1.1 $ $Date: 2012/03/16 09:42:39 $
**/
public class Request implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _mdn;

    private java.lang.String _SIM;

    private java.lang.String _cardType;

    private java.lang.String _IMSI;

    private java.lang.String _cardData;

    private java.lang.String _fee;

    private java.lang.String _payMethodId;

    private java.lang.String _voucherNo;

    private java.lang.String _optType;

    private java.lang.String _feeLog;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.Z10020.Request()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'optType'.
     * 
     * @return the value of field 'optType'.
    **/
    public java.lang.String getOptType()
    {
        return this._optType;
    } //-- java.lang.String getOptType() 

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
     * Returns the value of field 'SIM'.
     * 
     * @return the value of field 'SIM'.
    **/
    public java.lang.String getSIM()
    {
        return this._SIM;
    } //-- java.lang.String getSIM() 

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
     * Sets the value of field 'optType'.
     * 
     * @param optType the value of field 'optType'.
    **/
    public void setOptType(java.lang.String optType)
    {
        this._optType = optType;
    } //-- void setOptType(java.lang.String) 

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
     * Sets the value of field 'SIM'.
     * 
     * @param SIM the value of field 'SIM'.
    **/
    public void setSIM(java.lang.String SIM)
    {
        this._SIM = SIM;
    } //-- void setSIM(java.lang.String) 

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
    public static com.ai.mapp.model.HW0011.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0011.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0011.Request.class, reader);
    } //-- com.ai.mapp.model.Z10020.Request unmarshal(java.io.Reader) 

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
