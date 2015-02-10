/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.apapl;

import alice.tuprolog.Struct;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.apapl.TermConverter;
import aorta.msg.IncomingOrganizationalMessage;
import apapl.data.Literal;
import apapl.messaging.APLMessage;
import apapl.messaging.LocalMessenger;

/**
 *
 * @author asj
 */
public class AortaMessenger extends LocalMessenger {

	private Aorta aorta;

	public AortaMessenger(Aorta aorta) {
		this.aorta = aorta;
	}
	
	@Override
	public void sendMessage(APLMessage aplm) {
		if (aplm.getContent().getName().equals("om")) {
			AortaAgent rcp = aorta.getAgent(aplm.getReceiver());
			Struct apl2aorta = TermConverter.apl2aorta(new Literal(aplm.getContent(), true));
			IncomingOrganizationalMessage msg = new IncomingOrganizationalMessage(aplm.getSender(), apl2aorta.getTerm(0));
			rcp.getState().getExternalAgent().receiveMessage(msg);
		} else {
			super.sendMessage(aplm);
		}
	}

}
