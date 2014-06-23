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
import org.exolab.castor.xml.*;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * 
 * 
 * @version $Revision$ $Date$
**/
public class Phone implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _phoneId;

    private java.lang.String _brand;

    private java.lang.String _model;

    private java.lang.String _listPic;

    private Product _product;


      //----------------/
     //- Constructors -/
    //----------------/

    public Phone() {
        super();
    } //-- com.ai.mapp.model.HW0002.Phone()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'brand'.
     * 
     * @return the value of field 'brand'.
    **/
    public java.lang.String getBrand()
    {
        return this._brand;
    } //-- java.lang.String getBrand() 

    /**
     * Returns the value of field 'listPic'.
     * 
     * @return the value of field 'listPic'.
    **/
    public java.lang.String getListPic()
    {
        return this._listPic;
    } //-- java.lang.String getListPic() 

    /**
     * Returns the value of field 'model'.
     * 
     * @return the value of field 'model'.
    **/
    public java.lang.String getModel()
    {
        return this._model;
    } //-- java.lang.String getModel() 

    /**
     * Returns the value of field 'phoneId'.
     * 
     * @return the value of field 'phoneId'.
    **/
    public java.lang.String getPhoneId()
    {
        return this._phoneId;
    } //-- java.lang.String getPhoneId() 

    /**
     * Returns the value of field 'product'.
     * 
     * @return the value of field 'product'.
    **/
    public Product getProduct()
    {
        return this._product;
    } //-- Product getProduct() 

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
     * Sets the value of field 'brand'.
     * 
     * @param brand the value of field 'brand'.
    **/
    public void setBrand(java.lang.String brand)
    {
        this._brand = brand;
    } //-- void setBrand(java.lang.String) 

    /**
     * Sets the value of field 'listPic'.
     * 
     * @param listPic the value of field 'listPic'.
    **/
    public void setListPic(java.lang.String listPic)
    {
        this._listPic = listPic;
    } //-- void setListPic(java.lang.String) 

    /**
     * Sets the value of field 'model'.
     * 
     * @param model the value of field 'model'.
    **/
    public void setModel(java.lang.String model)
    {
        this._model = model;
    } //-- void setModel(java.lang.String) 

    /**
     * Sets the value of field 'phoneId'.
     * 
     * @param phoneId the value of field 'phoneId'.
    **/
    public void setPhoneId(java.lang.String phoneId)
    {
        this._phoneId = phoneId;
    } //-- void setPhoneId(java.lang.String) 

    /**
     * Sets the value of field 'product'.
     * 
     * @param product the value of field 'product'.
    **/
    public void setProduct(Product product)
    {
        this._product = product;
    } //-- void setProduct(Product) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0002.Phone unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0002.Phone) Unmarshaller.unmarshal(com.ai.mapp.model.HW0002.Phone.class, reader);
    } //-- com.ai.mapp.model.HW0002.Phone unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
