package hl7Integration.camel;

import org.springframework.stereotype.Component;

import ca.uhn.hl7v2.model.Message;

@Component
public class RespondACK {

	public Message process(Message in) throws Exception {

		System.out.println(in.toString());
		System.out.println(in);
		Message out = in.generateACK();
		System.out.println(out.toString());
		return out;
	}
}
