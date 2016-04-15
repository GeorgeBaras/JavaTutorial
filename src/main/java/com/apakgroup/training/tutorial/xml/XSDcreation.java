package com.apakgroup.training.tutorial.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.stream.StreamResult;

public class XSDcreation {

    public static void writeXsd(final File out, Class<?> passedClass) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(passedClass);
        SchemaOutputResolver sor = new SchemaOutputResolver() {

            @Override
            public StreamResult createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                StreamResult streamResult = new StreamResult(out);
                // Naming the file manually(not with the suggestedFileName parameter) because the createOutput needs to be implemented
                streamResult.setSystemId(out.toString() + ".xsd");
                return streamResult;
            }

        };

        jaxbContext.generateSchema(sor);
    }

}
