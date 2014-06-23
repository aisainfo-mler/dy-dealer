/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0001;

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

    private java.lang.String _phoneId;

    private java.lang.String _productId;

    private java.lang.String _productType;

    private java.lang.String _recommend;

    private java.lang.String _payType;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0001.Request()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'payType'.
     * 
     * @return the value of field 'payType'.
    **/
    public java.lang.String getPayType()
    {
        return this._payType;
    } //-- java.lang.String getPayType() 

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
     * Returns the value of field 'recommend'.
     * 
     * @return the value of field 'recommend'.
    **/
    public java.lang.String getRecommend()
    {
        return this._recommend;
    } //-- java.lang.String getRecommend() 

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
     * Sets the value of field 'payType'.
     * 
     * @param payType the value of field 'payType'.
    **/
    public void setPayType(java.lang.String payType)
    {
        this._payType = payType;
    } //-- void setPayType(java.lang.String) 

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
     * Sets the value of field 'recommend'.
     * 
     * @param recommend the value of field 'recommend'.
    **/
    public void setRecommend(java.lang.String recommend)
    {
        this._recommend = recommend;
    } //-- void setRecommend(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0001.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0001.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0001.Request.class, reader);
    } //-- com.ai.mapp.model.HW0001.Request unmarshal(java.io.Reader) 

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
