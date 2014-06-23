/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0004;

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
public class Item implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private Include _include;

    private Charge _charge;

    private Present _present;


      //----------------/
     //- Constructors -/
    //----------------/

    public Item() {
        super();
    } //-- com.ai.mapp.model.HW0004.Item()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'charge'.
     * 
     * @return the value of field 'charge'.
    **/
    public Charge getCharge()
    {
        return this._charge;
    } //-- Charge getCharge() 

    /**
     * Returns the value of field 'include'.
     * 
     * @return the value of field 'include'.
    **/
    public Include getInclude()
    {
        return this._include;
    } //-- Include getInclude() 

    /**
     * Returns the value of field 'present'.
     * 
     * @return the value of field 'present'.
    **/
    public Present getPresent()
    {
        return this._present;
    } //-- Present getPresent() 

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
     * Sets the value of field 'charge'.
     * 
     * @param charge the value of field 'charge'.
    **/
    public void setCharge(Charge charge)
    {
        this._charge = charge;
    } //-- void setCharge(Charge) 

    /**
     * Sets the value of field 'include'.
     * 
     * @param include the value of field 'include'.
    **/
    public void setInclude(Include include)
    {
        this._include = include;
    } //-- void setInclude(Include) 

    /**
     * Sets the value of field 'present'.
     * 
     * @param present the value of field 'present'.
    **/
    public void setPresent(Present present)
    {
        this._present = present;
    } //-- void setPresent(Present) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0004.Item unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0004.Item) Unmarshaller.unmarshal(com.ai.mapp.model.HW0004.Item.class, reader);
    } //-- com.ai.mapp.model.HW0004.Item unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
