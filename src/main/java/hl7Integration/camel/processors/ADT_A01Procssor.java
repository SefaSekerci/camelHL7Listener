package hl7Integration.camel.processors;

import org.springframework.stereotype.Component;

import ca.uhn.hl7v2.model.Message;

@Component
public class ADT_A01Procssor {

	public void process(Message in) throws Exception {
		System.out.println("ADT_A01 Messsage");
	}
}
