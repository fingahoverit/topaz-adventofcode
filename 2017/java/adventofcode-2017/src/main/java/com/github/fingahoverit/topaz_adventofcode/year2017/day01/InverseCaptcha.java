package com.github.fingahoverit.topaz_adventofcode.year2017.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * {@link} http://adventofcode.com/2017/day/1
 */
public class InverseCaptcha {

	private String input = "951484596541141557316984781494999179679767747627132447513171626424561779662873157761442952212296685573452311263445163233493199211387838461594635666699422982947782623317333683978438123261326863959719777179228599319321138948466562743761584836184512984131635354116264899181952748224523953976485816295227945792555726121913344959454458829485471174415775278865324142733339789878929596275998341778873889585819916457474773252249179366599951454182657225576277834669222982366884688565754691273745959468648957498511326215934353963981471593984617554514519623785326888374742147318993423214834751785956958395133486656388454552769722562524415715913869946325551396638593398729938526424994348267935153555851552287223313383583669912941364344694725478258297498969517632881187394141593479818536194597976519254215932257653777455227477617957833273463216593642394215275314734914719726618923177918342664351954252667253233858814365351722938716621544226598956257753212248859258351363174782742336961425325381561575992352415514168782816173861148859478285339529151631429536819286498721812323861771638574344416879476255929929157912984151742613268754779685396125954595318134933366626594498249956388771723777242772654678448815844555372892574747735672368299826548254744359377667294764559334659523233146587568261116253155189394188696831691284711264872914348961888253386971994431352474717376878745948769171243242621219912378731755544387249443997382399714738351857752329367997665166956467544459817582915478514486541453932175598413554259672117364863112592515988922747164842668361925135551248923449968328385889877512156952725198691746951431443497496455761516486573476185321748523644283494181119399874324683922393547682851931435931276267766772798261563117954648576421741384823494187895272582575669685279986988357796138794326125852772995446355723211161523161886222562853546488411563473998633847953246787557146187696947831335722888918172961256498971868946237299523474841983527391489962357196433927251798764362493965894995592683296651874787384247326643886774966828657393717626591578321174832222434128817871765347278152799425565633521152643686221411129463425496425385516719682884157452772141585743166647191938727971366274357874252166721759";

	public static void main(String[] args) {
		InverseCaptcha instance = new InverseCaptcha();
		System.out.println("Part One = " + instance.partOne());
		System.out.println("Part Two = " + instance.partTwo());
	}

	private Long partOne() {

		return calculateCaptcha(input);
	}

	private Long partTwo() {

		return calculateCaptcha2(input);
	}

	private Long calculateCaptcha2(String captcha) {

		Integer captchaLength = captcha.length();
		Long captchaSum = 0l;

		// System.out.println("==captcha:" + captcha);

		for (int i = 0; i < captchaLength; i++) {

			int currentIndex = i;
			int testIndex = ((i + (captchaLength / 2)) <= (captchaLength - 1)) ? (i + (captchaLength / 2))
					: (i + (captchaLength / 2)) - captchaLength;

			if (captcha.charAt(currentIndex) == captcha.charAt(testIndex)) {
				captchaSum += Character.getNumericValue(captcha.charAt(currentIndex));
			}
		}

		// System.out.println("==result:" + captchaSum);

		return captchaSum;
	}

	private Long calculateCaptcha(String captcha) {

		Character lastDigit = null;
		Long captchaSum = 0l;

		// System.out.println("==captcha:" + captcha);

		for (Character digit : captcha.toCharArray()) {

			if (lastDigit != null && lastDigit.equals(digit)) {
				captchaSum += Character.getNumericValue(digit);
			}

			lastDigit = digit;
		}

		// Get it circular
		if (captcha.charAt(0) == captcha.charAt(captcha.length() - 1)) {
			captchaSum += Character.getNumericValue(captcha.charAt(0));
		}

		// System.out.println("==result:" + captchaSum);

		return captchaSum;
	}

	public static class InverseCaptchaTests {

		@Test
		public void testCalculateCaptcha_01() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "1122";
			assertEquals(3l, instance.calculateCaptcha(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha_02() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "1111";
			assertEquals(4l, instance.calculateCaptcha(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha_03() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "1234";
			assertEquals(0l, instance.calculateCaptcha(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha_04() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "91212129";
			assertEquals(9l, instance.calculateCaptcha(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha2_01() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "1212";
			assertEquals(6l, instance.calculateCaptcha2(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha2_02() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "1221";
			assertEquals(0l, instance.calculateCaptcha2(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha2_03() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "123425";
			assertEquals(4l, instance.calculateCaptcha2(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha2_04() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "123123";
			assertEquals(12l, instance.calculateCaptcha2(captcha).longValue());
		}

		@Test
		public void testCalculateCaptcha2_05() {
			InverseCaptcha instance = new InverseCaptcha();

			String captcha = "12131415";
			assertEquals(4l, instance.calculateCaptcha2(captcha).longValue());
		}
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
}
