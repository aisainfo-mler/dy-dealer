/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0020;

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
public class GoodList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _goodList;


      //----------------/
     //- Constructors -/
    //----------------/

    public GoodList() {
        super();
        _goodList = new Vector();
    } //-- com.ai.mapp.model.HW0020.GoodList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vGood
    **/
    public void addGood(Good vGood)
        throws java.lang.IndexOutOfBoundsException
    {
        _goodList.addElement(vGood);
    } //-- void addGood(Good) 

    /**
     * 
     * 
     * @param index
     * @param vGood
    **/
    public void addGood(int index, Good vGood)
        throws java.lang.IndexOutOfBoundsException
    {
        _goodList.insertElementAt(vGood, index);
    } //-- void addGood(int, Good) 

    /**
    **/
    public java.util.Enumeration enumerateGood()
    {
        return _goodList.elements();
    } //-- java.util.Enumeration enumerateGood() 

    /**
     * 
     * 
     * @param index
    **/
    public Good getGood(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _goodList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Good) _goodList.elementAt(index);
    } //-- Good getGood(int) 

    /**
    **/
    public Good[] getGood()
    {
        int size = _goodList.size();
        Good[] mArray = new Good[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Good) _goodList.elementAt(index);
        }
        return mArray;
    } //-- Good[] getGood() 

    /**
    **/
    public int getGoodCount()
    {
        return _goodList.size();
    } //-- int getGoodCount() 

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
    public void removeAllGood()
    {
        _goodList.removeAllElements();
    } //-- void removeAllGood() 

    /**
     * 
     * 
     * @param index
    **/
    public Good removeGood(int index)
    {
        java.lang.Object obj = _goodList.elementAt(index);
        _goodList.removeElementAt(index);
        return (Good) obj;
    } //-- Good removeGood(int) 

    /**
     * 
     * 
     * @param index
     * @param vGood
    **/
    public void setGood(int index, Good vGood)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _goodList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _goodList.setElementAt(vGood, index);
    } //-- void setGood(int, Good) 

    /**
     * 
     * 
     * @param goodArray
    **/
    public void setGood(Good[] goodArray)
    {
        //-- copy array
        _goodList.removeAllElements();
        for (int i = 0; i < goodArray.length; i++) {
            _goodList.addElement(goodArray[i]);
        }
    } //-- void setGood(Good) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0020.GoodList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0020.GoodList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0020.GoodList.class, reader);
    } //-- com.ai.mapp.model.HW0020.GoodList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
