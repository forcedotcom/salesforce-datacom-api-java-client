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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.data.api.connect.client.AsyncCallback;
import com.data.api.connect.client.contact.Contact;
import com.data.api.connect.client.contact.ContactQuery;
import com.data.api.connect.client.contact.ContactService;
import com.data.api.connect.client.contact.Contacts;
import com.data.api.connect.client.contact.converter.ContactQueryToParamsConverter;
import com.data.api.connect.client.contact.validator.ContactQueryValidator;
import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.oauth2.UnauthenticatedSessionException;
import com.data.api.connect.client.util.StringUtils;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.Response;


public class ContactServiceImpl extends AbstractAsyncService implements ContactService {
    
    private static final String GET_URL = "/data/v3/contacts/get/";
    private static final String PURCHASE_URL = "/data/v3/contacts/purchase/";
    private static final String SEARCH_URL = "/data/v3/contacts/search/";
    
    private ContactQueryToParamsConverter queryConverter;
    private ContactQueryValidator queryValidator;
    
    public ContactServiceImpl (Map<String, String> config) {
        this.config = config;
        
        queryConverter = new ContactQueryToParamsConverter();
        queryValidator = new ContactQueryValidator();
    }
    
    @Override
    public List<Contact> get(List<Long> ids) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        return executeSync(generateGetURL(ids));
    }
    
    @Override
    public void get(List<Long> ids, final AsyncCallback<List<Contact>> handler)
            throws IOException, UnauthenticatedSessionException, AuthenticationException {
        executeAsync(generateGetURL(ids), handler);
    }
    
    private String generateGetURL(List<Long> ids) throws AuthenticationException {
        if (ids == null || ids.size() == 0) {
            throw new AuthenticationException("Contact ids collection can not be empty.");
        }
        return (config.containsKey("server_url") ? config.get("server_url")
                : "https://api.jigsaw.com/connect")
                + GET_URL
                + StringUtils.join(new HashSet<Long>(ids), ",");
    }
    
    @Override
    public List<Contact> purchase(List<Long> ids) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        return executeSync(generatePurchaseURL(ids));
    }
    
    @Override
    public void purchase(List<Long> ids, final AsyncCallback<List<Contact>> handler)
            throws IOException, UnauthenticatedSessionException, AuthenticationException {
        executeAsync(generatePurchaseURL(ids), handler);
    }
    
    private String generatePurchaseURL(List<Long> ids) throws AuthenticationException {
        if (ids == null || ids.size() == 0) {
            throw new AuthenticationException("Contact ids collection can not be empty.");
        }
        return (config.containsKey("server_url") ? config.get("server_url")
                : "https://api.jigsaw.com/connect")
                + PURCHASE_URL
                + StringUtils.join(new HashSet<Long>(ids), ",");
    }
    
    @Override
    public List<Contact> search(ContactQuery query) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        return executeSync(generateSearchURL(query));
    }
    
    @Override
    public void search(ContactQuery query, AsyncCallback<List<Contact>> handler)
            throws IOException, UnauthenticatedSessionException, AuthenticationException {
        executeAsync(generateSearchURL(query), handler);
    }
    
    private String generateSearchURL(ContactQuery query) throws AuthenticationException {
        if (query == null) {
            throw new AuthenticationException("ContactQuery can not be empty.");
        }
        
        queryValidator.validate(query);
        
        List<String> params = queryConverter.convert(query);
        return (config.containsKey("server_url") ? config.get("server_url")
                : "https://api.jigsaw.com/connect")
                + SEARCH_URL
                + "?"
                + StringUtils.join(new HashSet<String>(params), "&");
    }
    
    private List<Contact> executeSync(String url) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        Response response = execute(asyncHttpClient.prepareGet(url));
        int statusCode = response.getStatusCode();
        String responseBody = null;
        if (statusCode == 200) {
            Contacts contacts = processResponse(
                    responseBody = response.getResponseBody(), Contacts.class);
            return contacts != null ? contacts.getContacts() : null;
        }
        
        throw new AuthenticationException(statusCode + " " + responseBody);
    }
    
    private void executeAsync(String url, final AsyncCallback<List<Contact>> handler)
            throws IOException, UnauthenticatedSessionException, AuthenticationException {
        execute(asyncHttpClient.prepareGet(url), new AsyncCompletionHandler<Integer>() {
            
            @Override
            public Integer onCompleted(Response response) throws Exception {
                int statusCode = response.getStatusCode();
                String responseBody = null;
                if (response.getStatusCode() == 200) {
                    Contacts contacts = processResponse(
                            responseBody = response.getResponseBody(), Contacts.class);
                    handler.onCompleted(contacts != null ? contacts.getContacts() : null,
                            response);
                }
                
                throw new AuthenticationException(statusCode + " " + responseBody);
            }
            
            @Override
            public void onThrowable(Throwable t) {
                handler.onThrowable(t);
            }
        });
    }
    
}
