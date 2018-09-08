package com.udacity.gradle.builditbigger.backend;

import com.example.jon.jokesmith.JokeGenerator;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "findJoke")
    public MyBean findJoke() {
        JokeGenerator joker = new JokeGenerator();
        String[] joke = joker.getJoke();
        String jokeText = joke[0] + "\n\n" + joke[1];

        MyBean response = new MyBean();
        response.setData(jokeText);

        return response;
    }

}
