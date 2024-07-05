package ru.lilmoon.providers;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.lilmoon.Entities.Book;
import ru.lilmoon.enity.Reader;

@Service
public class DataProvider {

    private final WebClient webClient;

    public DataProvider(ReactorLoadBalancerExchangeFilterFunction reactorLoadBalancerExchangeFilterFunction) {
        this.webClient = WebClient.builder()
                .filter(reactorLoadBalancerExchangeFilterFunction)
                .build();
    }

    public Book getRandomBook() {
        return webClient.get()
                .uri("http://book-service/api/randomBook")
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }

    public Reader getRandomReader(){
        return webClient.get()
                .uri("http://reader-service/api/randomReader")
                .retrieve()
                .bodyToMono(Reader.class)
                .block();
    }

}
