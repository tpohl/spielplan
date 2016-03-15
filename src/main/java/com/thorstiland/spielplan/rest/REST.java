package com.thorstiland.spielplan.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * Offer REST interface.
 * 
 * @author Thorsten Pohl
 */
@ApplicationPath("rest")
public class REST extends Application {

	public REST() {

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("0.0.1");
		beanConfig.setSchemes(new String[] { "https" });
		beanConfig.setBasePath("/rest");
		beanConfig.setResourcePackage("com.thorstiland.spielplan.rest");
		beanConfig.setScan(true);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());
	}

}