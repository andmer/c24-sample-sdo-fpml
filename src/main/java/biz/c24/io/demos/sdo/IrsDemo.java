package biz.c24.io.demos.sdo;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.fpml.x2013.fpml54.rec20130813.confirmation.sdo.Fpmlmain54DocumentRoot;

import biz.c24.io.api.C24;
import static biz.c24.io.api.C24.Format.*;
import biz.c24.io.api.data.SimpleDataObject;

public class IrsDemo {
    
    public static void visualise(SimpleDataObject sdo, PrintStream stream) {

        byte[] bytes = sdo.getData().array();
        for(int i=0; i < bytes.length; i++) {
            if(i % 80 == 0) {
                stream.println();
            }
            char c = (char) bytes[i];
            stream.print((c <= 'z' && c >= ' '? c : '.'));
        }
        stream.println();
    }
    
    public static void main(String[] args) throws IOException {
        Fpmlmain54DocumentRoot doc = C24.parse(Fpmlmain54DocumentRoot.class).from(new File("/valid-ird-ex01-vanilla-swap.xml"));
        
        System.out.println("Stored size: " + doc.getData().array().length);
        
        visualise(doc, System.out);
        
        C24.write(doc).as(JSON).to(System.out);
    }

}
