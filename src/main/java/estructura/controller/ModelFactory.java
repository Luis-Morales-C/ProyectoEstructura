package estructura.controller;

import estructura.model.Proceso;

import java.io.IOException;
import java.util.Iterator;


public class ModelFactory {
    Proceso proceso;

    private static class SingletonHolder {
        private final static ModelFactory eINSTANCE = new ModelFactory();
    }

    public static ModelFactory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactory() {

    }
}