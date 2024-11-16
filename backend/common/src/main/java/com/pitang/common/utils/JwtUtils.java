package com.pitang.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Classe com atruibutos comuns para auxiliar no funcionamento de segurança e
 * autenticação.
 * 
 * @author Carlos Pereira
 */
@Component
public class JwtUtils {

	/**
	 * Chave secreta.
	 */
	@Value("${authentication.jwtSecret}")
	private String jwtSecret;

	/**
	 * Tempo de expiração do token.
	 */
	@Value("${authentication.jwtExpirationMs}")
	private int jwtExpirationMs;

	/**
	 * Método responsável por gerar o token com os valores recebidos.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param login Login do usuário.
	 * @return String Token gerado.
	 */
	public String generateToken(String login) {
		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, login);
	}

	/**
	 * Método responsável por verificar se o token é válido e retornar o token sem o
	 * Bearer.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param token Token gerado.
	 * @return Validade do token.
	 */
	public String validateRecoverToken(String token) {
		return !isTokenExpired(token) ? removeBearer(token) : null;
	}

	/**
	 * Método responsável por recuperar o login do usuário do token.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param token Token gerado.
	 * @return Login do usuário.
	 */
	public String getLogin(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Método respLoginel por recuperar a data de expiração do token.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param token Token gerado.
	 * @return Date Data de expiração.
	 */
	public Date getExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Método responsável por gerar a chave de assinatura para assinar o token.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @return Uma chave secreta do tipo {@link SecretKey}.
	 */
	private SecretKey keySignature() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	/**
	 * Método responsável por criar com valores personalizados e assinar o token com
	 * expiração.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param claims Mapa de informações sobre uma entidade e metadados sobre o token.
	 * @param login Login do usuário
	 * @return Token gerado.
	 */
	private String createToken(Map<String, Object> claims, String login) {
		return Jwts.builder().claims(claims).subject(login).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(keySignature()).compact();
	}

	/**
	 * Método responsável por extrair todos os valores do token.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param token Token gerado.
	 * @return Claims Informações sobre uma entidade e metadados sobre o token.
	 */
	private Claims extrairTodosClaims(String token) {
		return token != null
				? Jwts.parser().verifyWith(keySignature()).build().parseSignedClaims(removeBearer(token)).getPayload()
				: null;
	}

	/**
	 * Método responsável por extrair determinado valor do token.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param token Token gerado.
	 * @param claimsResolver Função usada para extrair e processar informações específicas dos claims.
	 * @return claims.
	 */
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extrairTodosClaims(token);
		return claims != null ? claimsResolver.apply(claims) : null;
	}

	/**
	 * Método responsável por verificar se o token está expirado.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param token Token gerado.
	 * @return boolean
	 */
	private boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}

	/**
	 * Método responsável por remover o Bearer do começo do token, caso exista.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param token Token gerado.
	 * @return Token sem a palavra Bearer.
	 */
	private String removeBearer(String token) {
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}

		return token;
	}

	/**
	 * Método responsável por obter o login a partir do token.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param request Requisição.
	 * @return Login do token. 
	 */
	public String getLoginFromToken(HttpServletRequest request) {
		String headerValue = request.getHeader("Authorization");
		if (headerValue != null) {
			String token = validateRecoverToken(headerValue);
			String login = getLogin(token);

			return login;
		}
		return null;
	}

}
