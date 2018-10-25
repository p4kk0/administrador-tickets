package mx.servis.admonticket.util;

import java.util.Arrays;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordGenerator;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public final class PasswordRandomGenerator {
	
	public static void main(String[] args) {
		PasswordRandomGenerator p = new PasswordRandomGenerator();
		System.out.println(p.generateRandomPassword());
	}
	
	public static String generateRandomPassword() {

		List rules = Arrays.asList(
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1), 
				new CharacterRule(EnglishCharacterData.Digit, 1), 
//				new CharacterRule(EnglishCharacterData.Alphabetical, 1), 
				new CharacterRule(EnglishCharacterData.UpperCase, 1));
		
		

		PasswordGenerator generator = new PasswordGenerator();
		String password = generator.generatePassword(8, rules);
		return password;
	}
	
	public static boolean isPasswordValid(String password) {
		boolean isPasswordValid = false;
		PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(8, 16),
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1), 
				new CharacterRule(EnglishCharacterData.Digit, 1),
				new CharacterRule(EnglishCharacterData.Special, 1), 
				new WhitespaceRule()));

		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			isPasswordValid = true;
			System.out.println("The supplied password - " + password + " is valid.");
		} else {
			isPasswordValid = false;
			final StringBuilder message = new StringBuilder();
			validator.getMessages(result).stream().forEach(message::append);
			System.out.println("The supplied password - " + password + " is invalid." + message);
		}
		return isPasswordValid;
	}

}
