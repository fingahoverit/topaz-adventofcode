package com.github.fingahoverit.topaz_adventofcode.year2017.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * {@link} http://adventofcode.com/2017/day/13
 */
public class PacketScanners {

	private String input = "0: 4\r\n" + "1: 2\r\n" + "2: 3\r\n" + "4: 4\r\n" + "6: 6\r\n" + "8: 5\r\n" + "10: 6\r\n"
			+ "12: 6\r\n" + "14: 6\r\n" + "16: 8\r\n" + "18: 8\r\n" + "20: 9\r\n" + "22: 12\r\n" + "24: 8\r\n"
			+ "26: 8\r\n" + "28: 8\r\n" + "30: 12\r\n" + "32: 12\r\n" + "34: 8\r\n" + "36: 12\r\n" + "38: 10\r\n"
			+ "40: 12\r\n" + "42: 12\r\n" + "44: 10\r\n" + "46: 12\r\n" + "48: 14\r\n" + "50: 12\r\n" + "52: 14\r\n"
			+ "54: 14\r\n" + "56: 12\r\n" + "58: 14\r\n" + "60: 12\r\n" + "62: 14\r\n" + "64: 18\r\n" + "66: 14\r\n"
			+ "68: 14\r\n" + "72: 14\r\n" + "76: 14\r\n" + "82: 14\r\n" + "86: 14\r\n" + "88: 18\r\n" + "90: 14\r\n"
			+ "92: 17";

	public static void main(String[] args) {
		PacketScanners ps = new PacketScanners();
		System.out.println("Part One = " + ps.partOne());
		System.out.println("Part Two = " + ps.partTwo());
	}

	public static class FirewallLayer {

		private Integer position = 0;
		private Integer depth = 0;
		private boolean goForward = true;

		public FirewallLayer(Integer position, Integer depth) {
			this.position = position;
			this.depth = depth;
		}

		public Integer getPosition() {
			return position;
		}

		public void setPosition(Integer position) {
			this.position = position;
		}

		public Integer getDepth() {
			return depth;
		}

		public void setDepth(Integer depth) {
			this.depth = depth;
		}

		public boolean isGoForward() {
			return goForward;
		}

		public void setGoForward(boolean goForward) {
			this.goForward = goForward;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			// sb.append("depth:").append(getDepth()).append("|position:").append(getPosition());
			// sb.append(getPosition());
			sb.append(getDepth());
			return sb.toString();
		}
	}

	private Long partOne() {

		// build firewall
		List<FirewallLayer> firewall = buildFirewall();

		// crossFirewall
		List<Integer> caughtPositions = crossFirewall(firewall);

		// calculate severity
		Long result = calcultateSeverity(firewall, caughtPositions);
		return result;
	}

	private int partTwo() {

		// build firewall
		List<FirewallLayer> firewall = buildFirewall();

		int delay = 0;
		while (crossFirewall(firewall, delay).size() != 0) {
			delay++;
		}

		return delay;
	}

	private boolean isAtRoot(int depth, int position) {

		int range = (depth * 2) - 2;

		return (position % range) == 0;
	}

	private Long calcultateSeverity(List<FirewallLayer> firewall, List<Integer> caughtPositions) {

		Long result = 0l;
		for (Integer caughtPosition : caughtPositions) {

			result += caughtPosition * firewall.get(caughtPosition).getDepth();
		}

		return result;
	}

	private void scan(List<FirewallLayer> firewall) {
		firewall.stream().forEach((layer) -> {

			if (layer != null) {
				if (layer.isGoForward()) {
					layer.setPosition(layer.getPosition() + 1);
				} else {
					layer.setPosition(layer.getPosition() - 1);
				}

				if (Integer.valueOf(layer.getDepth() - 1).equals(layer.getPosition())
						|| Integer.valueOf(0).equals(layer.getPosition())) {
					layer.setGoForward(!layer.isGoForward());
				}
			}
		});
	}

