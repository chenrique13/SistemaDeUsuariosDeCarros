package com.pitang.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.PathContainer;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import com.pitang.common.enums.EnumMicroservice;

/**
 * Classe que define as rotas publicas e privadas comum do sistema.
 * 
 * @author Carlos Pereira
 */
public class RoutesCommon {

	/**
	 * Mapas das rotas publicas, seu microservico, a lista de métodos HTTP.
	 */
	public static final Map<String, Map<EnumMicroservice, List<HttpMethod>>> PUBLIC_ROUTES;
	/**
	 * Mapas das rotas privadas, seu microservico, a lista de métodos HTTP.
	 */
	public static final Map<String, Map<EnumMicroservice, List<HttpMethod>>> PRIVATE_ROUTES;

	static {
		PUBLIC_ROUTES = new HashMap<>();
		addRoute("/api/signin", EnumMicroservice.USERS, List.of(HttpMethod.POST), PUBLIC_ROUTES);
		addRoute("/api/users", EnumMicroservice.USERS, List.of(HttpMethod.GET, HttpMethod.POST), PUBLIC_ROUTES);
		addRoute("/api/users/{id}", EnumMicroservice.USERS, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE),
				PUBLIC_ROUTES);
		addRoute("swagger-ui/**", EnumMicroservice.USERS, List.of(HttpMethod.GET), PUBLIC_ROUTES);
		addRoute("v3/api-docs/**", EnumMicroservice.USERS, List.of(HttpMethod.GET), PUBLIC_ROUTES);
		addRoute("swagger-ui/**", EnumMicroservice.CARS, List.of(HttpMethod.GET), PUBLIC_ROUTES);
		addRoute("v3/api-docs/**", EnumMicroservice.CARS, List.of(HttpMethod.GET), PUBLIC_ROUTES);
		
		PRIVATE_ROUTES = new HashMap<>();
		addRoute("/api/me", EnumMicroservice.USERS, List.of(HttpMethod.GET), PRIVATE_ROUTES);
		addRoute("/api/cars", EnumMicroservice.CARS, List.of(HttpMethod.GET, HttpMethod.POST), PRIVATE_ROUTES);
		addRoute("/api/cars/{id}", EnumMicroservice.CARS, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE),
				PRIVATE_ROUTES);
	}

	/**
	 * método para adicionar uma rota, seu microservico, a lista de métodos HTTP ao
	 * mapa de rotas PUBLIC_ROUTES ou PRIVATE_ROUTES.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param route Rota.
	 * @param service Enum do micro serviço.
	 * @param methods Lista de métodos http.
	 * @param routes Mapa de rota, micro serviço e métodos http.
	 */
	public static void addRoute(String route, EnumMicroservice service, List<HttpMethod> methods,
			Map<String, Map<EnumMicroservice, List<HttpMethod>>> routes) {
		routes.computeIfAbsent(route, r -> new HashMap<>()).put(service, methods);
	}

	/**
	 * Método para checar se a rota é publica.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param route Rota
	 * @param httpMethod Método http.
	 * @return boolean
	 */
	public static boolean isRoutePublic(String route, HttpMethod httpMethod) {
		PathPatternParser parser = new PathPatternParser();

		return PUBLIC_ROUTES.entrySet().stream().anyMatch(entry -> {
			PathPattern pattern = parser.parse(entry.getKey());
			PathContainer pathContainer = PathContainer.parsePath(route);
			return pattern.matches(pathContainer)
					&& entry.getValue().values().stream().anyMatch(methods -> methods.contains(httpMethod));
		});
	}

	/**
	 * Método para checar se a rota é privada.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param route Rota
	 * @param httpMethod Método http.
	 * @return boolean
	 */
	public static boolean isRoutePrivate(String route, HttpMethod httpMethod) {
		PathPatternParser parser = new PathPatternParser();

		return PRIVATE_ROUTES.entrySet().stream().anyMatch(entry -> {
			PathPattern pattern = parser.parse(entry.getKey());
			PathContainer pathContainer = PathContainer.parsePath(route);
			return pattern.matches(pathContainer)
					&& entry.getValue().values().stream().anyMatch(methods -> methods.contains(httpMethod));
		});
	}
}
