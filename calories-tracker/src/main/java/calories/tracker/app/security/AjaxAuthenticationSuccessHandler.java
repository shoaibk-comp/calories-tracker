package calories.tracker.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication success handler for integration with SPA applications that need to login using Ajax instead of
 * a form post.
 *
 * Detects if it's an Ajax login request, and if so sends a customized response in the body, otherwise defaults
 * to the existing behavior for non-Ajax login attempts.
 */
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthenticationSuccessHandler defaultHandler;

    public AjaxAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String ajaxHeader = request.getHeader("X-Login-Ajax-call");

        if ("true".equals(ajaxHeader)) {
            response.getWriter().write("ok");
            response.getWriter().flush();
        } else {
            defaultHandler.onAuthenticationSuccess(request, response, authentication);
        }
    }
}

