package com.github.fingahoverit.topaz_adventofcode.year2017.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * {@link} http://adventofcode.com/2017/day/2
 */
public class CorruptionChecksum {

	private String input = "86	440	233	83	393	420	228	491	159	13	110	135	97	238	92	396\n"
			+ "3646	3952	3430	145	1574	2722	3565	125	3303	843	152	1095	3805	134	3873	3024\n"
			+ "2150	257	237	2155	1115	150	502	255	1531	894	2309	1982	2418	206	307	2370\n"
			+ "1224	343	1039	126	1221	937	136	1185	1194	1312	1217	929	124	1394	1337	168\n"
			+ "1695	2288	224	2667	2483	3528	809	263	2364	514	3457	3180	2916	239	212	3017\n"
			+ "827	3521	127	92	2328	3315	1179	3240	695	3144	3139	533	132	82	108	854\n"
			+ "1522	2136	1252	1049	207	2821	2484	413	2166	1779	162	2154	158	2811	164	2632\n"
			+ "95	579	1586	1700	79	1745	1105	89	1896	798	1511	1308	1674	701	60	2066\n"
			+ "1210	325	98	56	1486	1668	64	1601	1934	1384	69	1725	992	619	84	167\n"
			+ "4620	2358	2195	4312	168	1606	4050	102	2502	138	135	4175	1477	2277	2226	1286\n"
			+ "5912	6261	3393	431	6285	3636	4836	180	6158	6270	209	3662	5545	204	6131	230\n"
			+ "170	2056	2123	2220	2275	139	461	810	1429	124	1470	2085	141	1533	1831	518\n"
			+ "193	281	2976	3009	626	152	1750	1185	3332	715	1861	186	1768	3396	201	3225\n"
			+ "492	1179	154	1497	819	2809	2200	2324	157	2688	1518	168	2767	2369	2583	173\n"
			+ "286	2076	243	939	399	451	231	2187	2295	453	1206	2468	2183	230	714	681\n"
			+ "3111	2857	2312	3230	149	3082	408	1148	2428	134	147	620	128	157	492	2879";

	public static void main(String[] args) {
		CorruptionChecksum instance = new CorruptionChecksum();
		System.out.println("Part One = " + instance.partOne());
		System.out.println("Part Two = " + instance.partTwo());
	}

	private Long partOne() {

		return calculateChecksum(input);
	}

	private Long partTwo() {

		return calculateChecksum2(input);
	}

	private Long calculateChecksum2(String sheet) {

		Long checksum = 0l;

		for (String line : sheet.split("\n")) {

			String[] array = line.split("	");

			Integer currentI = null;
			Integer currentJ = null;

			for (int i = 0; i < array.length; i++) {

				currentI = Integer.valueOf(array[i]);

				for (int j = i; j < array.length; j++) {

					if (j != i) {

						currentJ = Integer.valueOf(array[j]);

						if (currentI % currentJ == 0) {
							// System.out.println(currentI + "/" + currentJ + "=" + currentI / currentJ);
							checksum += currentI / currentJ;
							break;
						}

						if (currentJ % currentI == 0) {
							// System.out.println(currentJ + "/" + currentI + "=" + currentJ / currentI);
							checksum += currentJ / currentI;
							break;
						}

						currentJ = null;
					}
				}

				if (currentJ != null) {
					break;
				}
				currentI = null;
			}

		}

		// System.out.println("checksum:" + checksum);

		return checksum;
	}

	private Long calculateChecksum(String sheet) {

		Long checksum = 0l;

		for (String line : sheet.split("\n")) {

			Integer min = null;
			Integer max = null;

			for (String number : line.split("	")) {

				Integer current = Integer.valueOf(number);

				if (min == null) {
					min = current;
				} else {
					if (current < min) {
						min = current;
					}
				}

				if (max == null) {
					max = current;
				} else {
					if (current > max) {
						max = current;
					}
				}
			}

			checksum += (max - min);
		}

		// System.out.println("==" + checksum);

		return checksum;
	}

	public static class CorruptionChecksumTests {

		@Test
		public void testCalculateChecksum_01() {
			CorruptionChecksum instance = new CorruptionChecksum();
			String sheet = "5	1	9	5\n" + "7	5	3\n" + "2	4	6	8";
			assertEquals(18l, instance.calculateChecksum(sheet).longValue());
		}

		@Test
		public void testCalculateChecksum2_01() {
			CorruptionChecksum instance = new CorruptionChecksum();
			String sheet = "5	9	2	8\n" + "9	4	7	3\n" + "3	8	6	5";
			assertEquals(9l, instance.calculateChecksum2(sheet).longValue());
		}
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
}
