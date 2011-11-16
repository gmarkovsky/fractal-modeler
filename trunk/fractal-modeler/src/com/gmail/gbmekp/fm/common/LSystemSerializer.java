package com.gmail.gbmekp.fm.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import com.gmail.gbmekp.fm.j2d.engine.LSystem;

public class LSystemSerializer {
	
	public static ByteArrayOutputStream write(LSystem lSystem) {
		DOMImplementationRegistry registry;
		try {
			registry = DOMImplementationRegistry.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(); // shouldn't happen;
		}

		DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
		LSSerializer serializer = impl.createLSSerializer();

		DOMConfiguration config = serializer.getDomConfig();
		config.setParameter("format-pretty-print", Boolean.TRUE);

		LSOutput out = impl.createLSOutput();
		Writer ws = new StringWriter();
		out.setCharacterStream(ws);

		serializer.write(toDocument(lSystem), out);

		String res = ws.toString();

		try {
			ws.close();
		} catch (IOException e) {
			throw new RuntimeException(); // shouldn't happen;
		}

		byte[] resBytes = res.getBytes(Charset.forName("UTF-8"));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			byteArrayOutputStream.write(resBytes);
		} catch (IOException e) {
			throw new RuntimeException(); // shouldn't happen;
		}
		return byteArrayOutputStream;
	}
	

	/**
	 * @param lSystem
	 * @return
	 */
	private static Document toDocument(LSystem lSystem) {
		Document doc;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(); // shouldn't happen;
		}

		Element root = doc.createElement("L-System");
		doc.appendChild(root);
		
		root.setAttribute("name", lSystem.getName());
		root.setAttribute("axiom", lSystem.getFirst());
		
		Element rules = doc.createElement("rules");
		
		root.appendChild(rules);
		Map<Character, String> rules2 = lSystem.getRules();
		for (Character c : rules2.keySet()) {
			Element rule = doc.createElement("rule");
			rule.setAttribute("ls", c.toString());
			rule.setAttribute("rs", rules2.get(c));
			rules.appendChild(rule);
		}	

		return doc;
	}
	
	public static LSystem parse(InputStream stream) throws LSystemLoadException {
		DOMImplementationRegistry registry;
		try {
			registry = DOMImplementationRegistry.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(); // shouldn't happen;
		}

		DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");

		LSParser parser = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);

		LSInput in = impl.createLSInput();
		in.setByteStream(stream);		
		Document doc = null;
		
		try {
			doc = parser.parse(in);
			parser.getDomConfig();
		} catch (LSException lsException) {
			throw new LSystemLoadException(lsException.code);
		}

		Element root = doc.getDocumentElement();
		
		String name = root.getAttribute("name");
		String axiom = root.getAttribute("axiom");
		
		Map<Character, String> rules = new HashMap<Character, String>();
		
		NodeList list = root.getElementsByTagName("rule");
		for (int i = 0; i < list.getLength(); i++) {
			Element rule = (org.w3c.dom.Element) list.item(i);
			rules.put(rule.getAttribute("ls").charAt(0), rule.getAttribute("rs"));
		}
		
		return new LSystem(axiom, rules, name);
	}
}