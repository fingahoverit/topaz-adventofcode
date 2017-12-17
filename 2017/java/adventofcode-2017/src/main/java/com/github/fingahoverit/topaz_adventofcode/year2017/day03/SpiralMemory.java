package com.github.fingahoverit.topaz_adventofcode.year2017.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * {@link} http://adventofcode.com/2017/day/3
 */
public class SpiralMemory {

	private String input = "368078";

	public static void main(String[] args) {
		SpiralMemory instance = new SpiralMemory();
		System.out.println("Part One = " + instance.partOne());
		System.out.println("Part Two = " + instance.partTwo());
	}

	public static class PositionMetadata {
		private Integer level = 0;
		private Integer maxPositionLevel = 0;
		private Integer sideSize = 0;
		private Integer sideIndex = 0;
		private Integer sideCornerPosition = 0;

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[level:").append(level).append("]");
			sb.append("[maxPositionLevel:").append(maxPositionLevel).append("]");
			sb.append("[sideSize:").append(sideSize).append("]");
			sb.append("[sideIndex:").append(sideIndex).append("]");
			sb.append("[sideCornerPosition:").append(sideCornerPosition).append("]");
			return sb.toString();
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public Integer getMaxPositionLevel() {
			return maxPositionLevel;
		}

		public void setMaxPositionLevel(Integer maxPositionLevel) {
			this.maxPositionLevel = maxPositionLevel;
		}

		public Integer getSideSize() {
			return sideSize;
		}

		public void setSideSize(Integer sideSize) {
			this.sideSize = sideSize;
		}

		public Integer getSideIndex() {
			return sideIndex;
		}

		public void setSideIndex(Integer sideIndex) {
			this.sideIndex = sideIndex;
		}

		public Integer getSideCornerPosition() {
			return sideCornerPosition;
		}

		public void setSideCornerPosition(Integer sideCornerPosition) {
			this.sideCornerPosition = sideCornerPosition;
		}
	}

	private Integer partOne() {

		return calculatePathSize(Integer.valueOf(input));
	}

	private Integer partTwo() {

		return returnNextLargerValue(Integer.valueOf(input));
	}

	private Integer calculatePathSize(Integer square) {
		// System.out.println("<<" + square + ">>");

		Integer squareLevel = computeLevel(square);

		Integer minPath = squareLevel;
		Integer maxPath = minPath * 2;

		Integer cote = maxPath + 1;
		Integer maxNb = (int) Math.pow(cote, 2.0);

		// System.out.println("#" + squareLevel);
		// System.out.println("==min:" + minPath);
		// System.out.println("==max:" + maxPath);
		// System.out.println("==maxNb:" + maxNb);

		if (minPath != 0) {
			if (((maxNb - square) / minPath) % 2 != 0) {
				return (minPath + ((maxNb - square) % minPath));
			} else {
				return (maxPath - ((maxNb - square) % minPath));
			}
		} else {
			return 0;
		}
	}

	private Integer returnNextLargerValue(Integer valueTarget) {

		ArrayList<Integer> sequence = new ArrayList<>();

		Integer currentPosition = 1;
		Integer currentValue = 1;

		PositionMetadata metadata = new PositionMetadata();

		while (currentValue <= valueTarget) {

			if (currentPosition > metadata.getMaxPositionLevel()) {
				metadata.setLevel(computeLevel(currentPosition));
				metadata.setMaxPositionLevel(computeMaxPositionLevel(metadata.getLevel()));
				metadata.setSideSize(computeSideSize(metadata.getLevel()));
			}
			metadata.setSideIndex(computeSideIndex(currentPosition, metadata.getLevel(), metadata.getSideSize()));
			metadata.setSideCornerPosition(
					computeSideCornerPosition(metadata.getLevel(), metadata.getSideIndex(), metadata.getSideSize()));

			List<Integer> cellPositions = findCloseCells(currentPosition, metadata);

			currentValue = computeValue(sequence, cellPositions);

			sequence.add(currentValue);
			currentPosition++;
		}

		// System.out.println(Arrays.asList(sequence.toArray()).toString());

		return sequence.get(sequence.size() - 1);
	}

