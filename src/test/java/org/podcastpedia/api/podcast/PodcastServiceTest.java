package org.podcastpedia.api.podcast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PodcastServiceTest {

    @Mock
    private PodcastRepository podcastRepository;

    private PodcastService podcastService;

    @Before
    public void setUp() throws Exception {
        podcastService = new PodcastService(podcastRepository);
    }

    @Test
    public void getPodcastDetails_returnsPodcastInfo() {
        given(podcastRepository.findByIdentifier("javascript-jabber")).willReturn(new Podcast("javascript-jabber"));

        final Podcast podcast = podcastService.getPodcastDetails("javascript-jabber");

        assertThat(podcast.getIdentifier()).isEqualTo("javascript-jabber");
    }

    @Test
    public void getPodcasts_returnsListOfPodcasts() {
        Podcast mockPodcast = new Podcast("java-podcast");
        List<Podcast> mockList = new ArrayList<>();
        mockList.add(mockPodcast);
        given(podcastRepository.findAll()).willReturn(mockList);

        List<Podcast> allPodcasts = podcastService.getAllPodcasts();
    }

    @Test(expected = PodcastNotFoundException.class)
    public void getPodcastDetails_whenCarNotFound() {
        given(podcastRepository.findByIdentifier("awkward-identifier")).willReturn(null);

        podcastService.getPodcastDetails("awkward-identifier");

    }

}