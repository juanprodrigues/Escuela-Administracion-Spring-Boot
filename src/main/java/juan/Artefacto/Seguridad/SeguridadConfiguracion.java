package juan.Artefacto.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import juan.Artefacto.Servicio.UsuarioServicio;

@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServicio usuarioService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
	        http
	                .authorizeRequests()
	                    .antMatchers("/signup", "/registro", "/css/*", "/assets/*", "/img/*").permitAll()                   
	                    .antMatchers("/**").authenticated()
	                .and()
	                .formLogin()
	                    .loginPage("/login")
	                        .loginProcessingUrl("/logincheck")
	                        .usernameParameter("correo")
	                        .passwordParameter("clave")
	                        .defaultSuccessUrl("/", true)//home //donde te redirije cuando se logra con exito el login
	                        .failureUrl("/login?error=true")
	                        .permitAll()
	                .and()
	                    .logout()
	                        .logoutUrl("/logout")
	                        .logoutSuccessUrl("/login?logout=true")
	                        .permitAll()
	                        .deleteCookies("JSESSIONID")
	                .and()
	                    .csrf()
	                    .disable();
	        // @formatter:on
	}
}
