package mx.servis.admonticket.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mx.servis.admonticket.rest.error.MoreInfo;
import mx.servis.admonticket.rest.error.Error;
import mx.servis.admonticket.rest.error.Response;
import mx.servis.admonticket.rest.error.RestException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = { RestException.class })
    protected ResponseEntity<Object> handleRestException(RestException e, WebRequest request, HttpServletRequest httpRequest) {
		
		String moreInfo = null;
		if(HttpStatus.INTERNAL_SERVER_ERROR == e.getHttpStatus()) {
			moreInfo = new MoreInfo(httpRequest, e).build();
		}
        Response response = new Response(e.getHttpStatus().value(), e.getMessage(), moreInfo);
        return handleExceptionInternal(e, response.toString(), new HttpHeaders(), e.getHttpStatus(), request);
    }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
//			System.out.println("e.getBindingResult().getObjectName() " + e.getBindingResult().getObjectName());
//			System.out.println("e.getBindingResult().getFieldError().getCode() " + e.getBindingResult().getFieldError().getCode());
//			System.out.println("e.getBindingResult().getFieldError().getDefaultMessage() " + e.getBindingResult().getFieldError().getDefaultMessage());
//			System.out.println("e.getBindingResult().getFieldError().getField() " + e.getBindingResult().getFieldError().getField());
//			System.out.println("e.getBindingResult().getFieldError().getObjectName() " + e.getBindingResult().getFieldError().getObjectName());
//			System.out.println("e.getBindingResult().getGlobalError().getCode() " + e.getBindingResult().getGlobalError().getCode());
//			System.out.println("e.getBindingResult().getGlobalError().getDefaultMessage() " + e.getBindingResult().getGlobalError().getDefaultMessage());
//			System.out.println("e.getBindingResult().getGlobalError().getObjectName() " + e.getBindingResult().getGlobalError().getObjectName());
//			for(ObjectError oe: e.getBindingResult().getGlobalErrors()) {
//				System.out.println("oe.getCode() " + oe.getCode());
//				System.out.println("oe.getDefaultMessage() " + oe.getDefaultMessage());
//				System.out.println("oe.getObjectName() " + oe.getObjectName());
//			}
			List<Error> errors = e.getBindingResult().getFieldErrors()
					.stream()
					.map(fieldError -> new Error(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage()))
					.collect(Collectors.toList());
			
		 Response response = new Response(HttpStatus.BAD_REQUEST.value(), null, errors, null);
		 return super.handleExceptionInternal(e, response.toString(), headers, status, request);
	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
//			List<String> messages = e.getBindingResult()
//	        .getFieldErrors().stream()
//	        .map(ObjectError::getDefaultMessage)
////	        .map(error -> error.getField() + ":"+ error.getDefaultMessage())
//	        .collect(Collectors.toList());
//		 Response response = new Response(HttpStatus.BAD_REQUEST.value(), messages.toString(), null);
//		 return super.handleExceptionInternal(e, response.toString(), headers, status, request);
//	}
	
	
	

}
