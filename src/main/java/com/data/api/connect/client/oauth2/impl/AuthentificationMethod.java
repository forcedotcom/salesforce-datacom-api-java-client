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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;

import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.oauth2.OAuthToken;
import com.data.api.connect.client.oauth2.UnauthenticatedSessionException;
import com.ning.http.client.AsyncHttpClient;


public abstract class AuthentificationMethod {
    
    /**
     * <p>
     * This URL is where the default Salesforce.com authentication is performed
     * (regardless of environment).
     * </p>
     */
    private String ENVIRONMENT = "https://api.data.com/connect/oauth2/token";
    // can reuse, share globally
    private final static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
    
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    
    public void setHttpClient(AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
    }
    
    public AsyncHttpClient getHttpClient() {
        return asyncHttpClient;
    }
    
    public abstract OAuthToken authenticate() throws IOException,
            UnauthenticatedSessionException, AuthenticationException;
    
    /**
     * <p>
     * Example (good) response: <code><pre>
     * {"access_token":"823e1893-da32-406e-9557-c0c7c3317103","token_type":"bearer","refresh_token":"9fc91191-61e2-4881-9e9e-06668e2c391d","expires_in":3426,"scope":"read write"}
     * </pre></code>
     * </p>
     * 
     * @param response
     *            The body of the Data.com API. A JSON string is expected, and
     *            the "access_token" is extracted and used for the
     *            {@link OAuthToken}
     * @return A {@link OAuthToken} is successful, or null if the response does
     *         not correspond to an access token
     * @throws AuthenticationException
     *             This is a wrapped exception, and will most likely contain
     *             either {@link JsonParseException},
     *             {@link JsonMappingException} or {@link IOException} as its
     *             cause
     */
    protected OAuthToken processResponse(String response) throws AuthenticationException {
        try {
            return mapper.readValue(response, OAuthToken.class);
        }
        catch (JsonParseException e) {
            throw new AuthenticationException(e);
        }
        catch (JsonMappingException e) {
            throw new AuthenticationException(e);
        }
        catch (IOException e) {
            throw new AuthenticationException(e);
        }
    }
    
    public String getENVIRONMENT() {
        return ENVIRONMENT;
    }
    
    
    public void setENVIRONMENT(String eNVIRONMENT) {
        ENVIRONMENT = eNVIRONMENT;
    }
}
