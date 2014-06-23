/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id: Request.java,v 1.1 2012/03/16 09:42:39 luyang2 Exp $
 */

package com.ai.mapp.model.HW0009;

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

    private java.lang.String _rechargeTypeId;

    private java.lang.String _payedAmount;

    private java.lang.String _payMethodId;

    private java.lang.String _voucherNo;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.Z10011.Request()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'payMethodId'.
     * 
     * @return the value of field 'payMethodId'.
    **/
    public java.lang.String getPayMethodId()
    {
        return this._payMethodId;
    } //-- java.lang.String getPayMethodId() 

    /**
     * Returns the value of field 'payedAmount'.
     * 
     * @return the value of field 'payedAmount'.
    **/
    public java.lang.String getPayedAmount()
    {
        return this._payedAmount;
    } //-- java.lang.String getPayedAmount() 

    /**
     * Returns the value of field 'rechargeTypeId'.
     * 
     * @return the value of field 'rechargeTypeId'.
    **/
    public java.lang.String getRechargeTypeId()
    {
        return this._rechargeTypeId;
    } //-- java.lang.String getRechargeTypeId() 

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
     * Sets the value of field 'mdn'.
     * 
     * @param mdn the value of field 'mdn'.
    **/
    public void setMdn(java.lang.String mdn)
    {
        this._mdn = mdn;
    } //-- void setMdn(java.lang.String) 

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
     * Sets the value of field 'payedAmount'.
     * 
     * @param payedAmount the value of field 'payedAmount'.
    **/
    public void setPayedAmount(java.lang.String payedAmount)
    {
        this._payedAmount = payedAmount;
    } //-- void setPayedAmount(java.lang.String) 

    /**
     * Sets the value of field 'rechargeTypeId'.
     * 
     * @param rechargeTypeId the value of field 'rechargeTypeId'.
    **/
    public void setRechargeTypeId(java.lang.String rechargeTypeId)
    {
        this._rechargeTypeId = rechargeTypeId;
    } //-- void setRechargeTypeId(java.lang.String) 

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
    public static com.ai.mapp.model.HW0009.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0009.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0009.Request.class, reader);
    } //-- com.ai.mapp.model.Z10011.Request unmarshal(java.io.Reader) 

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
