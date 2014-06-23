/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0006;

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

    private java.lang.String _userCode;

    private java.lang.String _pwd;

    private java.lang.String _userTitle;

    private java.lang.String _firstName;

    private java.lang.String _lastName;

    private java.lang.String _gender;

    private java.lang.String _birthDay;

    private java.lang.String _email;

    private java.lang.String _contractPhone;

    private java.lang.String _postCode;

    private java.lang.String _mobileNo;

    private java.lang.String _address;

    private java.lang.String _state;

    private java.lang.String _city;

    private java.lang.String _street;

    private java.lang.String _idCardType;

    private java.lang.String _idCardNo;

    private java.lang.String _image;


      //----------------/
     //- Constructors -/
    //----------------/

    public Request() {
        super();
    } //-- com.ai.mapp.model.HW0006.Request()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'address'.
     * 
     * @return the value of field 'address'.
    **/
    public java.lang.String getAddress()
    {
        return this._address;
    } //-- java.lang.String getAddress() 

    /**
     * Returns the value of field 'birthDay'.
     * 
     * @return the value of field 'birthDay'.
    **/
    public java.lang.String getBirthDay()
    {
        return this._birthDay;
    } //-- java.lang.String getBirthDay() 

    /**
     * Returns the value of field 'city'.
     * 
     * @return the value of field 'city'.
    **/
    public java.lang.String getCity()
    {
        return this._city;
    } //-- java.lang.String getCity() 

    /**
     * Returns the value of field 'contractPhone'.
     * 
     * @return the value of field 'contractPhone'.
    **/
    public java.lang.String getContractPhone()
    {
        return this._contractPhone;
    } //-- java.lang.String getContractPhone() 

    /**
     * Returns the value of field 'email'.
     * 
     * @return the value of field 'email'.
    **/
    public java.lang.String getEmail()
    {
        return this._email;
    } //-- java.lang.String getEmail() 

    /**
     * Returns the value of field 'firstName'.
     * 
     * @return the value of field 'firstName'.
    **/
    public java.lang.String getFirstName()
    {
        return this._firstName;
    } //-- java.lang.String getFirstName() 

    /**
     * Returns the value of field 'gender'.
     * 
     * @return the value of field 'gender'.
    **/
    public java.lang.String getGender()
    {
        return this._gender;
    } //-- java.lang.String getGender() 

    /**
     * Returns the value of field 'idCardNo'.
     * 
     * @return the value of field 'idCardNo'.
    **/
    public java.lang.String getIdCardNo()
    {
        return this._idCardNo;
    } //-- java.lang.String getIdCardNo() 

    /**
     * Returns the value of field 'idCardType'.
     * 
     * @return the value of field 'idCardType'.
    **/
    public java.lang.String getIdCardType()
    {
        return this._idCardType;
    } //-- java.lang.String getIdCardType() 

    /**
     * Returns the value of field 'image'.
     * 
     * @return the value of field 'image'.
    **/
    public java.lang.String getImage()
    {
        return this._image;
    } //-- java.lang.String getImage() 

    /**
     * Returns the value of field 'lastName'.
     * 
     * @return the value of field 'lastName'.
    **/
    public java.lang.String getLastName()
    {
        return this._lastName;
    } //-- java.lang.String getLastName() 

    /**
     * Returns the value of field 'mobileNo'.
     * 
     * @return the value of field 'mobileNo'.
    **/
    public java.lang.String getMobileNo()
    {
        return this._mobileNo;
    } //-- java.lang.String getMobileNo() 

    /**
     * Returns the value of field 'postCode'.
     * 
     * @return the value of field 'postCode'.
    **/
    public java.lang.String getPostCode()
    {
        return this._postCode;
    } //-- java.lang.String getPostCode() 

    /**
     * Returns the value of field 'pwd'.
     * 
     * @return the value of field 'pwd'.
    **/
    public java.lang.String getPwd()
    {
        return this._pwd;
    } //-- java.lang.String getPwd() 

    /**
     * Returns the value of field 'state'.
     * 
     * @return the value of field 'state'.
    **/
    public java.lang.String getState()
    {
        return this._state;
    } //-- java.lang.String getState() 

    /**
     * Returns the value of field 'street'.
     * 
     * @return the value of field 'street'.
    **/
    public java.lang.String getStreet()
    {
        return this._street;
    } //-- java.lang.String getStreet() 

    /**
     * Returns the value of field 'userCode'.
     * 
     * @return the value of field 'userCode'.
    **/
    public java.lang.String getUserCode()
    {
        return this._userCode;
    } //-- java.lang.String getUserCode() 

    /**
     * Returns the value of field 'userTitle'.
     * 
     * @return the value of field 'userTitle'.
    **/
    public java.lang.String getUserTitle()
    {
        return this._userTitle;
    } //-- java.lang.String getUserTitle() 

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
     * Sets the value of field 'address'.
     * 
     * @param address the value of field 'address'.
    **/
    public void setAddress(java.lang.String address)
    {
        this._address = address;
    } //-- void setAddress(java.lang.String) 

    /**
     * Sets the value of field 'birthDay'.
     * 
     * @param birthDay the value of field 'birthDay'.
    **/
    public void setBirthDay(java.lang.String birthDay)
    {
        this._birthDay = birthDay;
    } //-- void setBirthDay(java.lang.String) 

    /**
     * Sets the value of field 'city'.
     * 
     * @param city the value of field 'city'.
    **/
    public void setCity(java.lang.String city)
    {
        this._city = city;
    } //-- void setCity(java.lang.String) 

    /**
     * Sets the value of field 'contractPhone'.
     * 
     * @param contractPhone the value of field 'contractPhone'.
    **/
    public void setContractPhone(java.lang.String contractPhone)
    {
        this._contractPhone = contractPhone;
    } //-- void setContractPhone(java.lang.String) 

    /**
     * Sets the value of field 'email'.
     * 
     * @param email the value of field 'email'.
    **/
    public void setEmail(java.lang.String email)
    {
        this._email = email;
    } //-- void setEmail(java.lang.String) 

    /**
     * Sets the value of field 'firstName'.
     * 
     * @param firstName the value of field 'firstName'.
    **/
    public void setFirstName(java.lang.String firstName)
    {
        this._firstName = firstName;
    } //-- void setFirstName(java.lang.String) 

    /**
     * Sets the value of field 'gender'.
     * 
     * @param gender the value of field 'gender'.
    **/
    public void setGender(java.lang.String gender)
    {
        this._gender = gender;
    } //-- void setGender(java.lang.String) 

    /**
     * Sets the value of field 'idCardNo'.
     * 
     * @param idCardNo the value of field 'idCardNo'.
    **/
    public void setIdCardNo(java.lang.String idCardNo)
    {
        this._idCardNo = idCardNo;
    } //-- void setIdCardNo(java.lang.String) 

    /**
     * Sets the value of field 'idCardType'.
     * 
     * @param idCardType the value of field 'idCardType'.
    **/
    public void setIdCardType(java.lang.String idCardType)
    {
        this._idCardType = idCardType;
    } //-- void setIdCardType(java.lang.String) 

    /**
     * Sets the value of field 'image'.
     * 
     * @param image the value of field 'image'.
    **/
    public void setImage(java.lang.String image)
    {
        this._image = image;
    } //-- void setImage(java.lang.String) 

    /**
     * Sets the value of field 'lastName'.
     * 
     * @param lastName the value of field 'lastName'.
    **/
    public void setLastName(java.lang.String lastName)
    {
        this._lastName = lastName;
    } //-- void setLastName(java.lang.String) 

    /**
     * Sets the value of field 'mobileNo'.
     * 
     * @param mobileNo the value of field 'mobileNo'.
    **/
    public void setMobileNo(java.lang.String mobileNo)
    {
        this._mobileNo = mobileNo;
    } //-- void setMobileNo(java.lang.String) 

    /**
     * Sets the value of field 'postCode'.
     * 
     * @param postCode the value of field 'postCode'.
    **/
    public void setPostCode(java.lang.String postCode)
    {
        this._postCode = postCode;
    } //-- void setPostCode(java.lang.String) 

    /**
     * Sets the value of field 'pwd'.
     * 
     * @param pwd the value of field 'pwd'.
    **/
    public void setPwd(java.lang.String pwd)
    {
        this._pwd = pwd;
    } //-- void setPwd(java.lang.String) 

    /**
     * Sets the value of field 'state'.
     * 
     * @param state the value of field 'state'.
    **/
    public void setState(java.lang.String state)
    {
        this._state = state;
    } //-- void setState(java.lang.String) 

    /**
     * Sets the value of field 'street'.
     * 
     * @param street the value of field 'street'.
    **/
    public void setStreet(java.lang.String street)
    {
        this._street = street;
    } //-- void setStreet(java.lang.String) 

    /**
     * Sets the value of field 'userCode'.
     * 
     * @param userCode the value of field 'userCode'.
    **/
    public void setUserCode(java.lang.String userCode)
    {
        this._userCode = userCode;
    } //-- void setUserCode(java.lang.String) 

    /**
     * Sets the value of field 'userTitle'.
     * 
     * @param userTitle the value of field 'userTitle'.
    **/
    public void setUserTitle(java.lang.String userTitle)
    {
        this._userTitle = userTitle;
    } //-- void setUserTitle(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0006.Request unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0006.Request) Unmarshaller.unmarshal(com.ai.mapp.model.HW0006.Request.class, reader);
    } //-- com.ai.mapp.model.HW0006.Request unmarshal(java.io.Reader) 

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
