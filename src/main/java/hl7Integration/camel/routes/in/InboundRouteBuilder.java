package hl7Integration.camel.routes.in;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.component.hl7.HL7DataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v26.segment.MSH;

@Component
public class InboundRouteBuilder extends SpringRouteBuilder {

	DataFormat hl7 = new HL7DataFormat();

	@Override
	public void configure() throws Exception {

		from("hl7listener").routeId("route_hl7listener").startupOrder(997).unmarshal().hl7(Boolean.FALSE).choice()
				.when(new Predicate() {
					public boolean matches(Exchange exchange) {
						if (msgType(exchange).equals("A01"))
							return true;
						return false;
					}
				}).to("bean:ADT_A01Procssor?method=process").when(new Predicate() {
					public boolean matches(Exchange exchange) {
						if (msgType(exchange).equals("A03"))
							return true;
						return false;
					}
				}).to("bean:ADT_A03Processor?method=process").when(new Predicate() {
					public boolean matches(Exchange exchange) {
						if (msgType(exchange).equals("R01"))
							return true;
						return false;
					}
				}).to("bean:ORU_R01Processor?method=process").end();

	}

	private String msgType(Exchange exchange) {
		try {
			Message msg = exchange.getIn().getBody(Message.class);
			MSH aa;
			aa = (MSH) msg.get("MSH");
			return aa.getMsh9_MessageType().getMsg2_TriggerEvent().toString();
		} catch (HL7Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
