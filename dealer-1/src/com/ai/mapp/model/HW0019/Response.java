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
import java.util.Enumeration;
import java.util.Vector;
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

    private java.util.Vector _goodTypeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Response() {
        super();
        _goodTypeList = new Vector();
    } //-- com.ai.mapp.model.HW0019.Response()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vGoodType
    **/
    public void addGoodType(GoodType vGoodType)
        throws java.lang.IndexOutOfBoundsException
    {
        _goodTypeList.addElement(vGoodType);
    } //-- void addGoodType(GoodType) 

    /**
     * 
     * 
     * @param index
     * @param vGoodType
    **/
    public void addGoodType(int index, GoodType vGoodType)
        throws java.lang.IndexOutOfBoundsException
    {
        _goodTypeList.insertElementAt(vGoodType, index);
    } //-- void addGoodType(int, GoodType) 

    /**
    **/
    public java.util.Enumeration enumerateGoodType()
    {
        return _goodTypeList.elements();
    } //-- java.util.Enumeration enumerateGoodType() 

    /**
     * 
     * 
     * @param index
    **/
    public GoodType getGoodType(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _goodTypeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (GoodType) _goodTypeList.elementAt(index);
    } //-- GoodType getGoodType(int) 

    /**
    **/
    public GoodType[] getGoodType()
    {
        int size = _goodTypeList.size();
        GoodType[] mArray = new GoodType[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (GoodType) _goodTypeList.elementAt(index);
        }
        return mArray;
    } //-- GoodType[] getGoodType() 

    /**
    **/
    public int getGoodTypeCount()
    {
        return _goodTypeList.size();
    } //-- int getGoodTypeCount() 

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
    **/
    public void removeAllGoodType()
    {
        _goodTypeList.removeAllElements();
    } //-- void removeAllGoodType() 

    /**
     * 
     * 
     * @param index
    **/
    public GoodType removeGoodType(int index)
    {
        java.lang.Object obj = _goodTypeList.elementAt(index);
        _goodTypeList.removeElementAt(index);
        return (GoodType) obj;
    } //-- GoodType removeGoodType(int) 

    /**
     * 
     * 
     * @param index
     * @param vGoodType
    **/
    public void setGoodType(int index, GoodType vGoodType)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _goodTypeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _goodTypeList.setElementAt(vGoodType, index);
    } //-- void setGoodType(int, GoodType) 

    /**
     * 
     * 
     * @param goodTypeArray
    **/
    public void setGoodType(GoodType[] goodTypeArray)
    {
        //-- copy array
        _goodTypeList.removeAllElements();
        for (int i = 0; i < goodTypeArray.length; i++) {
            _goodTypeList.addElement(goodTypeArray[i]);
        }
    } //-- void setGoodType(GoodType) 

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
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0019.Response unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0019.Response) Unmarshaller.unmarshal(com.ai.mapp.model.HW0019.Response.class, reader);
    } //-- com.ai.mapp.model.HW0019.Response unmarshal(java.io.Reader) 

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
