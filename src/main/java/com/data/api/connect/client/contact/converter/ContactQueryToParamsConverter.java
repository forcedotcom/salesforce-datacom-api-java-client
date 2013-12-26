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
package com.data.api.connect.client.contact.converter;

import java.util.ArrayList;
import java.util.List;

import com.data.api.connect.client.contact.ContactQuery;
import com.data.api.connect.client.oauth2.AuthenticationException;
import com.data.api.connect.client.util.CollectionUtils;
import com.data.api.connect.client.util.StringUtils;


public class ContactQueryToParamsConverter {
    
    public List<String> convert(ContactQuery query) throws AuthenticationException {
        List<String> params = new ArrayList<String>();
        
        
        if (!StringUtils.isBlank(query.getFirstName())) {
            params.add("firstName=" + query.getFirstName());
        }
        if (!StringUtils.isBlank(query.getLastName())) {
            params.add("lastName=" + query.getLastName());
        }
        if (!StringUtils.isBlank(query.getEmail())) {
            params.add("email=" + query.getEmail());
        }
        if (query.getShowDirectDialsOnly() != null) {
            params.add("showDirectDialsOnly=" + query.getShowDirectDialsOnly());
        }
        if (query.getShowInactive() != null) {
            params.add("showInactive=" + query.getShowInactive());
        }
        if (!CollectionUtils.isEmpty(query.getTitle())) {
            for (String title : query.getTitle()) {
                if (!StringUtils.isBlank(title)) {
                    params.add("title=" + title);
                }
            }
        }
        if (!CollectionUtils.isEmpty(query.getAreaCode())) {
            for (String areaCode : query.getAreaCode()) {
                if (!StringUtils.isBlank(areaCode)) {
                    params.add("areaCode=" + areaCode);
                }
            }
        }
        if (!CollectionUtils.isEmpty(query.getZipCode())) {
            for (String zipCode : query.getZipCode()) {
                if (!StringUtils.isBlank(zipCode)) {
                    params.add("zipCode=" + zipCode);
                }
            }
        }
        if (!CollectionUtils.isEmpty(query.getDepartment())) {
            for (Integer department : query.getDepartment()) {
                params.add("department=" + department);
            }
        }
        if (!CollectionUtils.isEmpty(query.getLevel())) {
            for (Integer level : query.getLevel()) {
                params.add("level=" + level);
            }
        }
        if (!CollectionUtils.isEmpty(query.getRevenue())) {
            for (Integer revenue : query.getRevenue()) {
                params.add("revenue=" + revenue);
            }
        }
        if (!CollectionUtils.isEmpty(query.getEmployee())) {
            for (Integer employee : query.getEmployee()) {
                params.add("employee=" + employee);
            }
        }
        if (!StringUtils.isBlank(query.getFortuneRank())) {
            params.add("fortuneRank=" + query.getFortuneRank());
        }
        if (!CollectionUtils.isEmpty(query.getOwnership())) {
            for (Integer ownership : query.getOwnership()) {
                params.add("ownership=" + ownership);
            }
        }
        if (query.getExcludeOwned() != null) {
            params.add("excludeOwned=" + query.getExcludeOwned());
        }
        if (!CollectionUtils.isEmpty(query.getCountry())) {
            for (String countyId : query.getCountry()) {
                params.add("country=" + countyId);
            }
        }
        if (!CollectionUtils.isEmpty(query.getState())) {
            for (String stateId : query.getState()) {
                params.add("state=" + stateId);
            }
        }
        if (!CollectionUtils.isEmpty(query.getIndustry())) {
            for (Integer industry : query.getIndustry()) {
                params.add("industry=" + industry);
            }
        }
        if (!CollectionUtils.isEmpty(query.getSubIndustry())) {
            for (Integer subIndustry : query.getSubIndustry()) {
                params.add("subIndustry=" + subIndustry);
            }
        }
        if (!CollectionUtils.isEmpty(query.getCompanyId())) {
            Integer ZERO = 0;
            for (Integer companyId : query.getCompanyId()) {
                if (companyId != null && !ZERO.equals(companyId)) {
                    params.add("companyId=" + companyId);
                }
            }
        }
        if (!CollectionUtils.isEmpty(query.getCompanyName())) {
            for (String companyName : query.getCompanyName()) {
                if (!StringUtils.isBlank(companyName)) {
                    params.add("companyName=" + companyName);
                }
            }
        }
        if (query.getLastUpdated() != null) {
            params.add("lastUpdated=" + query.getLastUpdated());
        }
        if (query.getOffset() != null) {
            params.add("offset=" + query.getOffset());
        }
        if (query.getPageSize() != null) {
            params.add("pageSize=" + query.getPageSize());
        }
        if (!StringUtils.isBlank(query.getOrder())) {
            params.add("order=" + query.getOrder());
        }
        if (!StringUtils.isBlank(query.getOrderBy())) {
            params.add("orderBy=" + query.getOrderBy());
        }
        
        if (CollectionUtils.isEmpty(params)) {
            throw new AuthenticationException("ContactQuery can not be empty.");
        }
        
        return params;
    }
}
