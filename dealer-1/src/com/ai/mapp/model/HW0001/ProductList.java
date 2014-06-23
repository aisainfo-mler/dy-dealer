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
public class ProductList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _contractType;

    private java.util.Vector _productList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ProductList() {
        super();
        _productList = new Vector();
    } //-- com.ai.mapp.model.HW0001.ProductList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vProduct
    **/
    public void addProduct(Product vProduct)
        throws java.lang.IndexOutOfBoundsException
    {
        _productList.addElement(vProduct);
    } //-- void addProduct(Product) 

    /**
     * 
     * 
     * @param index
     * @param vProduct
    **/
    public void addProduct(int index, Product vProduct)
        throws java.lang.IndexOutOfBoundsException
    {
        _productList.insertElementAt(vProduct, index);
    } //-- void addProduct(int, Product) 

    /**
    **/
    public java.util.Enumeration enumerateProduct()
    {
        return _productList.elements();
    } //-- java.util.Enumeration enumerateProduct() 

    /**
     * Returns the value of field 'contractType'.
     * 
     * @return the value of field 'contractType'.
    **/
    public java.lang.String getContractType()
    {
        return this._contractType;
    } //-- java.lang.String getContractType() 

    /**
     * 
     * 
     * @param index
    **/
    public Product getProduct(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _productList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Product) _productList.elementAt(index);
    } //-- Product getProduct(int) 

    /**
    **/
    public Product[] getProduct()
    {
        int size = _productList.size();
        Product[] mArray = new Product[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Product) _productList.elementAt(index);
        }
        return mArray;
    } //-- Product[] getProduct() 

    /**
    **/
    public int getProductCount()
    {
        return _productList.size();
    } //-- int getProductCount() 

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
    public void removeAllProduct()
    {
        _productList.removeAllElements();
    } //-- void removeAllProduct() 

    /**
     * 
     * 
     * @param index
    **/
    public Product removeProduct(int index)
    {
        java.lang.Object obj = _productList.elementAt(index);
        _productList.removeElementAt(index);
        return (Product) obj;
    } //-- Product removeProduct(int) 

    /**
     * Sets the value of field 'contractType'.
     * 
     * @param contractType the value of field 'contractType'.
    **/
    public void setContractType(java.lang.String contractType)
    {
        this._contractType = contractType;
    } //-- void setContractType(java.lang.String) 

    /**
     * 
     * 
     * @param index
     * @param vProduct
    **/
    public void setProduct(int index, Product vProduct)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _productList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _productList.setElementAt(vProduct, index);
    } //-- void setProduct(int, Product) 

    /**
     * 
     * 
     * @param productArray
    **/
    public void setProduct(Product[] productArray)
    {
        //-- copy array
        _productList.removeAllElements();
        for (int i = 0; i < productArray.length; i++) {
            _productList.addElement(productArray[i]);
        }
    } //-- void setProduct(Product) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0001.ProductList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0001.ProductList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0001.ProductList.class, reader);
    } //-- com.ai.mapp.model.HW0001.ProductList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
