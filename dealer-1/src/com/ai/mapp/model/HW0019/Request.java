/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0019;

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

    private java.lang.String _goodId;

    private java.lang.String _goodType;

    private java.lang.String _status;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0019.Request()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'goodId'.
     * 
     * @return the value of field 'goodId'.
    **/
    public java.lang.String getGoodId()
    {
        return this._goodId;
    } //-- java.lang.String getGoodId() 

    /**
     * Returns the value of field 'goodType'.
     * 
     * @return the value of field 'goodType'.
    **/
    public java.lang.String getGoodType()
    {
        return this._goodType;
    } //-- java.lang.String getGoodType() 

    /**
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'status'.
    **/
    public java.lang.String getStatus()
    {
        return this._status;
    } //-- java.lang.String getStatus() 

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
     * Sets the value of field 'goodId'.
     * 
     * @param goodId the value of field 'goodId'.
    **/
    public void setGoodId(java.lang.String goodId)
    {
        this._goodId = goodId;
    } //-- void setGoodId(java.lang.String) 

    /**
     * Sets the value of field 'goodType'.
     * 
     * @param goodType the value of field 'goodType'.
    **/
    public void setGoodType(java.lang.String goodType)
    {
        this._goodType = goodType;
    } //-- void setGoodType(java.lang.String) 

    /**
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
    **/
    public void setStatus(java.lang.String status)
    {
        this._status = status;
    } //-- void setStatus(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0019.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0019.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0019.Request.class, reader);
    } //-- com.ai.mapp.model.HW0019.Request unmarshal(java.io.Reader) 

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
