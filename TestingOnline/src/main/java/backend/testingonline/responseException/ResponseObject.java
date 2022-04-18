package backend.testingonline.responseException;

public class ResponseObject {
	private String status;
	private String message;
	private Object data;
	
	public ResponseObject() {
		super();
	}

	public ResponseObject(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

//	public void ResponeObject1(String string, String string2, Object delete) {
//		this.status = string;
//		this.message = string2;
//		this.data = delete;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
