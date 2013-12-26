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
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean getShowDirectDialsOnly() {
        return showDirectDialsOnly;
    }
    
    public void setShowDirectDialsOnly(Boolean showDirectDialsOnly) {
        this.showDirectDialsOnly = showDirectDialsOnly;
    }
    
    public Boolean getShowInactive() {
        return showInactive;
    }
    
    public void setShowInactive(Boolean showInactive) {
        this.showInactive = showInactive;
    }
    
    public List<String> getTitle() {
        return title;
    }
    
    public void setTitle(List<String> title) {
        this.title = title;
    }
    
    public List<String> getAreaCode() {
        return areaCode;
    }
    
    public void setAreaCode(List<String> areaCode) {
        this.areaCode = areaCode;
    }
    
    public List<String> getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(List<String> zipCode) {
        this.zipCode = zipCode;
    }
    
    public List<Integer> getDepartment() {
        return department;
    }
    
    public void setDepartment(List<Integer> department) {
        this.department = department;
    }
    
    public List<Integer> getLevel() {
        return level;
    }
    
    public void setLevel(List<Integer> level) {
        this.level = level;
    }
    
    public List<Integer> getRevenue() {
        return revenue;
    }
    
    public void setRevenue(List<Integer> revenue) {
        this.revenue = revenue;
    }
    
    public List<Integer> getEmployee() {
        return employee;
    }
    
    public void setEmployee(List<Integer> employee) {
        this.employee = employee;
    }
    
    public String getFortuneRank() {
        return fortuneRank;
    }
    
    public void setFortuneRank(String fortuneRank) {
        this.fortuneRank = fortuneRank;
    }
    
    public List<Integer> getOwnership() {
        return ownership;
    }
    
    public void setOwnership(List<Integer> ownership) {
        this.ownership = ownership;
    }
    
    public Boolean getExcludeOwned() {
        return excludeOwned;
    }
    
    public void setExcludeOwned(Boolean excludeOwned) {
        this.excludeOwned = excludeOwned;
    }
    
    public List<String> getCountry() {
        return country;
    }
    
    public void setCountry(List<String> country) {
        this.country = country;
    }
    
    public List<String> getState() {
        return state;
    }
    
    public void setState(List<String> state) {
        this.state = state;
    }
    
    public List<Integer> getIndustry() {
        return industry;
    }
    
    public void setIndustry(List<Integer> industry) {
        this.industry = industry;
    }
    
    public List<Integer> getSubIndustry() {
        return subIndustry;
    }
    
    public void setSubIndustry(List<Integer> subIndustry) {
        this.subIndustry = subIndustry;
    }
    
    public List<Integer> getCompanyId() {
        return companyId;
    }
    
    public void setCompanyId(List<Integer> companyId) {
        this.companyId = companyId;
    }
    
    public List<String> getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(List<String> companyName) {
        this.companyName = companyName;
    }
    
    public Integer getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public String getOrder() {
        return order;
    }
    
    public void setOrder(String order) {
        this.order = order;
    }
    
    public String getOrderBy() {
        return orderBy;
    }
    
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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
