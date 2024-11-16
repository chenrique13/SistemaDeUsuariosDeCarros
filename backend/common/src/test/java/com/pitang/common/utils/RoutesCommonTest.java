package com.pitang.common.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import com.pitang.common.enums.EnumMicroservice;

import java.util.List;

class RoutesCommonTest {

	/**
	 * Inicializa os objetos necessarios antes de cada teste.
	 */
	@BeforeEach
	void runBeforeEachTest() {
        RoutesCommon.PUBLIC_ROUTES.clear();
        RoutesCommon.PRIVATE_ROUTES.clear();
        // Adicionar rotas nos mapas após limpar antes de cada teste
        RoutesCommon.addRoute("/api/signin", EnumMicroservice.USERS, List.of(HttpMethod.POST), RoutesCommon.PUBLIC_ROUTES);
        RoutesCommon.addRoute("/api/users", EnumMicroservice.USERS, List.of(HttpMethod.GET, HttpMethod.POST), RoutesCommon.PUBLIC_ROUTES);
        RoutesCommon.addRoute("/api/users/{id}", EnumMicroservice.USERS, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE), RoutesCommon.PUBLIC_ROUTES);

        RoutesCommon.addRoute("/api/me", EnumMicroservice.USERS, List.of(HttpMethod.GET), RoutesCommon.PRIVATE_ROUTES);
        RoutesCommon.addRoute("/api/cars", EnumMicroservice.CARS, List.of(HttpMethod.GET, HttpMethod.POST), RoutesCommon.PRIVATE_ROUTES);
        RoutesCommon.addRoute("/api/cars/{id}", EnumMicroservice.CARS, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE), RoutesCommon.PRIVATE_ROUTES);
    }

    @SuppressWarnings("unused")
	@Test
    public void testConstructor() {
    	RoutesCommon routesCommon = new RoutesCommon();
        assertNotNull(RoutesCommon.PUBLIC_ROUTES);
        assertTrue(RoutesCommon.PUBLIC_ROUTES.containsKey("/api/signin"));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.containsKey("/api/users"));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.containsKey("/api/users/{id}"));

        assertNotNull(RoutesCommon.PRIVATE_ROUTES);
        assertTrue(RoutesCommon.PRIVATE_ROUTES.containsKey("/api/me"));
        assertTrue(RoutesCommon.PRIVATE_ROUTES.containsKey("/api/cars"));
        assertTrue(RoutesCommon.PRIVATE_ROUTES.containsKey("/api/cars/{id}"));

        assertTrue(RoutesCommon.PUBLIC_ROUTES.get("/api/signin")
                .get(EnumMicroservice.USERS).contains(HttpMethod.POST));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.get("/api/users")
                .get(EnumMicroservice.USERS).contains(HttpMethod.GET));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.get("/api/users")
                .get(EnumMicroservice.USERS).contains(HttpMethod.POST));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.get("/api/users/{id}")
                .get(EnumMicroservice.USERS).contains(HttpMethod.GET));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.get("/api/users/{id}")
                .get(EnumMicroservice.USERS).contains(HttpMethod.PUT));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.get("/api/users/{id}")
                .get(EnumMicroservice.USERS).contains(HttpMethod.DELETE));
        
        assertTrue(RoutesCommon.PRIVATE_ROUTES.get("/api/me")
                .get(EnumMicroservice.USERS).contains(HttpMethod.GET));
        assertTrue(RoutesCommon.PRIVATE_ROUTES.get("/api/cars")
                .get(EnumMicroservice.CARS).contains(HttpMethod.GET));
        assertTrue(RoutesCommon.PRIVATE_ROUTES.get("/api/cars")
                .get(EnumMicroservice.CARS).contains(HttpMethod.POST));
        assertTrue(RoutesCommon.PRIVATE_ROUTES.get("/api/cars/{id}")
                .get(EnumMicroservice.CARS).contains(HttpMethod.GET));
        assertTrue(RoutesCommon.PRIVATE_ROUTES.get("/api/cars/{id}")
                .get(EnumMicroservice.CARS).contains(HttpMethod.PUT));
        assertTrue(RoutesCommon.PRIVATE_ROUTES.get("/api/cars/{id}")
                .get(EnumMicroservice.CARS).contains(HttpMethod.DELETE));
    }
    
    @Test
    void testIsRoutePublic_shouldReturnTrueForPublicRoute() {
        String route = "/api/signin";
        HttpMethod method = HttpMethod.POST;
        assertNotNull(RoutesCommon.PRIVATE_ROUTES);
        boolean result = RoutesCommon.isRoutePublic(route, method);

        assertTrue(result);
    }

    @Test
    void testIsRoutePublic_shouldReturnFalseForPrivateRoute() {
        String route = "/api/me";
        HttpMethod method = HttpMethod.GET;

        boolean result = RoutesCommon.isRoutePublic(route, method);

        assertFalse(result);
    }

    @Test
    void testIsRoutePrivate_shouldReturnTrueForPrivateRoute() {
        String route = "/api/me";
        HttpMethod method = HttpMethod.GET;

        boolean result = RoutesCommon.isRoutePrivate(route, method);

        assertTrue(result);
    }

    @Test
    void testIsRoutePrivate_shouldReturnFalseForPublicRoute() {
        String route = "/api/signin";
        HttpMethod method = HttpMethod.POST;

        boolean result = RoutesCommon.isRoutePrivate(route, method);

        assertFalse(result);
    }

    @Test
    void testIsRoutePublic_shouldMatchPatternWithPlaceholder() {
        String route = "/api/users/123";
        HttpMethod method = HttpMethod.GET;

        boolean result = RoutesCommon.isRoutePublic(route, method);

        assertTrue(result);
    }

    @Test
    void testIsRoutePrivate_shouldMatchPatternWithPlaceholder() {
        String route = "/api/cars/123";
        HttpMethod method = HttpMethod.GET;

        boolean result = RoutesCommon.isRoutePrivate(route, method);

        assertTrue(result);
    }

    @Test
    void testIsRoutePublic_shouldReturnFalseForNonMatchingRoute() {
        String route = "/api/nonexistent";
        HttpMethod method = HttpMethod.GET;

        boolean result = RoutesCommon.isRoutePublic(route, method);

        assertFalse(result);
    }

    @Test
    void testIsRoutePrivate_shouldReturnFalseForNonMatchingRoute() {
        String route = "/api/nonexistent";
        HttpMethod method = HttpMethod.GET;

        boolean result = RoutesCommon.isRoutePrivate(route, method);

        assertFalse(result);
    }

    @Test
    void testIsRoutePublic_shouldReturnFalseForNonMatchingMethod() {
        String route = "/api/users";
        HttpMethod method = HttpMethod.PUT; // Método não permitido

        boolean result = RoutesCommon.isRoutePublic(route, method);

        assertFalse(result);
    }

    @Test
    void testIsRoutePrivate_shouldReturnFalseForNonMatchingMethod() {
        String route = "/api/me";
        HttpMethod method = HttpMethod.POST; // Método não permitido

        boolean result = RoutesCommon.isRoutePrivate(route, method);

        assertFalse(result);
    }

    @Test
    void testAddRoute_shouldAddRouteToPublicRoutes() {
        String route = "/api/test";
        EnumMicroservice service = EnumMicroservice.USERS;
        List<HttpMethod> methods = List.of(HttpMethod.GET);

        RoutesCommon.addRoute(route, service, methods, RoutesCommon.PUBLIC_ROUTES);

        assertTrue(RoutesCommon.PUBLIC_ROUTES.containsKey(route));
        assertTrue(RoutesCommon.PUBLIC_ROUTES.get(route).containsKey(service));
        assertEquals(methods, RoutesCommon.PUBLIC_ROUTES.get(route).get(service));
    }

    @Test
    void testAddRoute_shouldNotAddDuplicateRoutes() {
        String route = "/api/signin";
        EnumMicroservice service = EnumMicroservice.USERS;
        List<HttpMethod> methods = List.of(HttpMethod.POST);

        RoutesCommon.addRoute(route, service, methods, RoutesCommon.PUBLIC_ROUTES);
        RoutesCommon.addRoute(route, service, methods, RoutesCommon.PUBLIC_ROUTES);

        assertEquals(1, RoutesCommon.PUBLIC_ROUTES.get(route).size());
    }
}
