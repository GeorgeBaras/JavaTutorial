package com.apakgroup.training.tutorial.xml;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;

public class XSDcreation {

    public static void createXSDfromClass(Class<?> passedClass, String schemaName) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(passedClass);
        SchemaOutputResolver sor = new MySchemaOutputResolver();
        sor.createOutput("http://www.example.com/a", schemaName);
        jaxbContext.generateSchema(sor);

    }
}
