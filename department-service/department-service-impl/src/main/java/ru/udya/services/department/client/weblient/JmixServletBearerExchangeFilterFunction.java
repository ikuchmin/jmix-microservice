package ru.udya.services.department.client.weblient;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import ru.udya.services.department.auth.jwt.ExtJmixJwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServletBearerExchangeFilterFunction;

import java.util.Map;

/**
 * Copy-paste from {@link ServletBearerExchangeFilterFunction}
 */
public final class JmixServletBearerExchangeFilterFunction implements ExchangeFilterFunction {

	static final String SECURITY_REACTOR_CONTEXT_ATTRIBUTES_KEY = "org.springframework.security.SECURITY_CONTEXT_ATTRIBUTES";

	@Override
	public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
		// @formatter:off
		return oauth2Token().map((token) -> bearer(request, token))
				.defaultIfEmpty(request)
				.flatMap(next::exchange);
		// @formatter:on
	}

	private Mono<AbstractOAuth2Token> oauth2Token() {
		// @formatter:off
		return Mono.subscriberContext()
				.flatMap(this::currentAuthentication)

				// begin jmix

				.filter((authentication) -> authentication instanceof ExtJmixJwtAuthenticationToken)
				.cast(ExtJmixJwtAuthenticationToken.class)
				.filter((authentication) -> authentication.getToken() instanceof AbstractOAuth2Token)

				// end jmix

				.map(ExtJmixJwtAuthenticationToken::getToken)
				.cast(AbstractOAuth2Token.class);
		// @formatter:on
	}

	private Mono<Authentication> currentAuthentication(Context ctx) {
		return Mono.justOrEmpty(getAttribute(ctx, Authentication.class));
	}

	private <T> T getAttribute(Context ctx, Class<T> clazz) {
		// NOTE: SecurityReactorContextConfiguration.SecurityReactorContextSubscriber adds
		// this key
		if (!ctx.hasKey(SECURITY_REACTOR_CONTEXT_ATTRIBUTES_KEY)) {
			return null;
		}
		Map<Class<T>, T> attributes = ctx.get(SECURITY_REACTOR_CONTEXT_ATTRIBUTES_KEY);
		return attributes.get(clazz);
	}

	private ClientRequest bearer(ClientRequest request, AbstractOAuth2Token token) {
		// @formatter:off
		return ClientRequest.from(request)
				.headers((headers) -> headers.setBearerAuth(token.getTokenValue()))
				.build();
		// @formatter:on
	}

}
