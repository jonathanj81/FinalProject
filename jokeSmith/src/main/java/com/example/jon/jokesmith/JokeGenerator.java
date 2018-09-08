package com.example.jon.jokesmith;

import java.util.Random;

public class JokeGenerator {

    private String[] jokeQuestions = {"Why did the storm trooper buy an iPhone?",
            "Why does Snoop Dogg carry an umbrella?",
            "What do you get when you cross an elephant and a rhino?",
            "What do you call a deer with no eyes?",
            "The past, present and future walk into a bar...",
            "What did the green grape say to the purple grape?",
            "Why did the dinosaur cross the road?",
            "Which US state has the smallest soft drinks?",
            "Why is Peter Pan always flying?",
            "Why did the yogurt go to the art exhibit?"};
    private String[] jokeAnswers = {"Because he couldn't find the Droid he was looking for!",
            "For drizzle!",
            "Elephino!",
            "No eye deer!",
            "It was tense.",
            "Breathe!",
            "Because chickens didn't exist yet!",
            "Minnesota!",
            "He neverlands!",
            "Because it was cultured!"};

    private Random rand = new Random();

    public String[] getJoke(){
        int whichJoke = rand.nextInt(10);
        return new String[]{jokeQuestions[whichJoke], jokeAnswers[whichJoke]};
    }
}
