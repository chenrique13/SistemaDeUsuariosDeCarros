package com.pitang.user.controllers;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pitang.common.controllers.CustomExceptionHandler;

/**
 * Classe para manipulação comum de exceções do projeto dos usuarios.
 * 
 * Esta classe define manipuladores de exceções do projeto dos usuarios.
 * 
 * @author Carlos Pereira
 */
@RestControllerAdvice
public class CustomUserExceptionHandler extends CustomExceptionHandler{

}
