package com.siteupordown.siteupordown.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLCheck {
    private final String site_up="Site is up";
    private final String site_down="Site is down";
    private final String invalid_url="Invalid Url";

    @GetMapping("/check")
    public String checkSiteStatus(@RequestParam String url){
        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int response_code=con.getResponseCode();
            if(response_code!=200 && response_code!=300){
                return site_down;
            }
            else{
                return site_up;
            }
        } catch (MalformedURLException e) {
            return invalid_url;
        } catch (IOException e) {
            return site_down;
        }
        
    }
    
}
