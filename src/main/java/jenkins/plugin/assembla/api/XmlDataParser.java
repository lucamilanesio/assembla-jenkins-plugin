package jenkins.plugin.assembla.api;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

public abstract class XmlDataParser extends DefaultHandler2 {
	
	protected InputStream instream;
	protected SAXParser parser;
	protected StringBuilder currentReadCharacters;

	private static SAXParserFactory parserFactoryInstance;

	protected abstract void startElement(String elementName);

	protected abstract void endElement(String elementName, String elementValue);

	private static SAXParserFactory getParserFactoryInstance() {

		if (parserFactoryInstance == null) {

			parserFactoryInstance = SAXParserFactory.newInstance();
			parserFactoryInstance.setValidating(false);
		}

		return parserFactoryInstance;
	}

	public XmlDataParser(InputStream instream) {

		this.instream = instream;
	}

	public void setInstream(InputStream instream) {
		this.instream = instream;
	}

	public void start() throws IOException, SAXException,
			ParserConfigurationException {
		
		parser = getParserFactoryInstance().newSAXParser();
		parser.parse(instream, this);
	}

	// ///////////////////////////////////////////
	// SAX Handler methods
	// ///////////////////////////////////////////

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		super.startElement(uri, localName, qName, attributes);

		currentReadCharacters = new StringBuilder();

		String elementName = null;
		if (qName.length() == 0) {

			elementName = localName;
		} else {

			elementName = qName;
		}

		this.startElement(elementName);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);

		String elementName = null;
		if (qName.length() == 0) {

			elementName = localName;
		} else {

			elementName = qName;
		}

		if (currentReadCharacters != null) {
			this.endElement(elementName, currentReadCharacters.toString());
		} else {

			this.endElement(elementName, "");
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);

		if (currentReadCharacters == null) {

			currentReadCharacters = new StringBuilder();
		}

		for (int i = start; i < start + length; i++) {
			currentReadCharacters.append(ch[i]);
		}
	}

	@Override
	public void startDocument() throws SAXException {

		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {

		super.endDocument();
	}
}

