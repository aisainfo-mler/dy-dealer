/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id: Response.java,v 1.3 2012/05/15 03:25:55 luyang2 Exp $
 */

package com.ai.mapp.model.HW0008;

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
 * @version $Revision: 1.3 $ $Date: 2012/05/15 03:25:55 $
**/
public class Response implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _rspCode;

    private MdnList _mdnList;

    private java.lang.String _nextPageNum;

    private java.lang.String _MSG;


      //----------------/
     //- Constructors -/
    //----------------/

    public Response() {
        super();
    } //-- com.ai.mapp.model.Z10004.Response()


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
     * Returns the value of field 'mdnList'.
     * 
     * @return the value of field 'mdnList'.
    **/
    public MdnList getMdnList()
    {
        return this._mdnList;
    } //-- MdnList getMdnList() 

    /**
     * Returns the value of field 'nextPageNum'.
     * 
     * @return the value of field 'nextPageNum'.
    **/
    public java.lang.String getNextPageNum()
    {
        return this._nextPageNum;
    } //-- java.lang.String getNextPageNum() 

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
     * Sets the value of field 'mdnList'.
     * 
     * @param mdnList the value of field 'mdnList'.
    **/
    public void setMdnList(MdnList mdnList)
    {
        this._mdnList = mdnList;
    } //-- void setMdnList(MdnList) 

    /**
     * Sets the value of field 'nextPageNum'.
     * 
     * @param nextPageNum the value of field 'nextPageNum'.
    **/
    public void setNextPageNum(java.lang.String nextPageNum)
    {
        this._nextPageNum = nextPageNum;
    } //-- void setNextPageNum(java.lang.String) 

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
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0008.Response unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0008.Response) Unmarshaller.unmarshal(com.ai.mapp.model.HW0008.Response.class, reader);
    } //-- com.ai.mapp.model.Z10004.Response unmarshal(java.io.Reader) 

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
