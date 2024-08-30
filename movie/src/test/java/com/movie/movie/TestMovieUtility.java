package com.movie.movie;

import java.net.URL;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class TestMovieUtility {
    private static JAXBContext jaxbContextClass;

    public TestMovieUtility(){
        super();
        try {
            if (jaxbContextClass == null)
                jaxbContextClass =JAXBContext.newInstance(MovieMockData.class);
        } catch (JAXBException e) {
            System.out.println("Unable to initialize JAXB for the class");
        }
    }

    private final static ThreadLocal<Unmarshaller> unmarshallerClassThreadLocal = new ThreadLocal<Unmarshaller>(){
        protected synchronized Unmarshaller initialValue(){
            try {
                return jaxbContextClass.createUnmarshaller();
            } catch (Exception e) {
                throw new IllegalStateException("Unable to create unmarshaller for class");
            }
        }
    };

    /*
     * Read xml file containing mocked data
     * 
     * @param fileName
     * @return
     */

    private static URL readFile(String fileName){
        URL url = TestMovieUtility.class.getResource(fileName);
        return url;
    }

    public Object getJaxBClassObject(String filename)throws JAXBException{
        Unmarshaller unmarshaller = unmarshallerClassThreadLocal.get();

        return unmarshaller.unmarshal(readFile(filename));


    }
}
