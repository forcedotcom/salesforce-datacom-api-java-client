/*******************************************************************************
 * Copyright (c) 2013, salesforce.com, inc.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 * 
 *    Redistributions of source code must retain the above copyright notice, this list of conditions and the
 *    following disclaimer.
 * 
 *    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 *    the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 *    Neither the name of salesforce.com, inc. nor the names of its contributors may be used to endorse or
 *    promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package com.data.api.connect.client.oauth2.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Future;

import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.oauth2.OAuthToken;
import com.data.api.connect.client.oauth2.UnauthenticatedSessionException;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;


/**
 * @author Aleksey Kolesnik
 */
public class UsernamePasswordAuthentication extends AuthentificationMethod {
    
    
    private Map<String, String> config;
    
    public UsernamePasswordAuthentication (Map<String, String> config) {
        this.config = config;
    }
    
    /**
     * <p>
     * Authenticate using a username and password. This is discouraged by the
     * oauth flow, but it allows for transparent (and non human-intervention)
     * authentication).
     * </p>
     * 
     * @return The response retrieved from the REST API (usually an XML string
     *         with all the tokens)
     * @throws IOException
     * @throws UnauthenticatedSessionException
     * @throws AuthenticationException
     */
    @Override
    public OAuthToken authenticate() throws IOException, UnauthenticatedSessionException,
            AuthenticationException {
        String clientId = URLEncoder.encode(config.get("client_id"), "UTF-8");
        
        BoundRequestBuilder builder = getHttpClient().preparePost(
                (config.containsKey("server_url") ? config.get("server_url")
                        : "https://api.jigsaw.com/connect") + "/oauth2/token");
        
        builder.addHeader("Accept", "application/json");
        builder.addHeader("USER_CLIENT", "salesforce-datacom-api-java-client-v1");
        
        builder.addParameter("grant_type", "password");
        builder.addParameter("client_secret", "secret");
        builder.addParameter("client_id", clientId);
        builder.addParameter("username", config.get("username"));
        builder.addParameter("password", config.get("password"));
        
        Future<Response> f = builder.execute();
        int statusCode = -1;
        String responseBody = null;
        try {
            Response response = f.get();
            statusCode = response.getStatusCode();
            if (statusCode == 200) {
                return processResponse(responseBody = response.getResponseBody());
            }
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        throw new UnauthenticatedSessionException(statusCode + " " + responseBody);
    }
}
