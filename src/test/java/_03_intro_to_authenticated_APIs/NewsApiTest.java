package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;

import _01_intro_to_APIs.data_transfer_objects.Result;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsApiTest {

	NewsApi newsApi;

	@Mock
	WebClient webClientMock;

	@Mock
	WebClient.ResponseSpec responseSpecMock;

	@Mock
	WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

	@Mock
	WebClient.RequestHeadersSpec requestHeadersSpecMock;

	@Mock
	Mono resultMonoMock;

	@Mock
	ApiExampleWrapper wrapper;

	@Mock
	Article[] articles;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		newsApi = new NewsApi();
		newsApi.setWebClient(webClientMock);
	}

	@Test
	void itShouldGetNewsStoryByTopic() {
		// given
		String topic = "grass";
		List<Article> expected = Collections.singletonList(new Article());
		// when
		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(resultMonoMock);
		when(resultMonoMock.block()).thenReturn(wrapper);
		when(wrapper.getArticles()).thenReturn(expected);

		List<Article> actualNews = newsApi.getNewsStoryByTopic(topic).getArticles();

		// then
		verify(webClientMock, times(1)).get();
		assertEquals(expected, actualNews);
	}

	@Test
	void itShouldFindStory() {
		// given
		String topic = "grass";
		List<Article> expected = Collections.singletonList(new Article());
		// when
		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(resultMonoMock);
		when(resultMonoMock.block()).thenReturn(wrapper);
		when(wrapper.getArticles()).thenReturn(expected);
		String expectedArticle = "Ghost of Tsushima’s purring cats might be my favorite PS5 feature -\n"
				+ "The new directors cut adds a lot of great stuff including kitties\n"
				+ "Since the PlayStation 5 debuted, weve seen lots of novel uses of its DualSense controller. Game developers have utilized the haptic … [+3920 chars]\n"
				+ "Full article: https://www.theverge.com/22631060/ghost-of-tsushima-directors-cut-ps5-cats-dualsense-controller\n";
		String story = newsApi.findStory(topic);
		// then
		System.out.println(story);
		verify(webClientMock, times(1)).get();
		assertEquals(expectedArticle, story);
	}

}