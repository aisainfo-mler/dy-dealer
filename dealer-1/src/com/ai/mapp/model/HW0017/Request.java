/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0017;

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

    private java.lang.String _goodId;

    private java.lang.String _goodType;

    private java.lang.String _status;

    private java.lang.String _startTime;

    private java.lang.String _endTime;

    private java.lang.String _start;

    private java.lang.String _size;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0017.Request()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'endTime'.
     * 
     * @return the value of field 'endTime'.
    **/
    public java.lang.String getEndTime()
    {
        return this._endTime;
    } //-- java.lang.String getEndTime() 

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
     * Returns the value of field 'orderCode'.
     * 
     * @return the value of field 'orderCode'.
    **/
    public java.lang.String getOrderCode()
    {
        return this._orderCode;
    } //-- java.lang.String getOrderCode() 

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
     * Returns the value of field 'startTime'.
     * 
     * @return the value of field 'startTime'.
    **/
    public java.lang.String getStartTime()
    {
        return this._startTime;
    } //-- java.lang.String getStartTime() 

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
     * Sets the value of field 'endTime'.
     * 
     * @param endTime the value of field 'endTime'.
    **/
    public void setEndTime(java.lang.String endTime)
    {
        this._endTime = endTime;
    } //-- void setEndTime(java.lang.String) 

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
     * Sets the value of field 'orderCode'.
     * 
     * @param orderCode the value of field 'orderCode'.
    **/
    public void setOrderCode(java.lang.String orderCode)
    {
        this._orderCode = orderCode;
    } //-- void setOrderCode(java.lang.String) 

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
     * Sets the value of field 'startTime'.
     * 
     * @param startTime the value of field 'startTime'.
    **/
    public void setStartTime(java.lang.String startTime)
    {
        this._startTime = startTime;
    } //-- void setStartTime(java.lang.String) 

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
    public static com.ai.mapp.model.HW0017.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0017.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0017.Request.class, reader);
    } //-- com.ai.mapp.model.HW0017.Request unmarshal(java.io.Reader) 

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
