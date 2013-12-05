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
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.oauth2.IOAuthData;
import com.data.api.connect.client.oauth2.OAuthToken;
import com.data.api.connect.client.oauth2.UnauthenticatedSessionException;


/**
 * @author Aleksey Kolesnik
 */
public class UsernamePasswordAuthentication extends AuthentificationMethod {
    
    
    private final IOAuthData oAuthData;
    
    public UsernamePasswordAuthentication (IOAuthData oAuthData) {
        this.oAuthData = oAuthData;
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
        String clientId = URLEncoder.encode(oAuthData.getClientKey(), "UTF-8");
        String username = oAuthData.getUsername();
        String password = oAuthData.getPassword();
        
        HttpPost httpPost = new HttpPost(getENVIRONMENT());
        
        httpPost.setHeader("Accept", "application/json");
        
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        
        nvps.add(new BasicNameValuePair("grant_type", "password"));
        nvps.add(new BasicNameValuePair("client_secret", "secret"));
        nvps.add(new BasicNameValuePair("client_id", clientId));
        nvps.add(new BasicNameValuePair("username", username));
        nvps.add(new BasicNameValuePair("password", password));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        
        CloseableHttpResponse response = getHttpClient().execute(httpPost);
        
        String responseBody = null;
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity);
            }
            // ensure it is fully consumed
            EntityUtils.consume(entity);
            return processResponse(responseBody);
        }
        throw new UnauthenticatedSessionException(statusCode + " " + responseBody);
    }
}
