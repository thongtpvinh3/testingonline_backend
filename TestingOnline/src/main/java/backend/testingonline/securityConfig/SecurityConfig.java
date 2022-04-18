package backend.testingonline.securityConfig;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
	
//	@Autowired
//	private StaffServiceImpl staffService;
//	
//	@Autowired
//	private Filter jwtRequestFilter;
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(staffService);
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/auth/login").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	}
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//		auth.setUserDetailsService(staffService);
//		auth.setPasswordEncoder(passwordEncoder());
//		return auth;
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
	

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/auth/login")
//								.permitAll()
//								.anyRequest().authenticated()
//								.and()
//								.formLogin()
//								.loginPage("/login")
//								.and()
//								.logout().invalidateHttpSession(true).clearAuthentication(true)
//								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//								.logoutSuccessUrl("/").permitAll();
//	}

	
}
