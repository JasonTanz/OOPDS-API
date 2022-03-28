package oopds.assignment.DC.models;

/**
 * This class is used to store and return a value, together with a message, to the Spring REST APIs.
 */
public class DataResponse<T> {
	private T data = null;
	private String message = "";

	/**
	 * Constructs a DataResponse Object with all null value
	 */
	public DataResponse() {}

	/**
	 * Constructs a DataResponse Object with the specified values.
	 *
	 * @param data The data to be sent
	 * @param message The message to be sent to the Front-End
	 */
	public DataResponse(T data, String message) {
		this.data = data;
		this.message = message;
	}

	/**
	 * Gets and Returns the Data stored in the DataResponse Object
	 *
	 * @return Generic Type of data in DataResponse
	 */
	public T getData() {
		return data;
	}

	/**
	 * Gets and Returns the Message stored in the DataResponse Object
	 *
	 * @return a String value, containing the message of the operation
	 */
	public String getMessage() {
		return message;
	}
}
