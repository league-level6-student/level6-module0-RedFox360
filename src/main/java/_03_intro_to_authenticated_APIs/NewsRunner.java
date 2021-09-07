package _03_intro_to_authenticated_APIs;

import javax.swing.*;

public class NewsRunner {

    public static void main(String[] args) {
        NewsApi newsApi = new NewsApi();
        //newsApi.testRequest();
        String story = newsApi.findStory("grass");
        System.out.println(story);
    }

}
