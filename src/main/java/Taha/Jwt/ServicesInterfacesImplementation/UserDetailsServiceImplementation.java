package Taha.Jwt.ServicesInterfacesImplementation;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User; /*importe DE User*/
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Taha.Jwt.Entities.AppUser;
import Taha.Jwt.ServicesInterfaces.AccountServices;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	private AccountServices accountServices;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser user = accountServices.findUserByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});

		return new User(user.getUsername(), user.getPassword(), authorities);
		
	}

}
