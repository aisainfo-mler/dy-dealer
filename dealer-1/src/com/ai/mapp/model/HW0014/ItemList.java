/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0014;

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
public class ItemList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _itemList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ItemList() {
        super();
        _itemList = new Vector();
    } //-- com.ai.mapp.model.HW0014.ItemList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vItem
    **/
    public void addItem(Item vItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _itemList.addElement(vItem);
    } //-- void addItem(Item) 

    /**
     * 
     * 
     * @param index
     * @param vItem
    **/
    public void addItem(int index, Item vItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _itemList.insertElementAt(vItem, index);
    } //-- void addItem(int, Item) 

    /**
    **/
    public java.util.Enumeration enumerateItem()
    {
        return _itemList.elements();
    } //-- java.util.Enumeration enumerateItem() 

    /**
     * 
     * 
     * @param index
    **/
    public Item getItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _itemList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Item) _itemList.elementAt(index);
    } //-- Item getItem(int) 

    /**
    **/
    public Item[] getItem()
    {
        int size = _itemList.size();
        Item[] mArray = new Item[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Item) _itemList.elementAt(index);
        }
        return mArray;
    } //-- Item[] getItem() 

    /**
    **/
    public int getItemCount()
    {
        return _itemList.size();
    } //-- int getItemCount() 

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
    public void removeAllItem()
    {
        _itemList.removeAllElements();
    } //-- void removeAllItem() 

    /**
     * 
     * 
     * @param index
    **/
    public Item removeItem(int index)
    {
        java.lang.Object obj = _itemList.elementAt(index);
        _itemList.removeElementAt(index);
        return (Item) obj;
    } //-- Item removeItem(int) 

    /**
     * 
     * 
     * @param index
     * @param vItem
    **/
    public void setItem(int index, Item vItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _itemList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _itemList.setElementAt(vItem, index);
    } //-- void setItem(int, Item) 

    /**
     * 
     * 
     * @param itemArray
    **/
    public void setItem(Item[] itemArray)
    {
        //-- copy array
        _itemList.removeAllElements();
        for (int i = 0; i < itemArray.length; i++) {
            _itemList.addElement(itemArray[i]);
        }
    } //-- void setItem(Item) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0014.ItemList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0014.ItemList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0014.ItemList.class, reader);
    } //-- com.ai.mapp.model.HW0014.ItemList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
