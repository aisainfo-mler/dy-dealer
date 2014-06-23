/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.mapp.model.HW0024;

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
public class CityList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _cityList;


      //----------------/
     //- Constructors -/
    //----------------/

    public CityList() {
        super();
        _cityList = new Vector();
    } //-- com.ai.mapp.model.HW0024.CityList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCity
    **/
    public void addCity(City vCity)
        throws java.lang.IndexOutOfBoundsException
    {
        _cityList.addElement(vCity);
    } //-- void addCity(City) 

    /**
     * 
     * 
     * @param index
     * @param vCity
    **/
    public void addCity(int index, City vCity)
        throws java.lang.IndexOutOfBoundsException
    {
        _cityList.insertElementAt(vCity, index);
    } //-- void addCity(int, City) 

    /**
    **/
    public java.util.Enumeration enumerateCity()
    {
        return _cityList.elements();
    } //-- java.util.Enumeration enumerateCity() 

    /**
     * 
     * 
     * @param index
    **/
    public City getCity(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _cityList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (City) _cityList.elementAt(index);
    } //-- City getCity(int) 

    /**
    **/
    public City[] getCity()
    {
        int size = _cityList.size();
        City[] mArray = new City[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (City) _cityList.elementAt(index);
        }
        return mArray;
    } //-- City[] getCity() 

    /**
    **/
    public int getCityCount()
    {
        return _cityList.size();
    } //-- int getCityCount() 

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
    public void removeAllCity()
    {
        _cityList.removeAllElements();
    } //-- void removeAllCity() 

    /**
     * 
     * 
     * @param index
    **/
    public City removeCity(int index)
    {
        java.lang.Object obj = _cityList.elementAt(index);
        _cityList.removeElementAt(index);
        return (City) obj;
    } //-- City removeCity(int) 

    /**
     * 
     * 
     * @param index
     * @param vCity
    **/
    public void setCity(int index, City vCity)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _cityList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _cityList.setElementAt(vCity, index);
    } //-- void setCity(int, City) 

    /**
     * 
     * 
     * @param cityArray
    **/
    public void setCity(City[] cityArray)
    {
        //-- copy array
        _cityList.removeAllElements();
        for (int i = 0; i < cityArray.length; i++) {
            _cityList.addElement(cityArray[i]);
        }
    } //-- void setCity(City) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0024.CityList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0024.CityList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0024.CityList.class, reader);
    } //-- com.ai.mapp.model.HW0024.CityList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
