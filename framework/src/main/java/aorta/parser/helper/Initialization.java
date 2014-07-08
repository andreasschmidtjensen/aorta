/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.parser.helper;

import aorta.kr.language.OrganizationType;
import aorta.ts.strategy.Linear;

/**
 *
 * @author ascje
 */
public class Initialization {
    
    public String orgPath;
    public OrganizationType orgType;
    public String strategy = Linear.class.getSimpleName();
    
}
