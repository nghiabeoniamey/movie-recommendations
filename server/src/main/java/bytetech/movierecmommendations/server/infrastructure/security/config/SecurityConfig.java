package bytetech.movierecmommendations.server.infrastructure.security.config;

import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import bytetech.movierecmommendations.server.infrastructure.security.exception.RestAuthenticationEntryPoint;
import bytetech.movierecmommendations.server.infrastructure.security.filter.TokenAuthenticationFilter;
import bytetech.movierecmommendations.server.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${frontend.url}")
    private String reactOrigin;

    @Value("${python.url}")
    private String pythonOrigin;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration publicApiConfig = new CorsConfiguration();
        publicApiConfig.setAllowedOrigins(Collections.singletonList("*"));
        publicApiConfig.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "*"));
        publicApiConfig.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "OPTIONS", "PATCH"));
        publicApiConfig.setAllowCredentials(false);
        publicApiConfig.setExposedHeaders(List.of("Authorization"));
        source.registerCorsConfiguration(MappingConstant.API_EMBED_PREFIX + "/**", publicApiConfig);

        CorsConfiguration defaultConfig = new CorsConfiguration();
        defaultConfig.setAllowedOrigins(List.of(reactOrigin, pythonOrigin));
        defaultConfig.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "*"));
        defaultConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        defaultConfig.setAllowCredentials(true);
        defaultConfig.setExposedHeaders(List.of("Authorization"));
        source.registerCorsConfiguration("/**", defaultConfig);

        return source;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, TokenAuthenticationFilter tokenAuthenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(c -> c.configurationSource(corsConfigurationSource()));
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.exceptionHandling(e -> e.authenticationEntryPoint(new RestAuthenticationEntryPoint()));
        http.authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/",
                        "/error",
                        "/favicon.ico",
                        "/*/*.png",
                        "/*/*.gif",
                        "/*/*.svg",
                        "/*/*.jpg",
                        "/*/*.html",
                        "/*/*.css",

                        "/*/*.js"
                )
                .permitAll());
        http.authorizeHttpRequests(
                auth -> auth.requestMatchers(
                                Helper.appendWildcard(MappingConstant.API_AUTH_PREFIX),
                                Helper.appendWildcard(MappingConstant.PATH_OAUTH2),
                                Helper.appendWildcard(MappingConstant.API_EMBED_PREFIX),
                                Helper.appendWildcard(MappingConstant.API_CONNECTION_RECOMMENDATION),
                                Helper.appendWildcard(MappingConstant.API_SWAGGER),
                                Helper.appendWildcard(MappingConstant.API_CONNECTION_PYTHON),
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        )
                        .permitAll()
        );
        http.authorizeHttpRequests(
                auth -> auth.requestMatchers(
                                Helper.appendWildcard(MappingConstant.API_COMMON))
                        .hasAnyAuthority(
                                RoleConstant.ADMIN.name(),
                                RoleConstant.USER.name()
                        )
        );
        http.authorizeHttpRequests( // config author api admin
                auth -> auth.requestMatchers(
                                Helper.appendWildcard(MappingConstant.API_ADMIN_PREFIX)
                        )
                        .hasAnyAuthority(RoleConstant.ADMIN.name())
        );
        http.authorizeHttpRequests( // config author api manager
                auth -> auth.requestMatchers(
                                Helper.appendWildcard(MappingConstant.API_USER_PREFIX)
                        )
                        .hasAnyAuthority(RoleConstant.USER.name())
        );
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
