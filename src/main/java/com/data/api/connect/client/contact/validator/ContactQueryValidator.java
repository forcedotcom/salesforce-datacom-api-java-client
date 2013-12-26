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
package com.data.api.connect.client.contact.validator;

import java.util.Collection;

import com.data.api.connect.client.contact.ContactQuery;
import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.util.CollectionUtils;
import com.data.api.connect.client.util.StringUtils;


public class ContactQueryValidator {
    
    public void validate(ContactQuery query) throws AuthenticationException {
        if (StringUtils.isBlank(query.getFirstName())
                && StringUtils.isBlank(query.getLastName())
                && StringUtils.isBlank(query.getEmail())
                && CollectionUtils.isEmpty(query.getTitle())
                && CollectionUtils.isEmpty(query.getAreaCode())
                && CollectionUtils.isEmpty(query.getZipCode())
                && CollectionUtils.isEmpty(query.getDepartment())
                && CollectionUtils.isEmpty(query.getLevel())
                && CollectionUtils.isEmpty(query.getRevenue())
                && CollectionUtils.isEmpty(query.getEmployee())
                && StringUtils.isBlank(query.getFortuneRank())
                && CollectionUtils.isEmpty(query.getOwnership())
                && CollectionUtils.isEmpty(query.getCountry())
                && CollectionUtils.isEmpty(query.getState())
                && CollectionUtils.isEmpty(query.getIndustry())
                && CollectionUtils.isEmpty(query.getSubIndustry())
                && CollectionUtils.isEmpty(query.getCompanyId())
                && CollectionUtils.isEmpty(query.getCompanyName())) {
            throw new AuthenticationException("ContactQuery can not be empty.");
        }
        // are all fields valid
        if (isNotValid(query.getFirstName())) {
            throwBadValueError("first name");
        }
        if (isNotValid(query.getLastName())) {
            throwBadValueError("last name");
        }
        if (isNotValid(query.getEmail())) {
            throwBadValueError("email");
        }
        if (isNotValid(query.getTitle())) {
            throwBadValueError("title");
        }
        if (isNotValid(query.getAreaCode())) {
            throwBadValueError("area code");
        }
        if (isNotValid(query.getZipCode())) {
            throwBadValueError("zip code");
        }
        
        if (!CollectionUtils.isEmpty(query.getCountry())) {
            for (String countyId : query.getCountry()) {
                if (!StringUtils.isNumeric(countyId)) {
                    throwBadValueError("county");
                }
            }
        }
        if (!CollectionUtils.isEmpty(query.getState())) {
            for (String stateId : query.getState()) {
                if (!StringUtils.isNumeric(stateId)) {
                    throwBadValueError("state");
                }
            }
        }
        if (!CollectionUtils.isEmpty(query.getCompanyId())) {
            Integer ZERO = 0;
            for (Integer value : query.getCompanyId()) {
                if (value == null || ZERO.equals(value)) {
                    throwBadValueError("company id");
                }
            }
        }
        if (isNotValid(query.getCompanyName())) {
            throwBadValueError("company name");
        }
    }
    
    private boolean isNotValid(Collection<String> collection) {
        if (!CollectionUtils.isEmpty(collection)) {
            for (String value : collection) {
                if (value == null || StringUtils.isBlank(value) || "0".equals(value)) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
    
    private boolean isNotValid(String value) {
        if (!StringUtils.isBlank(value)) {
            if ("0".equals(value)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    
    private void throwBadValueError(String field) throws AuthenticationException {
        throw new AuthenticationException("ContactQuery : Bad {0} value".replace("{0}",
                field));
    }
}
