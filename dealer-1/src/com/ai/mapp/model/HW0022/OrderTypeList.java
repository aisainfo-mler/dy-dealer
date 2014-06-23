/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0022;

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
public class OrderTypeList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _orderTypeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public OrderTypeList() {
        super();
        _orderTypeList = new Vector();
    } //-- com.ai.mapp.model.HW0022.OrderTypeList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vOrderType
    **/
    public void addOrderType(OrderType vOrderType)
        throws java.lang.IndexOutOfBoundsException
    {
        _orderTypeList.addElement(vOrderType);
    } //-- void addOrderType(OrderType) 

    /**
     * 
     * 
     * @param index
     * @param vOrderType
    **/
    public void addOrderType(int index, OrderType vOrderType)
        throws java.lang.IndexOutOfBoundsException
    {
        _orderTypeList.insertElementAt(vOrderType, index);
    } //-- void addOrderType(int, OrderType) 

    /**
    **/
    public java.util.Enumeration enumerateOrderType()
    {
        return _orderTypeList.elements();
    } //-- java.util.Enumeration enumerateOrderType() 

    /**
     * 
     * 
     * @param index
    **/
    public OrderType getOrderType(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _orderTypeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (OrderType) _orderTypeList.elementAt(index);
    } //-- OrderType getOrderType(int) 

    /**
    **/
    public OrderType[] getOrderType()
    {
        int size = _orderTypeList.size();
        OrderType[] mArray = new OrderType[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (OrderType) _orderTypeList.elementAt(index);
        }
        return mArray;
    } //-- OrderType[] getOrderType() 

    /**
    **/
    public int getOrderTypeCount()
    {
        return _orderTypeList.size();
    } //-- int getOrderTypeCount() 

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
    public void removeAllOrderType()
    {
        _orderTypeList.removeAllElements();
    } //-- void removeAllOrderType() 

    /**
     * 
     * 
     * @param index
    **/
    public OrderType removeOrderType(int index)
    {
        java.lang.Object obj = _orderTypeList.elementAt(index);
        _orderTypeList.removeElementAt(index);
        return (OrderType) obj;
    } //-- OrderType removeOrderType(int) 

    /**
     * 
     * 
     * @param index
     * @param vOrderType
    **/
    public void setOrderType(int index, OrderType vOrderType)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _orderTypeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _orderTypeList.setElementAt(vOrderType, index);
    } //-- void setOrderType(int, OrderType) 

    /**
     * 
     * 
     * @param orderTypeArray
    **/
    public void setOrderType(OrderType[] orderTypeArray)
    {
        //-- copy array
        _orderTypeList.removeAllElements();
        for (int i = 0; i < orderTypeArray.length; i++) {
            _orderTypeList.addElement(orderTypeArray[i]);
        }
    } //-- void setOrderType(OrderType) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0022.OrderTypeList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0022.OrderTypeList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0022.OrderTypeList.class, reader);
    } //-- com.ai.mapp.model.HW0022.OrderTypeList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
