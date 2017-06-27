package com.stackroute.gupshup.activityproducer.domain;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Person implements ASObject{
	
	private final String context;
	
	@NotNull(message="type is required")
	private final String type;
	
	@NotNull(message="username is required")
	private final String name;
	
	@JsonCreator
	public Person(
			@JsonProperty("@context") String context,
			@JsonProperty("type") String type,
			@JsonProperty("name") String name) {
		this.context = context;
		this.type = type;
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getContext() {
		return context;
	}

	public String getName() {
		return name;
	}
}
