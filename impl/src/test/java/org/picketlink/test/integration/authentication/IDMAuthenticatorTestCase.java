/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.picketlink.test.integration.authentication;

import java.net.Authenticator;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.picketlink.authentication.internal.IdmAuthenticator;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.sample.IdentityLocator;
import org.picketlink.idm.model.sample.User;
import org.picketlink.test.integration.ArchiveUtils;

/**
 * <p>
 * Perform some authentication tests using the {@link IdmAuthenticator}, which is the default {@link Authenticator}.
 * </p>
 * 
 * @author Pedro Igor
 * 
 */
public class IDMAuthenticatorTestCase extends AbstractAuthenticatorTestCase {

    @Inject
    private IdentityManager identityManager;

    @Deployment
    public static WebArchive createDeployment() {
        return ArchiveUtils.create(IDMAuthenticatorTestCase.class);
    }
    
    @Before
    public void onSetup() {
        User john = IdentityLocator.getUser(this.identityManager, USER_NAME);

        if (john == null) {
            john = new User(USER_NAME);
            this.identityManager.add(john);
        }

        john.setEnabled(true);

        this.identityManager.update(john);

        Password password = new Password(USER_PASSWORD);

        this.identityManager.updateCredential(john, password);
    }
    
    @Override
    protected User doLockUserAccount() {
        User john = IdentityLocator.getUser(this.identityManager, USER_NAME);

        john.setEnabled(false);

        this.identityManager.update(john);
        
        return john;
    }
    
}