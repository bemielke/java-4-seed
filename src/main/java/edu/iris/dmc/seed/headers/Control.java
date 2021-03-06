package edu.iris.dmc.seed.headers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.iris.dmc.seed.Blockette;
import edu.iris.dmc.seed.control.station.B050;
import edu.iris.dmc.seed.control.station.B052;
import edu.iris.dmc.seed.control.station.SeedResponseStage;

public class Control {

	private Map<Integer, B050> map = new TreeMap<>();

	public B050 put(B050 b050) {
		return this.map.put(b050.getId(), b050);
	}

	public Blockette get(int id) {
		return this.map.get(id);
	}

	public List<B050> getB050s() {
		return new ArrayList<>(this.map.values());
	}

	public List<Blockette> getAll() {
		List<Blockette> list = new ArrayList<>();
		for (B050 b050 : this.map.values()) {
			list.add(b050);
			list.addAll(b050.getB051s());
			for (B052 b052 : b050.getB052s()) {
				list.add(b052);
				list.addAll(b052.getB059s());
				for (SeedResponseStage stage : b052.getResponseStages()) {
					list.addAll(stage.getBlockettes());
				}
			}
		}
		return list;
	}

	public int size() {
		return getAll().size();
	}

	public boolean isEmpty() {
		return this.map.isEmpty();
	}
}
