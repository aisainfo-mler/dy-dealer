/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0015;

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
public class Good implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _goodName;

    private java.lang.String _sale;

    private java.lang.String _unSale;

    private java.lang.String _all;


      //----------------/
     //- Constructors -/
    //----------------/

    public Good() {
        super();
    } //-- com.ai.mapp.model.HW0015.Good()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'all'.
     * 
     * @return the value of field 'all'.
    **/
    public java.lang.String getAll()
    {
        return this._all;
    } //-- java.lang.String getAll() 

    /**
     * Returns the value of field 'goodName'.
     * 
     * @return the value of field 'goodName'.
    **/
    public java.lang.String getGoodName()
    {
        return this._goodName;
    } //-- java.lang.String getGoodName() 

    /**
     * Returns the value of field 'sale'.
     * 
     * @return the value of field 'sale'.
    **/
    public java.lang.String getSale()
    {
        return this._sale;
    } //-- java.lang.String getSale() 

    /**
     * Returns the value of field 'unSale'.
     * 
     * @return the value of field 'unSale'.
    **/
    public java.lang.String getUnSale()
    {
        return this._unSale;
    } //-- java.lang.String getUnSale() 

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
     * Sets the value of field 'all'.
     * 
     * @param all the value of field 'all'.
    **/
    public void setAll(java.lang.String all)
    {
        this._all = all;
    } //-- void setAll(java.lang.String) 

    /**
     * Sets the value of field 'goodName'.
     * 
     * @param goodName the value of field 'goodName'.
    **/
    public void setGoodName(java.lang.String goodName)
    {
        this._goodName = goodName;
    } //-- void setGoodName(java.lang.String) 

    /**
     * Sets the value of field 'sale'.
     * 
     * @param sale the value of field 'sale'.
    **/
    public void setSale(java.lang.String sale)
    {
        this._sale = sale;
    } //-- void setSale(java.lang.String) 

    /**
     * Sets the value of field 'unSale'.
     * 
     * @param unSale the value of field 'unSale'.
    **/
    public void setUnSale(java.lang.String unSale)
    {
        this._unSale = unSale;
    } //-- void setUnSale(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0015.Good unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0015.Good) Unmarshaller.unmarshal(com.ai.mapp.model.HW0015.Good.class, reader);
    } //-- com.ai.mapp.model.HW0015.Good unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
