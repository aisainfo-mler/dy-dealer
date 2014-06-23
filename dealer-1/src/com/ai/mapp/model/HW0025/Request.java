/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0025;

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

    private java.lang.String _svn;

    private java.lang.String _fee;

    private java.lang.String _productId;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0025.Request()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'productId'.
     * 
     * @return the value of field 'productId'.
    **/
    public java.lang.String getProductId()
    {
        return this._productId;
    } //-- java.lang.String getProductId() 

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
     * Sets the value of field 'fee'.
     * 
     * @param fee the value of field 'fee'.
    **/
    public void setFee(java.lang.String fee)
    {
        this._fee = fee;
    } //-- void setFee(java.lang.String) 

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
     * Sets the value of field 'svn'.
     * 
     * @param svn the value of field 'svn'.
    **/
    public void setSvn(java.lang.String svn)
    {
        this._svn = svn;
    } //-- void setSvn(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0025.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0025.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0025.Request.class, reader);
    } //-- com.ai.mapp.model.HW0025.Request unmarshal(java.io.Reader) 

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
