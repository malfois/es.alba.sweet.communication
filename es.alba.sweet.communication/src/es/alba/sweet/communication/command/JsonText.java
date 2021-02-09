package es.alba.sweet.communication.command;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.alba.sweet.base.output.Output;

public class JsonText<T> {

	public final static String	SEPARATOR		= " - ";

	private Command				command;
	private T					argument;

	// create ObjectMapper instance
	private ObjectMapper		objectMapper	= new ObjectMapper();

	public JsonText(Command command, T parameters) {
		this.command = command;
		this.argument = parameters;
	}

	public Command getCommand() {
		return this.command;
	}

	public T getArgument() {
		return this.argument;
	}

	@Override
	public String toString() {
		StringWriter text = new StringWriter();
		try {
			// objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.writeValue(text, this.argument);
			return command.name() + SEPARATOR + text.toString();
		} catch (IOException e) {
			Output.DEBUG.info("es.alba.sweet.communication.command.JsonText.toString", "Error reading configuration ");
			return e.getMessage();
		}
	}

	@SuppressWarnings("unchecked")
	public void createObject(String text) {
		try {
			String[] texts = text.split(SEPARATOR);
			this.command = Command.Factory(texts[0]);
			if (texts.length == 2) {
				byte[] jsonData = texts[1].getBytes();
				// convert json string to object
				argument = (T) objectMapper.readValue(jsonData, argument.getClass());
			}
			Output.DEBUG.info("es.alba.sweet.communication.command.JsonText.createObject", "Object from " + argument.getClass() + " created");
		} catch (IOException e) {
			Output.MESSAGE.warning("es.alba.sweet.communication.command.JsonText.createObject", "Error reading file. Default settings loaded");
		}
	}

	public static Command getCommand(String text) {
		String[] texts = text.split(SEPARATOR);
		return Command.Factory(texts[0]);
	}

	public void print() {
		System.out.println(toString());
	}

}
