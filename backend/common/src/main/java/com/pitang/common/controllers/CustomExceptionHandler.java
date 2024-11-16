package com.pitang.common.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitang.common.dtos.common.ErrorDTO;
import com.pitang.common.exceptions.CustomException;

import feign.FeignException;

/**
 * Classe que captura as exceções.
 */
@RestControllerAdvice
public abstract class CustomExceptionHandler {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Manipulador para exceções personalizadas.
	 * 
	 * Este método lida com exceções do tipo {@link CustomException}.
	 *
	 * @author Carlos Pereira
	 *
	 * @param exception Exceção personalizada.
	 * @return {@link ResponseEntity} com {@link ErrorDTO}.
	 */
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorDTO> customException(CustomException exception) {
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), exception.getErrorCode());

		return ResponseEntity.status(exception.getHttpStatus()).body(errorDTO);
	}

	/**
	 * Manipulador para exceções campos invalidos.
	 * 
	 * Este método lida com exceções do tipo {@link HttpMessageNotReadableException}
	 * e {@link MethodArgumentNotValidException}.
	 *
	 * @author Carlos Pereira
	 *
	 * @param exception Exceção {@link HttpMessageNotReadableException} ou
	 *                  {@link MethodArgumentNotValidException}.
	 * @return {@link ResponseEntity} com {@link ErrorDTO}.
	 */
	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorDTO> invalidFieldsException(Exception exception) {
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), 4);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}

	/**
	 * Manipulador para exceções entre proxies.
	 * 
	 * Este método lida com exceções do tipo {@link FeignException}.
	 *
	 * @author Carlos Pereira
	 *
	 * @param exception Exceção {@link FeignException}.
	 * @return {@link ResponseEntity} com {@link ErrorDTO}.
	 * @throws JsonProcessingException exceção lançada quando algo dá errado ao
	 *                                 converter entre objetos Java e JSON.
	 * @throws JsonMappingException    Exceção lançada quando há um problema de
	 *                                 mapeamento durante a conversão entre objetos
	 *                                 Java e JSON.
	 */
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorDTO> customFeignException(FeignException exception)
			throws JsonMappingException, JsonProcessingException {
		if (exception.contentUTF8() != null) {
			JsonNode jsonNode = objectMapper.readTree(exception.contentUTF8());
			String message = jsonNode.path("message").asText();
			int errorCode = jsonNode.path("errorCode").asInt();
			ErrorDTO errorDTO = new ErrorDTO(message, errorCode);

			return ResponseEntity.status(exception.status()).body(errorDTO);
		}
		return ResponseEntity.internalServerError().build();
	}

}
