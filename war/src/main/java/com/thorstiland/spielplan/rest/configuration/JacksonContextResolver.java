package com.thorstiland.spielplan.rest.configuration;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

// Customized {@code ContextResolver} implementation to pass ObjectMapper to use
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
	private ObjectMapper objectMapper;

	public JacksonContextResolver() throws Exception {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new Hibernate5Module());
	}

	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}
}
