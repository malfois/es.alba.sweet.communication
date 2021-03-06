package es.alba.sweet.communication.command;

public enum Command {

	NAME, STOP, EXIT_SERVER, INFO, DEBUG, UNKNOWN;

	public static Command Factory(String name) {
		if (name.equals(NAME.name()))
			return NAME;
		if (name.equals(INFO.name()))
			return INFO;
		if (name.equals(EXIT_SERVER.name()))
			return EXIT_SERVER;
		if (name.equals(DEBUG.name()))
			return DEBUG;
		if (name.equals(STOP.name()))
			return STOP;
		return UNKNOWN;
	}
}