	private Integer move(Integer currentPosition) {
		if (currentPosition == null) {
			return 0;
		}

		return currentPosition + 1;
	}

	private List<Integer> crossFirewall(List<FirewallLayer> firewall, int delay) {

		List<Integer> caughtPositions = new ArrayList<>();
		for (int i = 0; i < firewall.size(); i++) {

			// is caught
			if (firewall.get(i) != null) {
				if (isAtRoot(firewall.get(i).getDepth(), i + delay)) {
					caughtPositions.add(i);
					break;
				}
			}
		}

		// StringBuilder sb = new StringBuilder();
		// sb.append("[caughts|delay:").append(delay).append("]");
		// if (!caughtPositions.isEmpty()) {
		// sb.append("[depth:").append(firewall.get(caughtPositions.get(0)).getDepth()).append("]");
		// }
		// sb.append(Arrays.toString(caughtPositions.toArray()));
		// sb.append(Arrays.toString(firewall.toArray()));
		//
		// System.out.println(sb.toString());

		return caughtPositions;
	}

	private List<Integer> crossFirewall(List<FirewallLayer> firewall) {

		List<Integer> caughtPositions = new ArrayList<>();
		Integer myPosition = null;
		for (int i = 0; i < firewall.size(); i++) {

			// move
			myPosition = move(myPosition);

			// is caught
			if (firewall.get(i) != null) {
				if (firewall.get(i).getPosition() == 0) {
					caughtPositions.add(i);
				}
			}

			// scan
			scan(firewall);

			// System.out.println("i:" + i + "---" + Arrays.toString(firewall.toArray()));
		}

		return caughtPositions;
	}

	private List<FirewallLayer> buildFirewall() {

		List<FirewallLayer> flList = new ArrayList<>();

		for (String line : input.split("\r\n")) {
			List<Integer> lineList = Arrays.asList(line.split(": ")).stream().map(Integer::valueOf)
					.collect(Collectors.toList());

			if (flList.size() < lineList.get(0)) {
				for (int i = flList.size(); i < lineList.get(0); i++) {
					flList.add(null);
				}
			}

			flList.add(lineList.get(0), new FirewallLayer(0, lineList.get(1)));
		}

		return flList;
	}

	public static class PacketScannersTests {

		@Test
		public void testCrossFirewall() {

			PacketScanners ps = new PacketScanners();

			List<FirewallLayer> firewall = Arrays.asList(new FirewallLayer[] { new FirewallLayer(0, 3),
					new FirewallLayer(0, 2), null, null, new FirewallLayer(0, 4), null, new FirewallLayer(0, 4) });
			List<Integer> expected = Arrays.asList(new Integer[] { 0, 6 });

			List<Integer> caughtPositions = ps.crossFirewall(firewall);

			assertEquals(Arrays.toString(expected.toArray()), Arrays.toString(caughtPositions.toArray()));
		}

		@Test
		public void testBuildFirewall() {

			PacketScanners ps = new PacketScanners();
			ps.setInput("0: 3\r\n" + "1: 2\r\n" + "4: 4\r\n" + "6: 4");

			List<FirewallLayer> firewall = ps.buildFirewall();

			assertEquals(3, firewall.get(0).getDepth().intValue());
			assertEquals(4, firewall.get(4).getDepth().intValue());
			assertNull(firewall.get(2));
		}

		@Test
		public void testPartOne() {
			PacketScanners ps = new PacketScanners();
			ps.setInput("0: 3\r\n" + "1: 2\r\n" + "4: 4\r\n" + "6: 4");

			assertEquals(24, ps.partOne().intValue());
		}

		@Test
		public void testPartTwo() {
			PacketScanners ps = new PacketScanners();
			ps.setInput("0: 3\r\n" + "1: 2\r\n" + "4: 4\r\n" + "6: 4");

			assertEquals(10, ps.partTwo());
		}
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
}
