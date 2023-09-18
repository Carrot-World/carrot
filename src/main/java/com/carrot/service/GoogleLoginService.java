package com.carrot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.carrot.domain.GoogleRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@PropertySource("/WEB-INF/application.properties")
public class GoogleLoginService {
	
	private final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
	private final String GOOGLE_TOKEN_INFO_URL = "https://oauth2.googleapis.com/tokeninfo";
	@Value("${oauth2.google.client-id}")
	private String GOOGLE_CLIENT_ID;
	@Value("${oauth2.google.client-secret}")
	private String GOOGLE_CLIENT_SECRET;
	@Value("${oauth2.google.redirect_uri}")
	private String LOGIN_REDIRECT_URL;
	public ResponseEntity<GoogleRequest> getGoogleAccessToken(String accessCode) {

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("code", accessCode);
		params.put("client_id", GOOGLE_CLIENT_ID);
		params.put("client_secret", GOOGLE_CLIENT_SECRET);
		params.put("redirect_uri", LOGIN_REDIRECT_URL);
		params.put("grant_type", "authorization_code");
		System.out.println("이곳은 구글서비스");
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

        String jsonBody = responseEntity.getBody();
        String idToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
	
        try {
			rootNode = objectMapper.readTree(jsonBody);
	        idToken = rootNode.get("id_token").asText();

		} catch (JsonMappingException e) {
			e.printStackTrace();
			System.out.println("구글 로그인: JsonMappingException 오류 ");
		} catch (JsonProcessingException e) {
			System.out.println("구글 로그인: JsonProcessingException 오류");
			e.printStackTrace();
		}
        Map<String, String> map=new HashMap<>();
        map.put("id_token",idToken);
        
        ResponseEntity<GoogleRequest> responseEntity2 = restTemplate.postForEntity(GOOGLE_TOKEN_INFO_URL, map, GoogleRequest.class);
        
        System.out.println("로그인서비스: "+responseEntity2);
		return responseEntity2;
	}
}
