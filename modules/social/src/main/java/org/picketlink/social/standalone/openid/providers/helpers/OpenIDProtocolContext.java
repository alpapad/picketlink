/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.picketlink.social.standalone.openid.providers.helpers;

import org.picketlink.identity.federation.core.interfaces.ProtocolContext;
import org.picketlink.identity.federation.core.interfaces.SecurityTokenProvider;
import org.picketlink.social.standalone.openid.providers.helpers.OpenIDProviderManager.OpenIDMessage;

import javax.xml.namespace.QName;

/**
 * {@code ProtocolContext} for the OpenID Layer
 *
 * @author Anil.Saldhana@redhat.com
 * @since Jan 20, 2011
 */
public class OpenIDProtocolContext implements ProtocolContext {

    public static final String OPENID_1_0_NS = "urn:openid:1:0";
    public static final String OPENID_1_1_NS = "urn:openid:1:1";
    public static final String OPENID_2_0_NS = "urn:openid:2:0";

    /**
     * The response message generated by the STS
     */
    protected OpenIDProviderManager.OpenIDMessage responseMessage;

    /**
     * The list of request parameters for use by the STS
     */
    protected OpenIDParameterList requestParameterList;

    /**
     * The Server Manager needs an endpoint
     */
    protected String endpoint;

    /**
     * Should the STS issue an error?
     */
    protected Boolean issueError = Boolean.FALSE;

    /**
     * What is the text the STS should use for the error?
     */
    protected String errorText = null;

    /**
     * The OpenID mode
     */
    protected MODE mode;

    /**
     * STS uses the AUTH_HOLDER information for processing authentication requests
     */
    protected AUTH_HOLDER authenticationHolder;

    /**
     * An Enum indicating the OpenID mode
     */
    public enum MODE {
        ASSOCIATE, CHECK_ID_SETUP, CHECK_ID_IMMEDIATE, CHECK_AUTHENTICATION
    }

    ;

    /**
     * Class to hold the information for authentication requests
     */
    public static class AUTH_HOLDER {

        private String userSelectedId, userSelectedClaimedId;
        private boolean authenticatedAndApproved;

        public AUTH_HOLDER(String userSelectedId, String userSelectedClaimedId, boolean authenticatedAndApproved) {
            this.userSelectedId = userSelectedId;
            this.userSelectedClaimedId = userSelectedClaimedId;
            this.authenticatedAndApproved = authenticatedAndApproved;
        }

        @Override
        public String toString() {
            return "AUTH_HOLDER [userSelectedId=" + userSelectedId + ", userSelectedClaimedId=" + userSelectedClaimedId
                    + ", authenticatedAndApproved=" + authenticatedAndApproved + "]";
        }

        public String getUserSelectedId() {
            return userSelectedId;
        }

        public String getUserSelectedClaimedId() {
            return userSelectedClaimedId;
        }

        public boolean isAuthenticatedAndApproved() {
            return authenticatedAndApproved;
        }
    }

    /**
     * Get the holder of authentication information
     *
     * @return
     */
    public AUTH_HOLDER getAuthenticationHolder() {
        return authenticationHolder;
    }

    /**
     * Set an authentication holder
     *
     * @param authenticationHolder
     */
    public void setAuthenticationHolder(AUTH_HOLDER authenticationHolder) {
        this.authenticationHolder = authenticationHolder;
    }

    /**
     * Get the Open ID mode
     *
     * @return
     */
    public MODE getMode() {
        return this.mode;
    }

    /**
     * Set the OpenID mode
     *
     * @param theMode
     */
    public void setMode(MODE theMode) {
        this.mode = theMode;
    }

    /**
     * Get the error text (Null by default)
     *
     * @return
     */
    public String getErrorText() {
        return errorText;
    }

    /**
     * Set the error text
     *
     * @param errorText
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * Should the STS issue an error response token
     *
     * @return
     */
    public Boolean getIssueError() {
        return issueError;
    }

    /**
     * Set the error response token for the STS
     *
     * @param issueError
     */
    public void setIssueError(Boolean issueError) {
        this.issueError = issueError;
    }

    /**
     * Get the endpoint address
     *
     * @return
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Set the endpoint address
     *
     * @param endpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Get the request parameter list
     *
     * @return an instanceof {@code OpenIDParameterList}
     */
    public OpenIDParameterList getRequestParameterList() {
        return requestParameterList;
    }

    /**
     * Set the request parameter list
     *
     * @param requestParameterList an instanceof {@code OpenIDParameterList}
     */
    public void setRequestParameterList(OpenIDParameterList requestParameterList) {
        this.requestParameterList = requestParameterList;
    }

    /**
     * Get the {@code OpenIDMessage} response message that the STS has generated
     *
     * @return
     */
    public OpenIDMessage getResponseMessage() {
        return responseMessage;
    }

    /**
     * Set the {@code OpenIDMessage} response message
     *
     * @param responseMessage
     */
    public void setResponseMessage(OpenIDMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * @see org.picketlink.identity.federation.core.interfaces.ProtocolContext#serviceName()
     */
    public String serviceName() {
        return null;
    }

    /**
     * @see org.picketlink.identity.federation.core.interfaces.ProtocolContext#tokenType()
     */
    public String tokenType() {
        return OPENID_1_0_NS;
    }

    /**
     * @see org.picketlink.identity.federation.core.interfaces.ProtocolContext#getQName()
     */
    public QName getQName() {
        return new QName(OPENID_1_0_NS);
    }

    /**
     * @see org.picketlink.identity.federation.core.interfaces.ProtocolContext#family()
     */
    public String family() {
        return SecurityTokenProvider.FAMILY_TYPE.OPENID.name();
    }
}