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
public class ImgInfoList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.util.Vector _imageList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ImgInfoList() {
        super();
        _imageList = new Vector();
    } //-- com.ai.mapp.model.HW0003.ImgInfoList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vImage
    **/
    public void addImage(Image vImage)
        throws java.lang.IndexOutOfBoundsException
    {
        _imageList.addElement(vImage);
    } //-- void addImage(Image) 

    /**
     * 
     * 
     * @param index
     * @param vImage
    **/
    public void addImage(int index, Image vImage)
        throws java.lang.IndexOutOfBoundsException
    {
        _imageList.insertElementAt(vImage, index);
    } //-- void addImage(int, Image) 

    /**
    **/
    public java.util.Enumeration enumerateImage()
    {
        return _imageList.elements();
    } //-- java.util.Enumeration enumerateImage() 

    /**
     * 
     * 
     * @param index
    **/
    public Image getImage(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _imageList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Image) _imageList.elementAt(index);
    } //-- Image getImage(int) 

    /**
    **/
    public Image[] getImage()
    {
        int size = _imageList.size();
        Image[] mArray = new Image[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Image) _imageList.elementAt(index);
        }
        return mArray;
    } //-- Image[] getImage() 

    /**
    **/
    public int getImageCount()
    {
        return _imageList.size();
    } //-- int getImageCount() 

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
    public void removeAllImage()
    {
        _imageList.removeAllElements();
    } //-- void removeAllImage() 

    /**
     * 
     * 
     * @param index
    **/
    public Image removeImage(int index)
    {
        java.lang.Object obj = _imageList.elementAt(index);
        _imageList.removeElementAt(index);
        return (Image) obj;
    } //-- Image removeImage(int) 

    /**
     * 
     * 
     * @param index
     * @param vImage
    **/
    public void setImage(int index, Image vImage)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _imageList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _imageList.setElementAt(vImage, index);
    } //-- void setImage(int, Image) 

    /**
     * 
     * 
     * @param imageArray
    **/
    public void setImage(Image[] imageArray)
    {
        //-- copy array
        _imageList.removeAllElements();
        for (int i = 0; i < imageArray.length; i++) {
            _imageList.addElement(imageArray[i]);
        }
    } //-- void setImage(Image) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.mapp.model.HW0003.ImgInfoList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.mapp.model.HW0003.ImgInfoList) Unmarshaller.unmarshal(com.ai.mapp.model.HW0003.ImgInfoList.class, reader);
    } //-- com.ai.mapp.model.HW0003.ImgInfoList unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
