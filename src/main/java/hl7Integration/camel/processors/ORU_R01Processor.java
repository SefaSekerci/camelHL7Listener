package hl7Integration.camel.processors;

import org.springframework.stereotype.Component;
import ca.uhn.hl7v2.model.Message;

@Component
public class ORU_R01Processor {

	public void process(Message in) throws Exception {
		System.out.println("ORU_R01 Messsage");
	}

}