	private Integer computeLevel(Integer sq) {

		Double squareRoot = Math.sqrt(sq);

		if ((squareRoot - squareRoot.intValue()) == 0) {
			return (int) (squareRoot.intValue() - Math.ceil(squareRoot / 2.0));
		} else {
			return (int) (squareRoot.intValue() - Math.floor(squareRoot / 2.0));
		}
	}

	private Integer computeMaxPositionLevel(Integer level) {

		return (int) Math.pow((level * 2) + 1, 2.0);
	}

	private Integer computeSideSize(Integer level) {

		return (level * 2);
	}

	private Integer computeSideIndex(Integer currentPosition, Integer level, Integer sideSize) {

		Integer sideIndex = 1;
		if (currentPosition != 1) {
			while (computeMaxPositionLevel(level - 1) + (sideSize * sideIndex) < currentPosition) {
				sideIndex++;
			}
		}

		return sideIndex;
	}

	private Integer computeSideCornerPosition(Integer level, Integer sideIndex, Integer sideSize) {

		return computeMaxPositionLevel(level - 1) + (sideIndex * sideSize);
	}

	private List<Integer> findCloseCells(Integer currentPosition, PositionMetadata metadata) {

		Set<Integer> cellPositions = new HashSet<>();
		// get value of previous

		if (currentPosition != 1) {

			Integer backLevel = metadata.getLevel() - 1;
			Integer backMaxPosition = computeMaxPositionLevel(backLevel);
			Integer backSideSize = computeSideSize(backLevel);
			Integer backSideCornerPosition = computeSideCornerPosition(backLevel, metadata.getSideIndex(),
					backSideSize);

			Integer cornerDown = (metadata.getSideCornerPosition() - metadata.getSideSize());
			Integer backCornerDown = (backSideCornerPosition - backSideSize);
			Integer backGap = currentPosition - cornerDown - 1;

			// Last position
			cellPositions.add(currentPosition - 1);

			// corners & close to corners
			if (currentPosition.equals(metadata.getSideCornerPosition()) || currentPosition.equals(cornerDown + 1)) {

				// not next to max position
				if (!currentPosition.equals(backMaxPosition + 1)) {

					// is a corner
					if (currentPosition.equals(metadata.getSideCornerPosition())) {
						cellPositions.add(backSideCornerPosition);
						// is max position
						if (currentPosition.equals(metadata.getMaxPositionLevel())) {
							cellPositions.add(currentPosition + 1 - (4 * metadata.getSideSize()));
						}
					} else if (currentPosition.equals(cornerDown + 1)) {
						// is after corner
						cellPositions.add(backSideCornerPosition - backSideSize);

						cellPositions.add(currentPosition - 2);
						if (backSideSize > 1 || metadata.getSideIndex().equals(4)) {
							cellPositions.add(backSideCornerPosition - backSideSize + 1);
						}
					}
				} else {
					// next to max position
					cellPositions.add(computeMaxPositionLevel(backLevel - 1) + 1);
				}

			} else {
				// not corners, neither next to corner

				// Second cell just after New level
				if (currentPosition.equals(backMaxPosition + 2)) {
					cellPositions.add(currentPosition - 2);
				} else {
					cellPositions.add(backCornerDown + backGap - 1);
				}

				// standard cells between corners
				cellPositions.add(backCornerDown + backGap);

				// Cells just before new level
				if (!currentPosition.equals(metadata.getSideCornerPosition() - 1)
						|| currentPosition.equals(metadata.getMaxPositionLevel() - 1)) {
					cellPositions.add(backCornerDown + backGap + 1);
				}
			}

			// System.out.println("[" + (currentPosition - 1) + "] [" +
			// (backSideCornerPosition) + "] [" + (cornerDown)
			// + ">" + (backCornerDown) + ">" + (backCornerDown + backGap) + "] [");
			//
			// System.out
			// .println("position:" + currentPosition + ", position:" +
			// Arrays.toString(cellPositions.toArray()));

		} else {
			cellPositions.add(0);
		}

		return cellPositions.stream().collect(Collectors.toList());
	}

