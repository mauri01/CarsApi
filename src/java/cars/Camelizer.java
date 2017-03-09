package cars;

public class Camelizer {
	public static String camelize(String str) {
		if (!str.contains("_") || hasUpperCaseCharacters(str))
			return str;
		String words[] = str.split("\\_");
		StringBuffer sb = new StringBuffer(words[0].toLowerCase());
		for (int i = 1; i < words.length; i++)
		{
			sb.append(Character.toUpperCase(words[i].charAt(0)));
			sb.append(words[i].substring(1).toLowerCase());
		}
		return sb.toString();
	}

	public static String unCamelize(String str) {
		StringBuffer sb = new StringBuffer();
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++)
		{

			if (i > 0 && charArray[i] >= 'A' && charArray[i] <= 'Z')
				sb.append("_");
			sb.append(charArray[i]);
		}
		return sb.toString().toLowerCase();
	}

	private static boolean hasUpperCaseCharacters(String str) {
		for (int i = 1; i < str.length(); i++)
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
				return true;
		return false;
	}
}
