package ISA.Model.response;



    import java.util.List;
    import java.util.Optional;

public class JwtResponse {
        private String token;
        private String type = "Bearer";

        private String username;

        private Optional<String> role;

        public JwtResponse(String accessToken,  String username,Optional <String> roles) {
            this.token = accessToken;

            this.username = username;

            this.role = roles;
        }

        public String getAccessToken() {
            return token;
        }

        public void setAccessToken(String accessToken) {
            this.token = accessToken;
        }

        public String getTokenType() {
            return type;
        }

        public void setTokenType(String tokenType) {
            this.type = tokenType;
        }





        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Optional<String> getRoles() {
            return role;
        }
    }

