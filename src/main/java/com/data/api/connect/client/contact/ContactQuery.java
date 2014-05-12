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
package com.data.api.connect.client.contact;

import java.util.List;


public class ContactQuery {
    
    private String firstName;
    private String lastName;
    private String email;
    private Boolean showDirectDialsOnly;
    private Boolean showInactive;
    private List<String> title;
    private List<String> areaCode;
    private List<String> zipCode;
    private List<Integer> department;
    private List<Integer> level;
    private List<Integer> revenue;
    private List<Integer> employee;
    private String fortuneRank;
    private List<Integer> ownership;
    private Boolean excludeOwned;
    private List<String> country;
    private List<String> state;
    private List<Integer> industry;
    private List<Integer> subIndustry;
    private List<Integer> companyId;
    private List<String> companyName;
    private Integer lastUpdated;
    private Integer offset;
    private Integer pageSize;
    private String order;
    private String orderBy;
    
    public String getFirstName() {
        return firstName;
    }
    
    public ContactQuery setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public ContactQuery setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    public String getEmail() {
        return email;
    }
    
    public ContactQuery setEmail(String email) {
        this.email = email;
        return this;
    }
    
    public Boolean getShowDirectDialsOnly() {
        return showDirectDialsOnly;
    }
    
    public ContactQuery setShowDirectDialsOnly(Boolean showDirectDialsOnly) {
        this.showDirectDialsOnly = showDirectDialsOnly;
        return this;
    }
    
    public Boolean getShowInactive() {
        return showInactive;
    }
    
    public ContactQuery setShowInactive(Boolean showInactive) {
        this.showInactive = showInactive;
        return this;
    }
    
    public List<String> getTitle() {
        return title;
    }
    
    public ContactQuery setTitle(List<String> title) {
        this.title = title;
        return this;
    }
    
    public List<String> getAreaCode() {
        return areaCode;
    }
    
    public ContactQuery setAreaCode(List<String> areaCode) {
        this.areaCode = areaCode;
        return this;
    }
    
    public List<String> getZipCode() {
        return zipCode;
    }
    
    public ContactQuery setZipCode(List<String> zipCode) {
        this.zipCode = zipCode;
        return this;
    }
    
    public List<Integer> getDepartment() {
        return department;
    }
    
    public ContactQuery setDepartment(List<Integer> department) {
        this.department = department;
        return this;
    }
    
    public List<Integer> getLevel() {
        return level;
    }
    
    public ContactQuery setLevel(List<Integer> level) {
        this.level = level;
        return this;
    }
    
    public List<Integer> getRevenue() {
        return revenue;
    }
    
    public ContactQuery setRevenue(List<Integer> revenue) {
        this.revenue = revenue;
        return this;
    }
    
    public List<Integer> getEmployee() {
        return employee;
    }
    
    public ContactQuery setEmployee(List<Integer> employee) {
        this.employee = employee;
        return this;
    }
    
    public String getFortuneRank() {
        return fortuneRank;
    }
    
    public ContactQuery setFortuneRank(String fortuneRank) {
        this.fortuneRank = fortuneRank;
        return this;
    }
    
    public List<Integer> getOwnership() {
        return ownership;
    }
    
    public ContactQuery setOwnership(List<Integer> ownership) {
        this.ownership = ownership;
        return this;
    }
    
    public Boolean getExcludeOwned() {
        return excludeOwned;
    }
    
    public ContactQuery setExcludeOwned(Boolean excludeOwned) {
        this.excludeOwned = excludeOwned;
        return this;
    }
    
    public List<String> getCountry() {
        return country;
    }
    
    public ContactQuery setCountry(List<String> country) {
        this.country = country;
        return this;
    }
    
    public List<String> getState() {
        return state;
    }
    
    public ContactQuery setState(List<String> state) {
        this.state = state;
        return this;
    }
    
    public List<Integer> getIndustry() {
        return industry;
    }
    
    public ContactQuery setIndustry(List<Integer> industry) {
        this.industry = industry;
        return this;
    }
    
    public List<Integer> getSubIndustry() {
        return subIndustry;
    }
    
    public ContactQuery setSubIndustry(List<Integer> subIndustry) {
        this.subIndustry = subIndustry;
        return this;
    }
    
    public List<Integer> getCompanyId() {
        return companyId;
    }
    
    public ContactQuery setCompanyId(List<Integer> companyId) {
        this.companyId = companyId;
        return this;
    }
    
    public List<String> getCompanyName() {
        return companyName;
    }
    
    public ContactQuery setCompanyName(List<String> companyName) {
        this.companyName = companyName;
        return this;
    }
    
    public Integer getLastUpdated() {
        return lastUpdated;
    }
    
    public ContactQuery setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public ContactQuery setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public ContactQuery setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    
    public String getOrder() {
        return order;
    }
    
    public ContactQuery setOrder(String order) {
        this.order = order;
        return this;
    }
    
    public String getOrderBy() {
        return orderBy;
    }
    
    public ContactQuery setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
    
    @Override
    public String toString() {
        return "SearchContactRequest [firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", showDirectDialsOnly=" + showDirectDialsOnly
                + ", showInactive=" + showInactive + ", title=" + title + ", areaCode="
                + areaCode + ", zipCode=" + zipCode + ", department=" + department
                + ", level=" + level + ", revenue=" + revenue + ", employee=" + employee
                + ", fortuneRank=" + fortuneRank + ", ownership=" + ownership
                + ", excludeOwned=" + excludeOwned + ", country=" + country + ", state="
                + state + ", industry=" + industry + ", subIndustry=" + subIndustry
                + ", companyId=" + companyId + ", companyName=" + companyName
                + ", lastUpdated=" + lastUpdated + ", offset=" + offset + ", pageSize="
                + pageSize + ", order=" + order + ", orderBy=" + orderBy + "]";
    }
}
