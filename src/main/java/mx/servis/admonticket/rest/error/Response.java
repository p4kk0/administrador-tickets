package mx.servis.admonticket.rest.error;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Response {

	private int code;
	private String message;
	private List<Error> errors;
	private String moreInfo;

	public Response() {
		super();
	}

	public Response(int code, String message, String moreInfo) {
		super();
		this.code = code;
		this.message = message;
		this.moreInfo = moreInfo;
	}

	public Response(int code, String message, List<Error> errors, String moreInfo) {
		super();
		this.code = code;
		this.message = message;
		this.errors = errors;
		this.moreInfo = moreInfo;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
