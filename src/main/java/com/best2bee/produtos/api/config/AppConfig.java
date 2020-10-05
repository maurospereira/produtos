package com.best2bee.produtos.api.config;

import org.springframework.context.annotation.Configuration;

@Configuration("app")
public class AppConfig {

	private String outputKafkaTopic;

	public String getOutputKafkaTopic() {
		return outputKafkaTopic;
	}

	public void setOutputKafkaTopic(String outputKafkaTopic) {
		this.outputKafkaTopic = outputKafkaTopic;
	}
}
