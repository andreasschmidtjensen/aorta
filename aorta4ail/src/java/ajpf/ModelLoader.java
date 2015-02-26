/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajpf;

import ajpf.product.MCAPLmodel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Andreas
 */
public class ModelLoader {

	public static MCAPLmodel loadModelFromFile(String filename) throws IOException, ClassNotFoundException {
		MCAPLmodel model;
		try (FileInputStream fin = new FileInputStream(filename)) {
			ObjectInputStream ois = new ObjectInputStream(fin);
			model = (MCAPLmodel) ois.readObject();
		}
		return model;
	}

}
