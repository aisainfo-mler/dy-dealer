/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id: Request.java,v 1.1 2012/03/16 09:42:37 luyang2 Exp $
 */

package com.ai.mapp.model.HW0005;

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
 * @version $Revision: 1.1 $ $Date: 2012/03/16 09:42:37 $
**/
public class Request implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _userName;

    private java.lang.String _passWd;

    private java.lang.String _IMSI;

    private java.lang.String _IMEI;

    private java.lang.String _clientVersion;

    private java.lang.String _hardwareBrand;

    private java.lang.String _hardwareModel;

    private java.lang.String _OS;

    private java.lang.String _from;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.Z10001.Request()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'clientVersion'.
     * 
     * @return the value of field 'clientVersion'.
    **/
    public java.lang.String getClientVersion()
    {
        return this._clientVersion;
    } //-- java.lang.String getClientVersion() 

    /**
     * Returns the value of field 'from'.
     * 
     * @return the value of field 'from'.
    **/
    public java.lang.String getFrom()
    {
        return this._from;
    } //-- java.lang.String getFrom() 

    /**
     * Returns the value of field 'hardwareBrand'.
     * 
     * @return the value of field 'hardwareBrand'.
    **/
    public java.lang.String getHardwareBrand()
    {
        return this._hardwareBrand;
    } //-- java.lang.String getHardwareBrand() 

    /**
     * Returns the value of field 'hardwareModel'.
     * 
     * @return the value of field 'hardwareModel'.
    **/
    public java.lang.String getHardwareModel()
    {
        return this._hardwareModel;
    } //-- java.lang.String getHardwareModel() 

    /**
     * Returns the value of field 'IMEI'.
     * 
     * @return the value of field 'IMEI'.
    **/
    public java.lang.String getIMEI()
    {
        return this._IMEI;
    } //-- java.lang.String getIMEI() 

    /**
     * Returns the value of field 'IMSI'.
     * 
     * @return the value of field 'IMSI'.
    **/
    public java.lang.String getIMSI()
    {
        return this._IMSI;
    } //-- java.lang.String getIMSI() 

    /**
     * Returns the value of field 'OS'.
     * 
     * @return the value of field 'OS'.
    **/
    public java.lang.String getOS()
    {
        return this._OS;
    } //-- java.lang.String getOS() 

    /**
     * Returns the value of field 'passWd'.
     * 
     * @return the value of field 'passWd'.
    **/
    public java.lang.String getPassWd()
    {
        return this._passWd;
    } //-- java.lang.String getPassWd() 

    /**
     * Returns the value of field 'userName'.
     * 
     * @return the value of field 'userName'.
    **/
    public java.lang.String getUserName()
    {
        return this._userName;
    } //-- java.lang.String getUserName() 

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
     * Sets the value of field 'clientVersion'.
     * 
     * @param clientVersion the value of field 'clientVersion'.
    **/
    public void setClientVersion(java.lang.String clientVersion)
    {
        this._clientVersion = clientVersion;
    } //-- void setClientVersion(java.lang.String) 

    /**
     * Sets the value of field 'from'.
     * 
     * @param from the value of field 'from'.
    **/
    public void setFrom(java.lang.String from)
    {
        this._from = from;
    } //-- void setFrom(java.lang.String) 

    /**
     * Sets the value of field 'hardwareBrand'.
     * 
     * @param hardwareBrand the value of field 'hardwareBrand'.
    **/
    public void setHardwareBrand(java.lang.String hardwareBrand)
    {
        this._hardwareBrand = hardwareBrand;
    } //-- void setHardwareBrand(java.lang.String) 

    /**
     * Sets the value of field 'hardwareModel'.
     * 
     * @param hardwareModel the value of field 'hardwareModel'.
    **/
    public void setHardwareModel(java.lang.String hardwareModel)
    {
        this._hardwareModel = hardwareModel;
    } //-- void setHardwareModel(java.lang.String) 

    /**
     * Sets the value of field 'IMEI'.
     * 
     * @param IMEI the value of field 'IMEI'.
    **/
    public void setIMEI(java.lang.String IMEI)
    {
        this._IMEI = IMEI;
    } //-- void setIMEI(java.lang.String) 

    /**
     * Sets the value of field 'IMSI'.
     * 
     * @param IMSI the value of field 'IMSI'.
    **/
    public void setIMSI(java.lang.String IMSI)
    {
        this._IMSI = IMSI;
    } //-- void setIMSI(java.lang.String) 

    /**
     * Sets the value of field 'OS'.
     * 
     * @param OS the value of field 'OS'.
    **/
    public void setOS(java.lang.String OS)
    {
        this._OS = OS;
    } //-- void setOS(java.lang.String) 

    /**
     * Sets the value of field 'passWd'.
     * 
     * @param passWd the value of field 'passWd'.
    **/
    public void setPassWd(java.lang.String passWd)
    {
        this._passWd = passWd;
    } //-- void setPassWd(java.lang.String) 

    /**
     * Sets the value of field 'userName'.
     * 
     * @param userName the value of field 'userName'.
    **/
    public void setUserName(java.lang.String userName)
    {
        this._userName = userName;
    } //-- void setUserName(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0005.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0005.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0005.Request.class, reader);
    } //-- com.ai.mapp.model.Z10001.Request unmarshal(java.io.Reader) 

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
