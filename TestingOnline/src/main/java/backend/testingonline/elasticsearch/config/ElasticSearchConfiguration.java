package backend.testingonline.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "backend.testingonline.elasticsearch.repository")
//@ComponentScan(basePackages = {"backend.testingonline"})
public class ElasticSearchConfiguration extends AbstractElasticsearchConfiguration {

	@Value("${elasticsearch.host}")
	private String esHost;

	@Value("{$elasticsearch.port}")
	private int esPort;

	@Value("${elasticsearch.clustername}")
	private String esClusterName;
	
	private String esUrl = "localhost:9200";

	@Bean
	@Override
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration config = ClientConfiguration.builder()
				.connectedTo(esUrl)
				.build();
		return RestClients.create(config).rest();
	}
}
