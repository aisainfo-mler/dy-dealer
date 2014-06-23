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
public class StateList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _stateList;


      //----------------/
     //- Constructors -/
    //----------------/

    public StateList() {
        super();
        _stateList = new Vector();
    } //-- com.ai.mapp.model.HW0024.StateList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vState
    **/
    public void addState(State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        _stateList.addElement(vState);
    } //-- void addState(State) 

    /**
     * 
     * 
     * @param index
     * @param vState
    **/
    public void addState(int index, State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        _stateList.insertElementAt(vState, index);
    } //-- void addState(int, State) 

    /**
    **/
    public java.util.Enumeration enumerateState()
    {
        return _stateList.elements();
    } //-- java.util.Enumeration enumerateState() 

    /**
     * 
     * 
     * @param index
    **/
    public State getState(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _stateList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (State) _stateList.elementAt(index);
    } //-- State getState(int) 

    /**
    **/
    public State[] getState()
    {
        int size = _stateList.size();
        State[] mArray = new State[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (State) _stateList.elementAt(index);
        }
        return mArray;
    } //-- State[] getState() 

    /**
    **/
    public int getStateCount()
    {
        return _stateList.size();
    } //-- int getStateCount() 

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
    public void removeAllState()
    {
        _stateList.removeAllElements();
    } //-- void removeAllState() 

    /**
     * 
     * 
     * @param index
    **/
    public State removeState(int index)
    {
        java.lang.Object obj = _stateList.elementAt(index);
        _stateList.removeElementAt(index);
        return (State) obj;
    } //-- State removeState(int) 

    /**
     * 
     * 
     * @param index
     * @param vState
    **/
    public void setState(int index, State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _stateList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _stateList.setElementAt(vState, index);
    } //-- void setState(int, State) 

    /**
     * 
     * 
     * @param stateArray
    **/
    public void setState(State[] stateArray)
    {
        //-- copy array
        _stateList.removeAllElements();
        for (int i = 0; i < stateArray.length; i++) {
            _stateList.addElement(stateArray[i]);
        }
    } //-- void setState(State) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0024.StateList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0024.StateList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0024.StateList.class, reader);
    } //-- com.ai.mapp.model.HW0024.StateList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
