package spring.boot.optic.okulist.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {
   /* @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {
        return new RestTemplate(httpComponentsClientHttpRequestFactory);
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .build();
    }

    @Bean
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.createDefault();
    }

    */
}
