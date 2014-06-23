/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0003;

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
public class PhoneInfo implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _phoneId;

    private java.lang.String _model;

    private java.lang.String _ifInt;

    private java.lang.String _exterior;

    private java.lang.String _OS;

    private java.lang.String _netWork;

    private java.lang.String _trans;

    private java.lang.String _browser;

    private java.lang.String _memory;

    private java.lang.String _extraMemory;

    private java.lang.String _screen;

    private java.lang.String _isWideScreen;

    private java.lang.String _screenType;

    private java.lang.String _resolution;

    private java.lang.String _musicType;

    private java.lang.String _videoFormat;

    private java.lang.String _batterySpace;

    private java.lang.String _gps;

    private java.lang.String _blue;

    private java.lang.String _brand;

    private java.lang.String _instructions;


      //----------------/
     //- Constructors -/
    //----------------/

    public PhoneInfo() {
        super();
    } //-- com.ai.mapp.model.HW0003.PhoneInfo()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'batterySpace'.
     * 
     * @return the value of field 'batterySpace'.
    **/
    public java.lang.String getBatterySpace()
    {
        return this._batterySpace;
    } //-- java.lang.String getBatterySpace() 

    /**
     * Returns the value of field 'blue'.
     * 
     * @return the value of field 'blue'.
    **/
    public java.lang.String getBlue()
    {
        return this._blue;
    } //-- java.lang.String getBlue() 

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
     * Returns the value of field 'browser'.
     * 
     * @return the value of field 'browser'.
    **/
    public java.lang.String getBrowser()
    {
        return this._browser;
    } //-- java.lang.String getBrowser() 

    /**
     * Returns the value of field 'exterior'.
     * 
     * @return the value of field 'exterior'.
    **/
    public java.lang.String getExterior()
    {
        return this._exterior;
    } //-- java.lang.String getExterior() 

    /**
     * Returns the value of field 'extraMemory'.
     * 
     * @return the value of field 'extraMemory'.
    **/
    public java.lang.String getExtraMemory()
    {
        return this._extraMemory;
    } //-- java.lang.String getExtraMemory() 

    /**
     * Returns the value of field 'gps'.
     * 
     * @return the value of field 'gps'.
    **/
    public java.lang.String getGps()
    {
        return this._gps;
    } //-- java.lang.String getGps() 

    /**
     * Returns the value of field 'ifInt'.
     * 
     * @return the value of field 'ifInt'.
    **/
    public java.lang.String getIfInt()
    {
        return this._ifInt;
    } //-- java.lang.String getIfInt() 

    /**
     * Returns the value of field 'instructions'.
     * 
     * @return the value of field 'instructions'.
    **/
    public java.lang.String getInstructions()
    {
        return this._instructions;
    } //-- java.lang.String getInstructions() 

    /**
     * Returns the value of field 'isWideScreen'.
     * 
     * @return the value of field 'isWideScreen'.
    **/
    public java.lang.String getIsWideScreen()
    {
        return this._isWideScreen;
    } //-- java.lang.String getIsWideScreen() 

    /**
     * Returns the value of field 'memory'.
     * 
     * @return the value of field 'memory'.
    **/
    public java.lang.String getMemory()
    {
        return this._memory;
    } //-- java.lang.String getMemory() 

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
     * Returns the value of field 'musicType'.
     * 
     * @return the value of field 'musicType'.
    **/
    public java.lang.String getMusicType()
    {
        return this._musicType;
    } //-- java.lang.String getMusicType() 

    /**
     * Returns the value of field 'netWork'.
     * 
     * @return the value of field 'netWork'.
    **/
    public java.lang.String getNetWork()
    {
        return this._netWork;
    } //-- java.lang.String getNetWork() 

    /**
     * Returns the value of field 'OS'.
     * 
     * @return the value of field 'OS'.
    **/
    public java.lang.String getOS()
    {
        return this._OS;
    } //-- java.lang.String getOS() 

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
     * Returns the value of field 'resolution'.
     * 
     * @return the value of field 'resolution'.
    **/
    public java.lang.String getResolution()
    {
        return this._resolution;
    } //-- java.lang.String getResolution() 

    /**
     * Returns the value of field 'screen'.
     * 
     * @return the value of field 'screen'.
    **/
    public java.lang.String getScreen()
    {
        return this._screen;
    } //-- java.lang.String getScreen() 

    /**
     * Returns the value of field 'screenType'.
     * 
     * @return the value of field 'screenType'.
    **/
    public java.lang.String getScreenType()
    {
        return this._screenType;
    } //-- java.lang.String getScreenType() 

    /**
     * Returns the value of field 'trans'.
     * 
     * @return the value of field 'trans'.
    **/
    public java.lang.String getTrans()
    {
        return this._trans;
    } //-- java.lang.String getTrans() 

    /**
     * Returns the value of field 'videoFormat'.
     * 
     * @return the value of field 'videoFormat'.
    **/
    public java.lang.String getVideoFormat()
    {
        return this._videoFormat;
    } //-- java.lang.String getVideoFormat() 

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
     * Sets the value of field 'batterySpace'.
     * 
     * @param batterySpace the value of field 'batterySpace'.
    **/
    public void setBatterySpace(java.lang.String batterySpace)
    {
        this._batterySpace = batterySpace;
    } //-- void setBatterySpace(java.lang.String) 

    /**
     * Sets the value of field 'blue'.
     * 
     * @param blue the value of field 'blue'.
    **/
    public void setBlue(java.lang.String blue)
    {
        this._blue = blue;
    } //-- void setBlue(java.lang.String) 

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
     * Sets the value of field 'browser'.
     * 
     * @param browser the value of field 'browser'.
    **/
    public void setBrowser(java.lang.String browser)
    {
        this._browser = browser;
    } //-- void setBrowser(java.lang.String) 

    /**
     * Sets the value of field 'exterior'.
     * 
     * @param exterior the value of field 'exterior'.
    **/
    public void setExterior(java.lang.String exterior)
    {
        this._exterior = exterior;
    } //-- void setExterior(java.lang.String) 

    /**
     * Sets the value of field 'extraMemory'.
     * 
     * @param extraMemory the value of field 'extraMemory'.
    **/
    public void setExtraMemory(java.lang.String extraMemory)
    {
        this._extraMemory = extraMemory;
    } //-- void setExtraMemory(java.lang.String) 

    /**
     * Sets the value of field 'gps'.
     * 
     * @param gps the value of field 'gps'.
    **/
    public void setGps(java.lang.String gps)
    {
        this._gps = gps;
    } //-- void setGps(java.lang.String) 

    /**
     * Sets the value of field 'ifInt'.
     * 
     * @param ifInt the value of field 'ifInt'.
    **/
    public void setIfInt(java.lang.String ifInt)
    {
        this._ifInt = ifInt;
    } //-- void setIfInt(java.lang.String) 

    /**
     * Sets the value of field 'instructions'.
     * 
     * @param instructions the value of field 'instructions'.
    **/
    public void setInstructions(java.lang.String instructions)
    {
        this._instructions = instructions;
    } //-- void setInstructions(java.lang.String) 

    /**
     * Sets the value of field 'isWideScreen'.
     * 
     * @param isWideScreen the value of field 'isWideScreen'.
    **/
    public void setIsWideScreen(java.lang.String isWideScreen)
    {
        this._isWideScreen = isWideScreen;
    } //-- void setIsWideScreen(java.lang.String) 

    /**
     * Sets the value of field 'memory'.
     * 
     * @param memory the value of field 'memory'.
    **/
    public void setMemory(java.lang.String memory)
    {
        this._memory = memory;
    } //-- void setMemory(java.lang.String) 

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
     * Sets the value of field 'musicType'.
     * 
     * @param musicType the value of field 'musicType'.
    **/
    public void setMusicType(java.lang.String musicType)
    {
        this._musicType = musicType;
    } //-- void setMusicType(java.lang.String) 

    /**
     * Sets the value of field 'netWork'.
     * 
     * @param netWork the value of field 'netWork'.
    **/
    public void setNetWork(java.lang.String netWork)
    {
        this._netWork = netWork;
    } //-- void setNetWork(java.lang.String) 

    /**
     * Sets the value of field 'OS'.
     * 
     * @param OS the value of field 'OS'.
    **/
    public void setOS(java.lang.String OS)
    {
        this._OS = OS;
    } //-- void setOS(java.lang.String) 

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
     * Sets the value of field 'resolution'.
     * 
     * @param resolution the value of field 'resolution'.
    **/
    public void setResolution(java.lang.String resolution)
    {
        this._resolution = resolution;
    } //-- void setResolution(java.lang.String) 

    /**
     * Sets the value of field 'screen'.
     * 
     * @param screen the value of field 'screen'.
    **/
    public void setScreen(java.lang.String screen)
    {
        this._screen = screen;
    } //-- void setScreen(java.lang.String) 

    /**
     * Sets the value of field 'screenType'.
     * 
     * @param screenType the value of field 'screenType'.
    **/
    public void setScreenType(java.lang.String screenType)
    {
        this._screenType = screenType;
    } //-- void setScreenType(java.lang.String) 

    /**
     * Sets the value of field 'trans'.
     * 
     * @param trans the value of field 'trans'.
    **/
    public void setTrans(java.lang.String trans)
    {
        this._trans = trans;
    } //-- void setTrans(java.lang.String) 

    /**
     * Sets the value of field 'videoFormat'.
     * 
     * @param videoFormat the value of field 'videoFormat'.
    **/
    public void setVideoFormat(java.lang.String videoFormat)
    {
        this._videoFormat = videoFormat;
    } //-- void setVideoFormat(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0003.PhoneInfo unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0003.PhoneInfo) Unmarshaller.unmarshal(com.ai.mapp.model.HW0003.PhoneInfo.class, reader);
    } //-- com.ai.mapp.model.HW0003.PhoneInfo unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
