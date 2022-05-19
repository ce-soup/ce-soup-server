package kr.soupio.soup.config.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()

            .cors().configurationSource { corsConfigure() }
            .and()

            .authorizeRequests()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/v3/api-docs").permitAll()
            .anyRequest().authenticated()
            .and()
    }

    fun corsConfigure(): CorsConfiguration {
        val cors = CorsConfiguration()
        cors.applyPermitDefaultValues()
        return cors
    }
}