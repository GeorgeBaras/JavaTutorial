package com.apakgroup.training.tutorial.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLcreation {

    public static void marshalToXMLfile(Object object, String filename) throws JAXBException, IOException {
        JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new FileOutputStream(filename + ".xml");
        marshaller.marshal(object, os);
        os.close();
    }

    public static Object unmarshalFromXMLfile(String filenameWithExtension, Class<?> object) throws JAXBException {
        File file = new File(filenameWithExtension);
        JAXBContext jaxbContext = JAXBContext.newInstance(object);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }
}
