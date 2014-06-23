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
public class Charge implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _voiceFee;

    private java.lang.String _videoFee;

    private java.lang.String _flowFee;

    private java.lang.String _otherFee;


      //----------------/
     //- Constructors -/
    //----------------/

    public Charge() {
        super();
    } //-- com.ai.mapp.model.HW0004.Charge()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'flowFee'.
     * 
     * @return the value of field 'flowFee'.
    **/
    public java.lang.String getFlowFee()
    {
        return this._flowFee;
    } //-- java.lang.String getFlowFee() 

    /**
     * Returns the value of field 'otherFee'.
     * 
     * @return the value of field 'otherFee'.
    **/
    public java.lang.String getOtherFee()
    {
        return this._otherFee;
    } //-- java.lang.String getOtherFee() 

    /**
     * Returns the value of field 'videoFee'.
     * 
     * @return the value of field 'videoFee'.
    **/
    public java.lang.String getVideoFee()
    {
        return this._videoFee;
    } //-- java.lang.String getVideoFee() 

    /**
     * Returns the value of field 'voiceFee'.
     * 
     * @return the value of field 'voiceFee'.
    **/
    public java.lang.String getVoiceFee()
    {
        return this._voiceFee;
    } //-- java.lang.String getVoiceFee() 

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
     * Sets the value of field 'flowFee'.
     * 
     * @param flowFee the value of field 'flowFee'.
    **/
    public void setFlowFee(java.lang.String flowFee)
    {
        this._flowFee = flowFee;
    } //-- void setFlowFee(java.lang.String) 

    /**
     * Sets the value of field 'otherFee'.
     * 
     * @param otherFee the value of field 'otherFee'.
    **/
    public void setOtherFee(java.lang.String otherFee)
    {
        this._otherFee = otherFee;
    } //-- void setOtherFee(java.lang.String) 

    /**
     * Sets the value of field 'videoFee'.
     * 
     * @param videoFee the value of field 'videoFee'.
    **/
    public void setVideoFee(java.lang.String videoFee)
    {
        this._videoFee = videoFee;
    } //-- void setVideoFee(java.lang.String) 

    /**
     * Sets the value of field 'voiceFee'.
     * 
     * @param voiceFee the value of field 'voiceFee'.
    **/
    public void setVoiceFee(java.lang.String voiceFee)
    {
        this._voiceFee = voiceFee;
    } //-- void setVoiceFee(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0004.Charge unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0004.Charge) Unmarshaller.unmarshal(com.ai.mapp.model.HW0004.Charge.class, reader);
    } //-- com.ai.mapp.model.HW0004.Charge unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
