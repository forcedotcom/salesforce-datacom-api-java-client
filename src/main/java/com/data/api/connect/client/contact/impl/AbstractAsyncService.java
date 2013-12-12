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
import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.oauth2.UnauthenticatedSessionException;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;


public abstract class AbstractAsyncService extends AbstractService {
    
    protected void execute(final BoundRequestBuilder builder,
            final AsyncCompletionHandler<Integer> handler) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        authenticateSession();
        setHeader(builder);
        builder.execute(new AsyncCompletionHandler<Integer>() {
            
            @Override
            public Integer onCompleted(Response response) throws Exception {
                if (isTokenExpired(response.getStatusCode())) {
                    authenticateSession();
                    setHeader(builder);
                    builder.execute(new AsyncCompletionHandler<Integer>() {
                        
                        @Override
                        public Integer onCompleted(Response response) throws Exception {
                            handler.onCompleted(response);
                            return response.getStatusCode();
                        }
                        
                        @Override
                        public void onThrowable(Throwable t) {
                            handler.onThrowable(t);
                        }
                    });
                }
                else {
                    handler.onCompleted(response);
                }
                return response.getStatusCode();
            }
            
            @Override
            public void onThrowable(Throwable t) {
                handler.onThrowable(t);
            }
        });
    }
}
