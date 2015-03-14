/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionhouse.aorta.simple.benchmark;

import ajpf.psl.MCAPLProperty;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import modelchecker.CreateProp;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class CreateBenchmark {

	public static void main(String[] args) throws IOException {
		int number = Integer.parseInt(args[0]);
		System.out.println("Creating from 1-" + number + " agents...");

		String[] types = new String[]{"enact", "inform"};

		for (int i = 1; i < number + 1; i++) {
			String gwendolen = "GWENDOLEN";
			for (int j = 1; j <= i; j++) {
				gwendolen += getAgent(j);
			}
			save(i + "customer.gwen", gwendolen);

			for (String type : types) {
				String ail = getAILconfig(type, i);
				String aortaSetup = getAortaSetup(type, i);

				save(i + "customer_" + type + ".ail", ail);
				save(i + "customer_" + type + ".aortasetup", aortaSetup);

				String psl = getPSL(type, i);
				save(i + "customer_" + type + ".psl", psl);
			}
		}

		for (String type : types) {
			for (int i = 1; i < number + 1; i++) {
				System.out.println(
						"java -jar ../../../../../../standalone/aorta-modelchecker.jar "
						+ i + "customer_" + type + ".ail " + i + "customer_"
						+ type + ".psl -summarize");
			}
		}
	}

	private static void save(String filename, String contents) throws
			IOException {
		try (FileWriter wr = new FileWriter(filename)) {
			wr.write(contents);
		}
	}

	private static String getAgent(int num) {
		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		sb.append(":name: agent").append(num).append("\n");
		sb.append(":Initial Beliefs:\n");
		sb.append(":Initial Goals:\n");
		sb.append(":Plans:\n");
		sb.append(
				"+!registered(Me) [achieve] : { True } <- register(address, account);\n");

		return sb.toString();
	}

	private static String getAILconfig(String type, int num) {
		StringBuilder sb = new StringBuilder();
		sb.append("env = auctionhouse.AuctionHouse\n");
		sb.append("mas.file = ").append(num).append("customer_").append(type).
				append(".aortasetup\n");
		sb.append("mas.builder = aorta.AortaMASBuilder\n");
		return sb.toString();
	}

	private static String getAortaSetup(String type, int num) {
		StringBuilder sb = new StringBuilder();
		sb.append("mas.file = ").append(num).append("customer.gwen\n");
		sb.append("mas.builder = gwendolen.GwendolenMASBuilder\n");
		sb.append("aorta.model = ah.mm\n");
		sb.append("aorta.rules =  ah_").append(type).append(".aorta\n");
		return sb.toString();
	}

	private static String getPSL(String type, int num) {
		CreateProp cp = new CreateProp(num + "customer_" + type + ".aortasetup",
				new String[]{"Agent=_"});
		Map<String, MCAPLProperty> props = cp.createProperties();

		StringBuilder sb = new StringBuilder();
		switch (type) {
			case "enact":
				sb.append("enactment_").append(num).append(": ").append(
						toPSLSyntax(props.get("enactment"))).append("\n");
				break;
			case "inform":
				if (num > 1) {
					sb.append("informed_").append(num).append(": ").append(
							toPSLSyntax(props.get("informed"))).append("\n");
				}
				break;
		}

		return sb.toString();
	}

	private static String toPSLSyntax(MCAPLProperty prop) {
		return prop.toString().replace("\\/", "||").replace("/\\", "&");
	}

}
