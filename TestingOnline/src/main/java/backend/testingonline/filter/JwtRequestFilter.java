package backend.testingonline.filter;

//@Component
public class JwtRequestFilter  {
//
//	@Autowired
//	private StaffServiceImpl impl;
//	
//	@Autowired
//	private JwtUtil jwtUtil;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		final String authorizationHeader = request.getHeader("Authorization");
//		
//		String username = null;
//		String jwt = null;
//		
//		if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
//			jwt = authorizationHeader.substring(7);
//			username = jwtUtil.extractUsername(jwt);
//		}
//		
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userDetails = this.impl.loadUserByUsername(username);
//			if(jwtUtil.validateToken(jwt, userDetails)) {
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		}
//		filterChain.doFilter(request, response);
//	}
}
