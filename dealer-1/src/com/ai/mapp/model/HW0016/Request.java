/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0016;

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

    private java.lang.String _orderCode;

    private java.lang.String _payMode;

    private java.lang.String _saleFee;

    private java.lang.String _voucherNo;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0016.Request()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'payMode'.
     * 
     * @return the value of field 'payMode'.
    **/
    public java.lang.String getPayMode()
    {
        return this._payMode;
    } //-- java.lang.String getPayMode() 

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
     * Sets the value of field 'orderCode'.
     * 
     * @param orderCode the value of field 'orderCode'.
    **/
    public void setOrderCode(java.lang.String orderCode)
    {
        this._orderCode = orderCode;
    } //-- void setOrderCode(java.lang.String) 

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
     * Sets the value of field 'saleFee'.
     * 
     * @param saleFee the value of field 'saleFee'.
    **/
    public void setSaleFee(java.lang.String saleFee)
    {
        this._saleFee = saleFee;
    } //-- void setSaleFee(java.lang.String) 

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
    public static com.ai.mapp.model.HW0016.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0016.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0016.Request.class, reader);
    } //-- com.ai.mapp.model.HW0016.Request unmarshal(java.io.Reader) 

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
