/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0018;

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


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0018.Request()


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
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0018.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0018.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0018.Request.class, reader);
    } //-- com.ai.mapp.model.HW0018.Request unmarshal(java.io.Reader) 

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
