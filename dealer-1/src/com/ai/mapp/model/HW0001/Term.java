/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0001;

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
public class Term implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _termValue;

    private java.util.Vector _productListList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Term() {
        super();
        _productListList = new Vector();
    } //-- com.ai.mapp.model.HW0001.Term()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vProductList
    **/
    public void addProductList(ProductList vProductList)
        throws java.lang.IndexOutOfBoundsException
    {
        _productListList.addElement(vProductList);
    } //-- void addProductList(ProductList) 

    /**
     * 
     * 
     * @param index
     * @param vProductList
    **/
    public void addProductList(int index, ProductList vProductList)
        throws java.lang.IndexOutOfBoundsException
    {
        _productListList.insertElementAt(vProductList, index);
    } //-- void addProductList(int, ProductList) 

    /**
    **/
    public java.util.Enumeration enumerateProductList()
    {
        return _productListList.elements();
    } //-- java.util.Enumeration enumerateProductList() 

    /**
     * 
     * 
     * @param index
    **/
    public ProductList getProductList(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _productListList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ProductList) _productListList.elementAt(index);
    } //-- ProductList getProductList(int) 

    /**
    **/
    public ProductList[] getProductList()
    {
        int size = _productListList.size();
        ProductList[] mArray = new ProductList[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ProductList) _productListList.elementAt(index);
        }
        return mArray;
    } //-- ProductList[] getProductList() 

    /**
    **/
    public int getProductListCount()
    {
        return _productListList.size();
    } //-- int getProductListCount() 

    /**
     * Returns the value of field 'termValue'.
     * 
     * @return the value of field 'termValue'.
    **/
    public java.lang.String getTermValue()
    {
        return this._termValue;
    } //-- java.lang.String getTermValue() 

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
    public void removeAllProductList()
    {
        _productListList.removeAllElements();
    } //-- void removeAllProductList() 

    /**
     * 
     * 
     * @param index
    **/
    public ProductList removeProductList(int index)
    {
        java.lang.Object obj = _productListList.elementAt(index);
        _productListList.removeElementAt(index);
        return (ProductList) obj;
    } //-- ProductList removeProductList(int) 

    /**
     * 
     * 
     * @param index
     * @param vProductList
    **/
    public void setProductList(int index, ProductList vProductList)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _productListList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _productListList.setElementAt(vProductList, index);
    } //-- void setProductList(int, ProductList) 

    /**
     * 
     * 
     * @param productListArray
    **/
    public void setProductList(ProductList[] productListArray)
    {
        //-- copy array
        _productListList.removeAllElements();
        for (int i = 0; i < productListArray.length; i++) {
            _productListList.addElement(productListArray[i]);
        }
    } //-- void setProductList(ProductList) 

    /**
     * Sets the value of field 'termValue'.
     * 
     * @param termValue the value of field 'termValue'.
    **/
    public void setTermValue(java.lang.String termValue)
    {
        this._termValue = termValue;
    } //-- void setTermValue(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0001.Term unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0001.Term) Unmarshaller.unmarshal(com.ai.mapp.model.HW0001.Term.class, reader);
    } //-- com.ai.mapp.model.HW0001.Term unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
