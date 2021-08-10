package com.example.etc.services;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ResttemplateService {

    private String loginOauth2 (String employeeId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic Y2xpZW50SWRQYXNzd29yZDpzZWNyZXQ="); // text ที่เข้ารหัสหลัง Basic มาจากเอา 'clientIdPassword:secret' ไป encode base64

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("client_id", "clientIdPassword");
        map.add("username", employeeId);
        map.add("password", "P@ssW0rd#ASC9");
        map.add("grant_type", "password");
        map.add("scope", "user");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String result = restTemplate.exchange("http://localhost/oauth/token", HttpMethod.POST,
                entity, String.class).getBody();

        return result;
    }

    private String callOboService(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String result = restTemplate.exchange("https://gcgpecsa-ad.azurewebsites.net/graphMeApi", HttpMethod.GET,
                entity, String.class).getBody();

        return result;
    }

//    public Map<String, Object> updatePartialIndividualCustomer(
//            UpdatePartialIndividualCustomerDTO dto
//    ) {
//        FundToken token = fundTokenRepository.findOneByUsername(username);
//        if (token == null) {
//            token = fcnAuthenticationService.token();
//        }
//        String url = fundBaseUrl + uploadPath;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        headers.add("X-Auth-Token", token.getAccessToken());
//
//        HttpEntity<Object> requestEntity = new HttpEntity<>(dto, headers);
//        Map<String, Object> response = new HashMap<>();
//        ThirdPartyApi s = new ThirdPartyApi();
//        try {
//            ResponseEntity<String> responseEntity = restTemplate.exchange(
//                    url,
//                    HttpMethod.PATCH,
//                    requestEntity,
//                    String.class
//            );
//            response.put("status", responseEntity.getBody());
//            s.setType("individualCustomer : " + dto.getCardNumber());
//            s.setComplete(true);
//            this.thirdPartyApiRepository.save(s);
//
//        } catch (HttpClientErrorException ex) {
//
//            s.setType("individualCustomer : " + dto.getCardNumber());
//            s.setComplete(false);
//            this.thirdPartyApiRepository.save(s);
//
//            response.put("code", ex.getRawStatusCode());
//            response.put("message", ex.getMessage());
//
//            //find error message
//            String[] err = StringUtils.substringsBetween(response.get("message").toString() , "\"", "\"");
//            String message = err[4];
//            if (ex.getRawStatusCode() == 401) {
//                fcnAuthenticationService.token();
//                throw new UnauthorizedException(
//                        HttpStatus.UNAUTHORIZED,
//                        message
//                );
//            }
//            throw new BadRequestException(
//                    HttpStatus.BAD_REQUEST,
//                    message
//            );
//        }
//        return response;
//    }
}
