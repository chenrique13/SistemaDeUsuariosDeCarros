package com.pitang.authenticationManager.security.configurations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import com.pitang.common.enums.EnumMicroservice;
import com.pitang.common.utils.RoutesCommon;

/**
 * Classe de configuração para realizar o roteamento dos endpoints utilizando o
 * balanceamento de carga.
 */
@Configuration
public class RoutingConfig {

	/**
	 * Método responsável por configurar dinamicamente as rotas a partir do
	 * {@link EnumMicrosservico} e utilizar o balanceamento de carga.
	 * 
	 * @param routeLocatorBuilder
	 * @return RouteLocator
	 */
	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

		Map<String, Map<EnumMicroservice, List<HttpMethod>>> routeMap = new HashMap<>();
		routeMap.putAll(RoutesCommon.PUBLIC_ROUTES);
		routeMap.putAll(RoutesCommon.PRIVATE_ROUTES);

		routeMap.forEach((route, methods) -> {
			methods.forEach((service, methodList) -> {
				methodList.forEach(method -> {
					routes.route(route + "_" + method.name(), 
							r -> r.path(route)
							.and()
							.method(method)
							.filters(f -> f.stripPrefix(1))
							.uri("lb://" + service.getName()));
				});
			});
		});

		return routes.build();
	}

}