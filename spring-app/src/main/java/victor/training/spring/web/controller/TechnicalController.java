package victor.training.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import victor.training.spring.props.WelcomeInfo;
import victor.training.spring.web.controller.dto.CurrentUserDto;

import javax.annotation.PostConstruct;
import java.util.Collections;

@RequiredArgsConstructor
@RestController
public class TechnicalController {

	@GetMapping("api/user/current")
	public CurrentUserDto getCurrentUsername() {
		CurrentUserDto dto = new CurrentUserDto();
		// SSO: KeycloakPrincipal<KeycloakSecurityContext>
		dto.username = "// TODO: get username";
		dto.role = "";//authentication.getAuthorities().iterator().next().getAuthority();
		dto.authorities = Collections.emptyList();//authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList());

		//<editor-fold desc="KeyCloak">
		//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		dto.username = authentication.getName();
//		dto.role = authentication.getAuthorities().iterator().next().getAuthority();
//		dto.authorities = stripRolePrefix(authentication.getAuthorities());
//    // Optional:
//		KeycloakPrincipal<KeycloakSecurityContext> keycloakToken =(KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
//		dto.fullName = keycloakToken.getKeycloakSecurityContext().getIdToken().getName();
//		log.info("Other details about user from ID Token: " + keycloakToken.getKeycloakSecurityContext().getIdToken().getOtherClaims());
		//</editor-fold>
		return dto;
	}

//	private List<String> stripRolePrefix(Collection<? extends GrantedAuthority> authorities) {
//		return authorities.stream()
//			.map(grantedAuthority -> grantedAuthority.getAuthority().substring("ROLE_".length()))
//			.collect(toList());
//	}

	@PostConstruct
	public void enableSecurityContextPropagationOverAsync() {
//		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

	@Autowired
	private WelcomeInfo welcomeInfo;

	// TODO [SEC] allow unsecured access
	@GetMapping("unsecured/welcome")
	public WelcomeInfo showWelcomeInfo(){
		return welcomeInfo;
	}

	// TODO [SEC] URL-pattern restriction: admin/**
	@GetMapping("admin/launch")
	public String restart() {
		return "What does this red button do?     ... [Missile Launched]";
	}
}