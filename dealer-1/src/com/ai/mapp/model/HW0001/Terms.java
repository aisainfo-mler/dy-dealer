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
public class Terms implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _termList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Terms() {
        super();
        _termList = new Vector();
    } //-- com.ai.mapp.model.HW0001.Terms()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vTerm
    **/
    public void addTerm(Term vTerm)
        throws java.lang.IndexOutOfBoundsException
    {
        _termList.addElement(vTerm);
    } //-- void addTerm(Term) 

    /**
     * 
     * 
     * @param index
     * @param vTerm
    **/
    public void addTerm(int index, Term vTerm)
        throws java.lang.IndexOutOfBoundsException
    {
        _termList.insertElementAt(vTerm, index);
    } //-- void addTerm(int, Term) 

    /**
    **/
    public java.util.Enumeration enumerateTerm()
    {
        return _termList.elements();
    } //-- java.util.Enumeration enumerateTerm() 

    /**
     * 
     * 
     * @param index
    **/
    public Term getTerm(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _termList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Term) _termList.elementAt(index);
    } //-- Term getTerm(int) 

    /**
    **/
    public Term[] getTerm()
    {
        int size = _termList.size();
        Term[] mArray = new Term[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Term) _termList.elementAt(index);
        }
        return mArray;
    } //-- Term[] getTerm() 

    /**
    **/
    public int getTermCount()
    {
        return _termList.size();
    } //-- int getTermCount() 

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
    public void removeAllTerm()
    {
        _termList.removeAllElements();
    } //-- void removeAllTerm() 

    /**
     * 
     * 
     * @param index
    **/
    public Term removeTerm(int index)
    {
        java.lang.Object obj = _termList.elementAt(index);
        _termList.removeElementAt(index);
        return (Term) obj;
    } //-- Term removeTerm(int) 

    /**
     * 
     * 
     * @param index
     * @param vTerm
    **/
    public void setTerm(int index, Term vTerm)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _termList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _termList.setElementAt(vTerm, index);
    } //-- void setTerm(int, Term) 

    /**
     * 
     * 
     * @param termArray
    **/
    public void setTerm(Term[] termArray)
    {
        //-- copy array
        _termList.removeAllElements();
        for (int i = 0; i < termArray.length; i++) {
            _termList.addElement(termArray[i]);
        }
    } //-- void setTerm(Term) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0001.Terms unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0001.Terms) Unmarshaller.unmarshal(com.ai.mapp.model.HW0001.Terms.class, reader);
    } //-- com.ai.mapp.model.HW0001.Terms unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