	private Integer computeValue(List<Integer> sequence, List<Integer> closeCellPositions) {

		if (sequence.size() > 1) {
			return closeCellPositions.stream().mapToInt(item -> sequence.get(item - 1)).sum();
		} else {
			return 1;
		}
	}

	public static class SpiralMemoryTests {

		@Test
		public void testComputeSideIndex_01() {
			SpiralMemory instance = new SpiralMemory();
			Integer position = 1;
			PositionMetadata metadata = new PositionMetadata();
			metadata.setLevel(instance.computeLevel(position));
			metadata.setMaxPositionLevel(instance.computeMaxPositionLevel(metadata.getLevel()));
			metadata.setSideSize(instance.computeSideSize(metadata.getLevel()));

			assertEquals(1,
					instance.computeSideIndex(position, metadata.getLevel(), metadata.getSideSize()).intValue());
		}

		@Test
		public void testComputeSideIndex_02() {
			SpiralMemory instance = new SpiralMemory();
			Integer position = 68;
			PositionMetadata metadata = new PositionMetadata();
			metadata.setLevel(instance.computeLevel(position));
			metadata.setMaxPositionLevel(instance.computeMaxPositionLevel(metadata.getLevel()));
			metadata.setSideSize(instance.computeSideSize(metadata.getLevel()));

			assertEquals(3,
					instance.computeSideIndex(position, metadata.getLevel(), metadata.getSideSize()).intValue());
		}

		@Test
		public void testComputeLevel_01() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 1;
			assertEquals(0, instance.computeLevel(square).intValue());
		}

		@Test
		public void testComputeLevel_02() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 94;
			assertEquals(5, instance.computeLevel(square).intValue());
		}

		@Test
		public void testComputeMaxPositionLevel_01() {
			SpiralMemory instance = new SpiralMemory();
			Integer level = 2;
			assertEquals(25, instance.computeMaxPositionLevel(level).intValue());
		}

		@Test
		public void testComputeMaxPositionLevel_02() {
			SpiralMemory instance = new SpiralMemory();
			Integer level = 6;
			assertEquals(169, instance.computeMaxPositionLevel(level).intValue());
		}

		@Test
		public void testComputeSideSize_01() {
			SpiralMemory instance = new SpiralMemory();
			Integer level = 3;
			assertEquals(6, instance.computeSideSize(level).intValue());
		}

		@Test
		public void testComputeSideSize_02() {
			SpiralMemory instance = new SpiralMemory();
			Integer level = 6;
			assertEquals(12, instance.computeSideSize(level).intValue());
		}

		@Test
		public void testCalculatePathSize_01() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 1;
			assertEquals(0, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_02() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 3;
			assertEquals(2, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_03() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 8;
			assertEquals(1, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_04() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 9;
			assertEquals(2, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_05() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 12;
			assertEquals(3, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_06() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 23;
			assertEquals(2, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_07() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 25;
			assertEquals(4, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_08() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 26;
			assertEquals(5, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testCalculatePathSize_09() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 99;
			assertEquals(8, instance.calculatePathSize(square).intValue());
		}

		@Test
		public void testReturnNextLargerValue_01() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 6;
			assertEquals(10, instance.returnNextLargerValue(square).intValue());
		}

		@Test
		public void testReturnNextLargerValue_02() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 10;
			assertEquals(11, instance.returnNextLargerValue(square).intValue());
		}

		@Test
		public void testReturnNextLargerValue_03() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 810;
			assertEquals(880, instance.returnNextLargerValue(square).intValue());
		}

		@Test
		public void testReturnNextLargerValue_04() {
			SpiralMemory instance = new SpiralMemory();
			Integer square = 42835;
			assertEquals(45220, instance.returnNextLargerValue(square).intValue());
		}
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}
