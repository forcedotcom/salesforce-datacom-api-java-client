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
package com.data.api.connect.client.oauth2;

import java.io.IOException;
import java.util.Map;

import com.data.api.connect.client.oauth2.impl.AuthentificationMethod;
import com.data.api.connect.client.oauth2.impl.UsernamePasswordAuthentication;


/**
 * <p>
 * Authentice is where all authentication is done. It takes various input
 * credentials and uses those to request an Access Token from Data.com.
 * </p>
 * <p>
 * This token is then returned to the caller and can be used in future calls to
 * the Data.com app.
 * </p>
 */
public class Authenticate {
    
    /**
     * <p>
     * Uses the information in {@link IOAuthData} to figure out when
     * authentication type to use. If successful, it returns a
     * {@link OAuthToken} which can be used to perform various queries/action
     * against the Data.com app.
     * </p>
     * 
     * @param oAutData
     * @return {@link OAuthToken} if successful, otherwise the
     *         {@link UnauthenticatedSessionException} is thrown
     * @throws IOException
     *             thrown if the communication between client and server fails
     * @throws UnauthenticatedSessionException
     *             thrown if the credentials are invalid or another exception
     *             occurred
     * @throws AuthenticationException
     */
    public OAuthToken authenticate(Map<String, String> config) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        return getAuthentificationMethod(config).authenticate();
    }
    
    public AuthentificationMethod getAuthentificationMethod(Map<String, String> config)
            throws AuthenticationException {
        if (config != null && config.containsKey("grant_type")) {
            if ("password".equalsIgnoreCase(config.get("grant_type"))) {
                
                return new UsernamePasswordAuthentication(config);
            }
            else {
                throw new AuthenticationException(config.get("grant_type")
                        + " is an unrecognised Auth form.");
            }
        }
        
        return null;
    }
}
