/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0023;

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
public class OrderList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _orderList;


      //----------------/
     //- Constructors -/
    //----------------/

    public OrderList() {
        super();
        _orderList = new Vector();
    } //-- com.ai.mapp.model.HW0023.OrderList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vOrder
    **/
    public void addOrder(Order vOrder)
        throws java.lang.IndexOutOfBoundsException
    {
        _orderList.addElement(vOrder);
    } //-- void addOrder(Order) 

    /**
     * 
     * 
     * @param index
     * @param vOrder
    **/
    public void addOrder(int index, Order vOrder)
        throws java.lang.IndexOutOfBoundsException
    {
        _orderList.insertElementAt(vOrder, index);
    } //-- void addOrder(int, Order) 

    /**
    **/
    public java.util.Enumeration enumerateOrder()
    {
        return _orderList.elements();
    } //-- java.util.Enumeration enumerateOrder() 

    /**
     * 
     * 
     * @param index
    **/
    public Order getOrder(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _orderList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Order) _orderList.elementAt(index);
    } //-- Order getOrder(int) 

    /**
    **/
    public Order[] getOrder()
    {
        int size = _orderList.size();
        Order[] mArray = new Order[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Order) _orderList.elementAt(index);
        }
        return mArray;
    } //-- Order[] getOrder() 

    /**
    **/
    public int getOrderCount()
    {
        return _orderList.size();
    } //-- int getOrderCount() 

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
    public void removeAllOrder()
    {
        _orderList.removeAllElements();
    } //-- void removeAllOrder() 

    /**
     * 
     * 
     * @param index
    **/
    public Order removeOrder(int index)
    {
        java.lang.Object obj = _orderList.elementAt(index);
        _orderList.removeElementAt(index);
        return (Order) obj;
    } //-- Order removeOrder(int) 

    /**
     * 
     * 
     * @param index
     * @param vOrder
    **/
    public void setOrder(int index, Order vOrder)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _orderList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _orderList.setElementAt(vOrder, index);
    } //-- void setOrder(int, Order) 

    /**
     * 
     * 
     * @param orderArray
    **/
    public void setOrder(Order[] orderArray)
    {
        //-- copy array
        _orderList.removeAllElements();
        for (int i = 0; i < orderArray.length; i++) {
            _orderList.addElement(orderArray[i]);
        }
    } //-- void setOrder(Order) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0023.OrderList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0023.OrderList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0023.OrderList.class, reader);
    } //-- com.ai.mapp.model.HW0023.OrderList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
