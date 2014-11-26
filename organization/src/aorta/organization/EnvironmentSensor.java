/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.organization;

import alice.tuprolog.Struct;
import java.util.List;

/**
 *
 * @author asj
 */
public interface EnvironmentSensor {
	
	List<Struct> getEnvState();
	
}
