package org.csanchez.jenkins.plugins.kubernetes;

import com.cloudbees.plugins.credentials.CredentialsProvider;
import org.jenkinsci.plugins.plaincredentials.StringCredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;

import hudson.Extension;
import hudson.util.Secret;

/**
 * @deprecated Use {@link StringCredentials}
 * @author <a href="mailto:andy.block@gmail.com">Andrew Block</a>
 */
@Deprecated
public class OpenShiftTokenCredentialImpl extends BaseStandardCredentials implements TokenProducer {


    private static final long serialVersionUID = 42L;

    private final Secret secret;

    @DataBoundConstructor
    public OpenShiftTokenCredentialImpl(CredentialsScope scope, String id, String description, Secret secret) {
        super(scope, id, description);
        this.secret = secret;
    }

    @Override
    public String getToken(String serviceAddress, String caCertData, boolean skipTlsVerify) {
        return secret.getPlainText();
    }
    
    public Secret getSecret() {
    	return secret;
    }

    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {

        @Override
        public String getDisplayName() {
            return "OpenShift OAuth token (Deprecated)";
        }

        @Override
        public boolean isApplicable(CredentialsProvider provider) {
            return false;
        }
    }

}
