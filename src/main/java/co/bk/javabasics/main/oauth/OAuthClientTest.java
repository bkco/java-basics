package co.bk.javabasics.main.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 * https://oltu.apache.org/index.html
 *
 * GITHUB("GitHub", "https://github.com/login/oauth/authorize", "https://github.com/login/oauth/access_token"),
 *
 */
public class OAuthClientTest {

    public static void main(String[] args) throws OAuthSystemException, IOException {

        try {
            OAuthClientRequest request = OAuthClientRequest
                    .authorizationProvider(OAuthProviderType.GITHUB)
                    .setClientId("131804060198305")
                    .setRedirectURI("http://localhost:8080/")
                    .buildQueryMessage();

            //in web application you make redirection to uri:
            System.out.println("Visit: " + request.getLocationUri() + "\nand grant permission");

            System.out.print("Now enter the OAuth code you have received in redirect uri ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String code = br.readLine();

            request = OAuthClientRequest
                    .tokenProvider(OAuthProviderType.FACEBOOK)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId("131804060198305")
                    .setClientSecret("3acb294b071c9aec86d60ae3daf32a93")
                    .setRedirectURI("http://localhost:8080/")
                    .setCode(code)
                    .buildBodyMessage();

//            request = OAuthClientRequest
//                    .tokenProvider(OAuthProviderType.FACEBOOK)
//                    .setGrantType(GrantType.AUTHORIZATION_CODE)
//                    .setClientId("131804060198305")
//                    .setClientSecret("3acb294b071c9aec86d60ae3daf32a93")
//                    .setRedirectURI("http://localhost:8080/")
//                    .setCode(code)
//                    .buildBodyMessage();

            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            //Facebook is not fully compatible with OAuth 2.0 draft 10, access token response is
            //application/x-www-form-urlencoded, not json encoded so we use dedicated response class for that
            //Own response class is an easy way to deal with oauth providers that introduce modifications to
            //OAuth specification
            GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(request, GitHubTokenResponse.class);

            System.out.println(
                    "Access Token: " + oAuthResponse.getAccessToken() + ", Expires in: " + oAuthResponse
                            .getExpiresIn());
        } catch (OAuthProblemException e) {
            System.out.println("OAuth error: " + e.getError());
            System.out.println("OAuth error description: " + e.getDescription());
        }
    }

    @Test
    public void dummyTest() {

    }
}