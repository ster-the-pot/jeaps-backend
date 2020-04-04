package jeaps.foodtruck.Token;
// get token
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jeaps.foodtruck.common.user.user.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Service("TokenService")
public class TokenService {

    public String getToken(User user, String type) {
        // the token will expire after 5 mins (we can make it longer).
        // request re-login to refresh token.
        long EXPIRE_TIME = 100 * 60 * 1000;
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        String token= JWT.create().withAudience(user.getUsername(), type)// store username into token
                .withExpiresAt(expireDate) // Set up the expire time.
                .sign(Algorithm.HMAC256(user.getPassword()));// make password as token's key.
        return token;
    }

    public String getUsername(HttpServletRequest req) {
        String token = req.getHeader("token");
        return JWT.decode(token).getAudience().get(0);
    }
}
