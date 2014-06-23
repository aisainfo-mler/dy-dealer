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
import java.io.Writer;
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.*;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * 
 * 
 * @version $Revision$ $Date$
**/
public class PhoneList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _phoneList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PhoneList() {
        super();
        _phoneList = new Vector();
    } //-- com.ai.mapp.model.HW0002.PhoneList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vPhone
    **/
    public void addPhone(Phone vPhone)
        throws java.lang.IndexOutOfBoundsException
    {
        _phoneList.addElement(vPhone);
    } //-- void addPhone(Phone) 

    /**
     * 
     * 
     * @param index
     * @param vPhone
    **/
    public void addPhone(int index, Phone vPhone)
        throws java.lang.IndexOutOfBoundsException
    {
        _phoneList.insertElementAt(vPhone, index);
    } //-- void addPhone(int, Phone) 

    /**
    **/
    public java.util.Enumeration enumeratePhone()
    {
        return _phoneList.elements();
    } //-- java.util.Enumeration enumeratePhone() 

    /**
     * 
     * 
     * @param index
    **/
    public Phone getPhone(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _phoneList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Phone) _phoneList.elementAt(index);
    } //-- Phone getPhone(int) 

    /**
    **/
    public Phone[] getPhone()
    {
        int size = _phoneList.size();
        Phone[] mArray = new Phone[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Phone) _phoneList.elementAt(index);
        }
        return mArray;
    } //-- Phone[] getPhone() 

    /**
    **/
    public int getPhoneCount()
    {
        return _phoneList.size();
    } //-- int getPhoneCount() 

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
    public void removeAllPhone()
    {
        _phoneList.removeAllElements();
    } //-- void removeAllPhone() 

    /**
     * 
     * 
     * @param index
    **/
    public Phone removePhone(int index)
    {
        java.lang.Object obj = _phoneList.elementAt(index);
        _phoneList.removeElementAt(index);
        return (Phone) obj;
    } //-- Phone removePhone(int) 

    /**
     * 
     * 
     * @param index
     * @param vPhone
    **/
    public void setPhone(int index, Phone vPhone)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _phoneList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _phoneList.setElementAt(vPhone, index);
    } //-- void setPhone(int, Phone) 

    /**
     * 
     * 
     * @param phoneArray
    **/
    public void setPhone(Phone[] phoneArray)
    {
        //-- copy array
        _phoneList.removeAllElements();
        for (int i = 0; i < phoneArray.length; i++) {
            _phoneList.addElement(phoneArray[i]);
        }
    } //-- void setPhone(Phone) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0002.PhoneList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0002.PhoneList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0002.PhoneList.class, reader);
    } //-- com.ai.mapp.model.HW0002.PhoneList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
