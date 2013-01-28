package org.picketlink.idm.model;

import org.picketlink.idm.model.annotation.RelationshipIdentity;
import org.picketlink.idm.query.RelationshipQueryParameter;

/**
 * Models an oAuth authorization
 * 
 * @author Shane Bryzak
 * 
 */
public class Authorization extends AbstractAttributedType implements Relationship {

    private static final long serialVersionUID = -8044173562668371515L;

    public static final RelationshipQueryParameter USER = new RelationshipQueryParameter() {

        @Override
        public String getName() {
            return "user";
        }
    };;

    public static final RelationshipQueryParameter APPLICATION = new RelationshipQueryParameter() {
        
        @Override
        public String getName() {
            return "application";
        }
    };;
    
    private User user;
    private Agent application;

    public Authorization() {
        super();
    }

    public Authorization(User user, Agent application) {
        this.user = user;
        this.application = application;
    }

    @RelationshipIdentity
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @RelationshipIdentity
    public Agent getApplication() {
        return application;
    }

    public void setApplication(Agent application) {
        this.application = application;
    }

}