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

    private java.lang.String _mobileModel;

    private java.lang.String _recommend;

    private java.lang.String _start;

    private java.lang.String _size;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0002.Request()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'mobileModel'.
     * 
     * @return the value of field 'mobileModel'.
    **/
    public java.lang.String getMobileModel()
    {
        return this._mobileModel;
    } //-- java.lang.String getMobileModel() 

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
     * Returns the value of field 'size'.
     * 
     * @return the value of field 'size'.
    **/
    public java.lang.String getSize()
    {
        return this._size;
    } //-- java.lang.String getSize() 

    /**
     * Returns the value of field 'start'.
     * 
     * @return the value of field 'start'.
    **/
    public java.lang.String getStart()
    {
        return this._start;
    } //-- java.lang.String getStart() 

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
     * Sets the value of field 'mobileModel'.
     * 
     * @param mobileModel the value of field 'mobileModel'.
    **/
    public void setMobileModel(java.lang.String mobileModel)
    {
        this._mobileModel = mobileModel;
    } //-- void setMobileModel(java.lang.String) 

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
     * Sets the value of field 'size'.
     * 
     * @param size the value of field 'size'.
    **/
    public void setSize(java.lang.String size)
    {
        this._size = size;
    } //-- void setSize(java.lang.String) 

    /**
     * Sets the value of field 'start'.
     * 
     * @param start the value of field 'start'.
    **/
    public void setStart(java.lang.String start)
    {
        this._start = start;
    } //-- void setStart(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0002.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0002.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0002.Request.class, reader);
    } //-- com.ai.mapp.model.HW0002.Request unmarshal(java.io.Reader) 

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
