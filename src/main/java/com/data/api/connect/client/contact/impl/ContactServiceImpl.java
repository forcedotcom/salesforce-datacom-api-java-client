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
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.data.api.connect.client.contact.Contact;
import com.data.api.connect.client.contact.ContactService;
import com.data.api.connect.client.contact.Contacts;
import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.oauth2.IOAuthData;
import com.data.api.connect.client.oauth2.UnauthenticatedSessionException;
import com.data.api.connect.client.util.StringUtils;
import com.ning.http.client.Response;


public class ContactServiceImpl extends AbstractService implements ContactService {
    
    public ContactServiceImpl (IOAuthData oAutData) {
        this.oAutData = oAutData;
    }
    
    @Override
    public List<Contact> get(List<Long> ids) throws IOException,
            UnauthenticatedSessionException, AuthenticationException {
        int statusCode = -1;
        String responseBody = null;
        try {
            Response response = execute(asyncHttpClient.prepareGet(env.SERVER()
                    + "/connect/data/v3/contacts/get/" + StringUtils.join(ids, ",")));
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
    
    protected List<Contact> processResponse(String response)
            throws AuthenticationException {
        try {
            Contacts contacts = mapper.readValue(response, Contacts.class);
            return contacts != null ? contacts.getContacts() : null;
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
    
}
