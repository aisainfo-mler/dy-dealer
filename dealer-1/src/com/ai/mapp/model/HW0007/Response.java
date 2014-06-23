/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0007;

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

    private java.lang.String _name;

    private java.lang.String _firstName;

    private java.lang.String _lastName;

    private java.lang.String _gender;

    private java.lang.String _birthDay;

    private java.lang.String _contactPhone;

    private java.lang.String _email;

    private java.lang.String _phoneNumber;

    private java.lang.String _postCode;

    private java.lang.String _address;

    private java.lang.String _idCardNo;

    private java.lang.String _idCardType;

    private java.lang.String _status;

    private java.lang.String _creatTime;

    private java.lang.String _balance;

    private java.lang.String _amount;

    private ProductList _productList;

    private java.lang.String _MSG;


      //----------------/
     //- Constructors -/
    //----------------/

    public Response() {
        super();
    } //-- com.ai.mapp.model.HW0007.Response()


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
     * Returns the value of field 'amount'.
     * 
     * @return the value of field 'amount'.
    **/
    public java.lang.String getAmount()
    {
        return this._amount;
    } //-- java.lang.String getAmount() 

    /**
     * Returns the value of field 'balance'.
     * 
     * @return the value of field 'balance'.
    **/
    public java.lang.String getBalance()
    {
        return this._balance;
    } //-- java.lang.String getBalance() 

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
     * Returns the value of field 'contactPhone'.
     * 
     * @return the value of field 'contactPhone'.
    **/
    public java.lang.String getContactPhone()
    {
        return this._contactPhone;
    } //-- java.lang.String getContactPhone() 

    /**
     * Returns the value of field 'creatTime'.
     * 
     * @return the value of field 'creatTime'.
    **/
    public java.lang.String getCreatTime()
    {
        return this._creatTime;
    } //-- java.lang.String getCreatTime() 

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
     * Returns the value of field 'lastName'.
     * 
     * @return the value of field 'lastName'.
    **/
    public java.lang.String getLastName()
    {
        return this._lastName;
    } //-- java.lang.String getLastName() 

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
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'name'.
    **/
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'phoneNumber'.
     * 
     * @return the value of field 'phoneNumber'.
    **/
    public java.lang.String getPhoneNumber()
    {
        return this._phoneNumber;
    } //-- java.lang.String getPhoneNumber() 

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
     * Returns the value of field 'productList'.
     * 
     * @return the value of field 'productList'.
    **/
    public ProductList getProductList()
    {
        return this._productList;
    } //-- ProductList getProductList() 

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
     * Sets the value of field 'address'.
     * 
     * @param address the value of field 'address'.
    **/
    public void setAddress(java.lang.String address)
    {
        this._address = address;
    } //-- void setAddress(java.lang.String) 

    /**
     * Sets the value of field 'amount'.
     * 
     * @param amount the value of field 'amount'.
    **/
    public void setAmount(java.lang.String amount)
    {
        this._amount = amount;
    } //-- void setAmount(java.lang.String) 

    /**
     * Sets the value of field 'balance'.
     * 
     * @param balance the value of field 'balance'.
    **/
    public void setBalance(java.lang.String balance)
    {
        this._balance = balance;
    } //-- void setBalance(java.lang.String) 

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
     * Sets the value of field 'contactPhone'.
     * 
     * @param contactPhone the value of field 'contactPhone'.
    **/
    public void setContactPhone(java.lang.String contactPhone)
    {
        this._contactPhone = contactPhone;
    } //-- void setContactPhone(java.lang.String) 

    /**
     * Sets the value of field 'creatTime'.
     * 
     * @param creatTime the value of field 'creatTime'.
    **/
    public void setCreatTime(java.lang.String creatTime)
    {
        this._creatTime = creatTime;
    } //-- void setCreatTime(java.lang.String) 

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
     * Sets the value of field 'lastName'.
     * 
     * @param lastName the value of field 'lastName'.
    **/
    public void setLastName(java.lang.String lastName)
    {
        this._lastName = lastName;
    } //-- void setLastName(java.lang.String) 

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
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
    **/
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'phoneNumber'.
     * 
     * @param phoneNumber the value of field 'phoneNumber'.
    **/
    public void setPhoneNumber(java.lang.String phoneNumber)
    {
        this._phoneNumber = phoneNumber;
    } //-- void setPhoneNumber(java.lang.String) 

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
     * Sets the value of field 'productList'.
     * 
     * @param productList the value of field 'productList'.
    **/
    public void setProductList(ProductList productList)
    {
        this._productList = productList;
    } //-- void setProductList(ProductList) 

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
    public static com.ai.mapp.model.HW0007.Response unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0007.Response) Unmarshaller.unmarshal(com.ai.mapp.model.HW0007.Response.class, reader);
    } //-- com.ai.mapp.model.HW0007.Response unmarshal(java.io.Reader) 

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
