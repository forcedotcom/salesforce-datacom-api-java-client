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
package com.data.api.connect.client.contact.impl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.codehaus.jackson.map.ObjectMapper;

import com.data.api.connect.client.ENV;
import com.data.api.connect.client.oauth2.Authenticate;
import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.oauth2.IOAuthData;
import com.data.api.connect.client.oauth2.OAuthToken;
import com.data.api.connect.client.oauth2.UnauthenticatedSessionException;
import com.data.api.connect.client.oauth2.impl.AuthentificationMethod;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.Response;


public abstract class AbstractService {
    
    /**
     * <p>
     * The credentials data used to authenticate. Provided when this service is
     * instantiated.
     * </p>
     */
    protected IOAuthData oAutData;
    
    /**
     * <p>
     * Used to perform the authentication.
     * </p>
     */
    protected Authenticate authenticator = new Authenticate();
    
    
    /**
     * <p>
     * Our access Token used to communicate with the Data.com APIs.
     * </p>
     */
    protected OAuthToken oAuthToken;
    
    /**
     * <p>
     * A useful quick way to check if we have a accessToken (see
     * {@link #authToken}).
     * </p>
     */
    protected boolean authenticated;
    
    protected AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    
    // can reuse, share globally
    protected final static ObjectMapper mapper = new ObjectMapper();
    
    protected ENV env = ENV.getInstance();
    
    protected Response execute(BoundRequestBuilder builder) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        authenticateSession();
        
        Response response = null;
        try {
            setHeader(builder);
            response = builder.execute().get();
            if (isTokenExpired(response.getStatusCode())) {
                // update access token
                authenticateSession();
                setHeader(builder);
                response = builder.execute().get();
            }
        }
        catch (ExecutionException e) {
            throw new AuthenticationException(response.getStatusCode() + " "
                    + response.getResponseBody());
        }
        catch (InterruptedException e) {
            throw new AuthenticationException(response.getStatusCode() + " "
                    + response.getResponseBody());
        }
        
        return response;
    }
    
    /**
     * @throws IOException
     *             Thrown if anything goes wrong communicated with the Data.com
     *             API.</p>
     * @throws UnauthenticatedSessionException
     *             thrown if the credentials are incorrect.</p>
     * @throws AuthenticationException
     */
    protected void authenticateSession() throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        if (!authenticated) {
            AuthentificationMethod method = authenticator.getAuthentificationMethod(this.oAutData);
            oAuthToken = method.authenticate();
            authenticated = true;
        }
    }
    
    protected void setHeader(BoundRequestBuilder builder) {
        FluentCaseInsensitiveStringsMap header = new FluentCaseInsensitiveStringsMap();
        header.add("Accept", "application/json");
        header.add("Authorization", "BEARER " + oAuthToken.getAccessToken());
        header.add("x-ddc-client-id", oAutData.getClientCode());
        builder.setHeaders(header);
    }
    
    protected boolean isTokenExpired(int statusCode){
        return statusCode == 401;
    }
}
