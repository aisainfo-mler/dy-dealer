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
public class Response implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _rspCode;

    private java.lang.String _MSG;

    private Terms _terms;


      //----------------/
     //- Constructors -/
    //----------------/

    public Response() {
        super();
    } //-- com.ai.mapp.model.HW0001.Response()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'MSG'.
     * 
     * @return the value of field 'MSG'.
    **/
    public java.lang.String getMSG()
    {
        return this._MSG;
    } //-- java.lang.String getMSG() 

    /**
     * Returns the value of field 'rspCode'.
     * 
     * @return the value of field 'rspCode'.
    **/
    public java.lang.String getRspCode()
    {
        return this._rspCode;
    } //-- java.lang.String getRspCode() 

    /**
     * Returns the value of field 'terms'.
     * 
     * @return the value of field 'terms'.
    **/
    public Terms getTerms()
    {
        return this._terms;
    } //-- Terms getTerms() 

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
     * Sets the value of field 'MSG'.
     * 
     * @param MSG the value of field 'MSG'.
    **/
    public void setMSG(java.lang.String MSG)
    {
        this._MSG = MSG;
    } //-- void setMSG(java.lang.String) 

    /**
     * Sets the value of field 'rspCode'.
     * 
     * @param rspCode the value of field 'rspCode'.
    **/
    public void setRspCode(java.lang.String rspCode)
    {
        this._rspCode = rspCode;
    } //-- void setRspCode(java.lang.String) 

    /**
     * Sets the value of field 'terms'.
     * 
     * @param terms the value of field 'terms'.
    **/
    public void setTerms(Terms terms)
    {
        this._terms = terms;
    } //-- void setTerms(Terms) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0001.Response unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0001.Response) Unmarshaller.unmarshal(com.ai.mapp.model.HW0001.Response.class, reader);
    } //-- com.ai.mapp.model.HW0001.Response unmarshal(java.io.Reader) 

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
