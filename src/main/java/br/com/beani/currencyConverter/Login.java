package br.com.beani.currencyConverter;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Login
{

	private String login;
    private String password;

    public boolean validateLogin() {
		if(login.equals("sim") && password.equals("sim")) {
			return true;
		}
    	return false;
    }
    
    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}